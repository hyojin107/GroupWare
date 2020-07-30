package main.email;

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

import javax.mail.MessagingException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.mainCtr.MainPageController;
import service.IEmailService;
import service.IVacationService;
import vo.EmailSendListVO;
import vo.EmployeeVO;
import vo.JavaMailViewVO;

public class SendEmailListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_write;

    @FXML
    private TableView<EmailSendListVO> table;

    @FXML
    private TableColumn<?, ?> colNo;

    @FXML
    private TableColumn<?, ?> colTo;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private Pagination pagination;
    
    private MainPageController main;
    public MainPageController getMain() {
    	return main;
    }
    public void setMain(MainPageController main) {
	   this.main = main;
    }

    @FXML
    void btnWrite(ActionEvent event) {
    	
    }

    @FXML
    void tableCliecked(MouseEvent e) throws IOException, MessagingException {
    	if(e.getClickCount()==2) {
    		EmailSendListVO sendVo = (EmailSendListVO) table.getSelectionModel().getSelectedItem();
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/email/SendEmailHam.fxml"));
            Parent root = loader.load();
            SendEmailHamController ctr = loader.getController();
            ctr.setMain(main);
            ctr.setting(sendVo);
            
            main.getMainborder().setCenter(root);
    	}
    }
    

	//DB의 전체 데이터를 가져와서 tableView에 출력하는 메서드 
	private void changeTable(int num) {
		int start = num * rowPerPage;
		int end = Math.min(start+rowPerPage, totalCount); //둘중에 작은놈을 채택
		
		Map<String,Integer> pageMap = new HashMap<String, Integer>();
		pageMap.put("start", start);
		pageMap.put("end", end);
		
		table.getItems().clear();
		for(int i = start; i < end; i++) {
			sendVoList.get(i).setNo(i+1);
			table.getItems().add( sendVoList.get(i) ); 
		}
		 
	}
	
	void paging() {
		totalCount = sendVoList.size();
		//총 페이지 개수 정해주기
		pageCount = (int) Math.ceil((double)totalCount / rowPerPage); 
		pagination.setPageCount(pageCount);
		pagination.setCurrentPageIndex(0); //기본값 첫번째 페이지로 설정 
		changeTable(0);
	}
	
	private int rowPerPage = 10; //한페이지당 보여줄 게시글 개수	
	private int totalCount;	//총 게시글수 		
	private int pageCount; // 페이지 개수

    private EmployeeVO loginUser;
    private List<EmailSendListVO> sendVoList;
    private IEmailService emailService;
    @FXML
    void initialize() {
    	Registry reg;
		try {
			reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			emailService = (IEmailService) reg.lookup("emailService");
			
			colNo.setCellValueFactory(new PropertyValueFactory<>("no"));
			colTitle.setCellValueFactory(new PropertyValueFactory<>("email_title"));
			colTo.setCellValueFactory(new PropertyValueFactory<>("email_rec_addr"));
			colDate.setCellValueFactory(new PropertyValueFactory<>("email_date"));
			
			loginUser = MainPageController.getLoginUser();
			
			Map<String,Integer> pageMap = new HashMap<String, Integer>();
	    	pageMap.put("start", 0);
			pageMap.put("end", 1999999999);
			pageMap.put("empNo", loginUser.getEmp_no());		//내가 보낸 이메일만 들고오기
			sendVoList = emailService.getSendMailList(pageMap);
			
			paging();
			pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
					changeTable(newValue.intValue());
				}
			});
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	
    }
}







