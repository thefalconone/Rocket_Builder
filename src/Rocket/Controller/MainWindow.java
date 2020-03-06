package Rocket.Controller;

import Rocket.Model.FilesReader;
import Rocket.Model.Genetic;
import Rocket.Model.Settings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MainWindow extends Application {

	public WebView webView;

	public RadioButton Button_BestCost;
	public RadioButton Button_BestDeltaV;
	public Label DeltaVCost;

	public Spinner<Integer> Spinner_moddeltavcost;
	public Spinner<Integer> Spinner_modtwr;
	public Slider Slider_twrmin;
	public Slider Slider_twrmax;

	public Spinner<Integer> Spinner_nbmaxstages;
	public Spinner<Integer> Spinner_nbmaxft;
	public Slider Slider_deltavcost;

	@Override
	public void start(javafx.stage.Stage stage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("../View/MainWindow.fxml"));
		stage.setTitle("Rocket Builder");
		stage.getIcons().add(new Image("file:icon.png"));
		stage.setScene(new Scene(root, 900, 600));
		stage.show();
	}

	@Override
	public void stop(){
		FilesReader.saveSettings();
	}

	public static void main(String[] args) {
		FilesReader.readAllData();
		launch(args);
	}

	public void buttonBuildClicked() {
		WebEngine webEngine = webView.getEngine();

		Path path = Paths.get("rocket.html");

		try {
			if(Files.exists(path))
				Files.delete(path);

			String content = HTML.createRocketHTML(Genetic.createRocket());
			Files.write(path, content.getBytes(), StandardOpenOption.CREATE_NEW);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		webEngine.load(path.toUri().toString());
	}

	public void menuFileOptions() throws Exception {
		Options options = new Options();
		options.start(new javafx.stage.Stage());
	}

	public void menuFileClose() {
		Stage stage = (Stage) webView.getScene().getWindow();
		stage.close();
	}

	public void deltavcostChangedValue() {
		if(Button_BestDeltaV.isSelected()){
			Settings.cost = (int) Slider_deltavcost.getValue();
		}
		else{
			Settings.dv = (int) Slider_deltavcost.getValue();
		}
	}

	public void mintwrChangedValue() {
	}

	public void maxtwrChangedValue() {
	}

	public void moddeltavcostChangedValue() {
		Settings.modcost = Spinner_moddeltavcost.getValue();
	}

	public void modtwrChangedValue() {
		Settings.modtwr = Spinner_modtwr.getValue();
		System.out.println("modtwr:"+Settings.modtwr);
	}

	public void nbmaxftChangedValue() {
		Settings.nbmaxstages = Spinner_nbmaxstages.getValue();
		System.out.println("nbmaxstages:"+Settings.nbmaxstages);
	}

	public void nbmaxstagesChangedValue() {
		Settings.nbmaxstages = Spinner_nbmaxstages.getValue();
		System.out.println("nbmaxstages:"+Settings.nbmaxstages);
	}

	public void deltavcostClicked() {
		if(Button_BestCost.isSelected()){
			Button_BestDeltaV.setSelected(false);
			DeltaVCost.setText("DeltaV");
		}
		else{
			Button_BestCost.setSelected(false);
			DeltaVCost.setText("Cost");
		}
	}
}
