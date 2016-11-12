package examples.dynamicrecursive;

import java.util.Arrays;

public class ChildRunUpStairCase {

	public static long runUp(int currentSteps, int totalSteps) {
		if (currentSteps >= totalSteps) {
			return 0;
		} else {
			long stepWithOne = runUp(currentSteps + 1, totalSteps) + 1;
			long stepWithTwo = runUp(currentSteps + 2, totalSteps) + 1;
			long stepWithThree = runUp(currentSteps + 3, totalSteps) + 1;
			return stepWithOne + stepWithTwo + stepWithThree;
		}
	}

	public static long runUpDP(int currentSteps, int totalSteps, long cachedSteps[]) {
		if (currentSteps >= totalSteps) {
			return 0;
		} else {
			int posicSteps1 = currentSteps + 1;
			int posicSteps2 = currentSteps + 2;
			int posicSteps3 = currentSteps + 3;

			long stepWithOne = 0;
			long stepWithTwo = 0;
			long stepWithThree = 0;
			if (posicSteps1 < totalSteps && cachedSteps[posicSteps1] != -1) {
				stepWithOne = cachedSteps[posicSteps1] + 1;
			} else {
				stepWithOne = runUpDP(posicSteps1, totalSteps, cachedSteps) + 1;

			}

			if (posicSteps2 < totalSteps && cachedSteps[posicSteps2] != -1) {
				stepWithTwo = cachedSteps[posicSteps2] + 1;
			} else {
				stepWithTwo = runUpDP(posicSteps2, totalSteps, cachedSteps) + 1;
			}

			if (posicSteps3 < totalSteps && cachedSteps[posicSteps3] != -1) {
				stepWithThree = cachedSteps[posicSteps3] + 1;
			} else {
				stepWithThree = runUpDP(posicSteps3, totalSteps, cachedSteps) + 1;
			}

			cachedSteps[currentSteps] = stepWithOne + stepWithTwo + stepWithThree;
			return stepWithOne + stepWithTwo + stepWithThree;
		}
	}

	public static void main(String[] args) {
		int TotalSteps = 20;
		int currentSteps = 0;
		long start = System.currentTimeMillis();
		long countSteps = runUp(currentSteps, TotalSteps);
		long stop = System.currentTimeMillis();
		System.out.println("steps:" + TotalSteps + " Total possibilities:" + countSteps + " time=" + (stop - start) + " ms");

		int TotalStepsDP = 40;
		long cachedSteps[] = new long[TotalStepsDP];
		long startDP = System.currentTimeMillis();
		Arrays.fill(cachedSteps, -1);
		long countStepsDP = runUpDP(currentSteps, TotalStepsDP, cachedSteps);
		long stopDP = System.currentTimeMillis();
		System.out.println("steps:" + TotalStepsDP + " Total possibilities:" + countStepsDP + " time=" + (stopDP - startDP) + " ms");
	}

}
