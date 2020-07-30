package main.mypage;

import java.io.ByteArrayInputStream;
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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.mainCtr.MainPageController;
import service.IAddressService;
import service.IEmployeeService;
import util.AlertUtil;
import vo.DepartmentsVO;
import vo.EmployeeVO;
import vo.ImgFileVO;

public class Mypage_infoModifyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView img_photo;
    
    @FXML
    private ImageView img_sign;

    @FXML
    private Button btn_signChange;

    @FXML
    private Button btn_imgChange;

    @FXML
    private Label lb_name;

    @FXML
    private TextField tf_depart;

    @FXML
    private TextField tf_depTel;

    @FXML
    private TextField tf_email;
    
    @FXML
    private PasswordField tf_pwd;

    @FXML
    private TextField tf_hireDate;

    @FXML
    private TextField tf_addr;

    @FXML
    private TextField tf_phone;

    @FXML
    private Button btn_modify;

    @FXML
    private Button btn_cancle;
    
    private MainPageController main;
    public MainPageController getMain() {
        return main;
    }
    public void setMain(MainPageController main) {
        this.main = main;
    }

    @FXML
    void btnCancle(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mypage/Mypage.fxml"));
        Parent root = loader.load();
        MypageController ctr = loader.getController();
        ctr.setMain(main);
        
        main.getMainborder().setCenter(root);
    }

    @FXML	// 이미지 사진 수정 페이지 이동
    void btnImgChange(ActionEvent event) throws IOException {
    	//Stage s = (Stage) btn_modify.getScene().getWindow();
		
		Parent root = FXMLLoader.load(MypageController.class.getResource("/fxml/mypage/Mypage_imgModify.fxml"));
		Scene scene = new Scene(root);
		
		Stage s = new Stage(StageStyle.DECORATED);
		s.initModality(Modality.WINDOW_MODAL);
		s.initOwner(btn_modify.getScene().getWindow());
		
		s.setScene(scene);
		s.setTitle("마이페이지 수정 - 사진수정");
		s.showAndWait();
		initialize();
    }
    
    @FXML	// 싸인 수정 페이지 이동
    void btnSignChange(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(MypageController.class.getResource("/fxml/mypage/Mypage_signModify.fxml"));
		Scene scene = new Scene(root);
		
		Stage s = new Stage(StageStyle.DECORATED);
		s.initModality(Modality.WINDOW_MODAL);
		s.initOwner(btn_modify.getScene().getWindow());
		
		s.setScene(scene);
		s.setTitle("마이페이지 수정 - 싸인수정");
		s.showAndWait();
		initialize();
    }

    @FXML
    void btnModify(ActionEvent event) {
    	boolean result = AlertUtil.confirm("수정", "정말로 수정하시겠습니까?");
    	if(result == false) return;
    	
    	try {
    		map = new HashMap<String, String>();
    		map.put("field", "emp_email");
    		map.put("data", tf_email.getText());
    		map.put("empNo", loginUser.getEmp_no()+"");
			empService.updateEmp(map);
			
			map = new HashMap<String, String>();
    		map.put("field", "emp_phone");
    		map.put("data", tf_phone.getText());
    		map.put("empNo", loginUser.getEmp_no()+"");
			empService.updateEmp(map);
			
			map = new HashMap<String, String>();
    		map.put("field", "emp_addr");
    		map.put("data", tf_addr.getText());
    		map.put("empNo", loginUser.getEmp_no()+"");
			empService.updateEmp(map);
			
			map = new HashMap<String, String>();
			map.put("field", "emp_pass");
			map.put("data", tf_pwd.getText());
			map.put("empNo", loginUser.getEmp_no()+"");
			empService.updateEmp(map);

			
			//페이지 전환해도 갱신되기 위함
			MainPageController.setLoginUser(empService.getEmpLogin(loginUser.getEmp_no()));
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mypage/Mypage.fxml"));
	        Parent root = loader.load();
	        MypageController ctr = loader.getController();
	        ctr.setMain(main);
	        
	        main.getMainborder().setCenter(root);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

    
    Map<String, String> map;
    private ImgFileVO fileVo;
    private IEmployeeService empService;
    private IAddressService addrService;
    private EmployeeVO loginUser;
    @FXML
    void initialize() {
    	tf_depart.setEditable(false);
    	tf_depTel.setEditable(false);
    	tf_hireDate.setEditable(false);
    	
    	try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			empService = (IEmployeeService) reg.lookup("empService");
			addrService = (IAddressService) reg.lookup("addrService");
			
			loginUser = MainPageController.getLoginUser();
			
			fileVo = new ImgFileVO();
			fileVo = empService.getImgFile(loginUser.getEmp_no());
			if(fileVo.getEmp_photo() != null) {
				ByteArrayInputStream b = new ByteArrayInputStream(fileVo.getEmp_photo());
				img_photo.setImage(new Image(b));				
			}else {
				img_photo.setImage(new Image(Mypage_imgModifyController.class.getResourceAsStream("/img/photoDefult.png")));
			}
			if(fileVo.getEmp_sign() != null) {
				ByteArrayInputStream b = new ByteArrayInputStream(fileVo.getEmp_sign());
				img_sign.setImage(new Image(b));				
			}
			else {
				img_sign.setImage(new Image(Mypage_imgModifyController.class.getResourceAsStream("/img/signDefult.jpg")));				
			}
			
			List<DepartmentsVO> depList = addrService.getDepartment();
			for(DepartmentsVO dep : depList) {
				if(dep.getDepartment_no() == loginUser.getDepartment_no()) {
					tf_depart.setText(dep.getDepartment_name());
					tf_depTel.setText(dep.getDepartment_tel());
				}
			}
			lb_name.setText(loginUser.getEmp_name());
			tf_email.setText(loginUser.getEmp_email());
			tf_hireDate.setText(loginUser.getEmp_hire_date().substring(0,10));
			tf_phone.setText(loginUser.getEmp_phone());
			tf_addr.setText(loginUser.getEmp_addr());
			tf_pwd.setText(loginUser.getEmp_pass());
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	
    }
}

