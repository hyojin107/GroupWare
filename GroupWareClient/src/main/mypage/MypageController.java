package main.mypage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.mainCtr.MainPageController;
import service.IAddressService;
import service.IEmployeeService;
import vo.DepartmentsVO;
import vo.EmployeeVO;
import vo.ImgFileVO;

public class MypageController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView img_photo;

    @FXML
    private ImageView img_sign;
    
    @FXML
    private Label lb_name;

    @FXML
    private Label lb_depart;

    @FXML
    private Label lb_depTel;

    @FXML
    private Label lb_email;

    @FXML
    private Label lb_phone;

    @FXML
    private Label lb_hireDate;

    @FXML
    private Label lb_addr;

    @FXML
    private Button btn_modify;

    private MainPageController main;
    public MainPageController getMain() {
		return main;
	}
	public void setMain(MainPageController main) {
		this.main = main;
	}
	
    @FXML
    void btn_modifyClicked(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mypage/Mypage_infoModify.fxml"));
	        Parent root = loader.load();
	        Mypage_infoModifyController ctr = loader.getController();
	        ctr.setMain(main);
	        
	        main.getMainborder().setCenter(root);
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    private IEmployeeService empService;
    private IAddressService addrService;
    private EmployeeVO loginUser;
    private ImgFileVO fileVo;
    @FXML
    void initialize() {
    	try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			empService = (IEmployeeService) reg.lookup("empService");
			addrService = (IAddressService) reg.lookup("addrService");
			
			loginUser = MainPageController.getLoginUser();
			List<DepartmentsVO> depList = addrService.getDepartment();
			for(DepartmentsVO dep : depList) {
				if(dep.getDepartment_no() == loginUser.getDepartment_no()) {
					lb_depart.setText(dep.getDepartment_name());
					lb_depTel.setText(dep.getDepartment_tel());
				}
			}
			lb_name.setText(loginUser.getEmp_name());
			lb_email.setText(loginUser.getEmp_email());
			lb_hireDate.setText(loginUser.getEmp_hire_date().substring(0,10));
			lb_phone.setText(loginUser.getEmp_phone());
			lb_addr.setText(loginUser.getEmp_addr());
			
			fileVo = new ImgFileVO();
			fileVo = empService.getImgFile(loginUser.getEmp_no());
			
			ByteArrayInputStream b = new ByteArrayInputStream(fileVo.getEmp_photo());
			img_photo.setImage(new Image(b));
			
			ByteArrayInputStream sign = new ByteArrayInputStream(fileVo.getEmp_sign());
			img_sign.setImage(new Image(sign));
			
			
			
//			img_sign.setImage(new Image(b));
			
//			if(fileVo.getEmp_photo() != null) {
//				ByteArrayInputStream b = new ByteArrayInputStream(fileVo.getEmp_photo());
//				img_photo.setImage(new Image(b));				
//			}else {
//				img_photo.setImage(new Image(Mypage_imgModifyController.class.getResourceAsStream("/img/photoDefult.png")));
//			}
//			if(fileVo.getEmp_sign() != null) {
//				ByteArrayInputStream b = new ByteArrayInputStream(fileVo.getEmp_sign());
//				img_sign.setImage(new Image(b));				
//			}
//			else {
//				img_sign.setImage(new Image(Mypage_imgModifyController.class.getResourceAsStream("/img/signDefult.jpg")));				
//			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	
    }
}




