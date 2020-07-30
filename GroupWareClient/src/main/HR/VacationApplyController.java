package main.HR;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.email.GetEmailListController;
import main.mainCtr.MainPageController;
import main.notice.NoticeListController;
import service.IAddressService;
import service.IEmployeeService;
import service.IPaymentService;
import service.IVacationService;
import util.AlertUtil;
import vo.DepartmentsVO;
import vo.EmployeeVO;
import vo.PaymentVO;
import vo.Payment_LineVO;
import vo.VacationVO;

public class VacationApplyController {
	
	public Button getBtnSearch1() {
		return btnSearch1;
	}

	public void setBtnSearch1(Button btnSearch1) {
		this.btnSearch1 = btnSearch1;
	}

	public Button getBtnSearch2() {
		return btnSearch2;
	}

	public void setBtnSearch2(Button btnSearch2) {
		this.btnSearch2 = btnSearch2;
	}

	public Button getBtnSearch3() {
		return btnSearch3;
	}

	public void setBtnSearch3(Button btnSearch3) {
		this.btnSearch3 = btnSearch3;
	}
	
	public TextField getTfTitle() {
		return tfTitle;
	}

	public void setTfTitle(TextField tfTitle) {
		this.tfTitle = tfTitle;
	}

	public TextArea getTaContent() {
		return taContent;
	}

	public void setTaContent(TextArea taContent) {
		this.taContent = taContent;
	}

	public TextField getTfGroup() {
		return tfGroup;
	}

	public void setTfGroup(TextField tfGroup) {
		this.tfGroup = tfGroup;
	}

	public TextField getTfEmp() {
		return tfEmp;
	}

	public void setTfEmp(TextField tfEmp) {
		this.tfEmp = tfEmp;
	}

	public TextField getTfLine1() {
		return tfLine1;
	}

	public void setTfLine1(TextField tfLine1) {
		this.tfLine1 = tfLine1;
	}

	public ImageView getImage1() {
		return image1;
	}

	public void setImage1(ImageView image1) {
		this.image1 = image1;
	}

	public TextField getTfLine2() {
		return tfLine2;
	}

	public void setTfLine2(TextField tfLine2) {
		this.tfLine2 = tfLine2;
	}

	public ImageView getImage2() {
		return image2;
	}

	public void setImage2(ImageView image2) {
		this.image2 = image2;
	}

	public TextField getTfLine3() {
		return tfLine3;
	}

	public void setTfLine3(TextField tfLine3) {
		this.tfLine3 = tfLine3;
	}

	public ImageView getImage3() {
		return image3;
	}

	public void setImage3(ImageView image3) {
		this.image3 = image3;
	}
	
	private Integer[] lineList = { 0, 0, 0 };
	public Integer[] getLineList() {
		return lineList;
	}

	public void setLineList(Integer[] lineList) {
		this.lineList = lineList;
	}
	
	private MainPageController main;
	public MainPageController getMain() {
	    return main;
	}
	public void setMain(MainPageController main) {
	    this.main = main;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private DatePicker dp_sDate;

    @FXML
    private DatePicker dp_eDate;

    @FXML
    private TextField tfTitle;

    @FXML
    private TextArea taContent;

    @FXML
    private TextField tfGroup;

    @FXML
    private TextField tfEmp;

    @FXML
    private TextField tfLine1;

    @FXML
    private Button btnSearch1;

    @FXML
    private ImageView image1;

    @FXML
    private Text txt1;

    @FXML
    private TextField tfLine2;

    @FXML
    private Button btnSearch2;

    @FXML
    private ImageView image2;

    @FXML
    private Text txt2;

    @FXML
    private TextField tfLine3;

    @FXML
    private Button btnSearch3;

    @FXML
    private ImageView image3;

    @FXML
    private Text txt3;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancle;

    
    private void showSearch(int x) {
		try {
			Stage parentStage = (Stage) tfEmp.getScene().getWindow();
			Stage searchStage = new Stage(StageStyle.DECORATED);
			searchStage.initModality(Modality.WINDOW_MODAL);
			searchStage.initOwner(parentStage);

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HR/SangsinSearch.fxml"));

			Parent root = loader.load();

			SangsinSearchController controller = loader.getController();

			controller.setController(this);
			controller.setX(x);

			Scene scene = new Scene(root);
			searchStage.setScene(scene);
			searchStage.setTitle("결재선 검색");
			searchStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    @FXML
    void btnSearch1(ActionEvent event) {	// 결재라인1
    	showSearch(1);
    }

    @FXML
    void btnSearch2(ActionEvent event) {	// 결재라인2
    	showSearch(2);
    }

    @FXML
    void btnSearch3(ActionEvent event) {	// 결재라인3
    	showSearch(3);
    }

    @FXML
    void cancle(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/HR/VacationListView.fxml"));
        Parent root = loader.load();
        VacationListViewController ctr = loader.getController();
        ctr.setMain(main);
        
        main.getMainborder().setCenter(root);
    }

    @FXML
    void comboClick(ActionEvent event) {
    	
    }

    @FXML
    void ok(ActionEvent event) {	// 신청버튼
    	try {
//	    	System.out.println("String 2020-04-22 : " + dp_sDate.getValue().toString());
//	    	System.out.println("String 20200422 : " + dp_sDate.getValue().format(DateTimeFormatter.BASIC_ISO_DATE));
    		
    		if(getLineList()[0] == 0 || getLineList()[1] == 0 || getLineList()[2] == 0) {
    			AlertUtil.errorMsg("입력오류", "결재라인을 모두 선택해주세요");
    			return;
    		}
//    		if(dp_sDate.getValue().isBefore(dp_eDate.getValue()) == false) {	//start날짜가 end보다 전에있는가? false일 경우 return
//    			AlertUtil.errorMsg("입력오류", "휴가 일자를 올바르게 선택해주세요.");
//    			return;
//    		}
    		if(tfTitle.getText().isEmpty() || taContent.getText().isEmpty()) {
    			AlertUtil.errorMsg("입력오류", "빈 항목이 있습니다.");
    		}
    		if(AlertUtil.confirm("신청", "정말로 휴가를 신청하시겠습니까?") == false) {
    			return;
    		}
    		
    		payVo = new PaymentVO();
    		payVo.setEmp_no(loginUser.getEmp_no());
    		payVo.setDoc_no(2);
    		payVo.setPay_title(tfTitle.getText());
    		payVo.setPay_content(taContent.getText());
			payService.insertPay(payVo);	// 결재추가
			
			int payNo = payService.writeNo(loginUser.getEmp_no());	//마지막으로 추가한 결재번호
			payLineVo = new Payment_LineVO();
			payLineVo.setPay_no(payNo);
			payLineVo.setEmp_no(getLineList()[0]);
			payLineVo.setPay_order(1);
			payLineVo.setPay_check(0);
			payService.insertPayLine(payLineVo);	//결재선1 추가
			
			payLineVo = new Payment_LineVO();
			payLineVo.setPay_no(payNo);
			payLineVo.setEmp_no(getLineList()[1]);
			payLineVo.setPay_order(2);
			payLineVo.setPay_check(0);
			payService.insertPayLine(payLineVo);	//결재선2 추가
			
			payLineVo = new Payment_LineVO();
			payLineVo.setPay_no(payNo);
			payLineVo.setEmp_no(getLineList()[2]);
			payLineVo.setPay_order(3);
			payLineVo.setPay_check(0);
			payService.insertPayLine(payLineVo);	//결재선3 추가
			
			vacVo = new VacationVO();
			vacVo.setPay_no(payNo);
			vacVo.setVac_start_date(dp_sDate.getValue().format(DateTimeFormatter.BASIC_ISO_DATE));
			vacVo.setVac_end_date(dp_eDate.getValue().format(DateTimeFormatter.BASIC_ISO_DATE));
			vacVo.setVac_form(combo.getValue());
			vacService.insertVacation(vacVo);		//휴가 추가
			
			
			//휴가일수 삭감은 payment에서의 pay_state가 1로 바뀌었을때 트리거 사용
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("vacCnt", "휴가일수");
//			map.put("empNo", loginUser.getEmp_no()+"");
//			empService.updateVacCnt(map);

			FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/HR/VacationListView.fxml"));
	        Parent root = loader.load();
	        VacationListViewController ctr = loader.getController();
	        ctr.setMain(main);
	        
	        main.getMainborder().setCenter(root);

			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
    
    private IPaymentService payService;
    private IVacationService vacService;
    private IAddressService addrService;
    private EmployeeVO loginUser;
    private List<DepartmentsVO> depVo;
    private PaymentVO payVo;
    private Payment_LineVO payLineVo;
    private VacationVO vacVo;
    @FXML
    void initialize() {
    	Registry reg;
		try {
			reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			addrService = (IAddressService) reg.lookup("addrService");
			payService = (IPaymentService) reg.lookup("payService");
			vacService = (IVacationService) reg.lookup("vacService");
			
			loginUser = MainPageController.getLoginUser();
			
			depVo = addrService.getDepartment();
			for(DepartmentsVO dep : depVo) {
				if(dep.getDepartment_no() == loginUser.getDepartment_no()) { 
					tfGroup.setText(dep.getDepartment_name());
				}
			}
			tfEmp.setText(loginUser.getEmp_name());
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	combo.getItems().addAll("연차", "반차(오전)", "반차(오후)", "경조사", "예비군", "민방위");
    	combo.setValue(combo.getItems().get(0));
    	
    	
    }
}

