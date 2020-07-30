package main.notice;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.mainCtr.MainPageController;
import service.IBoardService;
import util.AlertUtil;
import vo.Boardemp_joinVO;

public class BoardDetailController {  
	int boardNO=0;
	private BoardListController bolist;
	
	
//	  private BoardVO boardVo;
//	  
//	  public BoardVO getBoardVo() { return boardVo; } 
//	  public void setBoardVo(BoardVO boardVo) { 
//	  this.boardVo = boardVo;
//	  tfBoardTitle.setText(boardVo.getBoard_title());
//	  tfBoardDate.setText(boardVo.getBoard_date()); tfBoardName.setText("홍길순");
//	  taBoardCon.setText(boardVo.getBoard_content()); }
	 
	
	public BoardListController getBolist() {
		return bolist;
	}
	public void setBolist(BoardListController bolist) {
		this.bolist = bolist;
	}
	
	private BoardaddController bolist2;
	

	public BoardaddController getBolist2() {
		return bolist2; 
	}
	public void setBolist2(BoardaddController bolist2) {
		this.bolist2 = bolist2;
	}
	
//	private Map<String, String> boardMap;
//	
//	public Map getBoardMap() {
//		return boardMap;
//	}
	  private MainPageController main;
	  public MainPageController getMain() {
	      return main;
	   }
	  public void setMain(MainPageController main) {
	      this.main = main;
	   }
	//private Boardemp_joinVO getboard;
	
	//List에서 선택된 게시글 상세보기 vo
	Boardemp_joinVO joinVO=new Boardemp_joinVO();
	
	public void setBoardVO(Boardemp_joinVO joinVO) {
		this.joinVO=joinVO;
		boardNO=joinVO.getBoard_no();	//null
		tfBoardTitle.setText((String) joinVO.getBoard_title());
		tfBoardDate.setText((String) joinVO.getBoard_date());
		tfBoardName.setText((String) joinVO.getEmp_name());
		taBoardCon.setText((String) joinVO.getBoard_content());
    	//세션 (로그인값이랑 다르면 수정,삭제버튼안보임) 
    	
		int loginUser= MainPageController.getLoginUser().getEmp_no();
    	int empNO = joinVO.getEmp_no();
        if(loginUser != empNO) {
        	btnDelete.setVisible(false);
        	btnEdit.setVisible(false);
        }
        
	} 


	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;	//자유게시판 상세열람

    @FXML
    private TextField tfBoardTitle;	//제목

    @FXML
    private TextField tfBoardDate;	//작성일

    @FXML
    private TextField tfBoardName; 	//작성자	

    @FXML
    private TextArea taBoardCon;	//내용

    @FXML
    private Button btnBack; //뒤로가기버튼

    @FXML
    private Button btnEdit;//수정하기버튼

    @FXML
    private Button btnDelete;//삭제버튼
    
    void backtoList() throws IOException {
    	//FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/notice/BoardList.fxml"));
    	FXMLLoader loader = new FXMLLoader(BoardEditController.class.getResource("/fxml/notice/BoardList.fxml"));
    	Parent root = loader.load();
        BoardListController ctr = loader.getController();
        ctr.setMain(main);
        
        main.getMainborder().setCenter(root);

    	
    }
    
    private IBoardService service;

    @FXML
    void btnEditClick(ActionEvent event) throws IOException {//수정하기버튼 클릭
    	int cnt=0;
    	if(btnEdit.getText().equals("완료")) {
    		joinVO.setBoard_title(tfBoardTitle.getText());
    		joinVO.setBoard_content(taBoardCon.getText());
    		joinVO.setBoard_no(boardNO);
    		
    		try {
    			cnt=service.updateBoard(joinVO);
				//service.updateBoard(joinVO);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
    		if(cnt>0) {
    			AlertUtil.infoMsg("수정완료", "수정되었습니다.");
    		}
    		
    		tfBoardTitle.setEditable(false);
    		taBoardCon.setEditable(false);
    		btnEdit.setText("수정");
    		btnDelete.setDisable(false);
    		
    	}else {
    		tfBoardTitle.setEditable(true);
    		taBoardCon.setEditable(true);
    		btnEdit.setText("완료");
    		btnDelete.setDisable(true);
    		
    	}
    	
    	
    	
    }	 
    	
    	
    	/*
		 * FXMLLoader loader= new
		 * FXMLLoader(BoardDetailController.class.getResource("../fxml/BoardEdit.fxml"))
		 * ; Parent root= loader.load(); Stage stage = (Stage)
		 * btnBack.getScene().getWindow();
		 * 
		 * Scene scene = new Scene(root); stage.setScene(scene);
		 * stage.setTitle("자유게시판 리스트"); stage.show();
		 * 
		 * 
		 * taBoardCon.setDisable(false); tfBoardTitle.setDisable(false);
		 * 
		 * 
		 * 
		 * //수정확인완료가되어서 버튼을 눌렀을때 하는 액션 BoardVO vo = new BoardVO();
		 * vo.setBoard_content(taBoardCon.getText());
		 * vo.setBoard_title(tfBoardTitle.getText());
		 * vo.setBoard_date(tfBoardDate.getText()); //int no =
		 * MainLayoutController.loginUser.getEmp_no(); int no = 202004001;
		 * vo.setEmp_no(no);
		 * if(tfBoardTitle.getText().isEmpty()||taBoardCon.getText().isEmpty()) {
		 * AlertUtil.errorMsg("입력오류", "제목, 내용을 입력해주세요"); return; }
		 * 
		 * try { service.updateBoard(vo); } catch (RemoteException e) {
		 * e.printStackTrace(); }
		 */
		 

    

    @FXML
    void btnbackclick(ActionEvent event) throws IOException {//뒤로가기버튼클릭
    	backtoList();
    	
    }

    @FXML
    void btndeleteClick(ActionEvent event) throws IOException {//삭제하기버튼클릭
    	if(AlertUtil.confirm("삭제여부", "정말로 삭제 하시겠습니까?"));{
    		
    		//Boardemp_joinVO vo =new Boardemp_joinVO();
    		//vo.setBoard_del(1);
    		int cnt=service.deleteBoard(boardNO);
    		if(cnt>0) {
    			AlertUtil.infoMsg("삭제성공", "게시글이 삭제되었습니다.");
    			backtoList();
    			
    		}else {
    			AlertUtil.infoMsg("삭제실패", "삭제 작업 실패");
    		}
    		
    	}
    	
    
    }
    
    @FXML
    void initialize() {

    	try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			service = (IBoardService) reg.lookup("boardService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

    }
}
