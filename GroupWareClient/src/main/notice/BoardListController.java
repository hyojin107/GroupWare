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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import main.mainCtr.MainPageController;
import service.IBoardService;
import service.IEmployeeService;
import util.AlertUtil;
import vo.BoardVO;
import vo.Boardemp_joinVO;
import vo.EmployeeVO;


public class BoardListController {  

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnSearch;

    @FXML
    private TextField tfSearch;

    @FXML
    private TableView<Boardemp_joinVO> tbboard;

    @FXML
    private TableColumn<?,?> colNo;

    @FXML
    private TableColumn<?,?>colTitle;

    @FXML
    private TableColumn<?,?> colName;

    @FXML
    private TableColumn<?,?>Coldate;

    @FXML
    private Button btnAddboard;

    @FXML
    private Pagination pageboard;
    
    //서비스객체
    private IBoardService service;
    //private List<Boardemp_joinVO> list = FXCollections.observableArrayList();
    private List<Boardemp_joinVO> list ;
    
    private Boardemp_joinVO item; 
    private Map<String, String>map;
    private MainPageController main;
    public MainPageController getMain() {
      return main;
   }
   public void setMain(MainPageController main) {
      this.main = main;
   }

    @FXML
    void btnAddClick(ActionEvent event) throws IOException {///새글쓰기버튼클릭
    	
//    	FXMLLoader loader=new FXMLLoader(BoardListController.class.getResource("../fxml/BoardAdd.fxml"));
//    	Parent root=loader.load();
//    	Stage stage=(Stage)btnAddboard.getScene().getWindow();
//    	
//    	Scene scene =new Scene(root);
//    	stage.setScene(scene);
//    	stage.setTitle("자유게시판 등록");
//    	
//    	stage.show();

    	//FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/notice/BoardAdd.fxml"));
    	FXMLLoader loader = new FXMLLoader(BoardListController.class.getResource("/fxml/notice/BoardAdd.fxml"));
    	Parent root = loader.load();
        BoardaddController ctr = loader.getController();
        ctr.setMain(main);
        main.getMainborder().setCenter(root);
    }

    @FXML
    void btnSearchClick(ActionEvent event) { //검색버튼클릭
    	map=new HashMap<String, String>();
    	map.put("text", tfSearch.getText());
    	
    	try {
			list=service.searchBoard(map);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	paging();
    }
   
    @FXML
    void tbClick(MouseEvent event) {
    	if(tbboard.getSelectionModel().isEmpty()) {
    		return; 
    	} 
    	
    	try {
			item=tbboard.getSelectionModel().getSelectedItem(); //map으로 담아서 가져오기
			FXMLLoader loader = new FXMLLoader(BoardListController.class.getResource("/fxml/notice/BoardDetail.fxml"));
			Parent root = loader.load();
	        BoardDetailController ctr = loader.getController();
	        ctr.setBolist(this);
	        ctr.setBoardVO(item);
	        ctr.setMain(main);
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
    	int start = i*rowPerPage;
    	int end = Math.min(start+rowPerPage, totalCount);//둘중에 작은놈을 채택
    	
//    	Map<String, Integer> pageMap = new HashMap<String, Integer>();
//    	pageMap.put("start", start);
//    	pageMap.put("end",end);
    	
        tbboard.getItems().clear();
		for (int j = start; j < end; j++) {
			tbboard.getItems().add(list.get(j));

		}
		 
    	
    }
    
    void paging() {
    	totalCount = list.size();
		//총 페이지 개수 정해주기
		pageCount = (int) Math.ceil((double)totalCount / rowPerPage); 
		pageboard.setPageCount(pageCount); 
		pageboard.setCurrentPageIndex(0); //기본값 첫번째 페이지로 설정 
		changeTable(0);
    }
    
    @FXML
    void initialize() throws RemoteException {
    	try {
			Registry reg=LocateRegistry.getRegistry("192.168.0.117", 9999);
			service=(IBoardService)reg.lookup("boardService");
		} catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
    
    	//야메로 다 들고오는 방법 list = service.getAllBoard();
    	Map<String,Integer> pageMap = new HashMap<String, Integer>();
    	pageMap.put("start", 0);
		pageMap.put("end", 99999);
		list = service.boardjoinList(pageMap);
		paging();
		
		pageboard.currentPageIndexProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				changeTable(newValue.intValue());
			}
		});///page별 작업내용 보여주기 
		 
		//보여주는거 정하기
    	colNo.setCellValueFactory(new PropertyValueFactory<>("board_no"));
  	    colTitle.setCellValueFactory(new PropertyValueFactory<>("board_title"));
  	    colName.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
  	    Coldate.setCellValueFactory(new PropertyValueFactory<>("board_date"));
  	 
//  	    tbboard.getItems().addAll(colNo,colTitle,colName,Coldate);
//			
//  	  int loginDep= MainPageController.getLoginUser().getDepartment_no();
//		if(loginDep!=1) {
//			btnAddboard.setVisible(false);
//		}

  	    
    }
    
}
