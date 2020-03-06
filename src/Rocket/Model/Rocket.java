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
			upperStagesMass += newStage.getTotalMass();
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

	private static int print = 1;

	private boolean canprint(){
		return print==1;
	}

	public void printscore(float score){
		if (canprint())
		System.out.println(score);
	}

	public float getScore(){
		/*
		int cost = getCost();
		float dv = getDeltaV(), minTWR=getMinTWR(), maxTWR=getMaxTWR();
		float score;

		//no dividing by 0
		if(cost==0 || dv==0)
			return 0;

		//-----------------MAIN SCORE--------------------

		// (  e^( -(goal-real)^2 ) +1 )^mod

		score = (float) Math.pow( Math.exp( -Math.pow(Settings.dv-dv, 2.0) )+1, Settings.moddeltav);
		printscore(score);

		//float avrgMinTWR = Settings.maxtwr - Settings.mintwr;
		//score *= Math.pow(minTWR/avrgMinTWR, Settings.modtwr);

		score *= Math.pow(100000.0/cost, Settings.modcost);
		//printscore(score);


		//-----------------PENALTIES---------------------
		if(dv<Settings.mindv)
			score *= 0.1;
		else if(Settings.maxdv!=-1 && dv>Settings.maxdv)
			score *= 0.1*Settings.maxdv/dv;
		//printscore(score);

		//not punishing minTWR=0 because score would be 0
		if(minTWR!=0 && minTWR<Settings.mintwr)
			score *= 0.1;
		if(maxTWR!=0 && Settings.maxtwr!=-1 && maxTWR>Settings.maxtwr)
			score *= 0.1*Settings.maxtwr/maxTWR;
		//printscore(score);

		if(cost<Settings.mincost)
			score *= 0.1*cost/Settings.mincost;
		else if(Settings.maxcost!=-1 && cost>Settings.maxcost)
			score *= 0.1*Settings.maxcost/cost;
		//printscore(score);

		return score;
		 */
		return 0;
	}
}
