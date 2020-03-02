package Rocket;

import java.util.ArrayList;
import java.util.Random;

public class Rocket {

	private ArrayList<Stage> stages;
	private static Random random = new Random();

	public Rocket(float payloadMass){

		this.stages = new ArrayList<>();

		Stage payload = new Stage(payloadMass, payloadMass);
		stages.add(payload);
	}

	@Override
	public String toString() {
		StringBuilder val = new StringBuilder();
		for(Stage stage : stages)
			val.append("\nstage=").append(stage.toString());
		return "Rocket{" +
				val +
				'}';
	}

	public void randomize(){
		int nbStages = random.nextInt(Settings.nbmaxstages)+1;
		float upperStagesMass = Settings.payload;

		for(int i=0; i<nbStages; i++){

			int allEnginesSize = FilesReader.allEngines.size();
			Engine e = FilesReader.allEngines.get(random.nextInt(allEnginesSize));

			ArrayList<FuelTank> ft = new ArrayList<>();
			int allFuelTanksSize = FilesReader.allFuelTanks.size();

			int nbFuelTanks = random.nextInt(Settings.nbmaxft)+1;
			for(int j=0; j<nbFuelTanks; j++)
				if (random.nextInt(3) > 0)//66% de chance
					ft.add(FilesReader.allFuelTanks.get(random.nextInt(allFuelTanksSize)));

			//                                          entre 1 et 4 moteurs
			Stage newStage = new Stage(ft, e, random.nextInt(4)+1, upperStagesMass);
			this.addStage(newStage);
			upperStagesMass += newStage.getTotalMass();
		}
		if(this.stages.size()==1)
			System.out.println("aaa");
	}

	public void addStage(Stage stage){
		this.stages.add(stage);
	}

	public Stage getStage(int index){
		return this.stages.get(index);
	}

	public void mutate(){
		//décaler de 1 pour le payload
		int rand = random.nextInt(stages.size()-1)+1;
		//System.out.println("size="+stages.size()+" rand="+rand);
		stages.get(rand).mutate();
	}

	public int getStagesSize(){
		return this.stages.size();
	}

	public float getDeltaV(){
		float deltaV=0;

		for(Stage stage : stages)
			deltaV += stage.getDeltaV();

		return deltaV;
	}

	public int getCost(){
		int cost=0;

		for(Stage stage : stages)
			cost += stage.getCost();

		return cost;
	}

	public float getMinTWR(){
		float minTWR=Integer.MAX_VALUE;

		for(Stage stage : stages) {
			float stageTWR = stage.getMinTWR();
			if (minTWR > stageTWR)
				minTWR = stageTWR;
		}

		return minTWR;
	}

	public float getMaxTWR(){
		float maxTWR=0;

		for(Stage stage : stages) {
			float stageTWR = stage.getMaxTWR();
			if (maxTWR < stageTWR)
				maxTWR = stageTWR;
		}

		return maxTWR;
	}

	public float getScore(){
		int cost = getCost();
		float dv = getDeltaV(), minTWR=getMinTWR(), maxTWR=getMaxTWR();
		float score;

		//no dividing by 0
		if(cost==0 || dv==0)
			return 0;

		//-----------------MAIN SCORE--------------------
		float avrgDv = Settings.maxdv - Settings.mindv;
		score = (float) Math.pow(100*dv/avrgDv, Settings.moddeltav);

		//float avrgMinTWR = Settings.maxtwr - Settings.mintwr;
		//score *= Math.pow(100*minTWR/avrgMinTWR, Settings.modtwr);

		float avrgCost = Settings.maxcost - Settings.mincost;
		score *= Math.pow(100*cost/avrgCost, Settings.modcost);


		//-----------------PENALTIES---------------------
		if(dv<Settings.mindv)
			score *= 0.1*dv/Settings.mindv;
		else if(dv>Settings.maxdv)
			score *= 0.1*Settings.maxdv/dv;

		//not punishing minTWR=0 because score would be 0
		if(minTWR!=0 && minTWR<Settings.mintwr)
			score *= 0.1*minTWR/Settings.mintwr;
		if(maxTWR!=0 && Settings.maxtwr!=-1 && maxTWR>Settings.maxtwr)
			score *= 0.1*Settings.maxtwr/maxTWR;

		if(cost<Settings.mincost)
			score *= 0.1*cost/Settings.mincost;
		else if(Settings.maxcost!=-1 && cost>Settings.maxcost)
			score *= 0.1*Settings.maxcost/cost;

		return score;
	}
}
