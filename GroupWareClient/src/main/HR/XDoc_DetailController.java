package main.HR;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class XDoc_DetailController {

    @FXML
    private ResourceBundle resources; //증명서 (경력)상세열람

    @FXML
    private URL location;

    @FXML
    private Label DocDetail;

    @FXML
    private Button back;

    @FXML
    private Label lbcareertitle;

    @FXML
    private GridPane txpurpose;

    @FXML
    private TextField tfname; //이름

    @FXML
    private TextField tfdepartment; //부서명

    @FXML
    private TextField tfgigan; //재직기간

    @FXML
    private TextField tfsubmit; //제출처

    @FXML
    private TextField tfyongdo; //용도

    @FXML
    private Button download;

    @FXML
    private Text txting; //진행샅애

    @FXML
    private Button nop; //반려

    @FXML
    private Button ok; //승인

    @FXML
    void btnbackclick(ActionEvent event) { //뒤로가기버튼클릭

    }

    @FXML
    void btndownloadclick(ActionEvent event) { //다운로드버튼클릭

    }

    @FXML
    void btnnopclick(ActionEvent event) { //반려버튼클릭

    }

    @FXML
    void btnokclick(ActionEvent event) { //승인버튼클릭

    }

    @FXML
    void initialize() {

    }
}
