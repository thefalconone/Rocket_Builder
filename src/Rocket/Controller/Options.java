package Rocket.Controller;

import Rocket.Model.Settings;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Options extends Application {

	@FXML
	public Spinner<Integer> Spinner_nbpop = new Spinner<>(0, 10000, Settings.nbpop);
	@FXML
	public Spinner<Integer> Spinner_ratiokill = new Spinner<>(0, 1, Settings.ratiokill);
	@FXML
	public Spinner<Integer> Spinner_nbgen = new Spinner<>(0, 10000, Settings.nbgen);
	@FXML
	public Spinner<Integer> Spinner_nbmut = new Spinner<>(0, Settings.nbpop, Settings.nbmut);

	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("../View/Options.fxml"));
		stage.setTitle("Options");
		stage.getIcons().add(new Image("file:icon.png"));
		stage.setScene(new Scene(root, 600, 300));
		stage.show();
	}

	@FXML
	public void initialize(){
		Spinner_nbpop.setPromptText(String.valueOf(Settings.nbpop));
		Spinner_ratiokill.setPromptText(String.valueOf(Settings.ratiokill));
		Spinner_nbgen.setPromptText(String.valueOf(Settings.nbgen));
		Spinner_nbmut.setPromptText(String.valueOf(Settings.nbmut));
	}

	public void optionsOkPressed() {
		Settings.nbpop = Spinner_nbpop.getValue();
		Settings.ratiokill = Spinner_ratiokill.getValue()/100.0f;
		Settings.nbgen = Spinner_nbgen.getValue();
		Settings.nbmut = Spinner_nbmut.getValue();

		Settings.print();

		Stage stage = (Stage) Spinner_ratiokill.getScene().getWindow();
		stage.close();
	}

	public void optionsCancelPressed() {
		Stage stage = (Stage) Spinner_ratiokill.getScene().getWindow();
		stage.close();
	}
}
