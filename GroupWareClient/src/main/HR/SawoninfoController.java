package main.HR;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import main.addr.myaddrbookController;
import main.mainCtr.MainPageController;
import service.IEmployeeService;
import vo.EmployeeVO;
import vo.Employee_joinVO;
import vo.ImgFileVO;

public class SawoninfoController {
	
	private imjikwonmanagementController emplist0;
	public imjikwonmanagementController getemplist0() {
		return emplist0; 
	}
	public void setemplist0(imjikwonmanagementController emplist0) {
		this.emplist0 = emplist0;
	}
	
	private imjikwoninsertController emplist1;
	

	public imjikwoninsertController getemplist1() {
		return emplist1; 
	}
	public void setemplist1(imjikwoninsertController emplist1) {
		this.emplist1 = emplist1;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView image; //사진

    @FXML
    private Button modify; //수정버튼

    @FXML
    private TextField id; //사번

    @FXML
    private TextField phonenum; //휴대폰번호

    @FXML
    private TextField naesunnum; //내선번호

    @FXML 
    private TextField mail; //email

    @FXML
    private TextField department; //부서

    @FXML
    private TextField joblevel; //직급
    
    EmployeeVO empVO=new EmployeeVO();
    
    Employee_joinVO mpVO = new Employee_joinVO();
    
    private ImgFileVO fileVo;
    private IEmployeeService empService;
    public void setmpVO(Employee_joinVO mpVo) {
    	this.mpVO=mpVo;
    	id.setText(mpVo.getEmp_no()+"");
    	
    	Registry reg;
		try {
			reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			empService = (IEmployeeService) reg.lookup("empService");
			fileVo = new ImgFileVO();
			fileVo = empService.getImgFile(mpVo.getEmp_no());
			
			ByteArrayInputStream b = new ByteArrayInputStream(fileVo.getEmp_photo());
			image.setImage(new Image(b));
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	phonenum.setText(mpVo.getEmp_phone());
    	naesunnum.setText(mpVo.getDepartment_tel());
    	mail.setText(mpVo.getEmp_email());
    	if(mpVO.getDepartment_no()==1) {
			department.setText("인사총무팀");
		}else if(mpVO.getDepartment_no()==2){
			department.setText("영업1팀");
		}else if(mpVO.getDepartment_no()==3) {
			department.setText("영업2팀");
		}else if(mpVO.getDepartment_no()==4) {
			department.setText("품질관리팀");
		}else {
			department.setText("개발팀");
		}
		
		
		if(mpVO.getGrade_no()==1) {
			joblevel.setText("사원");
		}else if(mpVO.getGrade_no()==2) {
			joblevel.setText("대리");
		}else if(mpVO.getGrade_no()==3) {
			joblevel.setText("과장");
		}else if(mpVO.getGrade_no()==4) {
			joblevel.setText("부장");
		}else {
			joblevel.setText("대표이사");
		}
    	
    }
	
    public void setempVO(EmployeeVO empVO) {
		this.empVO=empVO;
		//사번 
		
		phonenum.setText((String) empVO.getEmp_phone());
		mail.setText((String) empVO.getEmp_email());
		
		if(empVO.getDepartment_no()==1) {
			department.setText("인사총무팀");
		}else if(empVO.getDepartment_no()==2){
			department.setText("영업1팀");
		}else if(empVO.getDepartment_no()==3) {
			department.setText("영업2팀");
		}else if(empVO.getDepartment_no()==4) {
			department.setText("품질관리팀");
		}else {
			department.setText("개발팀");
		}
		
		
		if(empVO.getGrade_no()==1) {
			joblevel.setText("사원");
		}else if(empVO.getGrade_no()==2) {
			joblevel.setText("대리");
		}else if(empVO.getGrade_no()==3) {
			joblevel.setText("과장");
		}else if(empVO.getGrade_no()==4) {
			joblevel.setText("부장");
		}else {
			joblevel.setText("대표이사");
		}
		
	} 
    
    private MainPageController main;
    public MainPageController getMain() {
      return main;
   }
   public void setMain(MainPageController main) {
      this.main = main;
   }

    @FXML
    void btnmodify(ActionEvent event) throws IOException { //수정하기

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HR/imjikwoninsert.fxml"));
        Parent root = loader.load();
        imjikwoninsertController Ctr = loader.getController();
        Ctr.setEmpVO(mpVO);
        Ctr.setMain(main);
        
        main.getMainborder().setCenter(root);
        
        
    }

    @FXML
    void initialize() {
        

    }
}




