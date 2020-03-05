package Rocket.Controller;

import Rocket.Model.FilesReader;
import Rocket.Model.Genetic;
import Rocket.Model.Settings;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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

	public Slider Slider_dv = new Slider(0, 1000000, Settings.maxdv);
	public Slider Slider_cost = new Slider(0, 100000, Settings.maxcost);
	public Slider Slider_twr = new Slider(0, 20, Settings.maxtwr);

	public Spinner<Integer> Spinner_moddeltav = new Spinner<>();
	public Spinner<Float> Spinner_modcost = new Spinner<>(0, 3, Settings.modcost);
	public Spinner<Float> Spinner_modtwr = new Spinner<>(0, 3, Settings.modtwr);

	public Spinner<Integer> Spinner_nbmaxstages = new Spinner<>(0, 5, Settings.nbmaxstages);
	public Spinner<Integer> Spinner_nbmaxft = new Spinner<>(0, 5, Settings.nbmaxft);

	@Override
	public void start(javafx.stage.Stage stage) throws IOException {

		int moddeltav = 1;

		// Value factory.
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 3, moddeltav);

		Spinner_moddeltav.setValueFactory(valueFactory);

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

	public void dvChangedValue() {
		Settings.maxdv = (int) Slider_dv.getValue();
		System.out.println("maxdv:"+Settings.maxdv);
	}

	public void costChangedValue() {
		Settings.maxcost = (int) Slider_cost.getValue();
	}

	public void twrChangedValue() {
		Settings.maxtwr = (int) Slider_twr.getValue();
	}

	public void moddeltavChangedValue() {
		Settings.moddeltav = Spinner_moddeltav.getValue();
		System.out.println("moddeltav:"+Settings.moddeltav);
	}

	public void modcostChangedValue() {
		Settings.modcost = Spinner_modcost.getValue();
	}

	public void modtwrChangedValue() {
		Settings.modtwr = Spinner_modtwr.getValue();
	}

	public void menuFileOptions() throws Exception {
		Options options = new Options();
		options.start(new javafx.stage.Stage());
	}

	public void menuFileClose() {
		Stage stage = (Stage) Spinner_moddeltav.getScene().getWindow();
		stage.close();
	}
}
