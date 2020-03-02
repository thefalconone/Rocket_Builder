package Rocket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainWindow extends Application {


	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
		stage.setTitle("Rocket Builder");
		stage.getIcons().add(new Image("file:icon.png"));
		stage.setScene(new Scene(root, 800, 500));
		stage.show();
	}

	public static void main(String[] args) {
		FilesReader.readAllData();
		System.out.println(Genetic.createRocket().toString());
		FilesReader.saveSetting();
		launch(args);
	}
}
