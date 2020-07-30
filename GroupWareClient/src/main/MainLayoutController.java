package main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vo.EmployeeVO;

public class MainLayoutController {

	public static EmployeeVO loginUser;
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lb_name;

    @FXML
    void initialize() {
        

    }
}
