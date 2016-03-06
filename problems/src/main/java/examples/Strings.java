package examples;

public class Strings {

	public static boolean UniqueCharacters(String input) {
		for (int indexCharacter = 0; indexCharacter < input.length(); indexCharacter++) {
			for (int secondIndexCharacter = indexCharacter + 1; secondIndexCharacter < input.length(); secondIndexCharacter++) {
				if (input.charAt(indexCharacter) == input.charAt(secondIndexCharacter)) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean IsPermutation(String stringToCheck, String availableCharacters) {
		int pendingChars = stringToCheck.length();
		for (int index = 0; index < stringToCheck.length(); index++) {
			for (int indexAvailableChars = 0; indexAvailableChars < availableCharacters.length(); indexAvailableChars++) {
				if (stringToCheck.charAt(index) == availableCharacters.charAt(indexAvailableChars)) {
					availableCharacters.replaceFirst(CharToString(stringToCheck, index), "");
					pendingChars--;
					break;
				}
			}
		}
		return (pendingChars == 0);
	}

	private static String CharToString(String stringToCheck, int index) {
		return String.valueOf(stringToCheck.charAt(index));
	}

	private final static char[] NEWCHAR = { '%', '2', '0' };

	public static char[] ReplacePercent20(char[] input) {
		final int size = input.length;
		char[] resultArray = new char[size];
		boolean addChars = false;

		int index = 0;
		int indexCandidate = 0;
		while (index < size || indexCandidate < size) {
			char candidate = input[index];
			if (candidate == ' ') {
				for (int i = 0; i < NEWCHAR.length; i++) {
					if (indexCandidate >= size) {
						break;
					}
					resultArray[indexCandidate] = NEWCHAR[i];
					indexCandidate++;
				}
			} else {
				resultArray[indexCandidate] = input[index];
				indexCandidate++;
			}
			index++;

		}

		return resultArray;
	}

	public static Object compress(String originalMsg) {
		char[] chars = originalMsg.toCharArray();

		char charCompare = '\n';
		int countChars = 0;

		StringBuffer result = new StringBuffer();

		for (char currentChar : chars) {
			if (currentChar == charCompare) {
				countChars++;
			} else {
				if (countChars != 0) {
					result.append(charCompare).append(countChars);
				}
				countChars = 1;
				charCompare = currentChar;
			}

		}
		if (countChars != 0) {
			result.append(charCompare).append(countChars);
		}
		return result.toString();
	}

}
