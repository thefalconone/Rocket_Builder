package Rocket;

import java.util.Comparator;

public class ScoreSorter implements Comparator<Score> {

	@Override
	public int compare(Score score, Score t1) {
		return Float.compare(score.getScore(), t1.getScore());
	}
}
