package Rocket.Controller;

import Rocket.Model.*;

import java.io.File;

public class HTML {

	public static String createRocketHTML(Rocket rocket){

		//en-tête html avec le début du tableau
		StringBuilder html = new StringBuilder("<!DOCTYPE html>\n<html>\n	<meta charset=\"utf-8\"/>\n	<link rel=\"stylesheet\" href=\"data/style.css\" />\n	<table>\n");
		html.append("	<tr>\n		<td colspan=\"1\" class=\"stats\">Stats:</td>\n		<td>").append(rocket.getDeltaV()).append(" Δv<br>").append(rocket.getCost()).append(" $<br>minimum TWR: ").append(rocket.getMinTWR()).append("<br>maximum TWR: ").append(rocket.getMaxTWR()).append("\n </tr>");

		//insertion du payload
		html.append("\n	<tr>\n      <td colspan=\"2\" id=\"bottom-space\"></td>\n </tr>");
		html.append("\n	<tr>\n		<td class=\"stats\">Payload</td>\n		<td id=\"payload\">");
		html.append(payload());
		html.append("</td>\n	</tr>\n");

		for(int i=1; i<rocket.getStagesSize(); i++)
			html.append(stageHTML(rocket.getStage(i), i));

		if(rocket.getStagesSize()==0)
			html.append("<p>La fusée est vide</p>\n");

		html.append("\n	<tr>\n      <td colspan=\"2\" class=\"stage\"></td>\n </tr>");
		//fin du tableau
		html.append("	</table>\n</html>");


		return html.toString();
	}

	//affichage du payload
	private static String payload() {
		float satellite = (float) (0.02*2 + 0.01 + 0.04), capsule = (float) (2.72 + 1.3 + 0.3), reservoir = (float) 36.0;
		String html = "";

		if(Settings.payload==satellite)//2 solar panels + 1 battery bank + 1 probodobodyne OKTO2
			html += "Small satellite: "+Settings.payload+" tonnes";
		else if(Settings.payload==capsule)
			html += "Mk1-3 Command Pod: "+Settings.payload+" tonnes";//MK3 capsule + heatshield + parachute
		else if(Settings.payload==reservoir)
			html += "Rockomax Jumbo-64 Fuel Tank: "+Settings.payload+" tonnes";//Rockomax Jumbo-64 Fuel Tank
		else
			html += "Custom payload:"+Settings.payload+" tonnes";

		return html;
	}

	private static String stageHTML(Stage stage, int index){

		StringBuilder html = new StringBuilder();
		//début de la ligne
		html.append("	<tr>\n		<td colspan=\"2\" class=\"stage\">Stage ").append(index).append("</td>\n	</tr>\n");

		//remplissage du tableau
		for(FuelTank ft : stage.getFuelTanks())
			html.append(fuelTankHTML(ft));
		html.append(engineHTML(stage.getEngine()));

		return html.toString();
	}

	private static String engineHTML(Engine e){

		String html = "";
		File imageFile = new File("data/Parts/"+e.name+".png");
		if(imageFile.exists())
			html += "	<tr>\n		<td><img src=\"data/Parts/"+e.name+".png\"/></td>\n";
		else
			html += "	<tr>\n		<td><img src=\"data/Parts/"+e.name+".webp.png\"/></td>\n";

		//stats
		html += "		<td>"+e.name+"<br>	"+e.mass+"t "+e.thrust+"kN	"+e.isp+"s	"+e.cost+"$";
		if(e.lf!=0)
			html += "	"+e.lf+"lf";
		if(e.sf!=0)
			html += "	"+e.sf+"sf";
		if(e.ox!=0)
			html += "	"+e.ox+"ox";
		html += "</td>\n	</tr>\n";

		return html;
	}

	private static String fuelTankHTML(FuelTank ft){

		String html = "";

		File imageFile = new File("data/Parts/"+ft.name+".png");
		if(imageFile.exists())
			html += "	<tr>\n		<td><img src=\"data/Parts/"+ft.name+".png\"/></td>\n";
		else
			html += "	<tr>\n		<td><img src=\"data/Parts/"+ft.name+".webp.png\"/></td>\n";

		//stats
		html += "		<td>"+ft.name+"<br>	"+ft.drymass+"t ";

		if(ft.lf!=0)
			html += "	"+ft.lf+"lf";
		if(ft.ox!=0)
			html += "	"+ft.ox+"ox";
		if(ft.mo!=0)
			html += "	"+ft.mo+"mo";
		html += "</td>\n	</tr>\n";

		return html;
	}
}
