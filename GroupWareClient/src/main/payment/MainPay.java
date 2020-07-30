package main.payment;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.email.GetEmailListController;

public class MainPay extends Application {

	@Override
	public void start(Stage primaryStage) {

		try {
//			FXMLLoader load = new FXMLLoader(MainPay.class.getResource("/fxml/payment/Sangsin.fxml"));
			Parent root = FXMLLoader.load(MainPay.class.getResource("/fxml/payment/PayList.fxml"));
//			FXMLLoader loader = new FXMLLoader(MainPay.class.getResource("/fxml/email/getEmailList.fxml"));
			
//			Parent root = loader.load();
//			Parent root = FXMLLoader.load(MainPay.class.getResource("/fxml/att/AttCalendar.fxml"));
//			Parent root = FXMLLoader.load(MainPay.class.getResource("/fxml/att/calendar2.fxml"));
//			Parent root = load.load();
//			SangsinController con = load.getController();
			
//			con.setTemp();
//			con.setRead(true);
			
//			getEmailListController con = loader.getController();
			
			
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("그");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
