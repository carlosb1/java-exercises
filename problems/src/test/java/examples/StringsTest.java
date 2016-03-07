package examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

public class StringsTest {

	@Test
	public void testUniqueCharactersTrue() {
		assertTrue(Strings.UniqueCharacters("abcde"));
	}

	@Test
	public void testUniqueCharactersFalse() {
		assertFalse(Strings.UniqueCharacters("aa"));
	}

	@Test
	public void testIsPermutationTrue() {
		assertTrue(Strings.IsPermutation("hello", "ollaaseh"));
	}

	@Test
	public void testIsPermutationFalse() {
		assertFalse(Strings.IsPermutation("hello", "olla"));
	}

	@Test
	public void testReplaceCharactersWithPercent20() {

		char[] input = { 'M', 'r', ' ', 'J', 'o', 'h', 'n', ' ', 'S', 'm', 'i', 't', 'h', ' ', ' ', ' ', ' ' };
		char[] output = { 'M', 'r', '%', '2', '0', 'J', 'o', 'h', 'n', '%', '2', '0', 'S', 'm', 'i', 't', 'h' };

		assertEquals(new String(Strings.ReplacePercent20(input)), new String(output));
	}

	@Test
	public void testCompressionCount() {
		String originalMsg = "aabcccccaaa";
		String resultMsg = "a2b1c5a3";
		assertEquals(Strings.compress(originalMsg), resultMsg);

	}

	public int[][] initMatrix() {
		Random random = new Random();
		int[][] array = new int[10][10];
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
		int[][] inputMatrix = initMatrix();
		printMatrix(inputMatrix);
		Strings.rotate(inputMatrix);
		printMatrix(inputMatrix);
	}

}
