package main.HR;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import main.mainCtr.MainPageController;
import service.IEmployeeService;
import util.AlertUtil;
import vo.EmployeeVO;
import vo.Employee_joinVO;

public class imjikwoninsertController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField tf_empNo;

	@FXML
	private TextField tf_empPass;

	@FXML
	private TextField tf_empName;

	@FXML
	private TextField tf_email;


    @FXML
    private DatePicker dp_hire;
    
    @FXML
    private DatePicker dp_retire;
    
    @FXML
    private TextField tf_hire;

    @FXML
    private TextField tf_retire;

	@FXML
	private TextField tft_salary;

	@FXML
	private TextField tf_depTel;

//	@FXML
//	private ComboBox<String> combodepartment;
//
//	@FXML
//	private ComboBox<String> combograde;

	@FXML
	private TextField tf_reg;

	@FXML
	private TextField tf_addr;

	@FXML
	private Button btnOk;

	@FXML
	private Button btnreset;

	@FXML
	private TextField tf_phone;

	@FXML
	private Button btnback;

	private MainPageController main;

	public MainPageController getMain() {
		return main;
	}

	public void setMain(MainPageController main) {
		this.main = main;
	}

	private Employee_joinVO empjoinVo;

	public void setEmpVO(Employee_joinVO empjoinVo) {
		this.empjoinVo = empjoinVo;

		tf_empNo.setText(empjoinVo.getEmp_no()+"");
		tf_empPass.setText(empjoinVo.getEmp_pass());
		tf_empName.setText(empjoinVo.getEmp_name());
		tf_email.setText(empjoinVo.getEmp_email());
		tf_hire.setText(empjoinVo.getEmp_hire_date());
		tf_retire.setText(empjoinVo.getEmp_retire_date());
		tft_salary.setText(empjoinVo.getEmp_salary() + "");
		tf_depTel.setText(empjoinVo.getDepartment_tel());
		tf_phone.setText(empjoinVo.getEmp_phone());
		tf_reg.setText(empjoinVo.getEmp_reg());
		tf_addr.setText(empjoinVo.getEmp_addr());
	}

	public Employee_joinVO getEmpVO(Employee_joinVO empVo) {
		return empVo;
	}

	@FXML
	void btnOk(ActionEvent event) { /// 수정버튼클릭
		try {
			if(!"^[0-9]|$".matches(tft_salary.getText())) {
				AlertUtil.warnMsg("입력값 오류", "급여는 숫자로만 입력해주세요");
				return;
			}
			
			empVo = new EmployeeVO();
			empVo.setEmp_no(empjoinVo.getEmp_no());
			empVo.setEmp_pass(tf_empPass.getText());
			empVo.setEmp_name(tf_empName.getText());
			empVo.setEmp_email(tf_email.getText());
			empVo.setEmp_salary(Integer.parseInt(tft_salary.getText()));
			empVo.setEmp_reg(tf_reg.getText());
			empVo.setEmp_addr(tf_addr.getText());
			
			if(dp_hire.getValue() == null) {
				empVo.setEmp_hire_date(empjoinVo.getEmp_hire_date());
			}else {
				empVo.setEmp_hire_date(dp_hire.getValue().format(DateTimeFormatter.BASIC_ISO_DATE));				
			}
			if(dp_retire.getValue() == null) {
				empVo.setEmp_retire_date(empjoinVo.getEmp_retire_date());
			}else {
				empVo.setEmp_retire_date(dp_retire.getValue().format(DateTimeFormatter.BASIC_ISO_DATE));				
			}
			service.gikwonenroll(empVo);
		
			/*
			 * if (combodepartment.getValue() == "인사총무팀") { vo.setDepartment_no(1); } else
			 * if (combodepartment.getValue() == "영업1팀") { vo.setDepartment_no(2); } else if
			 * (combodepartment.getValue() == "영업2팀") { vo.setDepartment_no(3); } else if
			 * (combodepartment.getValue() == "품질관리팀") { vo.setDepartment_no(4); } else {
			 * vo.setDepartment_no(5); }
			 */
			
			// 정보 보내고 나면 해당 객체 상세보기 페이지로 가기
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HR/Sawoninfo.fxml"));
			Parent root = loader.load();
			SawoninfoController Ctr = loader.getController();
			Ctr.setMain(main);
	
			main.getMainborder().setCenter(root);
			Ctr.setemplist1(this);
			Ctr.setempVO(empVo);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@FXML
	void btnCancle(ActionEvent event) throws IOException { // 취소버튼클릭
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HR/Sawoninfo.fxml"));
		Parent root = loader.load();
		SawoninfoController Ctr = loader.getController();
		Ctr.setMain(main);

		main.getMainborder().setCenter(root);
	}
	
	
	private IEmployeeService service;
	private EmployeeVO empVo;
	@FXML
	void initialize() {
//		combodepartment.getItems().addAll("인사총무팀", "영업1팀", "영업2팀", "품질관리팀", "개발팀");
//		combograde.getItems().addAll("사원", "대리", "과장", "부장", "대표이사");
		
		try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			service = (IEmployeeService) reg.lookup("empService");
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
		

	}
}
