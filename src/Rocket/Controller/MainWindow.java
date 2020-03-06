package Rocket.Controller;

import Rocket.Model.FilesReader;
import Rocket.Model.Genetic;
import Rocket.Model.Settings;
import javafx.application.Application;
import javafx.collections.FXCollections;
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

	public ChoiceBox<String> DeltaVCostChoice;
	public Label DeltaVCost;

	public Spinner<Integer> Spinner_moddeltavcost;
	public Spinner<Integer> Spinner_modtwr = new Spinner<>(0, 3, Settings.modtwr);
	public Slider Slider_twrmin;
	public Slider Slider_twrmax;

	public Spinner<Integer> Spinner_nbmaxstages = new Spinner<>(0, 5, Settings.nbmaxstages);
	public Spinner<Integer> Spinner_nbmaxft = new Spinner<>(0, 5, Settings.nbmaxft);
	public Slider Slider_deltavcost;

	@Override
	public void start(javafx.stage.Stage stage) throws IOException {

		DeltaVCostChoice = new ChoiceBox<>(FXCollections.observableArrayList("Best Cost", "Best DeltaV"));

		int modtwr = 1;
		// Value factory.
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 3, modtwr);

		Spinner_modtwr.setValueFactory(valueFactory);

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

	/*public void dvChangedValue() {
		Settings.maxdv = (int) Slider_dv.getValue();
		System.out.println("maxdv:"+Settings.maxdv);
	}*/

	public void modcostChangedValue() {
		Settings.modcost = Spinner_moddeltavcost.getValue();
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
	}

	public void mintwrChangedValue() {
	}

	public void maxtwrChangedValue() {
	}

	public void moddeltavcostChangedValue() {
	}
}
