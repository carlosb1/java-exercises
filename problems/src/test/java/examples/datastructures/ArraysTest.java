package examples.datastructures;

import java.util.Random;

import org.junit.Test;

public class ArraysTest {

	public int[][] initMatrix(int size) {
		Random random = new Random();
		int[][] array = new int[size][size];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				array[i][j] = random.nextInt(101);
			}
		}
		return array;
	}

	public void printMatrix(int[][] inputMatrix) {
		for (int i = 0; i < inputMatrix.length; i++) {
			System.out.print("[");
			for (int j = 0; j < inputMatrix[i].length; j++) {
				System.out.print(inputMatrix[i][j]);
				if (j < inputMatrix[i].length - 1) {
					System.out.print(",");
				}
			}
			System.out.println("]");
		}

	}

	@Test
	public void testRotateMatrix() {
		int[][] inputMatrix = initMatrix(10);
		// printMatrix(inputMatrix);
		int rotated[][] = Arrays.rotate(inputMatrix);
		// System.out.println("---------------");
		// printMatrix(rotated);
	}

	@Test
	public void testMarkZerosRowsCols() {
		int[][] inputMatrix = initMatrix(10);
		printMatrix(inputMatrix);
		int marked[][] = Arrays.markZeros(inputMatrix);
		System.out.println("---------------");
		printMatrix(marked);
	}

}
