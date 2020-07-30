package main.att;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.mainCtr.MainPageController;
import service.IAttendanceService;
import vo.AttendanceVO;


public class AttCalendarController {
	private IAttendanceService service;
	private List<AttendanceVO> calList;
	Calendar cal = Calendar.getInstance();

    private int nYear,	
    		    nMonth;
	private int startDay,	
	            lastDay,
	            inputDate;  //1일
    
	static String clickDate;
	static int clickYear,
			   clickMonth;
	//get
	public String getClickDate() {
		return clickDate;
	}

	Label[] lableList;
	HBox[] hboxList;
    @FXML
    private ResourceBundle resources;

    @FXML
    private GridPane grid;
    
    @FXML
    private URL location;
    
    @FXML
    private HBox hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, hbox8, hbox9, hbox10 ,hbox11 ,hbox12
    			 ,hbox13, hbox14, hbox15, hbox16, hbox17, hbox18, hbox19, hbox20, hbox21, hbox22, hbox23, hbox24, hbox25, hbox26, hbox27
    			 ,hbox28, hbox29,  hbox30, hbox31, hbox32, hbox33, hbox34, hbox35, hbox36, hbox37, hbox38, hbox39;
    
    @FXML
    private Label lbl00, lbl01, lbl02 ,lbl03, lbl04 ,lbl05 ,lbl06 ,lbl10, lbl11, lbl12 ,lbl13 ,lbl14 ,lbl15
    			 ,lbl16 ,lbl20 ,lbl21 ,lbl22 ,lbl23 ,lbl24 ,lbl25 ,lbl26 ,lbl30 ,lbl31, lbl32, lbl33
    			 ,lbl34, lbl35 ,lbl36 ,lbl40 ,lbl41 ,lbl42 ,lbl43 ,lbl44 ,lbl45 ,lbl46 ,lbl50 ,lbl51 ,lbl52, lbl54 ,lbl55 ,lbl56 ,lbl53;
    
    @FXML
    private Button btnAdd;
    
    @FXML
    private Button btnBMonth;

    @FXML
    private Button btnToday;

    @FXML
    private Button btnNMonth;
    
    @FXML
    private Button btnAll;
    
    @FXML
    private Text textHead;

    @FXML
    private Button bntMyAtt;
    
    private MainPageController main;
    public MainPageController getMain() {
		return main;
	}
	public void setMain(MainPageController main) {
		this.main = main;
		if(MainPageController.getLoginUser().getDepartment_no() == 1) btnAll.setVisible(true);
	}

    @FXML
    void allAtt(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/att/MonthlyAtt2.fxml"));
    	
			Parent root = loader.load();
			
			MonthlyAtt2Controller con = loader.getController();
			
			con.setMain(main);
			
			main.getMainborder().setCenter(root);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void myAtt(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/att/MonthlyAtt.fxml"));
    		
			Parent root = loader.load();
			
			MonthlyAttController con = loader.getController();
			
			con.setMain(main);
			con.setEmp_no(main.getLoginUser().getEmp_no());
			
			main.getMainborder().setCenter(root);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void addclcik(ActionEvent event) throws IOException {
//    	//새창 
//    	Parent add = FXMLLoader.load(getClass().getResource("../../fxml/plan/PlanDetail.fxml"));
//    	Scene scene = new Scene(add);
//    	
//    	Stage adding = new Stage();
//    	
//    	adding.setScene(scene);
//    	adding.showAndWait();
//    	
//    	//insert후 changeCalendar()부르기
//    	today();
//    	changeCalendar(nYear, nMonth);
    	
    	
//    	 FXMLLoader loader = new FXMLLoader(PlanListController.class.getResource("../../fxml/plan/PlanDetail.fxml"));
//         Parent root = loader.load();
//         Stage stage = (Stage) btnAdd.getScene().getWindow();
//         
//         //컨트롤 통로 만들어줌 
//         PlanDetailController addclicked = loader.getController();
//         //detail의 게터세터를 이용해서 
//         addclicked.getDetailHead().setText("일 정 등 록");;
//
//         Scene scene = new Scene (root);
//         stage.setScene(scene);
//         stage.setTitle("일정등록"); 
//         stage.show();
    }
    
    @FXML
    void bMonthClick(ActionEvent event) {
    	if(nMonth==0) {
    		nMonth = 11;
    		nYear -=1;
    	}else {
    		nMonth -=1;
    	}
    	changeCalendar(nYear, nMonth);
    }

    @FXML
    void nMonthClick(ActionEvent event) {
    	if(nMonth==11) {
    		nMonth =0;
    		nYear +=1;
    	}else {
    		nMonth +=1;
    	}
    	changeCalendar(nYear, nMonth);
    }

    @FXML
    void todayClick(ActionEvent event) {
    	today();
    }

    @FXML
    void clickGrid(MouseEvent e)  throws IOException {
    	//클릭한 날짜를 얻어오는 것
//    	for( Node node : grid.getChildren()) {
//            if( node instanceof VBox) {
//                ((VBox)node).setOnMouseClicked(ee->{
//                	clickDate = ((Label)(((VBox)(ee.getSource())).getChildren().get(0))).getText();
//					 if(clickDate.length()>2) {
//						 clickDate = clickDate.substring(0,2);
//						 try {
//							Integer.parseInt(clickDate);
//						} catch (Exception e2) {
//							clickDate = clickDate.substring(0,1);
//						}
//					 }
//					 clickYear = nYear;
//					 clickMonth = nMonth;
//					 
//					 if(e.getClickCount()==2) {
//						 node.setStyle("-fx-border-color: #fe4371; -fx-border-width: 2;");
//						 try {
//							 //detail뷰를 띄우는 곳
//							 Parent detail = FXMLLoader.load(getClass().getResource("../../fxml/att/detailCalendar.fxml"));
//							 Scene scene = new Scene(detail);
//							 Stage stage = new Stage();
//							 stage.setX(1000);
//							 stage.setY(200);
//							 stage.setScene(scene);
//							 stage.setTitle("datailMail");
//							 stage.showAndWait();
//							 
//							 changeCalendar(clickYear, clickMonth);
//							 node.setStyle("-fx-border-color: white black black white; -fx-border-style:  segments(3, 3, 3, 3);");
//						 } catch (IOException e1) {
//							 e1.printStackTrace();
//						 }
//					 }
//                });
//            }
//        }
    	
    }

    @FXML
    void initialize() {
    	if(MainPageController.getLoginUser().getDepartment_no() == 1 || MainPageController.getLoginUser().getDepartment_no() == 0)
    		btnAll.setVisible(true);
    	hboxList = new HBox[] {
    			hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, hbox8, hbox9, hbox10 ,hbox11 ,hbox12, hbox13, 
    			hbox14, hbox15, hbox16, hbox17, hbox18, hbox19, hbox20, hbox21, hbox22, hbox23, hbox24, hbox25, 
    			hbox26, hbox27, hbox28, hbox29,  hbox30, hbox31, hbox32, hbox33, hbox34, hbox35, hbox36, hbox37, hbox38, hbox39
    	};
    	lableList = new Label[] {lbl00, lbl01, lbl02 ,lbl03, lbl04 ,lbl05 ,lbl06 , lbl10, lbl11, lbl12 ,lbl13 ,lbl14 ,lbl15, lbl16 ,
    							 lbl20 ,lbl21 ,lbl22 ,lbl23 ,lbl24 ,lbl25 ,lbl26 , lbl30 ,lbl31, lbl32, lbl33, lbl34, lbl35 ,lbl36 ,
    							 lbl40 ,lbl41 ,lbl42 ,lbl43 ,lbl44 ,lbl45 ,lbl46 , lbl50 ,lbl51 ,lbl52, lbl53, lbl54 ,lbl55 ,lbl56 };

    	
    	try {
    		Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
    		service = (IAttendanceService) reg.lookup("attService");
    	
    		today();
    		changeCalendar(nYear, nMonth);
        
			
			
			
			
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}
    	
    	
    }
    public void today() {
    	Calendar calendar = new GregorianCalendar(Locale.KOREA);
    	nYear = calendar.get(Calendar.YEAR);
    	nMonth = calendar.get(Calendar.MONTH);
    	
    	changeCalendar(nYear, nMonth);
    }
    public void changeCalendar(int nYear, int nMonth) {
    	inputDate=1;
    	for(int i=0; i<lableList.length; i++) {
    		lableList[i].setDisable(false);
    		lableList[i].setText(" ");
    	}
    	for(int i=0; i<hboxList.length; i++) {
    		hboxList[i].setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
    		hboxList[i].setStyle("-fx-border-color: white;");
    	}
    	//현재 날짜 얻어오기
    	btnToday.setText(nYear + "년 " + (nMonth+1) + "월");
    	//현재날짜와 달, 1일로 set
    	cal.set(Calendar.YEAR, nYear);
    	cal.set(Calendar.MONTH, nMonth);
    	cal.set(Calendar.DATE, 1);
    	
    	startDay = cal.get(Calendar.DAY_OF_WEEK); 
    	lastDay = cal.getActualMaximum(Calendar.DATE); 
    	calList = new ArrayList<AttendanceVO>();
    	for(int i=0; inputDate<=lastDay; i++){
    		if(i>=startDay-1) {
    			lableList[i].setText(inputDate+"");
    			Map<String, Integer> paramMap = new HashMap<String, Integer>();
    			
    			paramMap.put("YEAR", nYear);
    			paramMap.put("MONTH", nMonth+1);
    			paramMap.put("DAY", inputDate);
    			paramMap.put("emp_no", MainPageController.getLoginUser().getEmp_no());	// 사번
    			
    			
    			
    			try {
					AttendanceVO vo = new AttendanceVO(); 
					vo = service.getAttCalander(paramMap);
    			

					
    			
					if(vo != null) {
						calList.add(vo);
//						lableList[i].setText(inputDate + "\n출근 : " + vo.getAtt_start() + "\n퇴근 : " + vo.getAtt_end()); 
						
						Label l1 = new Label();
						l1.setText("");
						l1.setText("출근 : " + vo.getAtt_start());
						if(Integer.parseInt(vo.getAtt_start().substring(0,2)) > 8)
							l1.setBackground(new Background(new BackgroundFill(Color.DARKORANGE,CornerRadii.EMPTY, Insets.EMPTY)));
						else
							l1.setBackground(new Background(new BackgroundFill(Color.AQUA,CornerRadii.EMPTY, Insets.EMPTY)));
						Label l2 = new Label();
						if(vo.getAtt_end() == null) {
							l2.setText("퇴근 미체크");
							l2.setBackground(new Background(new BackgroundFill(Color.DARKORANGE,CornerRadii.EMPTY, Insets.EMPTY)));
						}
						else {
							l2.setText("퇴근 : " + vo.getAtt_end());
							l2.setBackground(new Background(new BackgroundFill(Color.AQUA,CornerRadii.EMPTY, Insets.EMPTY)));
						}
						VBox v = new VBox();
						v.getChildren().addAll(l1,l2);
						hboxList[i].getChildren().clear();
						hboxList[i].getChildren().add(v);
					}
					else {
						lableList[i].setText(inputDate + "");
						hboxList[i].getChildren().clear();
					}
					lableList[i].setStyle("-fx-font-size: 19px ;");
    			if(calList.size()!=0) {
//    				if(!calList.get(0).getCal_edate().equals(calList.get(0).getCal_sdate())) {
//    					int count = (Integer.parseInt(calList.get(0).getCal_edate())-Integer.parseInt(calList.get(0).getCal_sdate()))+1;
//    					for(int j=0; j<count; j++) {
//    						hboxList[k].setBackground(new Background(new BackgroundFill(Color.rgb(0, 121, 196),CornerRadii.EMPTY, Insets.EMPTY)));
//    						k++;
//    					}
//    				}
    			}
    			inputDate++;
    			} catch (RemoteException e) {
    				e.printStackTrace();
    			}
    		}
    	}  
    }
}