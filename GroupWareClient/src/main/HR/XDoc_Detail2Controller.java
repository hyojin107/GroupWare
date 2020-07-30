package main.HR;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class XDoc_Detail2Controller {

    @FXML
    private ResourceBundle resources; //증명서상세열람(재직)

    @FXML
    private URL location;

    @FXML
    private Button back;

    @FXML
    private TextField tfname; //이름

    @FXML
    private TextField tfdepartments;//부서

    @FXML
    private TextField tfgigan;//기간

    @FXML
    private TextField tfsubmit;//제출처

    @FXML
    private TextField tfyongdo;//용도

    @FXML
    private Button download;

    @FXML
    private Text txstate;//처리상태

    @FXML
    private Button btnok;//승인

    @FXML
    private Button btnnop;//반려

    @FXML
    void btnbackclick(ActionEvent event) {//뒤로가기버튼클릭

    }

    @FXML
    void btndownloadclick(ActionEvent event) {

    }

    @FXML
    void btnnopclick(ActionEvent event) {//반려버튼클릭

    }

    @FXML
    void btnokclick(ActionEvent event) {//승인버튼클릭

    }

    @FXML
    void initialize() {

    }
}
