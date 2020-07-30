package main.HR;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.mainCtr.MainPageController;
import main.notice.NoticeListController;
import service.IVacationService;
import vo.MyVacationVO;

public class VacationAllListViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TableView<MyVacationVO> table;
    
    @FXML
    private TableColumn<?, ?> tc_no;

    @FXML
    private TableColumn<?, ?> tc_name;

    @FXML
    private TableColumn<?, ?> tc_date;
    
    @FXML
    private TableColumn<?, ?> tc_form;

    @FXML
    private TableColumn<?, ?> tc_cnt;

    @FXML
    private TableColumn<?, ?> tc_check;
    
    @FXML
    private Pagination pageNation;
    
    @FXML
    private Button btn_back;

    private MainPageController main;
    public MainPageController getMain() {
        return main;
    }
    public void setMain(MainPageController main) {
        this.main = main;
    }

    
    IVacationService service;
    List<MyVacationVO> vacList;
    private int rowPerPage = 10; //한페이지당 보여줄 게시글 개수	
	private int totalCount;	//총 게시글수 		
	private int pageCount; // 페이지 개수

	//DB의 전체 데이터를 가져와서 tableView에 출력하는 메서드 
	private void changeTable(int num) {
		int start = num * rowPerPage;
		int end = Math.min(start+rowPerPage, totalCount); //둘중에 작은놈을 채택
		
		Map<String,Integer> pageMap = new HashMap<String, Integer>();
		pageMap.put("start", start);
		pageMap.put("end", end);
		
		table.getItems().clear();
		for(int i = start; i < end; i++) {
			vacList.get(i).setNo(i+1);
			table.getItems().add( vacList.get(i) ); 
		}
		 
	}
	
	void paging() {
		totalCount = vacList.size();
		//총 페이지 개수 정해주기
		pageCount = (int) Math.ceil((double)totalCount / rowPerPage); 
		pageNation.setPageCount(pageCount);
		pageNation.setCurrentPageIndex(0); //기본값 첫번째 페이지로 설정 
		changeTable(0);
	}
	
	@FXML
    void btnBack(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HR/VacationListView.fxml"));
        Parent root = loader.load();
        VacationListViewController ctr = loader.getController();
        ctr.setMain(main);
        
        main.getMainborder().setCenter(root);
		
    }
    
    @FXML
    void initialize() {

    	try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			service = (IVacationService) reg.lookup("vacService");
			
			tc_no.setCellValueFactory(new PropertyValueFactory<>("no"));
			tc_name.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
			tc_date.setCellValueFactory(new PropertyValueFactory<>("vac_date"));
			tc_form.setCellValueFactory(new PropertyValueFactory<>("vac_form"));
			tc_cnt.setCellValueFactory(new PropertyValueFactory<>("vac_cnt"));
			tc_check.setCellValueFactory(new PropertyValueFactory<>("pay_state"));
			
			Map<String,Integer> pageMap = new HashMap<String, Integer>();
	    	pageMap.put("start", 0);
			pageMap.put("end", 1999999999);
			
			vacList = service.getAllVacation(pageMap);
			
			paging();
			
			pageNation.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					changeTable(newValue.intValue());
				}
			});
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    }
}

    
 
  