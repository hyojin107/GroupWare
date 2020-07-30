package main.notice;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.email.GetEmailHamController;
import main.mainCtr.MainPageController;
import service.INoticeService;
import vo.NoticeVO_join;

public class NoticeListController { 
 
	//서비스객체 
    private INoticeService service;
    private List<NoticeVO_join> list;
    
    //VO도 일종의 타입
    private NoticeVO_join item; 
    private Map<String, String> map;
    
    @FXML
    private ResourceBundle resources;
   
    @FXML
    private VBox noticeAddpage;
    
    
    @FXML
    private URL location;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField tfSearch;
    
    @FXML
    private TableView<NoticeVO_join> tbNotice;

    @FXML
    private TableColumn<?,?> colNo;

    @FXML
    private TableColumn<?,?> colTitle;

    @FXML
    private TableColumn<?,?> colName;

    @FXML
    private TableColumn<?,?> Coldate;

    @FXML
    private Button btnAddNotice;

    @FXML
    private Pagination pageNotice;
    
    private MainPageController main;
    public MainPageController getMain() {
		return main;
	}
	public void setMain(MainPageController main) {
		this.main = main;
	}

    @FXML
    void btnAddClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(NoticeListController.class.getResource("/fxml/notice/NoticeAdd.fxml"));
        Parent root = loader.load();
		NoticeAddController Ctr = loader.getController();
		Ctr.setMain(main);
		main.getMainborder().setCenter(root);

    	
//           FXMLLoader loader = new FXMLLoader(NoticeListController.class.getResource("/fxml/notice/NoticeAdd.fxml"));
//           Parent root = loader.load();
//           Stage stage = (Stage) btnAddNotice.getScene().getWindow();
//           
//           Scene scene = new Scene (root);
//           stage.setScene(scene);
//           stage.setTitle("공지등록"); 
//           stage.show();
    }
    
    @FXML
    void btnSearchClick(ActionEvent event) {
    	map = new HashMap<String, String>();
    	map.put("text", tfSearch.getText());
    	try {
			list = service.searchNotice(map);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

    	paging(); 
    }


    
    @FXML
    void tbClick(MouseEvent event) {
    	if(tbNotice.getSelectionModel().isEmpty()) {
    		return;
    	}
    	try {
    		item = tbNotice.getSelectionModel().getSelectedItem();
    		FXMLLoader loader = new FXMLLoader(NoticeListController.class.getResource("/fxml/notice/NoticeDetail.fxml"));
			Parent root = loader.load();
			NoticeDetailController Ctr = loader.getController();
			Ctr.setNoti(this);
			Ctr.setNoticeVO(item);
    		Ctr.setMain(main);
    		main.getMainborder().setCenter(root);

    	} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }




	
	
	private int rowPerPage = 10; //한페이지당 보여줄 게시글 개수	
	private int totalCount;	//총 게시글수 		
	private int pageCount; // 페이지 개수

	//DB의 전체 데이터를 가져와서 tableView에 출력하는 메서드 
	private void changeTable(int i) {
		int start = i * rowPerPage;
		int end = Math.min(start+rowPerPage, totalCount); //둘중에 작은놈을 채택
		
//		Map<String,Integer> pageMap = new HashMap<String, Integer>();
//		pageMap.put("start", start);
//		pageMap.put("end", end);
		
		tbNotice.getItems().clear();
		for(int j = start; j < end; j++) { tbNotice.getItems().add(list.get(j)); }
		 
	}
	
	void paging() {
		totalCount = list.size();
		//총 페이지 개수 정해주기
		pageCount = (int) Math.ceil((double)totalCount / rowPerPage); 
		pageNotice.setPageCount(pageCount);
		pageNotice.setCurrentPageIndex(0); //기본값 첫번째 페이지로 설정 
		changeTable(0);
	}
	
	@FXML
    void initialize() throws RemoteException {
    	
    	//(service = BoardServiceImpl.getInstance();)
    	try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			service = (INoticeService) reg.lookup("notiService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	//야메로 다 들고오는 방법 list = service.getAllBoard();
    	Map<String,Integer> pageMap = new HashMap<String, Integer>();
    	pageMap.put("start", 0);
		pageMap.put("end", 1999999999);
		list = service.getAllNotiEmp(pageMap);
		
		paging();
		
		//몰라이거뭐하는거더라
		pageNotice.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				changeTable(newValue.intValue());
			}
			
		});

		colNo.setCellValueFactory(new PropertyValueFactory<>("noti_no"));
		colTitle.setCellValueFactory(new PropertyValueFactory<>("noti_title"));
		colName.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
		Coldate.setCellValueFactory(new PropertyValueFactory<>("noti_date"));
		
		int loginDep= MainPageController.getLoginUser().getDepartment_no();
		if(loginDep!=1) {
			btnAddNotice.setVisible(false);
		}

    }
}
