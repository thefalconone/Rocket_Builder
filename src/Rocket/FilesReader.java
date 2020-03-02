package Rocket;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class FilesReader {

	public static ArrayList<Engine> allEngines;
	public static ArrayList<FuelTank> allFuelTanks;
	public static Settings settings;

	public static void readAllData(){
		FilesReader.allEngines = readEngines();
		FilesReader.allFuelTanks = readFuelTanks();
		FilesReader.settings = readSettings();
	}

	public static ArrayList<Engine> readEngines(){

		ArrayList<Engine> listEngines = new ArrayList<>();
		Path path = Paths.get("data","engine.txt");

		try {

			//si le fichier existe
			if(new java.io.File(path.toString()).exists()) {
				List<String> file = Files.readAllLines(path);
				ListIterator<String> lines = file.listIterator();

				//nb engines 99 max à cause du substring
				int nbEngines = Integer.parseInt(lines.next().substring(0,2));
				for (int i = 0; i < nbEngines-1; i++) {

					String name = lines.next();

					int cost = Integer.parseInt(lines.next().substring(7));

					float mass = Float.parseFloat(lines.next().substring(7));

					String type = lines.next().substring(13);
					FuelType fuelType = null;

					switch (type) {
						case "LiquidFuel":
							fuelType = FuelType.liquid;
							break;
						case "Nuclear":
							fuelType = FuelType.nuclear;
							break;
						case "SolidBooster":
							fuelType = FuelType.solid;
							break;
						case "MonoProp":
							fuelType = FuelType.monoprop;
							break;
					}

					int isp, thrust;
					int lf=0, ox=0, sf=0;
					//next one can be amount
					//amount can be solid fuel or liquid fuel and oxidizer
					String line1 = lines.next();
					String line2 = lines.next();

					if(line1.startsWith("amount") && line2.startsWith("amount")){
						lf = Integer.parseInt(line1.substring(9));
						ox = Integer.parseInt(line2.substring(9));
						isp = Integer.parseInt(lines.next().substring(8));
						thrust = Integer.parseInt(lines.next().substring(12));
					}else if(line1.startsWith("amount")){
						sf = Integer.parseInt(line1.substring(9));
						isp = Integer.parseInt(line2.substring(8));
						thrust = Integer.parseInt(lines.next().substring(12));
					}else{
						isp = Integer.parseInt(line1.substring(8));
						thrust = Integer.parseInt(line2.substring(12));
					}

					listEngines.add(new Engine(name, fuelType, mass, thrust, isp, cost, lf, ox, sf));

				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return listEngines;
	}

	public static ArrayList<FuelTank> readFuelTanks(){

		ArrayList<FuelTank> listFuelTanks = new ArrayList<>();
		Path path = Paths.get("data","fueltank.txt");

		try {

			//si le fichier existe
			if(new java.io.File(path.toString()).exists()) {
				List<String> file = Files.readAllLines(path);
				ListIterator<String> lines = file.listIterator();

				//nb engines 99 max à cause du substring
				int nbFuelTanks = Integer.parseInt(lines.next().substring(0,2));
				for (int i = 0; i < nbFuelTanks; i++) {

					String name = lines.next();

					int cost = Integer.parseInt(lines.next().substring(7));

					float drymass = Float.parseFloat(lines.next().substring(7));

					int lf=0, ox=0, mo=0;
					//next one can be amount
					//amount can be solid fuel or liquid fuel and oxidizer
					String line1 = lines.next();
					String line2 = lines.next();

					if(line1.startsWith("name = LiquidFuel") && line2.startsWith("name = Oxidizer")){
						lf = Integer.parseInt(lines.next().substring(9));
						ox = Integer.parseInt(lines.next().substring(9));
					}else if(line1.startsWith("name = LiquidFuel")){
						lf = Integer.parseInt(line2.substring(9));
					}else if(line1.startsWith("name = name = MonoPropellant")){
						mo = Integer.parseInt(line2.substring(9));
					}

					listFuelTanks.add(new FuelTank(name, drymass, cost, lf, ox, mo));

				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return listFuelTanks;
	}

	public static Settings readSettings(){

		Path path = Paths.get("data","settings.txt");

		try {

			//si le fichier existe pas
			if(!new java.io.File(path.toString()).exists()) {

				//créé le dossier si il existe pas
				java.io.File data = new java.io.File(Paths.get("data").toString());
				if(!data.exists()){
					if(!data.mkdir()){
						throw new IOException();
					}
				}

				//créé le fichier
				Files.write(path, ("0.5\n1\n1\n1\n1\n5\n4.32\n1000\n300\n3\n5\n100\n0\n-1\n0\n-1\n").getBytes(), StandardOpenOption.CREATE);
			}

			List<String> file = Files.readAllLines(path);
			ListIterator<String> lines = file.listIterator();

			return new Settings(
					Float.parseFloat(lines.next()),
					Float.parseFloat(lines.next()),
					Float.parseFloat(lines.next()),
					Float.parseFloat(lines.next()),
					Float.parseFloat(lines.next()),
					Float.parseFloat(lines.next()),
					Float.parseFloat(lines.next()),
					Integer.parseInt(lines.next()),
					Integer.parseInt(lines.next()),
					Integer.parseInt(lines.next()),
					Integer.parseInt(lines.next()),
					Integer.parseInt(lines.next()),
					Integer.parseInt(lines.next()),
					Integer.parseInt(lines.next()),
					Integer.parseInt(lines.next()),
					Integer.parseInt(lines.next())
			);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
