package main.addr;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class fxmain extends Application {

	@Override
	   public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
	     // Parent root=FXMLLoader.load(fxmain.class.getResource("../fxml/sawoninfo.fxml"));
//	      Parent root=FXMLLoader.load(fxmain.class.getResource("../../fxml/myaddrbookController.fxml"));
	      Parent root=FXMLLoader.load(Class.forName("main.addr.fxmain").getResource("/myaddrbookController.fxml"));
	     // Parent root3=FXMLLoader.load(fxmain.class.getResource("../fxml/getEmailHam.fxml"));
	   
	      Scene scene = new Scene(root);
	      primaryStage.setScene(scene);
	      primaryStage.setTitle("나의 주소록");
	      primaryStage.show();
	   }


	public static void main(String[] args) {
		launch(args);
	}
}
 