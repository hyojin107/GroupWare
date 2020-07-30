package main.plan;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
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
import javafx.stage.Stage;
import main.mainCtr.MainPageController;
import main.notice.NoticeDetailController;
import main.notice.NoticeListController;
import service.ICalendarService;
import service.IPlanService;
import vo.CalendarVO;
import vo.PlanVO_join;


public class calendarforPlanController {
	private List<PlanVO_join> planList;
	private IPlanService service;
	
	//날짜계산용 변수 만들기 3월 31일 다음날이 4월 1일인걸 계산해줌
	Calendar cal = Calendar.getInstance(); 

    private int nYear,	
    		    nMonth;
	private int startDay,	
	            lastDay,
	            inputDate;  //1일
	
	static String yearMonth; // 일정 월 리스트는 단추에서 따와야해!! ㅠㅠ 
	static String clickDate;
	static int clickYear,
			   clickMonth;
	static String checkDaily; // 일별 리스트로 보내줄 날짜


	Label[] labelList;
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
    private Button bntGoList;
    
    private MainPageController main;
    public MainPageController getMain() {
		return main;
	}
	public void setMain(MainPageController main) {
		this.main = main;
	}
    
	
    @FXML
    void addclcik(ActionEvent event) throws IOException {
//    	 //일정등록
//    	 FXMLLoader loader = new FXMLLoader(calendarforPlanController.class.getResource("/fxml/plan/PlanDetail.fxml"));
//         Parent root = loader.load();
//         Stage stage = (Stage) btnAdd.getScene().getWindow();
//         
//         //컨트롤 통로 만들어줌 
//         PlanDetailController addclicked = loader.getController();
//         //detail의 게터세터를 이용해서 
//         addclicked.getDetailHead().setText("일 정 등 록");
//         addclicked.getBtnBack().setText("캘린더 보기");
//         addclicked.getBtnEdit().setText("등록");
//         addclicked.getBtnDelete().setVisible(false);
//         addclicked.getTfTitle().setEditable(true);
//         addclicked.getTfContent().setEditable(true);
//         addclicked.getDateStart().setEditable(true);
//         addclicked.getDateEnd().setEditable(true);
//         Scene scene = new Scene (root);
//         stage.setScene(scene);
//         stage.setTitle("일정등록"); 
//         stage.show();
         
         FXMLLoader loader = new FXMLLoader(calendarforPlanController.class.getResource("/fxml/plan/PlanDetail.fxml"));
         Parent root = loader.load();
         PlanDetailController Ctr = loader.getController();
         Ctr.getDetailHead().setText("일 정 등 록");
         Ctr.getBtnBack().setText("캘린더 보기");
         Ctr.getBtnEdit().setText("등록");
         Ctr.getBtnDelete().setVisible(false);
         Ctr.getTfTitle().setEditable(true);
         Ctr.getTfContent().setEditable(true);
         Ctr.getDateStart().setEditable(true);
         Ctr.getDateEnd().setEditable(true);
		 Ctr.setMain(main);
		
		main.getMainborder().setCenter(root);

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
    	for( Node node : grid.getChildren()) {
            if( node instanceof VBox) {
                ((VBox)node).setOnMouseClicked(ee->{
                	clickDate = ((Label)(((VBox)(ee.getSource())).getChildren().get(0))).getText();
					 if(clickDate.length()>2) {
						 clickDate = clickDate.substring(0,2);
						 try {
							Integer.parseInt(clickDate);
						} catch (Exception e2) {
							clickDate = clickDate.substring(0,1);
						}
					 }
					 clickYear = nYear;
					 clickMonth = nMonth+1;
					 checkDaily=clickYear+"년 "+clickMonth + "월 "+clickDate+"일";
					 if(e.getClickCount()==2) {
						 node.setStyle("-fx-border-color: #fe4371; -fx-border-width: 2;");
						 try {
//								  //이창에 스태이지 객체 생성  
//					    		   FXMLLoader loader = new FXMLLoader(calendarforPlanController.class.getResource("/fxml/plan/PlanList.fxml"));
//						           Parent root = loader.load();
//						           PlanListController addclicked = loader.getController();
//						           addclicked.getPlanHead2().setText(checkDaily);
//						           addclicked.change();
//						           Stage stage = (Stage) btnAdd.getScene().getWindow();
//						           Scene scene = new Scene (root);
//						           stage.setScene(scene);
//						           stage.setTitle("일정리스트"); 
//						           stage.show();
						           
						           
						           FXMLLoader loader = new FXMLLoader(calendarforPlanController.class.getResource("/fxml/plan/PlanList.fxml"));
						           Parent root = loader.load();
						           PlanListController Ctr = loader.getController();
						           Ctr.getPlanHead2().setText(checkDaily);
						           Ctr.change();
						           Ctr.setMain(main);
						    	   main.getMainborder().setCenter(root);

						           
						 } catch (IOException e1) {
							 e1.printStackTrace();
						 }
					 }
                });
            }
        }
    	
    	
    }

    @FXML
    void bntGoListClick(ActionEvent event) throws IOException {
    	yearMonth=btnToday.getText();
//    	FXMLLoader loader = new FXMLLoader(calendarforPlanController.class.getResource("/fxml/plan/PlanList.fxml"));
//        Parent root = loader.load();
//        Stage stage = (Stage) btnToday.getScene().getWindow();
//        PlanListController addclicked = loader.getController();
//        addclicked.getPlanHead2().setText(yearMonth);
//        addclicked.change();
//        Scene scene = new Scene (root);
//        stage.setScene(scene);
//        stage.setTitle("일정리스트"); 
//        stage.show(); 
        
    	FXMLLoader loader = new FXMLLoader(calendarforPlanController.class.getResource("/fxml/plan/PlanList.fxml"));
        Parent root = loader.load();
        PlanListController Ctr = loader.getController();
        Ctr.getPlanHead2().setText(yearMonth);
        Ctr.change();
        Ctr.setMain(main);
 	   main.getMainborder().setCenter(root);
    }
    
    
    @FXML
    void initialize() throws RemoteException {
//    	if(Main.curEmp.getDept_id()!=20) {
//    		btnAdd.setDisable(true);
//    	}
    	yearMonth="";
    	hboxList = new HBox[] {
    			hbox1, hbox2, hbox3, hbox4, hbox5, hbox6, hbox7, hbox8, hbox9, hbox10 ,hbox11 ,hbox12, hbox13, 
    			hbox14, hbox15, hbox16, hbox17, hbox18, hbox19, hbox20, hbox21, hbox22, hbox23, hbox24, hbox25, 
    			hbox26, hbox27, hbox28, hbox29,  hbox30, hbox31, hbox32, hbox33, hbox34, hbox35, hbox36, hbox37, hbox38, hbox39
    	};
    	labelList = new Label[] {lbl00, lbl01, lbl02 ,lbl03, lbl04 ,lbl05 ,lbl06 , lbl10, lbl11, lbl12 ,lbl13 ,lbl14 ,lbl15, lbl16 ,
    							 lbl20 ,lbl21 ,lbl22 ,lbl23 ,lbl24 ,lbl25 ,lbl26 , lbl30 ,lbl31, lbl32, lbl33, lbl34, lbl35 ,lbl36 ,
    							 lbl40 ,lbl41 ,lbl42 ,lbl43 ,lbl44 ,lbl45 ,lbl46 , lbl50 ,lbl51 ,lbl52, lbl53, lbl54 ,lbl55 ,lbl56 };

    	Registry reg;
    	try {
    		reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
    		service = (IPlanService) reg.lookup("planService");
    	} catch (RemoteException e) {
    		e.printStackTrace();
    	} catch (NotBoundException e) {
    		e.printStackTrace();
    	}
    	Map<String,Integer> pageMap = new HashMap<String, Integer>();
    	pageMap.put("start", 0);
    	pageMap.put("end", 1999999999);
    	planList = service.getAllPlanEmp(pageMap); 
    	
    	today();
    	changeCalendar(nYear, nMonth);
		
		
		
		

    }
    public void today() {
    	Calendar calendar = new GregorianCalendar(Locale.KOREA);
    	nYear = calendar.get(Calendar.YEAR);
    	nMonth = calendar.get(Calendar.MONTH);
    	
    	changeCalendar(nYear, nMonth);
    }
    
    
    
    public void changeCalendar(int nYear, int nMonth) {
		
    	inputDate=1;
    	// 라벨(날짜)지우기 -> 매달 날짜의 위치가 바뀌니까 초기화 해줘야함
    	for(int i=0; i<labelList.length; i++) {
    		labelList[i].setDisable(false);
    		labelList[i].setText(" ");
    	}
    	// hbox도 초기화
    	for(int i=0; i<hboxList.length; i++) {
    		hboxList[i].getChildren().clear();
    		hboxList[i].setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY, Insets.EMPTY)));
    		hboxList[i].setStyle("-fx-border-color: white;");
    	}
    	//현재 날짜 얻어오기
    	btnToday.setText(nYear + "년 " + (nMonth+1) + "월");
    	// cal은 calendar뭉텅이로서 날짜계산을 용이하게해줌. 
    	cal.set(Calendar.YEAR, nYear);
    	cal.set(Calendar.MONTH, nMonth);
    	cal.set(Calendar.DATE, 1);
    	// 위에서 설정한걸 바탕(1일을 설정)으로, 첫번째 날이 무슨 요일인지 구해줌 
    	startDay = cal.get(Calendar.DAY_OF_WEEK); 
    	lastDay = cal.getActualMaximum(Calendar.DATE); 
    	for(int i=0; inputDate<=lastDay; i++){
    		//startDay가 요일에 관련한 숫자를갖고있으므로(7개의 칸에서 어디위치하는지) 이전달은 비워준다. 
    		if(i<startDay-1) {
    			labelList[i].setText(" ");
    			labelList[i].setDisable(true);
    		}else{
    			labelList[i].setText(inputDate+"");
    			Map<String, Integer> paramMap = new HashMap<String, Integer>();
    			
    			paramMap.put("nYear", nYear);
    			paramMap.put("nMonth", nMonth+1);
    			paramMap.put("inputDate", inputDate);
    			int k = i;
//    			if(planList.size()!=0) {
//				lableList[i].setText(inputDate + "\n" + planList.get(0).getCal_title()); 
//				lableList[i].setStyle("-fx-font-size: 19px ;");
//				if(!planList.get(0).getCal_edate().equals(planList.get(0).getCal_sdate())) {
//					int count = (Integer.parseInt(planList.get(0).getCal_edate())-Integer.parseInt(planList.get(0).getCal_sdate()))+1;
//					for(int j=0; j<count; j++) {
//						hboxList[k].setBackground(new Background(new BackgroundFill(Color.rgb(0, 121, 196),CornerRadii.EMPTY, Insets.EMPTY)));
//						k++;
//					}
//				}
//			}
    			
    			
//    			planList = new ArrayList<PlanVO_join>();
    			labelList[i].setText(inputDate + "");

				Label l1 = new Label(); 
				Label l2 = new Label();
				Label l3 = new Label();
				l1.setText("");
				l2.setText("");
				l3.setText("");
    			if(planList.size()!=0) {
    				String calYear= nYear+"";
    				String calMonth = (nMonth+1)+"";
    				calMonth = (calMonth.length()==1)? "0"+calMonth : calMonth;
    				String calDay= inputDate+"";
    				calDay = (calDay.length()==1)? "0"+calDay : calDay;
    				String calDate = calYear+"/"+calMonth+"/"+calDay;
    				//라벨 생성 & 초기화
    				for(int j=0; j<planList.size(); j++) {
    					//DB에서 불러온 일정 날짜 
    					String planDate=planList.get(j).getPlan_start();
    					String cnt=planList.get(j).getPlan_cnt();
    
    					int tmp=Integer.parseInt(cnt)+1;
    					cnt = ""+tmp;
    					cnt= cnt.equals("1")? "":"("+cnt+"일"+")";
    					if(calDate.equals(planDate)) {
    						if(l1.getText().equals("")) {
    							l1.setText(planList.get(j).getPlan_title()+cnt);
    							l1.setBackground(new Background(new BackgroundFill(Color.GOLD,CornerRadii.EMPTY, Insets.EMPTY)));
    						}
    						else if(l2.getText().equals("")) {
    							l2.setText(planList.get(j).getPlan_title()+cnt);
    							l2.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL,CornerRadii.EMPTY, Insets.EMPTY)));
    						}
    						else if(l3.getText().equals("")) {
    							l3.setText(planList.get(j).getPlan_title()+cnt);
    							l3.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE,CornerRadii.EMPTY, Insets.EMPTY)));
    						}
    						
    					}
    					
    				}
    				VBox v = new VBox();
					v.getChildren().addAll(l1,l2,l3);
					hboxList[i].getChildren().clear();
					hboxList[i].getChildren().add(v);
    			}

    			inputDate++;
    		}
    	}  
    	
    	
		
    	
    	
    	
    }
}