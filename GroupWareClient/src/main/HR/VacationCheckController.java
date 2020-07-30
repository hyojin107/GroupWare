package main.HR;

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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.mainCtr.MainPageController;
import service.IPaymentService;
import service.IVacationService;
import vo.EmployeeVO;
import vo.PaymentListVO;
import vo.Payment_LineVO;
import vo.VacationCheckVO;

public class VacationCheckController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<VacationCheckVO> table;

    @FXML
    private TableColumn<?, ?> colNo;

    @FXML
    private TableColumn<?, ?> colDep;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colStart;

    @FXML
    private TableColumn<?, ?> colEnd;

    @FXML
    private TableColumn<?, ?> colReason;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancle;
    
    private MainPageController main;
    public MainPageController getMain() {
        return main;
    }
    public void setMain(MainPageController main) {
        this.main = main;
    }

    @FXML
    void cancle(ActionEvent event) {
    	VacationCheckVO item = table.getSelectionModel().getSelectedItem();
    	
    	if(item == null) return;
    	Map<String, Integer> map = new HashMap<String, Integer>();

    	try {
			map.put("pay_order", item.getPay_order());
			map.put("pay_no", item.getPay_no());
			map.put("check", 2);
			// 반려하는 메서드
				payService.Check(map);
			if (item.getPay_order() == 1) {
				// 문서상태 반려로 변경하는 메서드
				payService.Check2(map);
			} else {
				map.replace("pay_order", item.getPay_order() - 1);
				map.replace("check", 0);
				payService.Check(map);
				// 앞에 사람 0으로 변경하는 메서드
			}
    	} catch (RemoteException e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void ok(ActionEvent event) {
    	VacationCheckVO item = table.getSelectionModel().getSelectedItem();
    	
    	if(item == null) return;
    	System.out.println(item);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("pay_no", item.getPay_no());
		map.put("check", 1);
		map.put("pay_order", item.getPay_order());// 수정
		try {
			payService.Check(map);
			if(item.getPay_order() == 3) payService.Check2(map);
			else {
				map.replace("check", 0);
				map.replace("pay_order", item.getPay_order() + 1);
				payService.Check(map);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		initialize();
    }

    private IVacationService service;
    private EmployeeVO loginUser;
    private IPaymentService payService;
    
    @FXML
    void initialize() {
    	loginUser = MainPageController.getLoginUser();
		try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
	    	service = (IVacationService) reg.lookup("vacService");
	    	payService = (IPaymentService) reg.lookup("payService");
	    	
	    	set();
	    	
	        colNo.setCellValueFactory(new PropertyValueFactory<>("pay_no"));
	        colDep.setCellValueFactory(new PropertyValueFactory<>("department_name"));
	        colName.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
	        colStart.setCellValueFactory(new PropertyValueFactory<>("vac_start_date"));
	        colEnd.setCellValueFactory(new PropertyValueFactory<>("vac_end_date"));
	        colReason.setCellValueFactory(new PropertyValueFactory<>("pay_content"));
        
        
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    }

	private void set() {
		table.getItems().clear();
		List<Payment_LineVO> check;
		try {
			check = service.getCheckVacList(loginUser.getEmp_no());
			List<VacationCheckVO>list = new ArrayList<VacationCheckVO>();
			int x = 1;
			for(Payment_LineVO vo : check) {
				if(vo.getPay_check() == 0) {
					if(vo.getEmp_no() == loginUser.getEmp_no() && x != 0) {
						VacationCheckVO vacvo = service.getVacListNo(vo.getPay_no()); // 문서번호로 가져오는 메서드 필요
						vacvo.setPay_order(vo.getPay_order());
						table.getItems().add(vacvo);
					}
					x = 0;
				}
				if(vo.getPay_order() == 3) x = 1;
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		
	}
}


