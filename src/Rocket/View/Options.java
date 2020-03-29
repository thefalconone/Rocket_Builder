package Rocket.View;

import Rocket.Model.Settings;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Options extends Application {

	public TextField textField_nbpop;
	public TextField textField_nbgen;
	public TextField textField_ratiokill;
	public TextField textField_nbmut;

	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("Options.fxml"));
		stage.setTitle("Options");
		stage.getIcons().add(new Image("file:icon.png"));
		stage.setScene(new Scene(root, 600, 160));
		stage.show();
	}

	@FXML
	public void initialize(){
		textField_nbpop.setText(String.valueOf(Settings.nbpop));
		textField_nbgen.setText(String.valueOf(Settings.nbgen));
		textField_ratiokill.setText(String.valueOf((int)(Settings.ratiokill*100)));
		textField_nbmut.setText(String.valueOf(Settings.nbmut));
	}

	public void optionsOkPressed() {
		Settings.nbpop = Integer.parseInt(textField_nbpop.getText());
		Settings.ratiokill = Integer.parseInt(textField_ratiokill.getText())/100.0f;
		Settings.nbgen = Integer.parseInt(textField_nbgen.getText());
		Settings.nbmut = Integer.parseInt(textField_nbmut.getText());

		Settings.print();

		Stage stage = (Stage) textField_nbpop.getScene().getWindow();
		stage.close();
	}

	public void optionsCancelPressed() {
		Stage stage = (Stage) textField_nbpop.getScene().getWindow();
		stage.close();
	}

	public void nbpopChanged() {
	}

	public void nbgenChanged() {
	}

	public void ratiokillChanged() {
	}

	public void nbmutChanged() {
	}
}
