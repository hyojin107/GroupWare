package main.HR;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.mainCtr.MainPageController;
import service.IPaymentService;
import util.AlertUtil;
import vo.EmployeeVO;
import vo.SangsinSearchVO;

public class SangsinSearchController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfName;

    @FXML
    private Button btnSearch;

    @FXML
    private TableView<SangsinSearchVO> table;

    @FXML
    private TableColumn<?, ?> colGroup;

    @FXML
    private TableColumn<?, ?> colGrade;

    @FXML
    private TableColumn<?, ?> colName;
    
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnQuit;

    @FXML
    void quit(ActionEvent event) {
    	Stage s = (Stage) btnDel.getScene().getWindow();
    	s.close();
    }

    private IPaymentService service;
    private int x;
    private VacationApplyController controller;
    SangsinSearchVO item; 
    
    public VacationApplyController getController() {
		return controller;
	}

	public void setController(VacationApplyController controller) {
		this.controller = controller;
	}

	@FXML
    void search(ActionEvent event) {
    	try {
			List<SangsinSearchVO> list = service.searchName(tfName.getText());
			ObservableList<SangsinSearchVO> oList = FXCollections.observableArrayList(list);
			table.setItems(oList);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    public Button getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(Button btnAdd) {
		this.btnAdd = btnAdd;
	}

	public Button getBtnDel() {
		return btnDel;
	}

	public void setBtnDel(Button btnDel) {
		this.btnDel = btnDel;
	}

	public Button getBtnQuit() {
		return btnQuit;
	}

	public void setBtnQuit(Button btnQuit) {
		this.btnQuit = btnQuit;
	}

	@FXML
    void select(MouseEvent event) {
		item = table.getSelectionModel().getSelectedItem();
    	System.out.println(item);
//    	if(table.getSelectionModel().isEmpty() || x == 4) {
//			return;
//		}
    	String emp_name = item.getEmp_name();
    	String grade_name = item.getGrade_name();
    	int emp_no = item.getEmp_no();
    	switch(x) {
    		case 1:
    			if(emp_no == loginUser.getEmp_no()) {
    				AlertUtil.errorMsg("오류", "결재 라인에 본인은 등록할 수 없습니다.");
    				return;
    			}
    			if(controller.getLineList()[1] == emp_no || controller.getLineList()[2] == emp_no) {
    				AlertUtil.errorMsg("오류", "이미 등록된 결재자 입니다.");
    				return;
    			}
    			controller.getTfLine1().setText(emp_name);
    			controller.getBtnSearch1().setText(grade_name);
    			controller.getLineList()[0] = emp_no;
    			break;
    		case 2:
    			if(emp_no == loginUser.getEmp_no()) {
    				AlertUtil.errorMsg("오류", "결재 라인에 본인은 등록할 수 없습니다.");
    				return;
    			}
    			if(controller.getLineList()[0] == emp_no || controller.getLineList()[2] == emp_no) {
    				AlertUtil.errorMsg("오류", "이미 등록된 결재자 입니다.");
    				return;
    			}
    			controller.getTfLine2().setText(emp_name);
    			controller.getBtnSearch2().setText(grade_name);
    			controller.getLineList()[1] = item.getEmp_no();
    			break;
    		case 3:
    			if(emp_no == loginUser.getEmp_no()) {
    				AlertUtil.errorMsg("오류", "결재 라인에 본인은 등록할 수 없습니다.");
    				return;
    			}
    			if(controller.getLineList()[1] == emp_no || controller.getLineList()[0] == emp_no) {
    				AlertUtil.errorMsg("오류", "이미 등록된 결재자 입니다.");
    				return;
    			}
    			controller.getTfLine3().setText(emp_name);
    			controller.getBtnSearch3().setText(grade_name);
    			controller.getLineList()[2] = item.getEmp_no();
    			break;
    	}
    	Stage s = (Stage) table.getScene().getWindow();
    	s.close();
    	
    }

    public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	private EmployeeVO loginUser;
	@FXML
    void initialize() {
    	try {
    		Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
    		
    		service = (IPaymentService) reg.lookup("payService");
    		
    		colGroup.setCellValueFactory(new PropertyValueFactory<>("department_name"));
    		colGrade.setCellValueFactory(new PropertyValueFactory<>("grade_name"));
    		colName.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
    		
    		loginUser = MainPageController.getLoginUser();
    		
    	} catch (RemoteException | NotBoundException e) {
    		e.printStackTrace();
    	}
    }
}
