package main.payment;

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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import main.email.GetEmailHamController;
import main.mainCtr.MainLeftController;
import main.mainCtr.MainPageController;
import service.IPaymentService;
import vo.EmployeeVO;
import vo.PaymentListVO;
import vo.Payment_LineVO;

public class PayListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<PaymentListVO> table;

    @FXML
    private TableColumn<?, ?> colNo;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colState;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private Pagination page;

    @FXML
    private ComboBox<Integer> combo;
	
    @FXML
    void showPayment(MouseEvent event) {
    	item = table.getSelectionModel().getSelectedItem();
    	if(item == null) return;
    	
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/payment/Sangsin.fxml"));
			Parent root = loader.load();
			SangsinController ctr = loader.getController();
			
			main.getMainborder().setCenter(root);
			ctr.setMain(main);
			ctr.setRead(item);
			if(!read) ctr.setCheck();
			
    		
    		main.getMainborder().setCenter(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    void comboClick(ActionEvent event) {
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	map.put("emp_no", loginUser.getEmp_no());
    	map.put("pay_state", combo.getValue());
    	
    	try {
			list = service.getComboPay(map);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	paging();
    }

	void paging() {
		if(list == null) return;
		totalCount = list.size();
		pageCount = (int) Math.ceil((double)totalCount / rowPerPage);
		
		page.setPageCount(pageCount);	
		page.setCurrentPageIndex(0);	
		
		changeTableView(0);
	}
    
	private void changeTableView(int index) {
		int start = index * rowPerPage;		
		int end = Math.min(start + rowPerPage, totalCount);	
		
//		view = list.subList(start, end);
		table.getItems().clear();
		
//		for(int i = start; i < end; i++) table.getItems().add(view.get(i)); 
		for(int i = start; i < end; i++) table.getItems().add(list.get(i)); 
	}
	
	public void setRead(boolean read) {
		int emp_no = loginUser.getEmp_no();
		try {
			this.read = read;
			if(read) list = service.getAllPay(emp_no);
			else { // 내 결재대기 상태인 놈만 들고오는 메서드 필요 문서 번호 넣기
				combo.setVisible(false);
				List<Payment_LineVO> p = service.getCheckList(emp_no); // 결재중인거 라인 모두 들고오는 메서드로 변경
				list = new ArrayList<PaymentListVO>();
				int x = 1;
				for(Payment_LineVO vo : p) {
					if(vo.getPay_check() == 0) {
						if(vo.getEmp_no() == emp_no && x != 0) {
							PaymentListVO payvo = service.getPayListNo(vo.getPay_no()); // 문서번호로 가져오는 메서드 필요
							list.add(payvo);
						}
						x = 0;
					}
					if(vo.getPay_order() == 3) x = 1;
				}
			}
		paging();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	private EmployeeVO loginUser;
    private IPaymentService service;
    private List<PaymentListVO> list;
//    private List<PaymentListVO> view;
    private PaymentListVO item; 
    private int rowPerPage = 10;	
	private int totalCount;			
	private int pageCount;	
	private boolean read;
	private MainPageController main;
	private MainLeftController left;
	
    public MainLeftController getLeft() {
		return left;
	}

	public void setLeft(MainLeftController left) {
		this.left = left;
	}

	public MainPageController getMain() {
		return main;
	}

	public void setMain(MainPageController main) {
		this.main = main;
	}

	@FXML
    void initialize() {
		loginUser = main.getLoginUser();

    	try {
    		Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
    		
    		service = (IPaymentService) reg.lookup("payService");
    		
    		page.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
    			@Override
    			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
    				changeTableView(newValue.intValue());
    			}
    		});
			
    		colNo.setCellValueFactory(new PropertyValueFactory<>("pay_no"));
        	colTitle.setCellValueFactory(new PropertyValueFactory<>("pay_title"));
        	colName.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
        	colState.setCellValueFactory(new PropertyValueFactory<>("pay_state"));
        	colDate.setCellValueFactory(new PropertyValueFactory<>("pay_date"));
    		
        	combo.setItems(FXCollections.observableArrayList(0, 1, 2));
        	
        	combo.setCellFactory(new Callback<ListView<Integer>, ListCell<Integer>>() {
    			
    			@Override
    			public ListCell<Integer> call(ListView<Integer> param) {
    				return new ListCell<Integer>() {
    					@Override
    					protected void updateItem(Integer item, boolean empty) {
    						super.updateItem(item, empty);
    						if(item==null || empty) {
    							setText(null);
    						}else if(item == 0){
    							setText("진행중");
    						}else if(item == 1) {
    							setText("승인 완료");
    						}else setText("반려");
    					}
    				};
    			}
    		});
    		
        	combo.setButtonCell(new ListCell<Integer>() {
    			@Override
    			protected void updateItem(Integer item, boolean empty) {
    				super.updateItem(item, empty);
    				if(item==null||empty) {
    					setText(null);
    				}else if(item == 0){
    					setText("진행중");
    				}else if(item == 1) {
						setText("승인 완료");
					}else setText("반려");
    			}
    		});
    		
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	

    }
}
