package main.addr;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import main.mainCtr.MainPageController;

public class addressbookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> addrcombo;

    @FXML
    private Button btnselect;

    @FXML
    private Pagination pagination;
    
    private MainPageController main;
    public MainPageController getMain() {
      return main;
   }
   public void setMain(MainPageController main) {
      this.main = main;
   }

    @FXML
    void btnselectclick(ActionEvent event) {  //선택완료버튼

    }

    @FXML
    void comboaddrsort(ActionEvent event) {  //콤보박스 선택
    	
    	addrcombo.setItems(FXCollections.observableArrayList("book_name"));
    	
    }

    @FXML
    void initialize() {
        
    	
    	
    }
}










































































































































































