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

import com.sun.javafx.css.parser.LadderConverter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.mainCtr.MainPageController;
import service.IAttendanceService;
import vo.AttAllEmpVO;
import vo.AttendanceVO;
import vo.DepartmentsVO;
import vo.EmployeeVO;
import vo.MonthlyAttVO;

public class MonthlyAtt2Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<AttAllEmpVO> table;

    @FXML
    private TableColumn<?, ?> colYear;

    @FXML
    private TableColumn<?, ?> colDep;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colWork;

    @FXML
    private TableColumn<?, ?> colLate;

    @FXML
    private TableColumn<?, ?> colMiss;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<Integer> comboYear;

    @FXML
    private ComboBox<DepartmentsVO> comboDepart;

    @FXML
    void back(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/att/AttCalendar.fxml"));
    		Parent root = loader.load();
			
    		AttCalendarController con = loader.getController();
    		con.setMain(main);
			main.getMainborder().setCenter(root);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void changeDep(ActionEvent event) {
    	if(comboYear.getValue() == null) return;
    	table.getItems().clear();
    	show();
    }

    @FXML
    void changeYear(ActionEvent event) {
    	if(comboDepart.getValue() == null) return;
    	table.getItems().clear();
    	show();
    }
    
    @FXML
    void tbClick(MouseEvent event) {
    	try {
    		AttAllEmpVO item = table.getSelectionModel().getSelectedItem();
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/att/MonthlyAtt.fxml"));
			Parent root = loader.load();

			MonthlyAttController controller = loader.getController();
			
			controller.setMain(main);
			controller.setInsa(true);
			controller.setEmp_no(item.getEmp_no());
			controller.getHead().setText(item.getEmp_name() + "님의 월별 근태현황");
			
			
			main.getMainborder().setCenter(root);
			
    	} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    void show() {
    	int year = comboYear.getValue();
    	int department_no = comboDepart.getValue().getDepartment_no();
    	
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	map.put("year", year);
    	map.put("department_no", department_no);
    		
    	try {
			List<AttendanceVO> list = service.getYearDep(map);
			List<EmployeeVO> empList = service.getDepEmp(department_no);
			
			for(EmployeeVO empVo : empList) {
				AttAllEmpVO vo = new AttAllEmpVO();
				vo.setYear(year);
				vo.setDepartment_name(comboDepart.getValue().getDepartment_name());
				vo.setEmp_name(empVo.getEmp_name());
				vo.setEmp_no(empVo.getEmp_no());

				int cnt = 0;
				int late = 0;
				int miss = 0;
				int work = 0;
				
				for(AttendanceVO attVo : list) {
					if(attVo.getEmp_no() == empVo.getEmp_no()) {
						String start = attVo.getAtt_start();
						String end = attVo.getAtt_end();
						cnt++;
						if(Integer.parseInt(start) >= 8) late++;
						if(end == null) {
							miss++;
							work += 17 - Integer.parseInt(start);
						}else work += Integer.parseInt(end) - Integer.parseInt(start);
					}
				}
				vo.setLate(late);
				vo.setMiss(miss);
				vo.setWork(cnt);
				vo.setTime(work);
				
				table.getItems().add(vo);
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    MainPageController main;
    IAttendanceService service;
    public MainPageController getMain() {
		return main;
	}

	public void setMain(MainPageController main) {
		this.main = main;
	}

	@FXML
    void initialize() {
    	try {
    		Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
    		service = (IAttendanceService) reg.lookup("attService");
			
			List<DepartmentsVO> list = service.getDepartment();
			for(DepartmentsVO vo : list)
				comboDepart.getItems().add(vo);
			for(int i = 2019; i <= Calendar.getInstance().getWeekYear(); i++) 
				comboYear.getItems().add(i);

			comboYear.setCellFactory(new Callback<ListView<Integer>, ListCell<Integer>>() {
				
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
    		
        	comboYear.setButtonCell(new ListCell<Integer>() {
    			@Override
    			protected void updateItem(Integer item, boolean empty) {
    				super.updateItem(item, empty);
    				if(item==null||empty) setText(null);
    				else setText(item + "년");
    			}
    		});
        	
        	comboDepart.setCellFactory(new Callback<ListView<DepartmentsVO>, ListCell<DepartmentsVO>>() {
				
				@Override
				public ListCell<DepartmentsVO> call(ListView<DepartmentsVO> param) {
					return new ListCell<DepartmentsVO>() {
						@Override
						protected void updateItem(DepartmentsVO item, boolean empty) {
							super.updateItem(item, empty);
							if(item == null ||empty) setText(null);
							else setText(item.getDepartment_name());
						}
					};
				}
			});
        	
        	comboDepart.setButtonCell(new ListCell<DepartmentsVO>() {
        		@Override
        		protected void updateItem(DepartmentsVO item, boolean empty) {
        			super.updateItem(item, empty);
        			if(item == null || empty) setText(null);
        			else setText(item.getDepartment_name());
        		}
        	});
			
        	colDep.setCellValueFactory(new PropertyValueFactory<>("department_name"));
        	colLate.setCellValueFactory(new PropertyValueFactory<>("late"));
        	colMiss.setCellValueFactory(new PropertyValueFactory<>("miss"));
        	colName.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
        	colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        	colWork.setCellValueFactory(new PropertyValueFactory<>("work"));
        	colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        	
			
    	} catch (RemoteException e) {
    		e.printStackTrace();
    	} catch (NotBoundException e) {
			e.printStackTrace();
		}
    }
}
