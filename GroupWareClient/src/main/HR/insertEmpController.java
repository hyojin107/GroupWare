package main.HR;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class insertEmpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfempid; ///사원id

    @FXML
    private TextField tfpw;	//비밀번호

    @FXML
    private TextField tfempname;//사원이름

    @FXML
    private TextField tfphoto;//사진

    @FXML
    private Button btnphoto; ///사진버튼

    @FXML
    private TextField tfemail; //이메일

    @FXML
    private TextField tfdate; //입사일

    @FXML
    private TextField tftsalary; //급여

    @FXML
    private TextField tftel; ///내선번호

    @FXML 
    private TextField tfph; //폰번호

    @FXML
    private ComboBox<?> combodepartment; ///부서콤보버튼

    @FXML
    private ComboBox<?> combogarde; //직급콤보버튼

    @FXML
    private TextField tfregno; ///주민번호

    @FXML
    private TextField tfaddr1; ///주소

    @FXML
    private TextField tfaddr2; //상세주소

    @FXML
    private Button btnregister; ///등록버튼

    @FXML
    private Button btnreset; //초기화버튼

    @FXML 
    private Button btnback; //뒤로가기

    @FXML
    void btnbackclick(ActionEvent event) { //뒤로가기버튼클릭

    }

    @FXML
    void btnregisterclick(ActionEvent event) { ///등록버튼클릭

    }

    @FXML 
    void btnresetclick(ActionEvent event) { //초기화버튼클릭

    }

    @FXML
    void initialize() {

    }
}
