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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.mainCtr.MainPageController;
import service.IBoardService;
import util.AlertUtil;
import vo.BoardVO;
import vo.Boardemp_joinVO;
import vo.EmployeeVO;

public class BoardEditController { 
	private IBoardService service;
	private Map<String, String>boardMap;
	
	private BoardDetailController bolist3;
	

    public BoardDetailController getBolist3() {
		return bolist3; 
	}

	public void setBolist3(BoardDetailController bolist3) {
		this.bolist3 = bolist3;
	}
	
	Boardemp_joinVO joinVO=new Boardemp_joinVO();
	public void setBoardVO(Boardemp_joinVO joinVO) {
		this.joinVO=joinVO;
		tfTitle.setText((String) joinVO.getBoard_title());
		tDate.setText((String) joinVO.getBoard_date());
		tfName.setText((String) joinVO.getEmp_name());
		tfCon.setText((String) joinVO.getBoard_content());
		
	}

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea tfCon;

    @FXML
    private TextField tfTitle;

    @FXML
    private Label tDate;

    @FXML
    private Label tfName;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnCancel;

    //private EmployeeVO loginUser;
    private MainPageController main;
    public MainPageController getMain() {
      return main;
   }
   public void setMain(MainPageController main) {
      this.main = main;
   }
    @FXML //수정완료버튼 
    void btnAddClick(ActionEvent event) throws IOException {
		/*
		 * update board set board_title=#board_title#,board_date=sysdate,
		 * board_content=#board_content# where board_no = #board_no#
		 */
    	Boardemp_joinVO vo=new Boardemp_joinVO();
    	vo.setBoard_title(tfTitle.getText());
    	vo.setBoard_content(tfCon.getText());
    	//vo.setBoard_no(joinVO.getBoard_no());
    	int loginUser=MainPageController.getLoginUser().getEmp_no();
    	vo.setEmp_no(loginUser);
    	vo.setBoard_no(joinVO.getBoard_no());
    	if(tfTitle.getText().isEmpty()||tfCon.getText().isEmpty()) {
    		AlertUtil.errorMsg("입력오류", "제목,내용 둘 다 입력해주세요");
    		return;
    	}
    	
    	try {
			service.updateBoard(vo);
		} catch (RemoteException e) {
			e.printStackTrace();
		} 

    	//FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/notice/BoardDetail.fxml"));
    	FXMLLoader loader = new FXMLLoader(BoardEditController.class.getResource("/fxml/notice/BoardDetail.fxml"));
    	Parent root = loader.load();
        BoardDetailController ctr = loader.getController();
        ctr.setMain(main);
        main.getMainborder().setCenter(root);

    }

    @FXML
    void btnCancel(ActionEvent event) throws IOException {
    	//FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/notice/BoardList.fxml"));
    	FXMLLoader loader = new FXMLLoader(BoardEditController.class.getResource("/fxml/notice/BoardList.fxml"));
    	Parent root = loader.load();
        BoardListController ctr = loader.getController();
        ctr.setMain(main);
        main.getMainborder().setCenter(root);
    	

    }

    @FXML
    void initialize() {
    	
    	try {
			Registry reg=LocateRegistry.getRegistry("192.168.0.117", 9999);
			service=(IBoardService)reg.lookup("boardService");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}catch (NotBoundException e) {
			e.printStackTrace();
		}
    	

    }
}
