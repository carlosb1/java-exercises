package examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

	@Test
	public void testSubstring() {
		assertTrue(Strings.isSubstring("Helloworld".toCharArray(), "world".toCharArray()));
	}

	@Test
	public void testIsRotationString() {
		assertTrue(Strings.isRotation("waterbottle".toCharArray(), "erbottlewat".toCharArray()));
	}

}
