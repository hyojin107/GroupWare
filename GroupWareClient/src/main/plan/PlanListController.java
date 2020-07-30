package main.plan;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.mainCtr.MainPageController;
import service.IPlanService;
import vo.PlanVO_join;

public class PlanListController {
	private int rowPerPage = 14; //한페이지당 보여줄 게시글 개수	
	private int totalCount;	//총 게시글수 		
	private int pageCount; // 페이지 개수
	
	//서비스객체 
    private IPlanService service;
    private List<PlanVO_join> list; 
    
    //VO도 일종의 타입
    private PlanVO_join item; 
    
    //이 컨트롤러에서 정보좀 받고싶습니다.
    private calendarforPlanController clickedDate;
	public calendarforPlanController getClickedDate() {
		return clickedDate;
	}
	public void setClickedDate(calendarforPlanController clickedDate) {
		this.clickedDate = clickedDate;
	}
	
    private MainPageController main;
    public MainPageController getMain() {
		return main;
	}
	public void setMain(MainPageController main) {
		this.main = main;
	}



    @FXML
    private TableView<PlanVO_join> tbPlan;

    @FXML
    private TableColumn<?, ?> colNo;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> ColStart;

    @FXML
    private TableColumn<?, ?> ColEnd;

    @FXML
    private Button btnAdd;
    
    @FXML
    private Button btnCalendar;

    @FXML
    private Pagination page;

    @FXML
    private Text PlanHead1;
    
	@FXML
    private Text PlanHead2;
	
    public Text getPlanHead2() { 
		return PlanHead2;
	}
	public void setPlanHead2(Text planHead2) {
		PlanHead2 = planHead2;
	}
	@FXML
    void colNo(ActionEvent event) {

    }
    @FXML
    void btnAddClick(ActionEvent event) throws IOException {
//    	 FXMLLoader loader = new FXMLLoader(PlanListController.class.getResource("/fxml/plan/PlanDetail.fxml"));
//         Parent root = loader.load();
//         Stage stage = (Stage) btnAdd.getScene().getWindow();
//         
//         //컨트롤 통로 만들어줌 
//         PlanDetailController addclicked = loader.getController();
//         addclicked.setHead(PlanHead2.getText());
//         //detail의 게터세터를 이용해서 
//         addclicked.getDetailHead().setText("일 정 등 록");;
//         addclicked.getBtnEdit().setText("등록");
//         addclicked.getBtnDelete().setText("취소");
//         addclicked.getTfTitle().setEditable(true);
//         addclicked.getTfContent().setEditable(true);
//         addclicked.getDateStart().setEditable(true);
//         addclicked.getDateEnd().setEditable(true);
//         Scene scene = new Scene (root); 
//         stage.setScene(scene);
//         stage.setTitle("일정등록");  
//         stage.show();
         
         
        FXMLLoader loader = new FXMLLoader(PlanListController.class.getResource("/fxml/plan/PlanDetail.fxml"));
 		Parent root = loader.load();
 		PlanDetailController Ctr = loader.getController();
 		 Ctr.setHead(PlanHead2.getText());
         //detail의 게터세터를 이용해서 
 		Ctr.getDetailHead().setText("일 정 등 록");;
 		Ctr.getBtnEdit().setText("등록");
 		Ctr.getBtnDelete().setText("취소");
 		Ctr.getTfTitle().setEditable(true);
 		Ctr.getTfContent().setEditable(true);
 		Ctr.getDateStart().setEditable(true);
 		Ctr.getDateEnd().setEditable(true);
 		Ctr.setMain(main);
 		main.getMainborder().setCenter(root);

    } 
    
    @FXML
    void btnCalClick(ActionEvent event) throws IOException {
//		FXMLLoader loader = new FXMLLoader(PlanListController.class.getResource("/fxml/plan/calendarforPlan.fxml"));
//        Parent root = loader.load();
//        Stage stage = (Stage) btnAdd.getScene().getWindow();
//         
//        Scene scene = new Scene (root);
//        stage.setScene(scene);
//        stage.setTitle("일정 캘린더");
//        stage.show();
        
    	FXMLLoader loader = new FXMLLoader(PlanListController.class.getResource("/fxml/plan/calendarforPlan.fxml"));
		Parent root = loader.load();
		calendarforPlanController Ctr = loader.getController();
		Ctr.setMain(main);
		main.getMainborder().setCenter(root);

    }

    @FXML
    void tbClick(MouseEvent event) {
    	
    	if(tbPlan.getSelectionModel().isEmpty()) {
    		return;
    	}
    	try {
 
//    		//이창에 스태이지 객체 생성 
//    		Stage s = (Stage) tbPlan.getScene().getWindow();
//    		//선택된한줄을 item에 담음 
//			item = tbPlan.getSelectionModel().getSelectedItem();
//    		// 여기로 load해서 데려갈거다
//			FXMLLoader loader = new FXMLLoader(PlanListController.class.getResource("/fxml/plan/PlanDetail.fxml"));
//			Parent root = loader.load();
//			//PlanDetailController에 ListController연결(객체,게터세터) NoticeVo도 연결해주기(객체,게터세터)
//			PlanDetailController planDetail = loader.getController();
//			planDetail.setControll1(this);
//			planDetail.setPjoinVO(item);
//			planDetail.setHead(PlanHead2.getText());
//			
//			Scene scene = new Scene(root);
//			s.setScene(scene);
//	    	s.setTitle("일정 상세 열람");
//	    	s.show();
	    	
	    	
	    	item = tbPlan.getSelectionModel().getSelectedItem();
	    	FXMLLoader loader = new FXMLLoader(PlanListController.class.getResource("/fxml/plan/PlanDetail.fxml"));
			Parent root = loader.load();
			PlanDetailController Ctr = loader.getController();
			Ctr.setControll1(this);
			Ctr.setPjoinVO(item);
			Ctr.setHead(PlanHead2.getText());
			Ctr.setMain(main);
			main.getMainborder().setCenter(root);
    	
    	} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void change() {
    		//월별 리스트 출력 
//    			if(calendarforPlanController.yearMonth.contains("년")&&!(calendarforPlanController.yearMonth.contains("일"))) {
    			String a = PlanHead2.getText();
    			System.out.println(a);
    			if(a.contains("년")&& !a.contains("일")) {
    				System.out.println("월별");
    				MonthlyList();
    				return;  
    			//일별 리스트 출력
//    				 if(calendarforPlanController.checkDaily.contains("일")) 
    			}else{
    				System.out.println("일별");
    				DailyList();
    				return;
    			}
    	
    }
    
	//DB의 전체 데이터를 가져와서 tableView에 출력하는 메서드 
	public void changeTable(int i) {
		int start = i * rowPerPage;
		int end = Math.min(start+rowPerPage, totalCount); //둘중에 작은놈을 채택
		tbPlan.getItems().clear();
		for(int j = start; j < end; j++) { tbPlan.getItems().add(list.get(j)); }

		}

	
	//월별 일정 리스트 
	private void MonthlyList() {
		//달력에서 표기된 년 월 가져오기
		String clicked=calendarforPlanController.yearMonth;
		tbPlan.getItems().clear();
		List<PlanVO_join> tmp = new ArrayList<PlanVO_join>();
		for(int j = 0; j < list.size(); j++) { 
			//달력의날짜
			String year2=clicked.substring(0, 4); //2020
			String month2=clicked.substring(5, 7); // 4
			month2=month2.replace(" ","0");  //04 
			
			year2=year2+month2;
			//내일정의날짜
			String getDate = list.get(j).getPlan_start();
			String year1=getDate.substring(0, 4);
			String month1=getDate.substring(5, 7); 
			year1=year1+month1;
			if(year1.equals(year2)) {
				tmp.add(list.get(j)); 
			}
			}
		
		list = tmp;
		paging();
	}
	
	//일별 일정 리스트
	private void DailyList() {
		//캘린더에서 선택한 날짜 
		int year1=calendarforPlanController.clickYear;
		int month1=calendarforPlanController.clickMonth;
		if(calendarforPlanController.clickDate == null) {
			System.out.println("calendarforPlanController.clickDate가 null입니다.");
		}
		int day1=Integer.parseInt(calendarforPlanController.clickDate);
		
		tbPlan.getItems().clear();
		for(int j = 0; j < list.size(); j++) { 
			//DB에서 데려오는 날짜 
			String getDay = list.get(j).getPlan_start();
			int year2 = Integer.parseInt(getDay.substring(0, 4));
			int month2 = Integer.parseInt(getDay.substring(5, 7));
			int day2 = Integer.parseInt(getDay.substring(8, 10));
			
			if(year1==year2 && month1==month2 && day1 ==day2) {
				tbPlan.getItems().add(list.get(j));
			}
			}
		
		page.setPageCount(1);
	}
	
	void paging() {
		totalCount = list.size();
		//총 페이지 개수 정해주기
		pageCount = (int) Math.ceil((double)totalCount / rowPerPage); 
		page.setPageCount(pageCount);
		page.setCurrentPageIndex(0); //기본값 첫번째 페이지로 설정 
		changeTable(0);
	}
    
    
    @FXML
    void initialize() throws RemoteException {
    	//(service = BoardServiceImpl.getInstance();)
    	try {
			Registry reg = LocateRegistry.getRegistry("192.168.0.117", 9999);
			service = (IPlanService) reg.lookup("planService");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
    	
    	//야메로 다 들고오는 방법 list = service.getAllBoard();
    	Map<String,Integer> pageMap = new HashMap<String, Integer>();
    	pageMap.put("start", 0);
		pageMap.put("end", 1999999999);
		list = service.getAllPlanEmp(pageMap); 
		
		
		//몰라이거뭐하는거더라
		page.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				changeTable(newValue.intValue());
			}
			
		});
		
		colNo.setCellValueFactory(new PropertyValueFactory<>("plan_no"));
		colTitle.setCellValueFactory(new PropertyValueFactory<>("plan_title"));
		colName.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
		ColStart.setCellValueFactory(new PropertyValueFactory<>("plan_start"));
		ColEnd.setCellValueFactory(new PropertyValueFactory<>("plan_end"));

		
  
		
    }
}
