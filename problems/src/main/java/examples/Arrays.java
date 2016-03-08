package examples;

public class Arrays {
	public static int[][] rotate(int[][] inputMatrix, int size) {
		int[][] array = new int[size][size];
		for (int i = 0; i < inputMatrix.length; i++) {
			for (int j = 0; j < inputMatrix[i].length; j++) {
				array[i][j] = inputMatrix[j][i];
			}
		}
		return array;

	}

}
