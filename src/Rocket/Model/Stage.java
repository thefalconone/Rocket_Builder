package Rocket.Model;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.StrictMath.log;

public class Stage {

	private float dryMass, totalMass;
	private ArrayList<FuelTank> fuelTanks = null;
	private Engine engine = null;
	private int nbEngines;
	private static Random random = new Random();

	public Stage(ArrayList<FuelTank> fuelTanks, Engine engine, int nbEngines, float upperStagesMass){
		this.fuelTanks = fuelTanks;
		this.engine = engine;
		this.nbEngines = nbEngines;
		this.calculateDryMass(upperStagesMass);
		this.calculateTotalMass(upperStagesMass);
	}

	public Stage(float dryMass, float totalMass){
		this.dryMass=dryMass;
		this.totalMass=totalMass;
	}

	public ArrayList<FuelTank> getFuelTanks() {
		return fuelTanks;
	}

	public Engine getEngine() {
		return engine;
	}

	@Override
	public String toString() {
		return "Stage{" +
				"dryMass=" + dryMass +
				", totalMass=" + totalMass +
				", fuelTanks=" + fuelTanks +
				", engine=" + engine +
				", nbEngines=" + nbEngines +
				'}';
	}

	public void mutate(){

		//System.out.println(this.toString());

		if(random.nextInt(2)==0) {//50% chance
			FuelTank randFt = FilesReader.allFuelTanks.get(random.nextInt(FilesReader.allFuelTanks.size()));
			if(fuelTanks.size()!=0)
				fuelTanks.set(random.nextInt(fuelTanks.size()), randFt);
		}
		else{
			engine = FilesReader.allEngines.get(random.nextInt(FilesReader.allEngines.size()));
			nbEngines = random.nextInt(4)+1;
		}

	}

	private void calculateTotalMass(float upperStagesMass){

		totalMass = upperStagesMass;
		totalMass += engine.getTotalMass() * nbEngines;

		for (FuelTank f : this.fuelTanks) {
			totalMass += f.getTotalMass();
		}
	}

	private void calculateDryMass(float upperStagesMass){

		dryMass = upperStagesMass;

		switch (engine.type){
			case liquid://consume lf and ox
				for(FuelTank f : fuelTanks)
					dryMass += f.getTotalMass() - f.getLfMass() - f.getOxMass();
				break;
			case solid://consume nothing
				for(FuelTank f : fuelTanks)
					dryMass += f.getTotalMass();
				break;
			case nuclear://consume lf
				for(FuelTank f : fuelTanks)
					dryMass += f.getTotalMass() - f.getLfMass();
				break;
			case monoprop://consume mo
				for(FuelTank f : fuelTanks)
					dryMass += f.getTotalMass() - f.getMoMass();
				break;
		}
	}

	public float getDryMass() {
		return dryMass;
	}

	public float getTotalMass(){
		return totalMass;
	}

	public int getCost(){

		int cost = engine.cost*nbEngines;

		for (FuelTank f : this.fuelTanks) {
			cost += f.cost;
		}

		return cost;
	}

	public float getDeltaV(){
		return (float) (log(this.getTotalMass()/this.getDryMass()) * engine.isp * 9.81);
	}

	public float getMinTWR(){

		float minTWR;

		if(this.getDryMass() == this.getTotalMass())
			minTWR=0;
		else
			minTWR = (float) (engine.thrust * nbEngines / (this.getTotalMass()*9.81));

		return minTWR;
	}

	public float getMaxTWR(){

		float maxTWR=0;

		if(this.getDryMass() != this.getTotalMass())
			maxTWR = (float) (engine.thrust * nbEngines / (this.getDryMass()*9.81));

		return maxTWR;
	}
}
