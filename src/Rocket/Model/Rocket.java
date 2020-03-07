package Rocket.Model;

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

			//                                          entre 1 et 4 moteurs
			Stage newStage = new Stage(ft, e, random.nextInt(4)+1, upperStagesMass);
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

	public void printscore(float score){
		if (canprint())
		System.out.println(iter++ +" "+ String.format("%.2f", score));
	}

	public float getScore(){

		int cost = getCost();
		float dv = getDeltaV(), minTWR=getMinTWR(), maxTWR=getMaxTWR();
		float score;

		//no dividing by 0
		if(cost==0 || dv==0)
			return 0;

		//-----------------MAIN SCORE--------------------

		//max = 1, min = .01
		// ( e^( (-(goal-real)^2)/(goal^2) ) +.01 )^2²&

		if(Settings.cost==0) {
			double upper = -Math.pow(Settings.dv-dv, 2);
			double lower = Math.pow(Settings.dv, 2);
			double exp = Math.exp(upper/lower);
			score = (float) Math.pow(exp+.01, Settings.moddeltav);

			score *= Math.pow(1f/cost, Settings.modcost);//penalty to lower cost
		}
		else {//Setting.dv==0
			double upper = -Math.pow(Settings.cost-cost, 2);
			double lower = Math.pow(Settings.cost, 2);
			double exp = Math.exp(upper/lower);
			score = (float) Math.pow(exp+.01, Settings.modcost);

			score *= Math.pow(10*dv, Settings.moddeltav);//incentive to raise dv
		}
		//printscore(score);

		//-----------------PENALTIES---------------------

		//not punishing minTWR=0 because score would be 0
		if(minTWR!=0 && minTWR<Settings.mintwr)
			score *= 0.01*minTWR/Settings.mintwr;
		if(maxTWR!=0 && Settings.maxtwr!=0 && maxTWR>Settings.maxtwr)
			score *= 0.01*Settings.maxtwr/maxTWR;
		//printscore(score);
		print--;

		return score;
	}
}
