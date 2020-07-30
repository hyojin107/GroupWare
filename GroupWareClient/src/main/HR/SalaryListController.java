package main.HR;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.mainCtr.MainPageController;
import service.ISalarySpecsService;
import vo.Salary_SpecsVO;

public class SalaryListController { 
	//필요한애들
	private ISalarySpecsService service;
	private List<Salary_SpecsVO> list;
	private Salary_SpecsVO vo;

	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TableView<Salary_SpecsVO> tbSalary;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private ComboBox<String> comboYear;

    private MainPageController main;
    public MainPageController getMain() {
      return main;
   }
   public void setMain(MainPageController main) {
      this.main = main;
   }
   private Salary_SpecsVO item; 
   @FXML
   void tbclick(MouseEvent event) {
	   	if(tbSalary.getSelectionModel().isEmpty()) {
    		return;
    	}
    	try {
    		FXMLLoader loader = new FXMLLoader(SalaryDetailController.class.getResource("/fxml/HR/SalaryDetail.fxml"));
			Parent root = loader.load();
			SalaryDetailController Ctr = loader.getController();
			item = tbSalary.getSelectionModel().getSelectedItem();
			Ctr.setSal(this);
			Ctr.salVO(item);
    		Ctr.setMain(main);
    		main.getMainborder().setCenter(root);

    	} catch (IOException e) {
			e.printStackTrace();
		}
   }
   
   @FXML
   void comboClick(ActionEvent event) {
	   tbSalary.getItems().clear();
	   for(int i =0; i<list.size(); i++) {
   		 if(list.get(i).getSalYear().equals(comboYear.getValue()))
   		tbSalary.getItems().addAll(list.get(i));
   	}
   }
   
    @FXML
    void initialize() {
    	
    	try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			service = (ISalarySpecsService) reg.lookup("salService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	//세션 emp_no를 넘겨서 해당하는 급여명세서 데려오기
    	int emp_no= MainPageController.getLoginUser().getEmp_no();
    	try {
			list=service.getAllSalary(emp_no);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
   	
    	colTitle.setCellValueFactory(new PropertyValueFactory<>("sal_title"));
    	colName.setCellValueFactory(new PropertyValueFactory<>("sal_name"));
    	
    	comboYear.getItems().addAll("2018","2019","2020");
    	// 테이블뷰와 콤보박스에 값 담아주기
    	for(int i =0; i<list.size(); i++) {
    		//콤보박스에 담아주기 
    		tbSalary.getItems().add(list.get(i));
    	}

    }
}
