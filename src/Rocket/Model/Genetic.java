package Rocket.Model;

import java.util.ArrayList;
import java.util.Random;

public class Genetic {


	public static Rocket createRocket(){

		ArrayList<Rocket> population = new ArrayList<>();
		Random random = new Random();

		//----------CREATING POPULATION OF RANDOM ROCKETS-----------
		for(int i=0; i<Settings.nbpop; i++){
			Rocket rocket = new Rocket();
			rocket.randomize();
			population.add(rocket);
		}

		/*for(int i=0; i<Settings.nbpop; i++){
			System.out.println(population.get(i).toString());
		}*/

		Rocket bestRocket = null;

		for(int generation=0; generation<Settings.nbgen; generation++){

			//---------------------SCORING-------------------------
			ArrayList<Score> scores = new ArrayList<>();
			for(int i=0; i<Settings.nbpop; i++) {
				float score = population.get(i).getScore();
				scores.add(new Score(i, score));
			}

			scores.sort(new ScoreSorter());
			Score bestScore = scores.get(Settings.nbpop-1);
			System.out.println(String.format("%.2e", bestScore.getScore()));
			bestRocket = population.get(bestScore.getIndex());

			//----------------------SELECTION----------------------
			int kills=0, nbToKill=(int) (Settings.nbpop * Settings.ratiokill);

			while(kills < nbToKill) {

				int rand = random.nextInt(scores.size());
				Score individuScore = scores.get(rand);

				rand = random.nextInt(scores.size());
				Score randomScore = scores.get(rand);

				//0=nul=0% chance de vivre
				//99=meilleur=99% chance de vivre
				if(individuScore.getScore() <= randomScore.getScore()) {//kill
					scores.remove(individuScore);
					kills++;
				}
			}

			//----------------PAIRING/BREEDING------------------
			ArrayList<Rocket> newPopulation = new ArrayList<>();
			for(int i=0; i<Settings.nbpop/2; i++) {

				Score score1 = scores.get(random.nextInt(scores.size()));
				Score score2 = scores.get(random.nextInt(scores.size()));
				Score score3 = scores.get(random.nextInt(scores.size()));
				scores.remove(score1);

				Rocket individual1 = population.get(score1.getIndex());
				Rocket individual2 = population.get(score2.getIndex());
				Rocket individual3 = population.get(score3.getIndex());
				Rocket child1 = new Rocket();
				Rocket child2 = new Rocket();

				for(int j=1; j<Settings.nbmaxstages; j++) {
					if (random.nextInt(2) == 0) {//1 & 2
						if (j < individual1.getStagesSize()) {
							child1.addStage(individual1.getStage(j));
						}
							if (j < individual2.getStagesSize()) {
							child2.addStage(individual2.getStage(j));
						}
					}
					else {//                             1 & 3
						if (j < individual3.getStagesSize()) {
							child1.addStage(individual3.getStage(j));
						}
						if (j < individual1.getStagesSize()) {
							child2.addStage(individual1.getStage(j));
						}
					}
				}
				newPopulation.add(child1);
				newPopulation.add(child2);
			}
			if(!newPopulation.isEmpty())
				population=newPopulation;

			//---------------------MUTATIONS----------------------
			for(int i=0; i< Settings.nbmut; i++){
				Rocket individual = population.get(random.nextInt(Settings.nbpop));
				individual.mutate();
			}
		}

		return bestRocket;
	}
}
