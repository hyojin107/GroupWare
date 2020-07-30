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
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.mainCtr.MainPageController;
import service.IEmployeeService;
import util.AlertUtil;
import vo.Employee_joinVO;


public class imjikwonmanagementController {
	
	private imjikwoninsertController emplist0;
	
	
  
	public imjikwoninsertController getemplist0() {
		return emplist0;
	}

	public void setemplist0(imjikwoninsertController emplist0) {
		this.emplist0 = emplist0;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combosearch; ///검색분류콤보버튼
    
    @FXML
    private TableView<Employee_joinVO> emptableview;

   

    @FXML
    private TableColumn<?, ?> empno; ///사원번호

    @FXML
    private TableColumn<?, ?> empname; //사원이름

    @FXML
    private TableColumn<?, ?> empdepartment;//부서명

    @FXML
    private TableColumn<?, ?> grade;//직급
    
    @FXML
    private Pagination pagination;

    @FXML
    private TextField tfnamesearch;//이름검색필드

    @FXML
    private Button btnsearch; ///검색버튼
    
	private MainPageController main;
	public MainPageController getMain() {
		return main;
	}
	public void setMain(MainPageController main) {
		this.main = main;
	}
    
    @FXML
    void emptableviewclick(MouseEvent event) {
    	//사원정보(Sawoninfo)페이지 이동
    	Employee_joinVO mpVO = new Employee_joinVO();
//    	mpVO.setEmp_no(emptableview.getSelectionModel().getSelectedItem().getEmp_no());
//    	mpVO.setEmp_phone(emptableview.getSelectionModel().getSelectedItem().getEmp_phone());
//    	mpVO.setDepartment_tel(emptableview.getSelectionModel().getSelectedItem().getDepartment_tel());
//    	mpVO.setEmp_email(emptableview.getSelectionModel().getSelectedItem().getEmp_email());
//    	mpVO.setDepartment_no(emptableview.getSelectionModel().getSelectedItem().getDepartment_no());
//    	mpVO.setGrade_no(emptableview.getSelectionModel().getSelectedItem().getGrade_no());
		if (emptableview.getSelectionModel().isEmpty()) {
			return;
		}
		try {
			// Stage stage=(Stage)emptableview.getScene().getWindow();
			item = emptableview.getSelectionModel().getSelectedItem();

			// item.getEmp_phone();
			System.out.println(item.getEmp_phone());
			System.out.println(item.getEmp_email());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HR/Sawoninfo.fxml"));
			Parent root = loader.load();
			SawoninfoController Ctr = loader.getController();
			Ctr.setemplist0(this);
			Ctr.setmpVO(item);
			Ctr.setMain(main);
			main.getMainborder().setCenter(root);

		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    	
    }

    @FXML
    void btnsearchclick(ActionEvent event) throws RemoteException { ///이름검색버튼클릭
    	if(tfnamesearch.getText().equals("")) initialize();
    	else {
    		map = new HashMap<String, String>();
    		map.put("field", combosearch.getSelectionModel().getSelectedItem());
    		map.put("text", tfnamesearch.getText());
    		list = service.searchEmp(map);
    		paging();
    	}

    }
    
    private IEmployeeService service;
    private List<Employee_joinVO> list;
    private Map<String, String> map;
    private Employee_joinVO item; 
	private int rowPerPage = 15; //한페이지당 보여줄 게시글 개수	
	private int totalCount;	//총 게시글수 		
	private int pageCount; // 페이지 개수
	private Object oldValue;  //Select 된 이전 값을 저장하기 위한 변수
    
    
    private void changeTable(int i) {
    	int start = i * rowPerPage;
		int end = Math.min(start+rowPerPage, totalCount); //둘중에 작은놈을 채택
		
//		Map<String,Integer> pageMap = new HashMap<String, Integer>();
//		pageMap.put("start", start);
//		pageMap.put("end", end);
		
		emptableview.getItems().clear();
		for(int j = start; j < end; j++) { 
			emptableview.getItems().add(list.get(j)); 
			}	
    }
    void paging() {
    	totalCount = list.size();
		//총 페이지 개수 정해주기
		pageCount = (int) Math.ceil((double)totalCount / rowPerPage); 
		
		pagination.setPageCount(pageCount);
		
		pagination.setCurrentPageIndex(0); //기본값 첫번째 페이지로 설정 
		changeTable(0);
    }
    
    

    @FXML
    void initialize() throws RemoteException {
    	
    	combosearch.setItems(FXCollections.observableArrayList("emp_no","emp_name","department_name","grade_name"));
    	
    	combosearch.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new ListCell<String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if(item==null || empty) {
							setText(null);
						}else if(item.equals("emp_no")){
							setText("사원번호");
						}else if(item.equals("emp_name")) {
							setText("성명");
						}else if(item.equals("department_name")) {
							setText("부서");
						}else {
							setText("직급");
						}
					}
				};
			}
		});
    	
    	combosearch.setButtonCell(new ListCell<String>() {
    		@Override
    		protected void updateItem(String item, boolean empty) {
    			super.updateItem(item, empty);
    			if(item==null || empty) {
					setText(null);
    			}else if(item.equals("emp_no")){
					setText("사원번호");
				}else if(item.equals("emp_name")) {
					setText("성명");
				}else if(item.equals("department_name")) {
					setText("부서");
				}else {
					setText("직급");
				}
				
    		}
    	});
    	
    	try {
			Registry reg=LocateRegistry.getRegistry("192.168.0.117", 9999);
			service=(IEmployeeService)reg.lookup("empService");
		} catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	
    	Map<String,Integer> pageMap = new HashMap<String, Integer>();
    	pageMap.put("start", 0);
		pageMap.put("end", 999999999);
		
		list = service.kido(pageMap);
		
		System.out.println(list.get(3).getEmp_phone());
		paging();
		
		pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				changeTable(newValue.intValue());
			}
		});
    	
		 empno.setCellValueFactory(new PropertyValueFactory<>("emp_no"));
		 empname.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
		 empdepartment.setCellValueFactory(new PropertyValueFactory<>("department_name")); 
		 grade.setCellValueFactory(new PropertyValueFactory<>("grade_name"));
		 
    }
}
