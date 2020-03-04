package Rocket.Controller;

import Rocket.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class MainWindow extends Application {

	public WebView webView;

	@Override
	public void start(javafx.stage.Stage stage) throws IOException {

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

	public void buttonBuildClicked() {
		WebEngine webEngine = webView.getEngine();
		webEngine.load(createRocketHTML(Genetic.createRocket()));
	}

	public String createRocketHTML(Rocket rocket){

		Path path = Paths.get("rocket.html");

		try {
			//créé le dossier si il existe pas
			File data = new File(Paths.get("data").toString());
			if(!data.exists()){
				if(!data.mkdir()){
					throw new IOException();
				}
			}

			//créé le fichier
			//en-tête html avec le début du tableau
			Files.delete(path);
			Files.write(path, "<!DOCTYPE html>\n<html>\n	<meta charset=\"utf-8\"/>\n	<link rel=\"stylesheet\" href=\"data/Parts_files/style.css\" />\n	<table>\n".getBytes(), StandardOpenOption.CREATE_NEW);
			Files.write(path, ("	<tr>\n		<td colspan=\"2\" id=\"stats\">Stats:</td>\n		<td>"+rocket.getDeltaV()+" Δv<br>"+rocket.getCost()+" $<br>minimum TWR: "+rocket.getMinTWR()+"<br>maximum TWR: "+rocket.getMaxTWR()+"<br>chargement: ").getBytes(), StandardOpenOption.APPEND);

			//insertion du payload
			payload(path);

			//début du tableau pour les étages
			Files.write(path, "</td>\n	</tr>\n".getBytes(), StandardOpenOption.APPEND);

			for(int i=1; i<rocket.getStagesSize(); i++)
				stageHTML(path, rocket.getStage(i), i);

			if(rocket.getStagesSize()==0)
				Files.write(path, "<p>La fusée est vide</p>\n".getBytes(), StandardOpenOption.APPEND);

			//fin du tableau
			Files.write(path, "	</table>\n</html>".getBytes(), StandardOpenOption.APPEND);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return "fusee.html";
	}

	//affichage du payload
	private void payload(Path path) throws IOException{
		float satellite = (float) (0.02*2 + 0.01 + 0.04), capsule = (float) (2.72 + 1.3 + 0.3), reservoir = (float) 36.0;

		if(Settings.payload==satellite)//2 solar panels + 1 battery bank + 1 probodobodyne OKTO2
			Files.write(path, ("petit satellite de "+Settings.payload+" tonnes").getBytes(), StandardOpenOption.APPEND);
		else if(Settings.payload==capsule)
			Files.write(path, ("capsule de "+Settings.payload+" tonnes\n").getBytes(), StandardOpenOption.APPEND);//MK3 capsule + heatshield + parachute
		else if(Settings.payload==reservoir)
			Files.write(path, ("grand réservoir de "+Settings.payload+" tonnes").getBytes(), StandardOpenOption.APPEND);//Rockomax Jumbo-64 Fuel Tank
		else
			Files.write(path, ("chargement custom de "+Settings.payload+" tonnes").getBytes(), StandardOpenOption.APPEND);
	}



	private void engineHTML(Path path, Engine e) throws IOException{

		Path imagePath = Paths.get("data", "Parts_files"+e.name+".png");
		File imageFile = new File(imagePath.getFileName().toString());
		if(imageFile.exists())
			Files.write(path, ("	<tr>\n		<td><img src=\"data/Parts_files/"+e.name+".png\"/></td>\n").getBytes(), StandardOpenOption.APPEND);
		else
			Files.write(path, ("	<tr>\n		<td><img src=\"data/Parts_files/"+e.name+".webp\"/></td>\n").getBytes(), StandardOpenOption.APPEND);

		//stats
		Files.write(path, ("		<td>"+e.name+"<br>	"+e.mass+" "+e.thrust+"kN	"+e.isp+"s	"+e.cost+"$").getBytes(), StandardOpenOption.APPEND);
		if(e.lf!=0)
			Files.write(path, ("	"+e.lf+"lf").getBytes(), StandardOpenOption.APPEND);
		if(e.sf!=0)
			Files.write(path, ("	"+e.sf+"lf").getBytes(), StandardOpenOption.APPEND);
		if(e.ox!=0)
			Files.write(path, ("	"+e.ox+"lf").getBytes(), StandardOpenOption.APPEND);
		Files.write(path, "</td>\n	</tr>\n".getBytes(), StandardOpenOption.APPEND);
	}

	private void fuelTankHTML(Path path, FuelTank ft) throws IOException{

		Path imagePath = Paths.get("data", "Parts_files"+ft.name+".png");
		File imageFile = new File(imagePath.getFileName().toString());
		if(imageFile.exists())
			Files.write(path, ("	<tr>\n		<td><img src=\"data/Parts_files/"+ft.name+".png\"/></td>\n").getBytes(), StandardOpenOption.APPEND);
		else
			Files.write(path, ("	<tr>\n		<td><img src=\"data/Parts_files/"+ft.name+".webp\"/></td>\n").getBytes(), StandardOpenOption.APPEND);

		//stats
		Files.write(path, ("		<td>"+ft.name+"<br>	"+ft.drymass+" "+ft.lf+"lf	"+ft.ox+"ox	"+ft.mo+"mo"+ft.cost).getBytes(), StandardOpenOption.APPEND);
	}

	private void stageHTML(Path path, Stage stage, int index) throws IOException{
		//début de la ligne
		Files.write(path, ("	<tr>\n		<td colspan=\"3\" class=\"stage\">Stage "+index+"</td>\n	</tr>\n").getBytes(), StandardOpenOption.APPEND);

		//remplissage du tableau
		for(FuelTank ft : stage.getFuelTanks())
			fuelTankHTML(path, ft);
		engineHTML(path, stage.getEngine());
	}
}
