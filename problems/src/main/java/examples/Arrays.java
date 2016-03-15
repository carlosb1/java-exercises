package examples;

import java.util.ArrayList;
import java.util.List;

public class Arrays {
	public static int[][] rotate(int[][] inputMatrix) {
		int[][] array = new int[inputMatrix.length][inputMatrix[0].length];
		for (int i = 0; i < inputMatrix.length; i++) {
			for (int j = 0; j < inputMatrix[i].length; j++) {
				array[i][j] = inputMatrix[j][i];
			}
		}
		return array;

	}

	public static int[][] markZeros(int[][] inputMatrix) {
		int[][] array = new int[inputMatrix.length][inputMatrix[0].length];
		List<Integer> listOfRows = new ArrayList<Integer>();
		List<Integer> listOfCols = new ArrayList<Integer>();

		for (int i = 0; i < inputMatrix.length; i++) {
			for (int j = 0; j < inputMatrix[i].length; j++) {
				if (inputMatrix[i][j] == 0) {
					if (!listOfRows.contains(i)) {
						listOfRows.add(i);
						markRow(array, i);
					}
					if (!listOfCols.contains(j)) {
						listOfCols.add(j);
						markCol(array, j);
					}
				} else if (!listOfRows.contains(i) && !listOfCols.contains(j)) {
					array[i][j] = inputMatrix[i][j];
				}
			}
		}
		return array;
	}

	private static void markCol(int[][] inputMatrix, int j) {
		for (int i = 0; i < inputMatrix.length; i++) {
			inputMatrix[i][j] = 0;
		}
	}

	private static void markRow(int[][] inputMatrix, int i) {
		for (int j = 0; j < inputMatrix[i].length; j++) {
			inputMatrix[i][j] = 0;
		}
	}

}
