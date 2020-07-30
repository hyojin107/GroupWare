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
import main.notice.NoticeDetailController;
import main.notice.NoticeListController;
import service.IEmployeeService;
import service.IVacationService;
import vo.EmployeeVO;
import vo.MyVacationVO;

public class VacationListViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_apply;

    @FXML
    private Button btn_check;

    @FXML
    private Button btn_allVac;

    @FXML
    private TableView<MyVacationVO> table;

    @FXML
    private TableColumn<?, ?> tc_no;

    @FXML
    private TableColumn<?, ?> tc_form;

    @FXML
    private TableColumn<?, ?> tc_date;

    @FXML
    private TableColumn<?, ?> tc_check;

    @FXML
    private Pagination pageNation;
    
    private MainPageController main;
    public MainPageController getMain() {
        return main;
    }
    public void setMain(MainPageController main) {
        this.main = main;
    }
    
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
			if(loginUser.getEmp_no() == vacList.get(i).getEmp_no()) {
				table.getItems().add( vacList.get(i) ); 
			}
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
    void banAllVac(ActionEvent event) throws IOException {	// 전직원 휴가현황 버튼
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HR/VacationAllListView.fxml"));
        Parent root = loader.load();
        VacationAllListViewController ctr = loader.getController();
        ctr.setMain(main);
        
        main.getMainborder().setCenter(root);
    	
    }

    @FXML
    void btnApply(ActionEvent event) throws IOException {	// 휴가 신청 버튼
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HR/VacationApply.fxml"));
        Parent root = loader.load();
        VacationApplyController ctr = loader.getController();
        ctr.setMain(main);
        
        main.getMainborder().setCenter(root);
    	
    }

    @FXML
    void btnCheck(ActionEvent event) throws IOException {	// 휴가 승인/반려 버튼
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HR/VacationCheck.fxml"));
        Parent root = loader.load();
        VacationCheckController ctr = loader.getController();
        ctr.setMain(main);
        
        main.getMainborder().setCenter(root);
    }

    private IEmployeeService empService;
    private IVacationService vacService;
    private EmployeeVO loginUser;
    @FXML
    void initialize() {
    	try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			empService = (IEmployeeService) reg.lookup("empService");
			vacService = (IVacationService) reg.lookup("vacService");
			
			loginUser = MainPageController.getLoginUser();
			
			tc_no.setCellValueFactory(new PropertyValueFactory<>("no"));
			tc_date.setCellValueFactory(new PropertyValueFactory<>("vac_date"));
			tc_form.setCellValueFactory(new PropertyValueFactory<>("vac_form"));
			tc_check.setCellValueFactory(new PropertyValueFactory<>("pay_state"));
			
			EmployeeVO loginUser = MainPageController.getLoginUser();
			if(loginUser.getDepartment_no() != 1) {
				btn_check.setVisible(false);
				btn_allVac.setVisible(false);
			}
			
			Map<String,Integer> pageMap = new HashMap<String, Integer>();
	    	pageMap.put("start", 0);
			pageMap.put("end", 1999999999);
			
			vacList = vacService.getAllVacation(pageMap);
			
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




