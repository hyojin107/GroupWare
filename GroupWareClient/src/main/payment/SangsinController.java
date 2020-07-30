package main.payment;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import main.mainCtr.MainLeftController;
import main.mainCtr.MainPageController;
import service.IAddressService;
import service.IEmployeeService;
import service.IPaymentService;
import service.ITempSaveService;
import util.AlertUtil;
import vo.DepartmentsVO;
import vo.Document_FormVO;
import vo.EmployeeVO;
import vo.ImgFileVO;
import vo.PaymentListVO;
import vo.PaymentVO;
import vo.Payment_LineVO;
import vo.ReferenceVO;
import vo.SangsinSearchVO;
import vo.Temp_SaveVO;

public class SangsinController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<Document_FormVO> combo;

	@FXML
	private Button btnReference;

	@FXML
	private TextField tfRef;

	@FXML
	private TextField tfTitle;

	@FXML
	private TextArea taContent;

	@FXML
	private TextField tfGroup;

	@FXML
	private TextField tfEmp;

	@FXML
	private TextField tfLine1;
	
	@FXML
	private Text textHead;

    @FXML
    private Text chk1;
    
    @FXML
    private Text chk2;
    
    @FXML
    private Text chk3;
    
	public Button getBtnSearch1() {
		return btnSearch1;
	}

	public void setBtnSearch1(Button btnSearch1) {
		this.btnSearch1 = btnSearch1;
	}

	public Button getBtnSearch2() {
		return btnSearch2;
	}

	public void setBtnSearch2(Button btnSearch2) {
		this.btnSearch2 = btnSearch2;
	}

	public Button getBtnSearch3() {
		return btnSearch3;
	}

	public void setBtnSearch3(Button btnSearch3) {
		this.btnSearch3 = btnSearch3;
	}

	@FXML
	private Button btnSearch1;

	@FXML
	private ImageView image1;

	@FXML
	private TextField tfLine2;

	@FXML
	private Button btnSearch2;

	@FXML
	private ImageView image2;

	@FXML
	private TextField tfLine3;

	@FXML
	private Button btnSearch3;

	public TextField getTfRef() {
		return tfRef;
	}

	public void setTfRef(TextField tfRef) {
		this.tfRef = tfRef;
	}

	public MainPageController getMain() {
		return main;
	}

	public void setMain(MainPageController main) {
		this.main = main;
	}

	public TextField getTfTitle() {
		return tfTitle;
	}

	public void setTfTitle(TextField tfTitle) {
		this.tfTitle = tfTitle;
	}

	public TextArea getTaContent() {
		return taContent;
	}

	public void setTaContent(TextArea taContent) {
		this.taContent = taContent;
	}

	public TextField getTfGroup() {
		return tfGroup;
	}

	public void setTfGroup(TextField tfGroup) {
		this.tfGroup = tfGroup;
	}

	public TextField getTfEmp() {
		return tfEmp;
	}

	public void setTfEmp(TextField tfEmp) {
		this.tfEmp = tfEmp;
	}

	public TextField getTfLine1() {
		return tfLine1;
	}

	public void setTfLine1(TextField tfLine1) {
		this.tfLine1 = tfLine1;
	}

	public ImageView getImage1() {
		return image1;
	}

	public void setImage1(ImageView image1) {
		this.image1 = image1;
	}

	public TextField getTfLine2() {
		return tfLine2;
	}

	public void setTfLine2(TextField tfLine2) {
		this.tfLine2 = tfLine2;
	}

	public ImageView getImage2() {
		return image2;
	}

	public void setImage2(ImageView image2) {
		this.image2 = image2;
	}

	public TextField getTfLine3() {
		return tfLine3;
	}

	public void setTfLine3(TextField tfLine3) {
		this.tfLine3 = tfLine3;
	}

	public ImageView getImage3() {
		return image3;
	}

	public void setImage3(ImageView image3) {
		this.image3 = image3;
	}

	@FXML
	private ImageView image3;

	@FXML
	public List<String> getRefNameList() {
		return refNameList;
	}

	public void setRefNameList(List<String> refNameList) {
		this.refNameList = refNameList;
	}

	public List<Integer> getReferenceList() {
		return referenceList;
	}

	public void setReferenceList(List<Integer> referenceList) {
		this.referenceList = referenceList;
	}

	public Integer[] getLineList() {
		return lineList;
	}

	public void setLineList(Integer[] lineList) {
		this.lineList = lineList;
	}

	@FXML
	private Button btnOk;

	@FXML
	private Button btnCancle;

	@FXML
	void btnReference(ActionEvent event) {
		showSearch(4);
	}

	private void showSearch(int x) {
		try {
			Stage parentStage = (Stage) tfEmp.getScene().getWindow();
			Stage searchStage = new Stage(StageStyle.DECORATED);
			searchStage.initModality(Modality.WINDOW_MODAL);
			searchStage.initOwner(parentStage);

			FXMLLoader loader = new FXMLLoader(SangsinController.class.getResource("/fxml/payment/SangsinSearch.fxml"));

			Parent root = loader.load();

			SangsinSearchController controller = loader.getController();

			controller.setController(this);
			controller.setX(x);

			Scene scene = new Scene(root);
			searchStage.setScene(scene);
			if (x == 4) {
				searchStage.setTitle("참조선 검색");
				controller.getBtnAdd().setVisible(true);
				controller.getBtnDel().setVisible(true);
				controller.getBtnQuit().setVisible(true);
			} else
				searchStage.setTitle("결재선 검색");
			searchStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void btnSearch1(ActionEvent event) {
		showSearch(1);
	}

	@FXML
	void btnSearch2(ActionEvent event) {
		showSearch(2);
	}

	@FXML
	void btnSearch3(ActionEvent event) {
		showSearch(3);
	}

	@FXML
	void comboClick(ActionEvent event) {

	}

	@FXML
	void cancle(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(SangsinController.class.getResource("/fxml/payment/payList.fxml"));
			Parent root = loader.load();

			PayListController control = loader.getController();

			control.setLeft(left);
			control.setMain(main);
			if (btnCancle.getText().equals("반려")) {
				Map<String, Integer> map = new HashMap<String, Integer>();

				map.put("pay_order", payOrder);
				map.put("pay_no", payVo.getPay_no());
				map.put("check", 2);
				// 반려하는 메서드
				service.Check(map);
				if (payOrder == 1) {
					// 문서상태 반려로 변경하는 메서드
					service.Check2(map);
				} else {
					map.replace("pay_order", payOrder - 1);
					map.replace("check", 0);
					service.Check(map);
					// 앞에 사람 0으로 변경하는 메서드
				}
				control.setRead(false);
			} else control.setRead(true);

			if(sa != null && !sa.isInterrupted()) {
				sa.interrupt();
				tempService.deleteTempSave(MainPageController.getLoginUser().getEmp_no());
			}
			main.getMainborder().setCenter(root);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void ok(ActionEvent event) {
		try {
		if(write) {
			if (taContent.getText().equals("") || tfTitle.getText().equals("") || combo.getValue() == null) {
				AlertUtil.errorMsg("작성오류", "입력하지 않은 값이 있습니다.");
				return;
			}
			for (int i : lineList) {
				if (i == 0) {
					AlertUtil.errorMsg("결재지정 오류", "결재선이 지정되지 않은 부분이 있습니다.");
					return;
				}
			}
				PaymentVO write = new PaymentVO();
				
				write.setDoc_no(combo.getValue().getDoc_no());
				write.setEmp_no(MainPageController.getLoginUser().getEmp_no());
				write.setPay_content(taContent.getText());
				write.setPay_title(tfTitle.getText());
				
				service.insertPay(write);
				// 방금 쓴거 no 받아오는 메서드
				int payNo = service.writeNo(MainPageController.getLoginUser().getEmp_no());
				System.out.println(MainPageController.getLoginUser().getEmp_no());
				
				Payment_LineVO lineVo = new Payment_LineVO();
				for (int i = 0; i < lineList.length; i++) {
					lineVo.setPay_order(i + 1);
					lineVo.setEmp_no(lineList[i]);
					lineVo.setPay_no(payNo);
					
					service.insertPayLine(lineVo);
				}
				
				for (int no : referenceList) {
					ReferenceVO ref = new ReferenceVO();
					ref.setPay_no(payNo);
					ref.setEmp_no(no);
					
					service.insertRef(ref);
				}
				
			}
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/payment/payList.fxml"));
			Parent root = loader.load();
			PayListController control = loader.getController();
			control.setMain(main);
			control.setLeft(left);
			if(btnOk.getText().equals("승인")) {
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("pay_no", payVo.getPay_no());
				map.put("check", 1);
				map.put("pay_order", payOrder);// 수정
				service.Check(map);
				if(payOrder == 3) service.Check2(map);
				else {
					map.replace("check", 0);
					map.replace("pay_order", payOrder + 1);
					service.Check(map);
				}
				control.setRead(false);
			}else control.setRead(true);
			if(sa != null && !sa.isInterrupted()) {
				sa.interrupt();
				tempService.deleteTempSave(MainPageController.getLoginUser().getEmp_no());
			}
			
			main.getMainborder().setCenter(root);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class Save extends Thread {

		@Override
		public void run() {
			Temp_SaveVO save = null;
			try {
				tempService.deleteTempSave(MainPageController.getLoginUser().getEmp_no());
				save = set();
				tempService.insertTempSave(save);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			while (true) {
				if(!left.getLeftName().getText().equals("전자결재")) continue;
				if(left.getLeft2().getStyle().equals("-fx-background-color:none;") &&
					left.getLeft4().getStyle().equals("-fx-background-color:none;")) continue;
				save = set();
				try {
					tempService.updateTempSave(save);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				try {
					sleep(10000);
				} catch (InterruptedException e) {
				}
			}
		}

		public Temp_SaveVO set() {
			Temp_SaveVO save = new Temp_SaveVO();
			if(combo.getSelectionModel().getSelectedItem() == null) save.setDoc_no(4);
			else save.setDoc_no(combo.getSelectionModel().getSelectedItem().getDoc_no());
			save.setEmp_no(MainPageController.getLoginUser().getEmp_no());
			String str = "";
			for (int i : lineList) {
				if (!str.equals(""))
					str += ",";
				str += i;
			}
			save.setPay_line(str);

			str = "";
			for (int i : referenceList) {
				if (!str.equals(""))
					str += ",";
				str += i;
			}
			save.setRef_line(str);

			save.setTemp_content(taContent.getText());
			save.setTemp_title(tfTitle.getText());
			return save;
		}

	}

	public void setRead(PaymentListVO vo) { // VO 아이바티스 고치기
		try {
			payVo = vo;
			write = false;

			List<SangsinSearchVO> list = service.getAllPayLineName(vo.getPay_no()); // 문서no로 결재선 모두 가져오는 메서드
			
			// payOrder에 현 접속자의 결재순서를 담는다
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getEmp_no() == MainPageController.getLoginUser().getEmp_no()) { // vo 아님
					payOrder = i + 1;
					break;
				}
			}
			
			for(int i = 0; i < btnList.length; i++) {
				btnList[i].setDisable(true);
				btnList[i].setText(list.get(i).getGrade_name());
				tfList[i].setText(list.get(i).getEmp_name());
				
				switch(list.get(i).getPay_check()) {
				case 1:
					ImgFileVO img = new ImgFileVO();
					img = empService.getImgFile(list.get(i).getEmp_no());	// 수정
						Image image = new Image(new ByteArrayInputStream(img.getEmp_sign()));	// 수정
						imgList[i].setImage(image);
					break;
				case 2:
					textList[i].setText("반려");
				}
			}
			
			btnCancle.setVisible(false);
			taContent.setDisable(true);
			tfTitle.setDisable(true);
			btnReference.setDisable(true);
			btnReference.setText("참조");
			combo.setVisible(false);
			for (Document_FormVO doc : combo.getItems()) {
				if (vo.getDoc_no() == doc.getDoc_no()) {
					textHead.setText(doc.getDoc_name());
					break;
				}
			}
			
			// 참조 추가
			list = service.getAllRef(vo.getPay_no());
			String str = "";
			for (SangsinSearchVO s : list) {
				if (!str.equals("")) str += ",";
				str += s.getEmp_name();
			}

			tfRef.setText(str);
			taContent.setText(vo.getPay_content());
			tfTitle.setText(vo.getPay_title());
			tfEmp.setText(vo.getEmp_name());

			// 모든 부서 가져오는 메서드
			List<DepartmentsVO> dList = addrService.getDepartment();
			for (DepartmentsVO d : dList)
				if (d.getDepartment_no() == vo.getDepartment_no()) 
					tfGroup.setText(d.getDepartment_name());

			btnCancle.setVisible(false);
			btnOk.setText("뒤로가기");

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void setCheck() {
		btnCancle.setVisible(true);
		btnOk.setVisible(true);
		btnCancle.setText("반려");
		btnOk.setText("승인");
	}

	// 결재선에 추가 인덱스, 사번
	public void lineSet(int i, int emp_no) {
		if(emp_no == 0) return;
		try {
			EmployeeVO vo = empService.getEmpLogin(emp_no);
			if(vo != null) {
				lineList[i] = emp_no;
				List<SangsinSearchVO> sang = service.searchName(vo.getEmp_name());
				for (SangsinSearchVO s : sang) {
					if (emp_no == s.getEmp_no()) {
						btnList[i].setText(s.getGrade_name());
						tfList[i].setText(vo.getEmp_name());
						break;
					}
				}
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setTemp() {
		try {

			Temp_SaveVO tmp = tempService.getTempSave(MainPageController.getLoginUser().getEmp_no());
			if(tmp == null) return;
			for (Document_FormVO vo : combo.getItems()) {
				if (tmp.getDoc_no() == vo.getDoc_no()) {
					combo.setValue(vo);
					break;
				}
			}
			
			String[] strLine = tmp.getPay_line().split(",");
			
			for(int i = 0; i < strLine.length; i++) {
				lineSet(i, Integer.parseInt(strLine[i]));
			}

			if (tmp.getRef_line() != null) {
				String[] refLine = tmp.getRef_line().split(",");
				String strRef = "";
				for (String s : refLine) {
					EmployeeVO empVo = empService.getEmpLogin(Integer.parseInt(s));
					refNameList.add(empVo.getEmp_name());
					referenceList.add(empVo.getEmp_no());
					if (!strRef.equals(""))
						strRef += ",";
					strRef += empVo.getEmp_name();
				}
				tfRef.setText(strRef);
			}

			taContent.setText(tmp.getTemp_content());
			tfTitle.setText(tmp.getTemp_title());

		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}

	public void setWrite() {
		try {
			write = true;
			List<DepartmentsVO> dList = addrService.getDepartment();
			for (DepartmentsVO d : dList)
				if (d.getDepartment_no() == MainPageController.getLoginUser().getDepartment_no()) {
					tfGroup.setText(d.getDepartment_name());
					break;
				}
			tfEmp.setText(MainPageController.getLoginUser().getEmp_name());
			if(sa != null && !sa.isInterrupted()) sa.interrupt(); 
				sa = new Save();
				sa.setDaemon(write);
				sa.start();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	private boolean write;
	private MainPageController main;
	private ITempSaveService tempService;
	private List<Integer> referenceList = new ArrayList<Integer>();
	private Integer[] lineList = { 0, 0, 0 };
	private List<String> refNameList = new ArrayList<String>();
	private IPaymentService service;
	private IEmployeeService empService;
	private IAddressService addrService;
	private PaymentListVO payVo;
	private static Save sa;
	private int payOrder;
	private TextField[] tfList;
	private Text[] textList;
	private Button[] btnList;
	private ImageView[] imgList;
	private MainLeftController left;
	
	public MainLeftController getLeft() {
		return left;
	}

	public void setLeft(MainLeftController left) {
		this.left = left;
	}

	@FXML
	void initialize() {
		textList = new Text[]{chk1,chk2,chk3};
		tfList = new TextField[] {tfLine1,tfLine2,tfLine3};
		btnList = new Button[] {btnSearch1, btnSearch2, btnSearch3};
		imgList = new ImageView[] {image1,image2,image3};
		
		try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			service = (IPaymentService) reg.lookup("payService");
			tempService = (ITempSaveService) reg.lookup("tempService");
			empService = (IEmployeeService) reg.lookup("empService");
			addrService = (IAddressService) reg.lookup("addrService");

			List<Document_FormVO> docuList = service.getDocument();

//			for(Document_FormVO vo : docuList) combo.getItems().add(vo);
			combo.setItems(FXCollections.observableArrayList(docuList)); // 문서 Vo 넣기 4,5
			
			combo.setCellFactory(new Callback<ListView<Document_FormVO>, ListCell<Document_FormVO>>() {
				
				@Override
				public ListCell<Document_FormVO> call(ListView<Document_FormVO> param) {
					return new ListCell<Document_FormVO>() {
						@Override
						protected void updateItem(Document_FormVO item, boolean empty) {
							super.updateItem(item, empty);
							if(item==null || empty) setText(null);
							else setText(item.getDoc_name());
						}
					};
				}
			});
    		
        	combo.setButtonCell(new ListCell<Document_FormVO>() {
    			@Override
    			protected void updateItem(Document_FormVO item, boolean empty) {
    				super.updateItem(item, empty);
    				if(item==null||empty) setText(null);
    				else setText(item.getDoc_name());
    			}
    		});
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}


}
