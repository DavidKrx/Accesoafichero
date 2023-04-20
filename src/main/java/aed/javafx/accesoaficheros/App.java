package aed.javafx.accesoaficheros;

import aed.javafx.vista.VistaController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application{

	private VistaController verController;
	public void start(Stage primaryStage) throws Exception {

		// ver
		
		verController = new VistaController();
		
		Scene verScene = new Scene(verController.getView(), 601, 601); 
		
		Stage verStage = new Stage(); 
		verStage.setMinHeight(600);
		verStage.setMinWidth(600);
		verStage.setMaxHeight(610);
		verStage.setMaxWidth(610);
		verStage.setTitle("Ver");
		verStage.setScene(verScene);
		verStage.show();
	

		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
