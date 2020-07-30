package main.mainCtr;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.HR.VacationApplyController;
import main.HR.VacationCheckController;
import service.IAttendanceService;
import service.IEmployeeService;
import util.AlertUtil;
import vo.EmployeeVO;
import vo.ImgFileVO;

public class MainPageController {
	private LoginController loginCtr;
	private static EmployeeVO loginUser;
	
	public void setLogin(LoginController loginCtr) {
		this.loginCtr = loginCtr;
	}
	public void setLoginVO(EmployeeVO emp) {
		loginUser = emp;
		lb_loginUser.setText(loginUser.getEmp_name());
		try {
			ImgFileVO imgVo = empService.getImgFile(emp.getEmp_no());
			InputStream in = new ByteArrayInputStream(imgVo.getEmp_photo());
			Image img = new Image(in);
			
			imgphoto.setImage(img);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	public static EmployeeVO getLoginUser() {
		return loginUser;
	}
	public static void setLoginUser(EmployeeVO empVo) {
		loginUser = empVo;
	}
	
	public BorderPane getMainborder() {
		return mainborder;
	}
	public void setMainborder(BorderPane mainborder) {
		this.mainborder = mainborder;
	}


	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private BorderPane mainborder;

    @FXML
    private Label lb_loginUser;

    @FXML
    private Button btn_out;
    
    @FXML
    private Rectangle top_mailBox;

    @FXML
    private Rectangle top_paymentBox;

    @FXML
    private Rectangle top_HRBox;

    @FXML
    private Rectangle top_planBox;

    @FXML
    private Rectangle top_noticeBox;

    @FXML
    private Rectangle top_addrBox;

    @FXML
    private Rectangle top_myPageBox;

   @FXML
    private ImageView top_mail;

    @FXML
    private ImageView top_payment;

    @FXML
    private ImageView top_HR;

    @FXML
    private ImageView top_plan;

    @FXML
    private ImageView top_notice;

    @FXML
    private ImageView top_addr;

    @FXML
    private ImageView top_myPage;
    
    @FXML
    private ImageView mainImg;

    @FXML
    private ImageView imgphoto;

    @FXML
    private ImageView btnEnd;
    
    @FXML
    void btnPay(MouseEvent event) throws IOException {
    	MainLeftController left = topPayment(event);
    	
    	left.left2Cliecked(event);
    }

    @FXML
    void btnVac(MouseEvent event) throws IOException {
    	MainLeftController left = topHR(event);
    	
    	left.left3Cliecked(event);
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HR/VacationApply.fxml"));
        Parent root = loader.load();
        VacationApplyController ctr = loader.getController();
        ctr.setMain(this);
        
        mainborder.setCenter(root);
    	
    }

    @FXML
    void btnEndclick(MouseEvent event) {
    	try {
			int cnt = service.updateEndDate(loginUser.getEmp_no());
			if(cnt == 1) {
				AlertUtil.infoMsg("퇴근", "열심히 일한 당신 쉬어라.");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }

    
    Rectangle[] topList = {};
    void topStyle(int no) {
		for(int i=0; i<7; i++) {
			topList[i].setVisible(false);
		}
		topList[no].setVisible(true);
	}

    @FXML
    void btnOut(ActionEvent event) throws IOException {
    	if(AlertUtil.confirm("로그아웃", "로그아웃을 하시겠습니까?") == false) {
    		return;
    	}
    	Stage s = (Stage) btn_out.getScene().getWindow();
    	s.close();
    	loginUser = null;
    	
    	Parent root = FXMLLoader.load(LoginController.class.getResource("/fxml/main/Login.fxml"));
		Scene scene = new Scene(root);
		
		Stage sub = new Stage(StageStyle.DECORATED);
		sub.setScene(scene);
		sub.setTitle("GroupWare 로그인");
		sub.show();
    	
    }

    @FXML
    void mainLogo(MouseEvent event) throws IOException {
    	for(int i=0; i<6; i++) {
			topList[i].setVisible(false);
		}
    	FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/fxml/main/MainPage.fxml"));
    	Parent root = loader.load();
		Scene scene = new Scene(root);
		
		MainPageController con = loader.getController();
		con.setLoginVO(loginUser);
		Stage sub = (Stage) btn_out.getScene().getWindow();
		sub.setScene(scene);
		sub.setTitle("GroupWare");
		sub.show();
    }

    @FXML
    void topAddr(MouseEvent event) throws IOException {
    	topStyle(5);
		FXMLLoader loderLeft = new FXMLLoader(getClass().getResource("/fxml/main/MainLeft.fxml"));
		Parent left = loderLeft.load();
		mainborder.setLeft(left);
//    	mainborder.setCenter(left);
//		mainborder.getLeft().setVisible(false);
		
		MainLeftController leftCtr = loderLeft.getController();
		leftCtr.getLeftName().setText("주소록");
//		leftCtr.getLeft1().setText("사내 주소록");
//		leftCtr.getLeft2().setText("나의 주소록");
		leftCtr.getLeft1().setVisible(false);
		leftCtr.getLeft2().setVisible(false);		
		leftCtr.getLeft3().setVisible(false);
		leftCtr.getLeft4().setVisible(false);
		leftCtr.getLeft5().setVisible(false);
		leftCtr.getLeft6().setVisible(false);
		leftCtr.getLeft1().setStyle("-fx-background-color: #f1c36e;");
		leftCtr.setMain(this);
		leftCtr.left1Clicked(event);
		
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/addr/juncheaddrbook.fxml"));
//		Parent root = loader.load();
//		juncheaddrbookController ctr = loader.getController();
//		ctr.setMain(this);
//		
//		getMainborder().setCenter(root);
		
    }

    @FXML
     MainLeftController topHR(MouseEvent event) throws IOException {
    	topStyle(2);
		FXMLLoader loderLeft = new FXMLLoader(getClass().getResource("/fxml/main/MainLeft.fxml"));
		Parent left = loderLeft.load();
		mainborder.setLeft(left);
		
		MainLeftController leftCtr = loderLeft.getController();
		leftCtr.getLeftName().setText("HR");
		leftCtr.getLeft1().setText("근태");
		leftCtr.getLeft2().setText("증명서");
		leftCtr.getLeft3().setText("휴가");
		leftCtr.getLeft4().setText("급여");
		leftCtr.getLeft5().setText("사원등록");
		leftCtr.getLeft6().setText("임직원관리");
		leftCtr.getLeft1().setStyle("-fx-background-color: #f1c36e;");
		leftCtr.setMain(this);
		leftCtr.left1Clicked(event);
		
		return leftCtr;
    }

    @FXML
    void topMail(MouseEvent event) throws IOException {
    	topStyle(0);
//    	Parent root = FXMLLoader.load(LoginController.class.getResource("../../fxml/email/getEmailList.fxml"));
//		AnchorPane anchor = (AnchorPane) btn_out.getScene().getRoot();
//		BorderPane border = (BorderPane) anchor.getChildren().get(0);
//		mainborder.setCenter(root);
//    	mainborder.getLeft().setVisible(false);
		
		FXMLLoader loderLeft = new FXMLLoader(getClass().getResource("/fxml/main/MainLeft.fxml"));
		Parent left = loderLeft.load();
		mainborder.setLeft(left);
		
		MainLeftController leftCtr = loderLeft.getController();
//		MainLeftController.leftName.setText("메일");
		leftCtr.getLeftName().setText("메일");
		leftCtr.getLeft1().setText("받은 메일함");
		leftCtr.getLeft2().setText("메일쓰기");
		leftCtr.getLeft3().setText("보낸 메일함");
		leftCtr.getLeft4().setVisible(false);
		leftCtr.getLeft5().setVisible(false);
		leftCtr.getLeft6().setVisible(false);
		leftCtr.getLeft1().setStyle("-fx-background-color: #f1c36e;");
		leftCtr.setMain(this);
		leftCtr.left1Clicked(event);
    }

    @FXML
    void topMyPage(MouseEvent event) throws IOException {
    	topStyle(6);
		FXMLLoader loderLeft = new FXMLLoader(getClass().getResource("/fxml/main/MainLeft.fxml"));
		Parent left = loderLeft.load();
		mainborder.setLeft(left);
		
		MainLeftController leftCtr = loderLeft.getController();
		leftCtr.getLeftName().setText("마이페이지");
//		leftCtr.getLeft1().setText("마이페이지");
		leftCtr.getLeft1().setVisible(false);
		leftCtr.getLeft2().setVisible(false);
		leftCtr.getLeft3().setVisible(false);
		leftCtr.getLeft4().setVisible(false);
		leftCtr.getLeft5().setVisible(false);
		leftCtr.getLeft6().setVisible(false);
		leftCtr.getLeft1().setStyle("-fx-background-color: #f1c36e;");
		leftCtr.setMain(this);
		leftCtr.left1Clicked(event);
    	
//    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/mypage/Mypage.fxml"));
//		Parent root = loader.load();
//		MypageController ctr = loader.getController();
//		ctr.setMain(this);
//		
//		getMainborder().setCenter(root);
    }

    @FXML
    void topNotice(MouseEvent event) throws IOException {
    	topStyle(4);
		FXMLLoader loderLeft = new FXMLLoader(getClass().getResource("/fxml/main/MainLeft.fxml"));
		Parent left = loderLeft.load();
		mainborder.setLeft(left);
		
		MainLeftController leftCtr = loderLeft.getController();
		leftCtr.getLeftName().setText("게시판");
		leftCtr.getLeft1().setText("공지사항");
		leftCtr.getLeft2().setText("자유게시판");
		leftCtr.getLeft3().setVisible(false);
		leftCtr.getLeft4().setVisible(false);
		leftCtr.getLeft5().setVisible(false);
		leftCtr.getLeft6().setVisible(false);
		leftCtr.getLeft1().setStyle("-fx-background-color: #f1c36e;");
		leftCtr.setMain(this);
		leftCtr.left1Clicked(event);
    }

    @FXML
    MainLeftController topPayment(MouseEvent event) throws IOException {
    	topStyle(1);
		FXMLLoader loderLeft = new FXMLLoader(getClass().getResource("/fxml/main/MainLeft.fxml"));
		Parent left = loderLeft.load();
		mainborder.setLeft(left);
		
		MainLeftController leftCtr = loderLeft.getController();
		leftCtr.getLeftName().setText("전자결재");
		leftCtr.getLeft1().setText("결재조회");
		leftCtr.getLeft2().setText("상신하기");
		leftCtr.getLeft3().setText("결재하기");
		leftCtr.getLeft4().setText("임시저장함");
		leftCtr.getLeft5().setVisible(false);
		leftCtr.getLeft6().setVisible(false);
		leftCtr.getLeft1().setStyle("-fx-background-color: #f1c36e;");
		leftCtr.setMain(this);
		leftCtr.left1Clicked(event);
		
		return leftCtr;
    }

    @FXML
    void topPlan(MouseEvent event) throws IOException {
    	topStyle(3);
		FXMLLoader loderLeft = new FXMLLoader(getClass().getResource("/fxml/main/MainLeft.fxml"));
		Parent left = loderLeft.load();
		mainborder.setLeft(left);
		
		MainLeftController leftCtr = loderLeft.getController();
		leftCtr.getLeftName().setText("일정관리");
		leftCtr.getLeft1().setText("일정공유");
		leftCtr.getLeft2().setText("휴가일정");
		leftCtr.getLeft3().setVisible(false);
		leftCtr.getLeft4().setVisible(false);
		leftCtr.getLeft5().setVisible(false);
		leftCtr.getLeft6().setVisible(false);
		leftCtr.getLeft1().setStyle("-fx-background-color: #f1c36e;");
		leftCtr.setMain(this);
		leftCtr.left1Clicked(event);
    }

    
	private IAttendanceService service; 
	private IEmployeeService empService;
    private ImgFileVO fileVo;
    @FXML
    void initialize() {
    	
    	try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			empService = (IEmployeeService) reg.lookup("empService");
			service = (IAttendanceService) reg.lookup("attService"); 
			

//			loginUser = MainPageController.getLoginUser();
//			fileVo = new ImgFileVO();
//			fileVo = empService.getImgFile(loginUser.getEmp_no());
//			
//			ByteArrayInputStream b = new ByteArrayInputStream(fileVo.getEmp_photo());
//			imgphoto.setImage(new Image(b));
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	topList = new Rectangle[]{top_mailBox, top_paymentBox, top_HRBox, top_planBox, top_noticeBox, top_addrBox, top_myPageBox};
    	for(int i=0; i<7; i++) {
			topList[i].setVisible(false);
		}
    }
}


