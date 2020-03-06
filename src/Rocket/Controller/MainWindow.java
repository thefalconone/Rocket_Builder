package Rocket.Controller;

import Rocket.Model.FilesReader;
import Rocket.Model.Genetic;
import Rocket.Model.Settings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
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
	public Label Label_deltavcost;

	public Spinner<Integer> Spinner_moddeltavcost;
	public Spinner<Integer> Spinner_modtwr;
	public Label Label_mintwr;
	public Label Label_maxtwr;
	public Slider Slider_mintwr;
	public Slider Slider_maxtwr;

	public Spinner<Integer> Spinner_nbmaxstages;
	public Spinner<Integer> Spinner_nbmaxft;
	public Slider Slider_deltavcost;

	@Override
	public void start(javafx.stage.Stage stage) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("../View/MainWindow.fxml"));
		stage.setTitle("Rocket Builder");
		stage.getIcons().add(new Image("file:icon.png"));
		stage.setScene(new Scene(root, 1000, 600));
		stage.show();
	}

	@Override
	public void stop(){
		FilesReader.saveSettings();
	}

	public static void main(String[] args) {
		FilesReader.readAllData();
		Settings.print();
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

		Settings.print();
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
			Settings.dv = 0;
			Settings.cost = (int) Slider_deltavcost.getValue();
			Label_deltavcost.setText("Target Cost : "+ Settings.cost);
		}
		else{
			Settings.cost = 0;
			Settings.dv = (int) Slider_deltavcost.getValue();
			Label_deltavcost.setText("Target DeltaV : "+ Settings.dv);
		}
	}

	public void mintwrChangedValue() {
		Settings.mintwr = (float) Slider_mintwr.getValue();
		if(Settings.mintwr==0)
			Label_mintwr.setText("No Minimum TWR");
		else
			Label_mintwr.setText("Minimum TWR : "+ String.format("%2.1f%n", Settings.mintwr));
	}

	public void maxtwrChangedValue() {
		Settings.maxtwr = (float) Slider_maxtwr.getValue();
		if(Settings.maxtwr==0)
			Label_maxtwr.setText("No Maximum TWR");
		else
			Label_maxtwr.setText("Maximum TWR : "+ String.format("%2.1f%n", Settings.maxtwr));
	}

	public void moddeltavcostChangedValue() {
		if(Button_BestDeltaV.isSelected()){
			Settings.cost = Spinner_moddeltavcost.getValue();
			//System.out.println("cost:"+Settings.cost);
		}
		else{
			Settings.dv = Spinner_moddeltavcost.getValue();
			//System.out.println("dv:"+Settings.dv);
		}
	}

	public void modtwrChangedValue() {
		Settings.modtwr = Spinner_modtwr.getValue();
		//System.out.println("modtwr:"+Settings.modtwr);
	}

	public void nbmaxftChangedValue() {
		Settings.nbmaxft = Spinner_nbmaxft.getValue();
		//System.out.println("nbmaxft:"+Settings.nbmaxft);
	}

	public void nbmaxstagesChangedValue() {
		Settings.nbmaxstages = Spinner_nbmaxstages.getValue();
		//System.out.println("nbmaxstages:"+Settings.nbmaxstages);
	}
}
