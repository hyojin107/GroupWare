package main.mypage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import main.mainCtr.MainPageController;
import service.IEmployeeService;
import util.AlertUtil;
import vo.EmployeeVO;
import vo.ImgFileVO;

public class Mypage_signModifyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView img_sign;

    @FXML
    private Button btn_imgUpload;

    @FXML
    private Button btn_imgDel;

    @FXML
    private Button btn_ok;

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
    void btnCancle(ActionEvent event) {
    	Stage s = (Stage)btn_imgUpload.getScene().getWindow();
    	s.close();
    }

    @FXML
    void btnImgDel(ActionEvent event) {
    	if(!AlertUtil.confirm("확인", "정말로 삭제하시겠습니까?")) {
    		return;
    	}
    	img_sign.setImage(new Image(Mypage_signModifyController.class.getResourceAsStream("/img/signDefult.jpg")));
    	
    	flag = false;
    	
//    	fileVo = new ImgFileVO();
//		fileVo.setEmp_no(loginUser.getEmp_no());
//		fileVo.setEmp_sign(null);
//		empService.setImgSign(fileVo);
//	
//		Stage s = (Stage)btn_imgUpload.getScene().getWindow();
//		s.close();
			
    }

    @FXML
    void btnImgUpload(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
		// 화면에 보여줄 파일 종류를 결정
		fileChooser.getExtensionFilters().addAll(
			new ExtensionFilter("All Files", "*.*"),
			new ExtensionFilter("Text File","*.txt")
		);
		Stage s = (Stage)btn_imgUpload.getScene().getWindow();
		selectedFile = fileChooser.showOpenDialog(s);
		if(selectedFile != null) {
			// 이미지 파일 바꾸기
			Image img = new Image(selectedFile.getAbsoluteFile().toURI().toString());
			img_sign.setImage(img);
		}
		
		flag = true;
    }

    @FXML
    void btnOk(ActionEvent event) {
    	try {
    		if(AlertUtil.confirm("수정", "사진을 수정하시겠습니까?") == false) {
    			return;
    		}
    		
    		if(!flag) {	// 이미지가 default인 경우 DB에 null값 입력
				Map<String, String> map = new HashMap<String, String>();
		    	map.put("field", "emp_sign");
		    	map.put("data", null);
		    	map.put("empNo", loginUser.getEmp_no()+"");
				empService.updateEmp(map);
				
			}else {
				fileData = new byte[(int)selectedFile.length()];
				FileInputStream fin = new FileInputStream(selectedFile);
				fin.read(fileData);		//selectedFile을 읽어와서 fileData에 담음
				
				fileVo = new ImgFileVO();
				fileVo.setEmp_no(loginUser.getEmp_no());
				fileVo.setEmp_sign(fileData);
				empService.setImgSign(fileVo);
				
				fin.close();
			}
    		
			
			Stage s = (Stage)btn_imgUpload.getScene().getWindow();
	    	s.close();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private boolean flag;
    private File selectedFile;
    private byte[] fileData;
    private IEmployeeService empService;
    private EmployeeVO loginUser;
    private ImgFileVO fileVo;
    @FXML
    void initialize() {
    	try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			empService = (IEmployeeService) reg.lookup("empService");
			
			loginUser = MainPageController.getLoginUser();
			
			fileVo = new ImgFileVO();
			fileVo = empService.getImgFile(loginUser.getEmp_no());
			
			ByteArrayInputStream b = new ByteArrayInputStream(fileVo.getEmp_sign());
			img_sign.setImage(new Image(b));
			
			
			
//			if(fileVo.getEmp_sign() != null)
//				img_sign.setImage(new Image(fileVo.getEmp_sign().toString()));
//			else
//				img_sign.setImage(new Image(Mypage_signModifyController.class.getResourceAsStream("../../img/signDefult.jpg")));
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    }
}







