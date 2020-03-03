package Rocket.Model;

public class Score {

	private int index;
	private float score;

	public Score(int index, float score){
		this.index = index;
		this.score = score;
	}

	public float getScore() {
		return score;
	}

	public int getIndex() {
		return index;
	}
}
