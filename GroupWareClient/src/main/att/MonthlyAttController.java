package main.att;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.mainCtr.MainPageController;
import service.IAttendanceService;
import service.IEmployeeService;
import vo.AttendanceVO;
import vo.EmployeeVO;
import vo.MonthlyAttVO;

public class MonthlyAttController {

    @FXML
    private ResourceBundle resources;
    
    @FXML
    private Text head;
    
    @FXML
    private URL location;

    public Text getHead() {
		return head;
	}

	public void setHead(Text head) {
		this.head = head;
	}

	@FXML
    private TableView<MonthlyAttVO> table;

    @FXML
    private TableColumn<?, ?> colMonth;

    @FXML
    private TableColumn<?, ?> colWork;

    @FXML
    private TableColumn<?, ?> colOutMiss;

    @FXML
    private TableColumn<?, ?> colLate;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableColumn<?, ?> colJan;

    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<Integer> combo;

    public boolean isInsa() {
		return insa;
	}

	public void setInsa(boolean insa) {
		this.insa = insa;
	}

	@FXML
    void back(ActionEvent event) {
    	try {
    		String url;
    		if(insa) url = "/fxml/att/MonthlyAtt2.fxml";
    		else url = "/fxml/att/AttCalendar.fxml";
			
    		FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
    		Parent root = loader.load();
			
    		if(insa) {
    			MonthlyAtt2Controller con = loader.getController();
    			con.setMain(main);
    			main.getMainborder().setCenter(root);
    		}else {
    			AttCalendarController con = loader.getController();
    			con.setMain(main);
    			main.getMainborder().setCenter(root);
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    }

    @FXML
    void change(ActionEvent event) {
    	show();
    }
    
    private void show() {
    	try {
    		
    		if(combo.getValue() == null) return;
    		table.getItems().clear();
	    	Map<String, Integer> map = new HashMap<String, Integer>();
	    	map.put("emp_no", emp_no); // 바꾸기
	    	map.put("year", combo.getValue());
	    	
			List<AttendanceVO> list = service.getYear(map);
			for(int i = 1; i <= 12; i++) {
				MonthlyAttVO vo = new MonthlyAttVO();
				vo.setMonth(i);
				
				int cnt = 0;
				int late = 0;
				int miss = 0;
				int work = 0;
				int jan = 0;
				for(int j = 0; j < list.size(); j++) {
					String start = list.get(j).getAtt_start();
					String end = list.get(j).getAtt_end();
					if(Integer.parseInt(start.substring(0,2)) == i) {
						cnt++;
						if(Integer.parseInt(start.substring(3)) >= 8) late++;
						if(end == null) {
							miss++;
							work += 17 - Integer.parseInt(start.substring(3));
						}else {
							work += Integer.parseInt(end.substring(3)) - Integer.parseInt(start.substring(3));
							if(Integer.parseInt(end.substring(3)) > 19) jan += Integer.parseInt(end.substring(3)) - 19;
						}
					}
				}
				vo.setJan(jan);
				vo.setLate(late);
				vo.setOutMiss(miss);
				vo.setWork(cnt);
				vo.setWorkTime(work);
				
				table.getItems().add(vo);
			}
    	} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		try {
			this.emp_no = emp_no;
		
			EmployeeVO vo = empService.getEmpLogin(emp_no);
			
			combo.getItems().clear();
			System.out.println(Calendar.getInstance().getWeekYear());
			for(int i = Integer.parseInt(vo.getEmp_hire_date().substring(0,4)); i <= Calendar.getInstance().getWeekYear(); i++)
			combo.getItems().add(i);
			
			combo.setValue(Calendar.getInstance().getWeekYear());
			show();
		} catch (RemoteException e) {
			e.printStackTrace();
		} // 수정
	}

	private int emp_no;
    private IAttendanceService service;
    private IEmployeeService empService;
    private MainPageController main;
    public MainPageController getMain() {
		return main;
	}

	public void setMain(MainPageController main) {
		this.main = main;
	}

	private boolean insa;
    @FXML
    void initialize() {
    	try {
    		Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
    		service =  (IAttendanceService) reg.lookup("attService");
			empService = (IEmployeeService) reg.lookup("empService");
			
			combo.setCellFactory(new Callback<ListView<Integer>, ListCell<Integer>>() {
				
				@Override
				public ListCell<Integer> call(ListView<Integer> param) {
					return new ListCell<Integer>() {
						@Override
						protected void updateItem(Integer item, boolean empty) {
							super.updateItem(item, empty);
							if(item==null || empty) setText(null);
							else setText(item + "년");
						}
					};
				}
			});
    		
        	combo.setButtonCell(new ListCell<Integer>() {
    			@Override
    			protected void updateItem(Integer item, boolean empty) {
    				super.updateItem(item, empty);
    				if(item==null||empty) setText(null);
    				else setText(item + "년");
    			}
    		});

        	colJan.setCellValueFactory(new PropertyValueFactory<>("jan"));
        	colLate.setCellValueFactory(new PropertyValueFactory<>("late"));
        	colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        	colOutMiss.setCellValueFactory(new PropertyValueFactory<>("outMiss"));
        	colTime.setCellValueFactory(new PropertyValueFactory<>("workTime"));
        	colWork.setCellValueFactory(new PropertyValueFactory<>("work"));
			
			
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}

    }
}
