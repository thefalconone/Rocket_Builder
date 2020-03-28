package Rocket.Model;

import java.util.ArrayList;
import java.util.Random;

public class Rocket {

	private ArrayList<Stage> stages;
	private static Random random = new Random();

	public Rocket(){

		this.stages = new ArrayList<>();

		Stage payload = new Stage(Settings.payload, Settings.payload);
		stages.add(payload);
	}

	@Override
	public String toString() {
		StringBuilder val = new StringBuilder();
		for(Stage stage : stages)
			val.append("\nstage=").append(stage.toString());
		return "Rocket{" +
				val +
				"\n}";
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

			//entre 1 et 4 moteurs
			//int nbEngines = random.nextInt(4)+1;
			//aussi décmmenter stage.mutate pour nbengine
			int nbEngines=1;
			Stage newStage = new Stage(ft, e, nbEngines, upperStagesMass);
			this.addStage(newStage);
			upperStagesMass += newStage.getTotalMass() - upperStagesMass;
		}
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

		for(int i=1; i<stages.size(); i++) {
			deltaV += stages.get(i).getDeltaV();
		}

		return deltaV;
	}

	public int getCost(){
		int cost=0;

		for(int i=1; i<stages.size(); i++) {
			cost += stages.get(i).getCost();
		}

		return cost;
	}

	public float getMinTWR(){
		float minTWR=Integer.MAX_VALUE;

		for(int i=1; i<stages.size(); i++) {
			float stageTWR = stages.get(i).getMinTWR();
			if (minTWR > stageTWR)
				minTWR = stageTWR;
		}
		if(minTWR==Integer.MAX_VALUE)
			minTWR=0;

		return minTWR;
	}

	public float getMaxTWR(){
		float maxTWR=0;

		for(int i=1; i<stages.size(); i++) {
			float stageTWR = stages.get(i).getMaxTWR();
			if (maxTWR < stageTWR)
				maxTWR = stageTWR;
		}

		return maxTWR;
	}

	private static int print = 1, iter=0;

	private boolean canprint(){
		return print==1;
	}

	/*public void printscore(float score){
		if (canprint())
		System.out.println(iter++ +" "+ String.format("%.2f", score));
	}*/

	public float getScore(){

		float dv = getDeltaV(), minTWR=getMinTWR(), maxTWR=getMaxTWR(), cost = getCost();
		float score;

		//no dividing by 0
		if(cost==0 || dv==0)
			return 0;

		//-----------------MAIN SCORE--------------------

		//max = 1, min = .01
		// ( e^( (-(goal-real)^2)/(goal^2) ) +.01 )^2²&

		//target dv, best cost
		if(Settings.cost==0) {
			//insufficient dv
			if(dv<Settings.dv)
				score = dv/Settings.dv;
			//sufficient dv
			else//1=max dv
				//the lower the cost the higher the score
				score = 1 + 1000/cost;
		}
		//target cost, best dv
		else {//Setting.dv==0
			//cost too high
			if(cost>Settings.cost)
				score = Settings.cost/cost;
			//good cost
			else//1=best cost
				//the higher the dv the higher the score
				score = 1 + dv;
		}
		//printscore(score);

		//-----------------PENALTIES---------------------

		//not punishing minTWR=0 because score would be 0
		if(minTWR!=0 && minTWR<Settings.mintwr)
			score *= minTWR/Settings.mintwr;
		if(maxTWR!=0 && Settings.maxtwr!=0 && maxTWR>Settings.maxtwr)
			score *= Settings.maxtwr/maxTWR;
		//printscore(score);
		print--;

		return score;
	}
}
