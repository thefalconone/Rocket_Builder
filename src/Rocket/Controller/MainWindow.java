package Rocket.Controller;

import Rocket.Model.FilesReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow extends Application {

	public Button buttonBuild;

	@Override
	public void start(Stage stage) throws IOException {

		Button buttonBuild = new Button();
		//CA MARCHE PAS PUTIN
		buttonBuild.setText("aaaa");
		buttonBuild.setOnAction(e -> System.out.println("zzz"));

		Parent root = FXMLLoader.load(getClass().getResource("../View/MainWindow.fxml"));
		stage.setTitle("Rocket Builder");
		stage.getIcons().add(new Image("file:icon.png"));
		stage.setScene(new Scene(root, 800, 500));
		stage.show();
	}

	public static void main(String[] args) {
		FilesReader.readAllData();
		//System.out.println(Genetic.createRocket().toString());
		FilesReader.saveSetting();
		launch(args);
	}
}
