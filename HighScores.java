/**
 * Calls <code>RandomScoresGenerator</code> to create an integer array of size 100_000
 * containing random values from 0 to 100_000_000. The array will be used to analyze
 * the duration of execution for the Selection Sort algorithm.
 * @author Logan Duck
 * @see <code>RandomScoresGenerator.java</code>
 */
public class HighScores {
	/**
	 * Performs a selection sort on an integer array.
	 * @param scores - the array of scores to sort.
	 * @return the sorted integer array.
	 */
	public int[] selectionSort(int[] scores) {
		if (scores == null) {
			System.out.println("Null input. Program exiting...");
			System.exit(1);
		}

		if (scores.length == 1) {
			return scores;
		}

		for (int i = 0; i < scores.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < scores.length; j++) {
				if (scores[j] < scores[min]) {
					min = j;
				}
			}
			int temp = scores[min];
			scores[min] = scores[i];
			scores[i] = temp;
		}
		return scores;
	}

	/**
	 * Performs a merge sort on an integer array.
	 * @param scores - the array of scores to sort.
	 * @param left - left index.
	 * @param right - right index.
	 * @return the sorted integer array.
	 */
	public int[] mergeSort(int[] scores, int left, int right) {
		if (scores == null) {
			System.out.println("Null input. Program exiting...");
			System.exit(1);
		}

		if (scores.length == 1) {
			return scores;
		}

		if (left < right) {
			int middle = (left + right) / 2;
			mergeSort(scores, left, middle);
			mergeSort(scores, middle + 1, right);
			merge(scores, left, middle, right);
		}
		return scores;
	}

	/**
	 * Merges the divided integer array.
	 * @param scores - unsorted integer array.
	 * @param left - left index.
	 * @param middle - middle index of array.
	 * @param right - right index.
	 */
	private void merge(int[] scores, int left, int middle, int right) {
		int sizeL = middle - left + 1;
		int sizeR = right - middle;

		int[] L = new int[sizeL];
		int[] R = new int[sizeR];

		for (int i = 0; i < sizeL; i++)
			L[i] = scores[left + i];
		for (int i = 0; i < sizeR; i++)
			R[i] = scores[middle + 1 + i];

        int i = 0, j = 0;
        int k = left;

        while (i < sizeL && j < sizeR) {
            if (L[i] <= R[j]) {
                scores[k] = L[i]; i++;
            }
            else {
                scores[k] = R[j]; j++;
            }
            k++;
        }

        while (i < sizeL) {
            scores[k] = L[i];
            i++;
            k++;
        }

        while (j < sizeR) {
            scores[k] = R[j];
            j++;
            k++;
        }
	}

	/**
	 * Prints the top 5, highest, scores in the sorted list.
	 * @param list - the list of scores.
	 */
	private static void printTop5Scores(int[] scores) {
		if (scores == null) {
			System.out.println("Null input. Program exiting...");
			System.exit(1);
		}

		if (scores.length >= 5) {
			System.out.println("***** Top 5 Scores *****");
			int rank = 1;
			for (int i = scores.length - 1; i > scores.length - 6; i--) {
				System.out.println("   " + (rank++) + ") " + scores[i]);
			}
			System.out.println();
		} else {
			System.out.println("Unable to print top 5 scores. Array length too small.");
		}
	}

	public static void main(String[] args) {
		// generate random scores from [RandomScoresGenerator] and create an integer array of [scores].
		RandomScoresGenerator generator = new RandomScoresGenerator();
		int[] scores = generator.getRandomScores();

		// set algorithm's array
		int[] selectionSortArray = scores;
		int[] mergeSortArray = scores;

		HighScores hs = new HighScores();

		/*
		 * Variables used to calculate the algorithm's duration of execution by getting the current
		 * values of the JVM's time source.
		 */
		long startTime, endTime, selectionSortDuration, mergeSortDuration;

		System.out.println("Executing Selection Sort algorithm...");
		startTime = System.nanoTime();
		selectionSortArray = hs.selectionSort(selectionSortArray);
		endTime = System.nanoTime();
		selectionSortDuration = (endTime - startTime);
		System.out.println("Selection Sort duration: " + (selectionSortDuration / 1000000) + " milliseconds.");

		System.out.println("\nExecuting Merge Sort algorithm...");
		startTime = System.nanoTime();
		mergeSortArray = hs.mergeSort(mergeSortArray, 0, mergeSortArray.length - 1);
		endTime = System.nanoTime();
		mergeSortDuration = (endTime - startTime);
		System.out.println("Merge Sort duration: " + (mergeSortDuration / 1000000) + " milliseconds.");

		// determine the recommended algorithm for use
		if (selectionSortDuration < mergeSortDuration) {
			System.out.println("\nSelection Sort is the recommended algorithm to use.\n");
		} else {
			System.out.println("\nMerge Sort is the recommended algorithm to use.\n");
		}

		printTop5Scores(scores);
	}
}