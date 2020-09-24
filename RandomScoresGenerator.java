import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Generates random scores.
 * @author Logan Duck
 */
public class RandomScoresGenerator {
	/**
	 * Creates an integer array of randomly generated values from 0 to 100_000_000
	 * of size 100_000.
	 * @return the randomize value array.
	 */
	public int[] getRandomScores() {
		Random random = new Random();
		int[] scores = new int[100000];
		for (int i = 0; i < 100000; i++) {
			scores[i] = random.nextInt(100000000);
		}
		return scores;
	}
}