
public final class Hash {

	public static void main(String[] args) {

		String h0 = "01100111010001010010001100000001";
		String h1 = "11101111110011011010101110001001";
		String h2 = "10011000101110101101110011111110";
		String h3 = "00010000001100100101010001110110";
		String h4 = "11000011110100101110000111110000";

		String A = "";
		String B = "";
		String C = "";
		String D = "";
		String E = "";

		String stringToHash = "";

		char[] stringToHashCaracters = null;

		int[] asciiCodeForStringToHash = new int[100];

		String[] binaryStrings = new String[100];

		String binarStringsConcatenated = "";

		int firstBinaryStringLength = 0;

		int nrOf512bitBlocks = 0;

		String[] blocks512bits = new String[100];

		String[][] words32Bit = new String[100][80];

		try {
			stringToHash = args[0];

			System.out.println(" ---- Secure Hash Algorithm 1 sau SHA-1 ----\n");

			System.out.println(" ----> Pasul 1: Initializare de variabile\n");
			System.out.println("\th0 = " + h0);
			System.out.println("\th1 = " + h1);
			System.out.println("\th2 = " + h2);
			System.out.println("\th3 = " + h3);
			System.out.println("\th4 = " + h4);

			System.out.println("\n ----> Pasul 2: Alegere cuvant care va fi hashuit");
			System.out.println("\n\tcuvant = '" + args[0] + "'");

			System.out.println("\n ----> Pasul 3: Spargere in litere");
			System.out.print("\n\t ");
			stringToHashCaracters = stringToHash.toCharArray();
			for (int i = 0; i < stringToHashCaracters.length; i++) {
				System.out.print(stringToHashCaracters[i] + "  ");
			}
			System.out.print("\n");

			System.out.println("\n ----> Pasul 4: Conversia literelor in cod ASCII");
			System.out.print("\n\t ");
			for (int j = 0; j < stringToHashCaracters.length; j++) {
				asciiCodeForStringToHash[j] = stringToHashCaracters[j];
				System.out.print(asciiCodeForStringToHash[j] + "  ");

			}
			System.out.print("\n");

			System.out.println("\n ----> Pasul 5: Conversia in numere binare");
			System.out.print("\n\t ");
			for (int k = 0; k < stringToHashCaracters.length; k++) {
				binaryStrings[k] = String.format("%8s", Integer.toBinaryString(asciiCodeForStringToHash[k]))
						.replace(' ', '0');
				System.out.print(binaryStrings[k] + "  ");

			}
			System.out.print("\n");

			System.out.println("\n ----> Pasul 6: Concatenare numerelor binare si adaugare 1 la sfarsit");
			System.out.print("\n\t ");
			for (int k = 0; k < stringToHashCaracters.length; k++) {
				binarStringsConcatenated += binaryStrings[k];
			}
			firstBinaryStringLength = binarStringsConcatenated.length();
			binarStringsConcatenated += "1";
			System.out.print(binarStringsConcatenated);
			System.out.print("\n");

			System.out.println("\n ----> Pasul 7: Adaugare 0 la sfarsit");
			System.out.print("\n\t ");
			int vlaidMessagLength = binarStringsConcatenated.length() % 512;
			while (vlaidMessagLength != 448) {
				binarStringsConcatenated += "0";
				vlaidMessagLength = binarStringsConcatenated.length() % 512;
			}
			System.out.print(binarStringsConcatenated);
			System.out.print("\n");

			System.out.println("\n ----> Pasul 8: Adaugare lungimea mesajului original la sfarsit");
			System.out.print("\n\t ");
			binarStringsConcatenated += String.format("%64s", Integer.toBinaryString(firstBinaryStringLength))
					.replace(' ', '0');
			System.out.print(binarStringsConcatenated);
			System.out.print("\n");

			System.out.println("\n ----> Pasul 9: Spargerea mesajului in block-uri de 512-bit");
			System.out.print("\n\t ");
			nrOf512bitBlocks = binarStringsConcatenated.length() / 512;
			System.out.print("Numar total de blockuri: " + nrOf512bitBlocks);
			for (int m = 0; m < nrOf512bitBlocks; m++) {
				blocks512bits[m] = binarStringsConcatenated.substring(m, 512);
				System.out.print("\n\t Block " + (m + 1) + " =  \n" + blocks512bits[m]);
			}
			System.out.print(binarStringsConcatenated);
			System.out.print("\n");

			System.out.println("\n ----> Pasul 10: Spargerea blockurilor in cuvinte de 32-bit");
			System.out.print("\n\t ");
			for (int m = 0; m < nrOf512bitBlocks; m++) {
				System.out.print("\n\t Cuvintele pentru Block " + (m + 1) + " =  \n");
				int nrWords32Bit = blocks512bits[m].length() / 32;
				int temVar = 0;
				for (int i = 0; i < nrWords32Bit; i++) {
					words32Bit[m][i] = blocks512bits[m].substring(temVar, 32 + temVar);
					temVar += 32;
					System.out.print("\n\t " + i + " = " + words32Bit[m][i]);
				}

			}
			System.out.print("\n");

			System.out.println("\n ----> Pasul 11: Extinderea la 80 de cuvinte de 32-bit folosind XOR");
			System.out.print("\n\t ");
			for (int m = 0; m < nrOf512bitBlocks; m++) {

				for (int nrOf32BitWords = 16; nrOf32BitWords < 80; nrOf32BitWords++) {

					char[] firstWord = words32Bit[m][nrOf32BitWords - 3].toCharArray();
					char[] secondWord = words32Bit[m][nrOf32BitWords - 8].toCharArray();
					char[] thirdWord = words32Bit[m][nrOf32BitWords - 14].toCharArray();
					char[] fourthWord = words32Bit[m][nrOf32BitWords - 16].toCharArray();

					char[] firstXor = xorGateCharArrays(firstWord, secondWord);
					char[] secondXor = xorGateCharArrays(firstXor, thirdWord);
					char[] thirdXor = xorGateCharArrays(secondXor, fourthWord);

					char[] next32bitWord = leftRotate(thirdXor, 1);

					String next32bitWordAsString = new String(next32bitWord);
					words32Bit[m][nrOf32BitWords] = next32bitWordAsString;

				}
			}

			for (int m = 0; m < nrOf512bitBlocks; m++) {
				System.out.print("\n\t Cuvintele pentru Block " + (m + 1) + " =  \n");
				for (int i = 0; i < words32Bit[m].length; i++) {
					System.out.print("\n\t " + i + " = " + words32Bit[m][i]);
				}

			}
			System.out.print("\n");

			System.out.println("\n ----> Pasul 12: Initializare de variabile");
			System.out.print("\n\t ");
			A = h0;
			B = h1;
			C = h2;
			D = h3;
			E = h4;
			System.out.print("\n\tA = h0");
			System.out.print("\n\tB = h1");
			System.out.print("\n\tC = h2");
			System.out.print("\n\tD = h3");
			System.out.print("\n\tE = h4");
			System.out.print("\n");

			System.out
					.println("\n ----> Pasul 13: Recalcularea variablelor A,B,C,D,E cu ajutorul celor 80 de cuvinte.");
			System.out.print("\n\t ");

			for (int m = 0; m < nrOf512bitBlocks; m++) {
				for (int i = 0; i < words32Bit[m].length; i++) {
					String F = "", K = "";

					if (i < 20) {
						F = new String(firstFunction(B.toCharArray(), C.toCharArray(), D.toCharArray()));
						K = "01011010100000100111100110011001";

					} else if (i < 40) {

						F = new String(secondFunction(B.toCharArray(), C.toCharArray(), D.toCharArray()));
						K = "01101110110110011110101110100001";

					} else if (i < 60) {

						F = new String(thirdFunction(B.toCharArray(), C.toCharArray(), D.toCharArray()));
						K = "10001111000110111011110011011100";

					} else if (i < 80) {

						F = new String(secondFunction(B.toCharArray(), C.toCharArray(), D.toCharArray()));
						K = "11001010011000101100000111010110";

					}

					String aLeftRot = new String(leftRotate(A.toCharArray(), 5));
					String temp = new String(
							addBinary(addBinary(addBinary(addBinary(aLeftRot, F), E), K), words32Bit[m][i]));
					temp = truncateStringLeft(temp, 32);

					E = D;
					D = C;
					C = new String(leftRotate(B.toCharArray(), 30));
					B = A;
					A = temp;
				}

			}

			System.out.print("\n\tA = " + A);
			System.out.print("\n\tB = " + B);
			System.out.print("\n\tC = " + C);
			System.out.print("\n\tD = " + D);
			System.out.print("\n\tE = " + E);
			System.out.print("\n");

			System.out.println("\n ----> Pasul 14: Recalcularea variablelor h0, h1, h2, h3, h4 cu ajutorul A,B,C,D,E ");
			System.out.print("\n\t ");
			h0 = truncateStringLeft(addBinary(h0, A), 32);
			h1 = truncateStringLeft(addBinary(h1, B), 32);
			h2 = truncateStringLeft(addBinary(h2, C), 32);
			h3 = truncateStringLeft(addBinary(h3, D), 32);
			h4 = truncateStringLeft(addBinary(h4, E), 32);

			System.out.print("h0 = h0 + A\n");
			System.out.print("\t h1 = h1 + B\n");
			System.out.print("\t h2 = h2 + C\n");
			System.out.print("\t h3 = h3 + D\n");
			System.out.print("\t h4 = h4 + E\n");

			System.out.print("\n");

			System.out.print("\n\t h0 = " + h0);
			System.out.print("\n\t h1 = " + h1);
			System.out.print("\n\t h2 = " + h2);
			System.out.print("\n\t h3 = " + h3);
			System.out.print("\n\t h4 = " + h4);
			System.out.print("\n");
			

			System.out.println("\n ----> Pasul 15: Conversi variablelor h0, h1, h2, h3, h4 in HEX");
			System.out.print("\n\t ");

			String h0Hex = String.format("%21x", Long.parseLong(h0, 2));
			String h1Hex = String.format("%21x", Long.parseLong(h1, 2));
			String h2Hex = String.format("%21x", Long.parseLong(h2, 2));
			String h3Hex = String.format("%21x", Long.parseLong(h3, 2));
			String h4Hex = String.format("%21x", Long.parseLong(h4, 2));
			
			
			System.out.print("\n\t h0 = " + h0Hex);
			System.out.print("\n\t h1 = " + h1Hex);
			System.out.print("\n\t h2 = " + h2Hex);
			System.out.print("\n\t h3 = " + h3Hex);
			System.out.print("\n\t h4 = " + h4Hex);			
			System.out.print("\n\t ");
			
			
			

			System.out.println("\n ----> Pasul 16: Afisare rezultat final");
			System.out.println("\n\t ");
			System.out.println("\n\t " + h0Hex.trim() + h1Hex.trim() + h2Hex.trim() + h3Hex.trim() + h4Hex.trim());
			System.out.println("\n\t ");

		} catch (Exception e) {
			System.out.println("ERROR: Programul trebuie apelat cu un argument!\n");
			System.out.println(e);
		}

	}

	public static char[] firstFunction(char[] B, char[] C, char[] D) {

		char[] BandC = andGateCharArrays(B, C);
		char[] notB = notGateCharArrays(B);
		char[] notBandD = andGateCharArrays(notB, D);
		char[] result = orGateCharArrays(BandC, notBandD);

		return result;
	}

	public static char[] secondFunction(char[] B, char[] C, char[] D) {

		char[] BxorC = xorGateCharArrays(B, C);
		char[] BxorCxorD = xorGateCharArrays(BxorC, D);
		return BxorCxorD;
	}

	public static char[] thirdFunction(char[] B, char[] C, char[] D) {

		char[] BandC = andGateCharArrays(B, C);
		char[] BandD = andGateCharArrays(B, D);
		char[] CandD = andGateCharArrays(C, D);
		char[] BandCorBandD = orGateCharArrays(BandC, BandD);
		char[] result = orGateCharArrays(BandCorBandD, CandD);

		return result;
	}

	public static char[] xorGateCharArrays(char[] charArray1, char[] charArray2) {

		char[] xorResult = new char[charArray1.length];

		for (int l = 0; l < charArray1.length; l++) {
			if (charArray1[l] == charArray2[l]) {
				xorResult[l] = '0';
			} else {
				xorResult[l] = '1';
			}
		}

		return xorResult;
	}

	public static char[] andGateCharArrays(char[] charArray1, char[] charArray2) {

		char[] andResult = new char[charArray1.length];

		for (int l = 0; l < charArray1.length; l++) {
			if ('1' == charArray1[l] && '1' == charArray2[l]) {
				andResult[l] = '1';
			} else {
				andResult[l] = '0';
			}
		}

		return andResult;
	}

	public static char[] orGateCharArrays(char[] charArray1, char[] charArray2) {

		char[] orResult = new char[charArray1.length];

		for (int l = 0; l < charArray1.length; l++) {
			if ('1' == charArray1[l] || '1' == charArray2[l]) {
				orResult[l] = '1';
			} else {
				orResult[l] = '0';
			}
		}

		return orResult;
	}

	public static char[] notGateCharArrays(char[] charArray) {

		char[] result = new char[charArray.length];

		for (int l = 0; l < charArray.length; l++) {
			if ('1' == charArray[l]) {
				result[l] = '0';
			} else {
				result[l] = '1';
			}
		}

		return result;
	}

	public static char[] leftRotate(char[] charArray, int rotateStep) {
		char[] result = new char[charArray.length];

		int count = 0;
		for (int i = rotateStep; i < charArray.length; i++) {
			result[count] = charArray[i];
			count++;
		}

		for (int j = 0; j < rotateStep; j++) {
			result[count] = charArray[j];
			count++;
		}

		return result;
	}

	public static String addBinary(String a, String b) {
		// Start typing your Java solution below
		// DO NOT write main() function
		int la = a.length();
		int lb = b.length();

		int max = Math.max(la, lb);

		StringBuilder sum = new StringBuilder("");
		int carry = 0;

		for (int i = 0; i < max; i++) {
			int m = getBit(a, la - i - 1);
			int n = getBit(b, lb - i - 1);
			int add = m + n + carry;
			sum.append(add % 2);
			carry = add / 2;
		}

		if (carry == 1)
			sum.append("1");

		return sum.reverse().toString();

	}

	public static int getBit(String s, int index) {
		if (index < 0 || index >= s.length())
			return 0;

		if (s.charAt(index) == '0')
			return 0;
		else
			return 1;

	}

	public static String truncateStringLeft(String string, int length) {

		char[] stringArray = string.toCharArray();
		char[] finalStringArray = new char[length];

		int j = stringArray.length - 1;
		for (int i = length - 1; i >= 0; i--) {
			finalStringArray[i] = stringArray[j];
			j--;
		}

		return new String(finalStringArray);
	}
}
