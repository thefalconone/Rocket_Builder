package Rocket.Controller;

import Rocket.Model.FilesReader;
import Rocket.Model.Genetic;
import Rocket.Model.Rocket;
import Rocket.Model.Settings;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
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
import java.util.HashMap;

public class MainWindow extends Application {

	public WebView webView;

	public RadioButton Button_BestCost;
	public RadioButton Button_BestDeltaV;
	public Label Label_deltavcost;
	public Slider Slider_deltavcost;

	public Label Label_mintwr;
	public Label Label_maxtwr;
	public Slider Slider_mintwr;
	public Slider Slider_maxtwr;

	public Spinner<Integer> Spinner_nbmaxstages;
	public Spinner<Integer> Spinner_nbmaxft;

	public TextField payloadText;
	public ComboBox<String> payloadMenu;
	public ToggleGroup toggleGroup;

	@Override
	public void start(javafx.stage.Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../View/MainWindow.fxml"));
		stage.setTitle("Rocket Builder");
		stage.getIcons().add(new Image("file:icon.png"));
		stage.setScene(new Scene(root, 800, 600));
		stage.show();
	}

	@Override
	public void stop(){
		FilesReader.saveSettings();
	}

	@FXML
	public void initialize(){


		//------------DELTAV - COST----------------
		if(Settings.cost==0){//best cost
			Slider_deltavcost.setValue(Settings.dv);
			Slider_deltavcost.setMax(10000);
			Slider_deltavcost.setBlockIncrement(100);
			Slider_deltavcost.setMajorTickUnit(2500);
			Button_BestCost.setSelected(true);
			Label_deltavcost.setText("Target DeltaV : "+ Settings.dv);
		}
		else{
			Slider_deltavcost.setValue(Settings.cost);
			Slider_deltavcost.setMax(100000);
			Slider_deltavcost.setBlockIncrement(1000);
			Slider_deltavcost.setMajorTickUnit(25000);
			Button_BestDeltaV.setSelected(true);
			Label_deltavcost.setText("Target Cost : "+ Settings.cost);
		}

		//-----------------TWR------------------
		Slider_mintwr.setValue(Settings.mintwr);
		mintwrChangedValue();
		Slider_maxtwr.setValue(Settings.maxtwr);
		maxtwrChangedValue();

		//-----------------STAGE AND FT------------------
		Spinner_nbmaxstages.setPromptText(String.valueOf(Settings.nbmaxstages-1));
		Spinner_nbmaxft.setPromptText(String.valueOf(Settings.nbmaxft));

		//----------------PAYLOAD-----------------
		float satellite = (float) (0.02*2 + 0.01 + 0.04), capsule = (float) (2.72 + 1.3 + 0.3), reservoir = (float) 36.0;
		HashMap<String, Float> payloadList = new HashMap<>();
		payloadList.put("Small Satellite", satellite);
		payloadList.put("Mk1-3 Command Pod", capsule);
		payloadList.put("Jumbo-64 Fuel Tank", reservoir);
		payloadList.put("Custom payload", 0f);
		payloadMenu.setItems(FXCollections.observableArrayList(payloadList.keySet()));
		ChangeListener<String> changeListener = (observableValue, oldValue, newValue) -> {
			if (newValue != null) {
				Settings.payload = payloadList.get(newValue);
				payloadText.setText(String.valueOf(Settings.payload));
			}
		};
		payloadMenu.getSelectionModel().selectedItemProperty().addListener(changeListener);

		payloadText.setText(String.valueOf(Settings.payload));
	}

	public static void main(String[] args) {
		FilesReader.readAllData();
		Settings.print();
		/*Rocket r = new Rocket();
		r.randomize();
		System.out.println(r.toString());
		System.out.println("dv:"+r.getDeltaV());
		System.out.println("cost:"+r.getCost());
		System.out.println("mintwr:"+r.getMinTWR());
		System.out.println("maxtwr:"+r.getMaxTWR());
		System.out.println("score:"+r.getScore());*/
		launch(args);
	}

	public void buttonBuildClicked() {
		WebEngine webEngine = webView.getEngine();

		Path path = Paths.get("rocket.html");

		try {
			if(Files.exists(path))
				Files.delete(path);

			Rocket r = Genetic.createRocket();
			/*System.out.println(r.toString());
			System.out.println("dv:"+r.getDeltaV());
			System.out.println("cost:"+r.getCost());
			System.out.println("mintwr:"+r.getMinTWR());
			System.out.println("maxtwr:"+r.getMaxTWR());
			System.out.println("score:"+r.getScore());*/
			String content = HTML.createRocketHTML(r);
			Files.write(path, content.getBytes(), StandardOpenOption.CREATE_NEW);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		webEngine.load(path.toUri().toString());

		Settings.print();
		//payload mass fix
		payloadText.setText(String.valueOf(Settings.payload));
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
			Slider_deltavcost.setMax(100000);
			Slider_deltavcost.setBlockIncrement(1000);
			Slider_deltavcost.setMajorTickUnit(25000);
		}
		else{
			Settings.cost = 0;
			Settings.dv = (int) Slider_deltavcost.getValue();
			Label_deltavcost.setText("Target DeltaV : "+ Settings.dv);
			Slider_deltavcost.setMax(10000);
			Slider_deltavcost.setBlockIncrement(100);
			Slider_deltavcost.setMajorTickUnit(2500);
		}
	}

	public void mintwrChangedValue() {
		Settings.mintwr = (float) Slider_mintwr.getValue();
		if(Settings.mintwr==0)
			Label_mintwr.setText("No Minimum TWR");
		else
			Label_mintwr.setText("Minimum TWR : "+ String.format("%2.1f", Settings.mintwr));
	}

	public void maxtwrChangedValue() {
		Settings.maxtwr = (float) Slider_maxtwr.getValue();
		if(Settings.maxtwr==0)
			Label_maxtwr.setText("No Maximum TWR");
		else
			Label_maxtwr.setText("Maximum TWR : "+ String.format("%2.1f", Settings.maxtwr));
	}

	public void nbmaxftChangedValue() {
		Settings.nbmaxft = Spinner_nbmaxft.getValue();
		//System.out.println("nbmaxft:"+Settings.nbmaxft);
	}

	public void nbmaxstagesChangedValue() {
		Settings.nbmaxstages = Spinner_nbmaxstages.getValue() +1;
		//System.out.println("nbmaxstages:"+Settings.nbmaxstages);
	}

	public void payloadChanged() {
		if(!payloadText.getText().equals("")) {
			Settings.payload = Float.parseFloat(payloadText.getText());
			System.out.println(Settings.payload);
		}
		else{
			Settings.payload=0f;
		}
	}
}
