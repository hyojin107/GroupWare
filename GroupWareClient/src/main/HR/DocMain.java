package main.HR;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DocMain extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
	Parent root=FXMLLoader.load(getClass().getResource("/fxml/Doc_applystate.fxml"));
		
		Scene scene = new Scene(root); 
		primaryStage.setScene(scene);
		primaryStage.setTitle("증명서신청현황");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
