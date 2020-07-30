package main.mainCtr;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import main.HR.Doc_applystateController;
import main.HR.SalaryListController;
import main.HR.SingyusawonenrollController;
import main.HR.VacationListViewController;
import main.HR.imjikwonmanagementController;
import main.addr.juncheaddrbookController;
import main.addr.myaddrbookController;
import main.att.AttCalendarController;
import main.email.GetEmailListController;
import main.email.SendEmailController;
import main.email.SendEmailListController;
import main.mypage.MypageController;
import main.notice.BoardListController;
import main.notice.NoticeListController;
import main.payment.PayListController;
import main.payment.SangsinController;
import main.plan.calendarforHRController;
import main.plan.calendarforPlanController;
import util.AlertUtil;
import vo.EmployeeVO;

public class MainLeftController {
	
    public Label getLeftName() {
		return leftName;
	}

	public void setLeftName(Label leftName) {
		this.leftName = leftName;
	}

	public Label getLeft1() {
		return left1;
	}

	public void setLeft1(Label left1) {
		this.left1 = left1;
	}

	public Label getLeft2() {
		return left2;
	}

	public void setLeft2(Label left2) {
		this.left2 = left2;
	}

	public Label getLeft3() {
		return left3;
	}

	public void setLeft3(Label left3) {
		this.left3 = left3;
	}

	public Label getLeft4() {
		return left4;
	}

	public void setLeft4(Label left4) {
		this.left4 = left4;
	}

	public Label getLeft5() {
		return left5;
	}

	public void setLeft5(Label left5) {
		this.left5 = left5;
	}

	public Label getLeft6() {
		return left6;
	}

	public void setLeft6(Label left6) {
		this.left6 = left6;
	}

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label leftName;

    @FXML
    private Label left1;

    @FXML
    private Label left2;

    @FXML
    private Label left3;

    @FXML
    private Label left4;

    @FXML
    private Label left5;

    @FXML
    private Label left6;
    
    private Label[] leftList;
    
    private MainPageController main;
    public MainPageController getMain() {
		return main;
	}
	public void setMain(MainPageController main) {
		this.main = main;
	}

	private Parent root;
	private FXMLLoader loader;
	
	void LeftStyle(int no) {
		for(int i=0; i<6; i++) {
			leftList[i].setStyle("-fx-background-color:none;");
		}
		leftList[no].setStyle("-fx-background-color: #f1c36e;");
	}
	
	@FXML
    public void left1Clicked(MouseEvent event) throws IOException {
		LeftStyle(0);
    	if(leftName.getText().equals("메일")) {
    		loader = new FXMLLoader(getClass().getResource("/fxml/email/GetEmailList.fxml"));
    		root = loader.load();
    		GetEmailListController ctr = loader.getController();
    		ctr.setMain(main);
    	}else if(leftName.getText().equals("전자결재")){
    		loader = new FXMLLoader(getClass().getResource("/fxml/payment/PayList.fxml"));
    		root = loader.load();
    		PayListController payCtr = loader.getController();
    		payCtr.setLeft(this);
    		payCtr.setRead(true);
    		payCtr.setMain(main);
    	}else if(leftName.getText().equals("HR")) {
    		loader = new FXMLLoader(getClass().getResource("/fxml/att/AttCalendar.fxml"));
    		root = loader.load();
    		AttCalendarController ctr = loader.getController();
    		ctr.setMain(main);
    	}else if(leftName.getText().equals("일정관리")) {
    		loader = new FXMLLoader(getClass().getResource("/fxml/plan/CalendarforPlan.fxml"));
    		root = loader.load();
    		calendarforPlanController ctr = loader.getController();
    		ctr.setMain(main);
    	}else if(leftName.getText().equals("게시판")) {
    		loader = new FXMLLoader(getClass().getResource("/fxml/notice/NoticeList.fxml"));
    		root = loader.load();
    		NoticeListController ctr = loader.getController();
    		ctr.setMain(main);
    	}else if(leftName.getText().equals("주소록")) {
    		loader = new FXMLLoader(getClass().getResource("/fxml/addr/juncheaddrbook.fxml"));
    		root = loader.load();
    		juncheaddrbookController ctr = loader.getController();
    		ctr.setMain(main);
    	}else { //마이페이지
    		loader = new FXMLLoader(getClass().getResource("/fxml/mypage/Mypage.fxml"));
    		root = loader.load();
    		MypageController ctr = loader.getController();
    		ctr.setMain(main);
    	}
    	
    	change(root);
    }

    @FXML
    public void left2Cliecked(MouseEvent event) throws IOException {
    	LeftStyle(1);
    	if(leftName.getText().equals("메일")) {
    		loader = new FXMLLoader(getClass().getResource("/fxml/email/SendEmail.fxml"));
    		root = loader.load();
    		SendEmailController ctr = loader.getController();
    		ctr.setMain(main);
    	}else if(leftName.getText().equals("전자결재")){
    		loader = new FXMLLoader(getClass().getResource("/fxml/payment/Sangsin.fxml"));
    		root = loader.load();
    		SangsinController payCtr = loader.getController();
    		payCtr.setLeft(this);
    		payCtr.setWrite();
    		payCtr.setMain(main);
    	}else if(leftName.getText().equals("HR")) {
    		loader = new FXMLLoader(getClass().getResource("/fxml/HR/Doc_applystate.fxml"));
    		root = loader.load();
    		Doc_applystateController ctr = loader.getController();
    		ctr.setMain(main);
    	}else if(leftName.getText().equals("일정관리")) {
    		loader = new FXMLLoader(getClass().getResource("/fxml/plan/CalendarforHR.fxml"));
    		root = loader.load();
    		calendarforHRController ctr = loader.getController();
    		ctr.setMain(main);
    	}else if(leftName.getText().equals("게시판")) {
    		loader = new FXMLLoader(getClass().getResource("/fxml/notice/BoardList.fxml"));
    		root = loader.load();
    		BoardListController ctr = loader.getController();
    		ctr.setMain(main);
    	}else if(leftName.getText().equals("주소록")) {
    		loader = new FXMLLoader(getClass().getResource("/fxml/addr/myaddrbook.fxml"));
    		root = loader.load();
    		myaddrbookController ctr = loader.getController();
    		ctr.setMain(main);
    	}
    	
    	change(root);
    }

    @FXML
    public void left3Cliecked(MouseEvent event) throws IOException {
    	LeftStyle(2);
    	if(leftName.getText().equals("메일")) {
    		loader = new FXMLLoader(getClass().getResource("/fxml/email/SendEmailList.fxml"));
    		root = loader.load();
    		SendEmailListController ctr = loader.getController();
    		ctr.setMain(main);
    	}else if(leftName.getText().equals("전자결재")){
    		loader = new FXMLLoader(getClass().getResource("/fxml/payment/PayList.fxml"));
    		root = loader.load();
    		PayListController payCtr = loader.getController();
    		payCtr.setLeft(this);
    		payCtr.setRead(false);
    		payCtr.setMain(main);
    	}else if(leftName.getText().equals("HR")) {
    		loader = new FXMLLoader(getClass().getResource("/fxml/HR/VacationListView.fxml"));
    		root = loader.load();
    		VacationListViewController ctr = loader.getController();
    		ctr.setMain(main);
    	}
    	
    	change(root);
    }

    @FXML
    public void left4Cliecked(MouseEvent event) throws IOException {
    	LeftStyle(3);
    	if(leftName.getText().equals("전자결재")){
    		loader = new FXMLLoader(getClass().getResource("/fxml/payment/Sangsin.fxml"));
    		root = loader.load();
    		SangsinController payCtr = loader.getController();
    		payCtr.setLeft(this);
    		payCtr.setMain(main);
    		payCtr.setTemp();
    		payCtr.setWrite();
    	}else if(leftName.getText().equals("HR")) {
    		loader = new FXMLLoader(getClass().getResource("/fxml/HR/SalaryList.fxml"));
    		root = loader.load();
    		SalaryListController ctr = loader.getController();
    		ctr.setMain(main);
    	}
    	
    	change(root);
    }

    @FXML
    public void left5Cliecked(MouseEvent event) throws IOException {
    	LeftStyle(4);
    	
    	if(loginUser.getDepartment_no() != 1 && loginUser.getDepartment_no() != 0) {
    		AlertUtil.warnMsg("접근권한", "관리자만 접근할 수 있습니다.");
    		return;
    	}
    	loader = new FXMLLoader(getClass().getResource("/fxml/HR/Singyusawonenroll.fxml"));
    	root = loader.load();
    	SingyusawonenrollController ctr = loader.getController();
		ctr.setMain(main);
    	change(root);
    }

    @FXML
    public void left6Cliecked(MouseEvent event) throws IOException {
    	LeftStyle(5);
    	if(loginUser.getDepartment_no() != 1 && loginUser.getDepartment_no() != 0) {
    		AlertUtil.warnMsg("접근권한", "관리자만 접근할 수 있습니다.");
    		return;
    	}
    	loader = new FXMLLoader(getClass().getResource("/fxml/HR/imjikwonmanagement.fxml"));
    	root = loader.load();
    	imjikwonmanagementController ctr = loader.getController();
		ctr.setMain(main);
    	change(root);
    }

    @FXML
    void leftNameCliecked(MouseEvent event) {  }
    
    void change(Parent root) {
    	main.getMainborder().setCenter(root);    	
    }
    
    
    private EmployeeVO loginUser;
    @FXML
    void initialize() {
    	leftList = new Label[]{left1, left2, left3, left4, left5, left6};
    	
    	loginUser = MainPageController.getLoginUser();
    	
    }
}





