package main.plan;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.mainCtr.MainPageController;
import main.notice.NoticeEditController;
import oracle.net.aso.p;
import service.IPlanService;
import util.AlertUtil;
import vo.PlanVO;
import vo.PlanVO_join;

public class PlanDetailController { 

	
	//tbclick을 위한 listcontroller게터세터
	private PlanListController controll1;
	
    public PlanListController getControll1() {
		return controll1;
	}
	public void setControll1(PlanListController controll1) {
		this.controll1 = controll1;
	}
  

	public Label getDetailHead() {
		return detailHead; 
	}

	public void setDetailHead(Label detailHead) {
		this.detailHead = detailHead;
	}
	
	String head;
	
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	
	public Button getBtnBack() {
		return btnBack;
	}
	public void setBtnBack(Button btnBack) {
		this.btnBack = btnBack;
	}
	
    private MainPageController main;
    public MainPageController getMain() {
		return main;
	}
	public void setMain(MainPageController main) {
		this.main = main;
	}


	//controller를 통해 받아올 vo 생성 
	int planNO;
	int empNO;
	PlanVO_join pjoinVO = new PlanVO_join();
	public void setPjoinVO(PlanVO_join pjoinVO) {
		this.pjoinVO = pjoinVO;
		tfTitle.setText(pjoinVO.getPlan_title());
		tfContent.setText(pjoinVO.getPlan_content());
		tName.setText(pjoinVO.getEmp_name());
		//DB정보를 LocalDate형식으로 변환
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate1 = LocalDate.parse(pjoinVO.getPlan_start(), formatter);
        LocalDate localDate2 = LocalDate.parse(pjoinVO.getPlan_end(), formatter);
		dateStart.setValue(localDate1);
		dateEnd.setValue(localDate2);
		planNO = pjoinVO.getPlan_no();
		empNO = pjoinVO.getEmp_no();
		
	}

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label detailHead;

    @FXML
    private TextField tfTitle;

    @FXML
    private TextArea tfContent;

    @FXML
    private Text tName;

    @FXML
    private DatePicker dateStart;

    @FXML
    private DatePicker dateEnd;

    @FXML
    private Button btnBack;
    
    public TextField getTfTitle() {
		return tfTitle;
	}

	public void setTfTitle(TextField tfTitle) {
		this.tfTitle = tfTitle;
	}

	public TextArea getTfContent() {
		return tfContent;
	}

	public void setTfContent(TextArea tfContent) {
		this.tfContent = tfContent;
	}

	public DatePicker getDateStart() {
		return dateStart;
	}

	public void setDateStart(DatePicker dateStart) {
		this.dateStart = dateStart;
	}

	public DatePicker getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(DatePicker dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Button getBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(Button btnEdit) {
		this.btnEdit = btnEdit;
	}

	public Button getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(Button btnDelete) {
		this.btnDelete = btnDelete;
	}


	@FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;
    
    
    
    @FXML
    void btnBackClick(ActionEvent event) throws IOException {
    	if(btnBack.getText().equals("일정 게시판"))
    		backtoList();
    	if(btnBack.getText().equals("캘린더 보기")) {
    		backtoCal();
    	}
    }

    @FXML
    void btnDelClick(ActionEvent event) throws IOException {
    	if(btnDelete.getText().equals("취소")) {
    	backtoList();
    	}
    	if(btnDelete.getText().equals("삭제")) {
    		//세션 받아서 본인 글만 삭제가능하도록
        	int loginUser= MainPageController.getLoginUser().getEmp_no();
        	if(loginUser!=empNO) {
        		AlertUtil.infoMsg("수정불가", "본인글만 수정가능합니다.");
        		return;
        	}
    		if(AlertUtil.confirm("삭제확인", "해당 일정을 삭제하시겠습니까?")) {
    			int cnt = service.deletePlan(planNO);
    			if(cnt>0) {
    				AlertUtil.infoMsg("삭제성공", "게시글이 삭제되었습니다.");
        			backtoList();
    			}else {
        			AlertUtil.infoMsg("삭제실패", "삭제 작업 실패");
        		}
    		}
    		
    		
    	}
    }

    @FXML
    void btnEditClick(ActionEvent event) throws IOException { 
    	int cnt=0;
    	//Localdate형식을 String으로 변환 (Localdate -> date -> String ) 
    	LocalDate start = dateStart.getValue();
    	LocalDate end = dateEnd.getValue();
    	
	    // 빈칸있을때 경고창 
    	if((tfTitle.getText().equals("")) || (tfContent.getText().equals("")) || start==null || end ==null) {
    			AlertUtil.infoMsg("입력오류", "제목, 내용, 일정날짜 빠짐없이 입력해주세요"); 
    			return;
    	}
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    	//DB에 입력할 날짜
    	String dateStart = start.format(formatter);
    	String dateEnd = end.format(formatter); 
    	//대소비교할 날짜 
    	int dateS = Integer.parseInt(dateStart);
    	int dateE = Integer.parseInt(dateEnd);
    	// 일정 시작일, 마감일 비교 
    	if(dateS>dateE) {
	    		AlertUtil.errorMsg("알림", "일정시작일은 마감일보다 이전이어야합니다");
	    		return;
    	}
    	
    	//일정등록일경우
    	pjoinVO = new PlanVO_join();
    	if(btnEdit.getText().equals("등록")) {
    		pjoinVO.setPlan_title(tfTitle.getText());
    		pjoinVO.setPlan_content(tfContent.getText());
    		pjoinVO.setPlan_start(dateStart);
    		pjoinVO.setPlan_end(dateEnd);
    		//세션으로 넣어주기
    		int loginUser= MainPageController.getLoginUser().getEmp_no();
    		pjoinVO.setEmp_no(loginUser); 
    		
    		try {
				cnt=service.insertPlan(pjoinVO);
			} catch (RemoteException e) {
				e.printStackTrace();
			} if(cnt>0) {
    			AlertUtil.infoMsg("등록성공", "일정이 등록되었습니다");
    			backtoCal();
			}
    		return; 
    	 }
    	//일정수정
    	if(btnEdit.getText().equals("수정")) {
    		//세션 받아서 본인 글만 수정가능하도록
        	int loginUser= MainPageController.getLoginUser().getEmp_no();
        	if(loginUser!=empNO) {
        		AlertUtil.infoMsg("수정불가", "본인글만 수정가능합니다.");
        		return;
        	}
    		editOn();
    		detailHead.setText("일 정 수 정");
    		btnEdit.setText("완료");
        	btnDelete.setText("취소");
        	return;
    	} 
    	
    	//수정완료
    	pjoinVO = new PlanVO_join();
    	if(btnEdit.getText().equals("완료")) {
    		pjoinVO.setPlan_title(tfTitle.getText());
    		pjoinVO.setPlan_content(tfContent.getText());
    		pjoinVO.setPlan_start(dateStart);
    		pjoinVO.setPlan_end(dateEnd);
    		pjoinVO.setPlan_no(planNO);
    		try {
				cnt=service.updatePlan(pjoinVO);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    		if(cnt>0) {
    			AlertUtil.infoMsg("수정성공", "일정이 수정되었습니다");
    			btnEdit.setText("수정");
    	    	btnDelete.setText("삭제");
    			return;
    		}
    		return;
    	 }
    	
    
    		
    }

    // 수정 삭제버튼 나타나기 
//    void btnOn() {
//    	btnDelete.setVisible(true);
//    	btnEdit.setVisible(true);
//    }
    // 수정 삭제버튼 숨기기 
//    void btnOff() {
//    	btnDelete.setVisible(false);
//    	btnEdit.setVisible(false);
//    }

    // 상세보기페이지
    void returnToDetail() {
    	detailHead.setText("일 정 상 세 보 기");
		btnEdit.setText("수정");
    	btnDelete.setText("삭제");
    	tfTitle.setEditable(false);
    	tfContent.setEditable(false);
    	dateStart.setEditable(false);
    	dateEnd.setEditable(false);
    	int loginUser= MainPageController.getLoginUser().getEmp_no();
    	int emp_no = pjoinVO.getEmp_no();
    	if(loginUser!=emp_no) {
    		btnEdit.setVisible(false);
        	btnDelete.setVisible(false);
    	}
    }
    void editOn() {
    	tfTitle.setEditable(true);
    	tfContent.setEditable(true);
    	dateStart.setEditable(true);
    	dateEnd.setEditable(true);
    }
    // 리스트로 되돌아가기
    void backtoList() throws IOException {

        FXMLLoader loader = new FXMLLoader(PlanDetailController.class.getResource("/fxml/plan/PlanList.fxml"));
        Parent root = loader.load();
        PlanListController Ctr = loader.getController();
        Ctr.getPlanHead2().setText(head);
        Ctr.change();
		Ctr.setMain(main);
		main.getMainborder().setCenter(root);

    }
    
    // 캘린더로 돌아가기 
    void backtoCal() throws IOException {

        FXMLLoader loader = new FXMLLoader(PlanDetailController.class.getResource("/fxml/plan/calendarforPlan.fxml"));
		Parent root = loader.load();
		calendarforPlanController Ctr = loader.getController();
		Ctr.setMain(main);
		main.getMainborder().setCenter(root);

    }
    private IPlanService service;
    @FXML
    void initialize() {
    	try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			service = (IPlanService) reg.lookup("planService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	//세션으로 작성자 세팅 
    	String name= MainPageController.getLoginUser().getEmp_name();
    	tName.setText(name);

    	// 기본은 상세보기이므로 달력 막음 
    	dateStart.setOnMouseClicked(e->{
    		if(!dateStart.isEditable()) {
    			dateStart.hide();
    		}
    	});
    	dateEnd.setOnMouseClicked(e->{
    		if(!dateEnd.isEditable()) {
    			dateEnd.hide();
    		}
    	});
    	

    	
    	
    
    	
    }
}
