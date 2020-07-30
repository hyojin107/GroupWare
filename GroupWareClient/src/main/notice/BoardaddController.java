package main.notice;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.mainCtr.MainPageController;
import service.IBoardService;
import util.AlertUtil;
import vo.Boardemp_joinVO;
import vo.EmployeeVO;

public class BoardaddController {  

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox boardAddpage; //자유게시판

    @FXML
    private TextArea taCon; //내용

    @FXML
    private Text textDate; //작성일

    @FXML
    private Text textname; //작성자

    @FXML
    private TextField tftitle; //제목

    @FXML
    private Button btnAdd; //

    @FXML
    private Button btnCancel;

    private IBoardService service;
   // private Boardemp_joinVO item;
    private MainPageController main;
    
    public MainPageController getMain() {
      return main;
   }
   public void setMain(MainPageController main) {
      this.main = main;
   }

   int cnt=0;
   Boardemp_joinVO vo = new Boardemp_joinVO();
  //private int loginUser;
    @FXML
    void btnaddclick(ActionEvent event) throws IOException { //등록버튼클릭
//    	seq_boardno.nextval,#board_title#,sysdate,#board_content#,0,#emp_no#
    	
    	//BoardVO vo=new BoardVO();
    	vo.setBoard_title(tftitle.getText());
    	vo.setBoard_content(taCon.getText());
    	//세션
    	int no=MainPageController.getLoginUser().getEmp_no();
    	String name=MainPageController.getLoginUser().getEmp_name();
    	vo.setEmp_no(no);
    	vo.setEmp_name(name);
    	vo.setBoard_date(textDate.getText());
    	if(tftitle.getText().isEmpty()||taCon.getText().isEmpty()) {
    		AlertUtil.errorMsg("입력오류", "제목,내용 둘 다 입력해주세요");
    		return; 
    	}
    	
    	//서버로 정보보내기
    	try {
			cnt=service.insertBoard(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
		}if(cnt>0) {
			AlertUtil.infoMsg("등록성공", "새로운 자유게시판 공지가 등록되었습니다.");
			backtoList();
		}
		
    	
    }



	@FXML
    void btncancelclick(ActionEvent event) throws IOException { //취소버튼클릭

		backtoList();
    	
    }
	void backtoList() throws IOException {

		//FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/notice/BoardList.fxml"));
		FXMLLoader loader = new FXMLLoader(BoardaddController.class.getResource("/fxml/notice/BoardList.fxml"));
		Parent root = loader.load();
        BoardListController ctr = loader.getController();
        ctr.setMain(main);
        main.getMainborder().setCenter(root);
		
	}
    String tfime1="";
    String name="";
    
    @FXML
    void initialize() {
    	//서버연결
    	try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			service = (IBoardService) reg.lookup("boardService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	//글작성시 작성날짜 오늘날짜로 미리보여주기
    	SimpleDateFormat format1=new SimpleDateFormat("yyyy/MM/dd");
    	Date time=new Date();
    	tfime1=format1.format(time);
    	textDate.setText(tfime1);
    	

    	String name= MainPageController.getLoginUser().getEmp_name();
    	textname.setText(name);
    	
    	
    	
    	

    }
}

