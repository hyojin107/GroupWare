package main.payment;

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
    void add(ActionEvent event) {
    	if(table.getSelectionModel().isEmpty()) return;
    	
    	if(item.getEmp_no() == MainPageController.getLoginUser().getEmp_no()) {
    		AlertUtil.errorMsg("오류", "자기자신을 참조에 등록할 수 없습니다.");
    		return;
    	}
    	String emp_name = item.getEmp_name();
    	if(controller.getReferenceList().contains((Integer)item.getEmp_no())) {
    		AlertUtil.errorMsg("중복 추가", emp_name + "님은 이미 추가 되어 있습니다.");
    		return;
    	}
    	for(int a : controller.getLineList()) {
    		if(a == item.getEmp_no()) {
    			AlertUtil.errorMsg("결재자 확인", "이미 결재자로 추가된 사람입니다.");
    			return;
    		}
    	}
    	delRef(item);
    	controller.getReferenceList().add(item.getEmp_no());
    	controller.getRefNameList().add(emp_name);
    		
    	showRef();
    	AlertUtil.infoMsg("추가 성공", emp_name + "님을 참조에 추가했습니다.");
    }

    @FXML
    void del(ActionEvent event) {
    	if(table.getSelectionModel().isEmpty()) return;
    	delRef(item);
    }
    
    void delRef(SangsinSearchVO item) {
    	if(controller.getReferenceList().remove((Integer)item.getEmp_no())) {
    		AlertUtil.infoMsg("삭제", item.getEmp_name() + "님을 참조에서 제거했습니다.");
    		controller.getRefNameList().remove(item.getEmp_name());
    		showRef();
    	}
    }
    
    void showRef() {
    	controller.getTfRef().clear();
    	for(String s : controller.getRefNameList()) {
    		if(!controller.getTfRef().getText().equals("")) controller.getTfRef().appendText(", ");
    		controller.getTfRef().appendText(s);
    	}
    }

    @FXML
    void quit(ActionEvent event) {
    	Stage s = (Stage) btnDel.getScene().getWindow();
    	s.close();
    }

    private IPaymentService service;
    private int x;
    private SangsinController controller;
    SangsinSearchVO item; 
    
	public SangsinController getController() {
		return controller;
	}

	public void setController(SangsinController controller) {
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
    	if(table.getSelectionModel().isEmpty() || x == 4) {
			return;
		}
    	String emp_name = item.getEmp_name();
    	String grade_name = item.getGrade_name();
    	int emp_no = item.getEmp_no();
    	if(emp_no == MainPageController.getLoginUser().getEmp_no()) {
    		AlertUtil.errorMsg("오류", "자기자신을 결재자로 등록할 수 없습니다.");
    		return;
    	}
    	switch(x) {
    		case 1:
    			if(controller.getLineList()[1] == emp_no || controller.getLineList()[2] == emp_no) {
    				AlertUtil.errorMsg("오류", "이미 등록된 결재자 입니다.");
    				return;
    			}
    			controller.getTfLine1().setText(emp_name);
    			controller.getBtnSearch1().setText(grade_name);
    			controller.getLineList()[0] = emp_no;
    			break;
    		case 2:
    			if(controller.getLineList()[0] == emp_no || controller.getLineList()[2] == emp_no) {
    				AlertUtil.errorMsg("오류", "이미 등록된 결재자 입니다.");
    				return;
    			}
    			controller.getTfLine2().setText(emp_name);
    			controller.getBtnSearch2().setText(grade_name);
    			controller.getLineList()[1] = item.getEmp_no();
    			break;
    		case 3:
    			if(controller.getLineList()[1] == emp_no || controller.getLineList()[0] == emp_no) {
    				AlertUtil.errorMsg("오류", "이미 등록된 결재자 입니다.");
    				return;
    			}
    			controller.getTfLine3().setText(emp_name);
    			controller.getBtnSearch3().setText(grade_name);
    			controller.getLineList()[2] = item.getEmp_no();
    			break;
    	}
    	delRef(item);
    	Stage s = (Stage) table.getScene().getWindow();
    	s.close();
    }

    public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@FXML
    void initialize() {
    	try {
    		Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);    		
    		service = (IPaymentService) reg.lookup("payService");
    		
    		colGroup.setCellValueFactory(new PropertyValueFactory<>("department_name"));
    		colGrade.setCellValueFactory(new PropertyValueFactory<>("grade_name"));
    		colName.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
    		
    		
    	} catch (RemoteException | NotBoundException e) {
    		e.printStackTrace();
    	}
    }
}
