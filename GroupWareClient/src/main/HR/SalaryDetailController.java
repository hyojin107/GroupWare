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
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import main.mainCtr.MainPageController;
import service.ISalarySpecsService;
import vo.Salary_SpecsVO;

public class SalaryDetailController {
	//필요한애들 
	private ISalarySpecsService service;
	private List<Salary_SpecsVO> list;
	private SalaryListController sal;
	
	String userName= MainPageController.getLoginUser().getEmp_name();
	
	private MainPageController main;
    public MainPageController getMain() {
		return main;
	}
	public void setMain(MainPageController main) {
		this.main = main;
	}
    public SalaryListController getSal() {
		return sal;
	}
	public void setSal(SalaryListController sal) {
		this.sal = sal;
	}
	
	
	Salary_SpecsVO salVO = new Salary_SpecsVO();
	public void salVO(Salary_SpecsVO salVO) {
		this.salVO = salVO;
		tName.setText(userName);
		tDate.setText(salVO.getSalYear()+"년"+salVO.getSalMonth()+"월");
		
		int total = salVO.getSalary()*1000; //3천만원
		int realwage = total/12;
		int wage = total/12-100000; //비과세액 10만원 제외 
		int text4 = (int) (wage*0.045); //108.000원
		int text5 = (int) (wage*0.03335);
		int text6 = (int) (text5 *0.1025);
		int text7 = (int) (wage*0.008);
		int text8 = (int) (wage*0.01345);
		int text9 = (int) (text8*0.1);
		
		System.out.println(text4+"/"+text5+"/"+text6+"/"+text7+"/"+text8+"/"+text9+"/");
		int subtotal = text4+text5+text7+text8; //공제 총액
		int text1 = realwage - subtotal; //실수령액
		
		this.text1.setText(realwage+"");
		this.text2.setText("-");
		this.text3.setText("-");
		this.text4.setText(text4+"");
		this.text5.setText(text5+"");
		this.text6.setText(text6+"");
		this.text7.setText(text7+"");
		this.text8.setText(text8+"");
		this.text9.setText(text9+"");
		this.total.setText(text1+" 원");
		
		
	}
		
    @FXML
    private Text tName;

    @FXML
    private Text tDate;

    @FXML
    private Text text1;

    @FXML
    private Text text2;

    @FXML
    private Text text3;

    @FXML
    private Text text4;

    @FXML
    private Text text5;

    @FXML
    private Text text6;

    @FXML
    private Text text7;

    @FXML
    private Text text8;

    @FXML
    private Text text9;

    @FXML
    private Text text10;

    @FXML
    private Text total;

    @FXML
    private Button btnBack;


    @FXML
    void btnBackClick(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(SalaryListController.class.getResource("/fxml/HR/SalaryList.fxml"));
		Parent root = loader.load();
		SalaryListController Ctr = loader.getController();
		Ctr.setMain(main);
		main.getMainborder().setCenter(root);
    }
    
    
    void getSalaryDetail() {
    	
    	
    	
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
    	

    	
    	
    	
    	
       }

}
