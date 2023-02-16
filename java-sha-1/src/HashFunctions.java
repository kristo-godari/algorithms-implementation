import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HashFunctions {

	private JFrame frmImplementareaFunctieiSha;
	private JTextField textInput;
	private JButton calculeazaBtn;
	private JButton arataPasiBtn;
	private JTextArea textareaPasi;
	private JLabel rezultatLbl;
	private JTextField resultHash;
	
	private boolean showTextArea = false;
	private JScrollPane scrollPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HashFunctions window = new HashFunctions();
					window.frmImplementareaFunctieiSha.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HashFunctions() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmImplementareaFunctieiSha = new JFrame();
		frmImplementareaFunctieiSha.setTitle("AAAI - Implementarea Functiei Hash SHA-1");
		frmImplementareaFunctieiSha.setBounds(100, 100, 644, 414);
		frmImplementareaFunctieiSha.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmImplementareaFunctieiSha.getContentPane().setLayout(new MigLayout("", "[][33.00,grow][49.00][64.00][76.00][65.00][][grow][]", "[][grow][][][]"));
		
		textInput = new JTextField();
		frmImplementareaFunctieiSha.getContentPane().add(textInput, "cell 0 0 7 1,grow");
		textInput.setColumns(10);
		
		calculeazaBtn = new JButton("Calculeaza Hash");
		frmImplementareaFunctieiSha.getContentPane().add(calculeazaBtn, "cell 7 0");
		
		arataPasiBtn = new JButton("Arata Pasi");		
		frmImplementareaFunctieiSha.getContentPane().add(arataPasiBtn, "cell 8 0");				
		
		scrollPane = new JScrollPane();
		frmImplementareaFunctieiSha.getContentPane().add(scrollPane, "cell 0 1 9 1,grow");		
		textareaPasi = new JTextArea();
		scrollPane.setViewportView(textareaPasi);
		if(showTextArea == false){
			scrollPane.hide();			
		}
		
		rezultatLbl = new JLabel("Rezultat:");
		frmImplementareaFunctieiSha.getContentPane().add(rezultatLbl, "cell 0 3,alignx trailing");
		
		resultHash = new JTextField();
		frmImplementareaFunctieiSha.getContentPane().add(resultHash, "cell 1 3 8 1,growx");
		resultHash.setColumns(10);
		
		calculeazaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textInput.getText().equals("")){					
					JOptionPane.showMessageDialog(frmImplementareaFunctieiSha, "Trebuie sa introduceti un sir de caractere pentru a continua mai departe.");
				}else{
					String[] hashCalculation = calculareHash(textInput.getText());
					resultHash.setText(hashCalculation[1]);
					textareaPasi.setText(hashCalculation[0]);
				}				
			}
		});
		
		arataPasiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(showTextArea == false){
					scrollPane.show();
					showTextArea = true;
					arataPasiBtn.setText("Ascunde Pasi");
				}else{
					scrollPane.hide();
					showTextArea = false;
					arataPasiBtn.setText("Arata Pasi");
				}
			}
		});
	}
	
	/**
	 * Calculate Hash
	 * @param stringToHash
	 * @return
	 */
	public String[] calculareHash(String stringToHash){
		
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

		char[] stringToHashCaracters = null;

		int[] asciiCodeForStringToHash = new int[10000];

		String[] binaryStrings = new String[10000];

		String binarStringsConcatenated = "";

		int firstBinaryStringLength = 0;

		int nrOf512bitBlocks = 0;

		String[] blocks512bits = new String[10000];

		String[][] words32Bit = new String[10000][80];
		
		String outputString = "";
		
		String finalHash = "";
		
		String[] resultReturned = new String[2];

		try {

			outputString ="\n---- Secure Hash Algorithm 1 sau SHA-1 ----\n\n";

			outputString +=" ----> Pasul 1: Initializare de variabile\n\n";
			outputString +="\th0 = " + h0;
			outputString +="\n\th1 = " + h1;
			outputString +="\n\th2 = " + h2;
			outputString +="\n\th3 = " + h3;
			outputString +="\n\th4 = " + h4;

			outputString +="\n\n ----> Pasul 2: Alegere cuvant care va fi hash-uit";
			outputString +="\n\n\tcuvant = '" + stringToHash + "'";

			outputString +="\n\n ----> Pasul 3: Spargere in litere";
			outputString +="\n\n\t ";
			stringToHashCaracters = stringToHash.toCharArray();
			for (int i = 0; i < stringToHashCaracters.length; i++) {
				outputString +=stringToHashCaracters[i] + "  ";
			}
			outputString +="\n";

			outputString +="\n ----> Pasul 4: Conversia literelor in cod ASCII";
			outputString +="\n\n\t ";
			for (int j = 0; j < stringToHashCaracters.length; j++) {
				asciiCodeForStringToHash[j] = stringToHashCaracters[j];
				outputString +=asciiCodeForStringToHash[j] + "  ";

			}
			outputString +="\n";

			outputString +="\n ----> Pasul 5: Conversia in numere binare";
			outputString +="\n\n\t ";
			for (int k = 0; k < stringToHashCaracters.length; k++) {
				binaryStrings[k] = String.format("%8s", Integer.toBinaryString(asciiCodeForStringToHash[k]))
						.replace(' ', '0');
				outputString +=binaryStrings[k] + "  ";

			}
			outputString +="\n";

			outputString +="\n ----> Pasul 6: Concatenare numerelor binare si adaugare 1 la sfarsit";
			outputString +="\n\n\t ";
			for (int k = 0; k < stringToHashCaracters.length; k++) {
				binarStringsConcatenated += binaryStrings[k];
			}
			firstBinaryStringLength = binarStringsConcatenated.length();
			binarStringsConcatenated += "1";
			outputString +=binarStringsConcatenated;
			outputString +="\n";

			outputString +="\n ----> Pasul 7: Adaugare 0 la sfarsit";
			outputString +="\n\n\t ";
			int vlaidMessagLength = binarStringsConcatenated.length() % 512;
			while (vlaidMessagLength != 448) {
				binarStringsConcatenated += "0";
				vlaidMessagLength = binarStringsConcatenated.length() % 512;
			}
			outputString +=binarStringsConcatenated;
			outputString +="\n";

			outputString +="\n ----> Pasul 8: Adaugare lungimea mesajului original la sfarsit";
			outputString +="\n\n\t ";
			binarStringsConcatenated += String.format("%64s", Integer.toBinaryString(firstBinaryStringLength))
					.replace(' ', '0');
			outputString +=binarStringsConcatenated;
			outputString +="\n";

			outputString +="\n ----> Pasul 9: Spargerea mesajului in block-uri de 512-bit";
			outputString +="\n\n\t ";
			nrOf512bitBlocks = binarStringsConcatenated.length() / 512;
			outputString +="Numar total de blockuri: " + nrOf512bitBlocks;
			for (int m = 0; m < nrOf512bitBlocks; m++) {
				blocks512bits[m] = binarStringsConcatenated.substring(m, 512);
				outputString +="\n\n\t Block " + (m + 1) + " =  \n\t " + blocks512bits[m];
			}
			outputString +="\t " +binarStringsConcatenated;
			outputString +="\n";

			outputString +="\n ----> Pasul 10: Spargerea blockurilor in cuvinte de 32-bit";
			outputString +="\n\t ";
			for (int m = 0; m < nrOf512bitBlocks; m++) {
				outputString +="\n\t Cuvintele pentru Block " + (m + 1) + " =  \n";
				int nrWords32Bit = blocks512bits[m].length() / 32;
				int temVar = 0;
				for (int i = 0; i < nrWords32Bit; i++) {
					words32Bit[m][i] = blocks512bits[m].substring(temVar, 32 + temVar);
					temVar += 32;
					outputString +="\n\t " + i + " = " + words32Bit[m][i];
				}

			}
			outputString +="\n";

			outputString +="\n ----> Pasul 11: Extinderea la 80 de cuvinte de 32-bit folosind XOR";
			outputString +="\n\t ";
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
				outputString +="\n\t Cuvintele pentru Block " + (m + 1) + " =  \n";
				for (int i = 0; i < words32Bit[m].length; i++) {
					outputString +="\n\t " + i + " = " + words32Bit[m][i];
				}

			}
			outputString +="\n";

			outputString +="\n ----> Pasul 12: Initializare de variabile";
			outputString +="\n\t ";
			A = h0;
			B = h1;
			C = h2;
			D = h3;
			E = h4;
			outputString +="\n\tA = h0";
			outputString +="\n\tB = h1";
			outputString +="\n\tC = h2";
			outputString +="\n\tD = h3";
			outputString +="\n\tE = h4";
			outputString +="\n";

			outputString +="\n ----> Pasul 13: Recalcularea variablelor A,B,C,D,E cu ajutorul celor 80 de cuvinte.";
			outputString +="\n\t ";

			for (int m = 0; m < nrOf512bitBlocks; m++) {
				for (int i = 0; i < words32Bit[m].length; i++) {
					String F = "", K = "";

					if (i < 20) {
						F = new String(firstFunction(B.toCharArray(), C.toCharArray(), D.toCharArray()));
						K = "01011010100000100111100110011001";
						
						/**
						 * for debugging purpose
							if(i == 0){
								System.out.println("========= " + F);
							}
						**/

					} else if (i > 19 && i < 40) {

						F = new String(secondFunction(B.toCharArray(), C.toCharArray(), D.toCharArray()));
						K = "01101110110110011110101110100001";
						
						/**
						 * for debugging purpose
						 *
							if(i == 20){
								System.out.println("========= " + F);
							}
						 */

					} else if (i > 39 && i < 60) {

						F = new String(thirdFunction(B.toCharArray(), C.toCharArray(), D.toCharArray()));
						K = "10001111000110111011110011011100";
						
						/**
						 * for debugging purpose
						 * if(i == 40){
							System.out.println("========= " + F);
							}
						 * 
						 */						

					} else if (i > 59 && i < 80) {

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

			outputString +="\n\tA = " + A;
			outputString +="\n\tB = " + B;
			outputString +="\n\tC = " + C;
			outputString +="\n\tD = " + D;
			outputString +="\n\tE = " + E;
			outputString +="\n";

			outputString +="\n ----> Pasul 14: Recalcularea variablelor h0, h1, h2, h3, h4 cu ajutorul A,B,C,D,E ";
			outputString +="\n\n\t ";
			h0 = truncateStringLeft(addBinary(h0, A), 32);
			h1 = truncateStringLeft(addBinary(h1, B), 32);
			h2 = truncateStringLeft(addBinary(h2, C), 32);
			h3 = truncateStringLeft(addBinary(h3, D), 32);
			h4 = truncateStringLeft(addBinary(h4, E), 32);

			outputString +="h0 = h0 + A\n";
			outputString +="\t h1 = h1 + B\n";
			outputString +="\t h2 = h2 + C\n";
			outputString +="\t h3 = h3 + D\n";
			outputString +="\t h4 = h4 + E\n";

			outputString +="\n";

			outputString +="\n\t h0 = " + h0;
			outputString +="\n\t h1 = " + h1;
			outputString +="\n\t h2 = " + h2;
			outputString +="\n\t h3 = " + h3;
			outputString +="\n\t h4 = " + h4;
			outputString +="\n";
			

			outputString +="\n ----> Pasul 15: Conversi variablelor h0, h1, h2, h3, h4 in HEX";
			outputString +="\n\t ";

			String h0Hex = String.format("%21x", Long.parseLong(h0, 2));
			String h1Hex = String.format("%21x", Long.parseLong(h1, 2));
			String h2Hex = String.format("%21x", Long.parseLong(h2, 2));
			String h3Hex = String.format("%21x", Long.parseLong(h3, 2));
			String h4Hex = String.format("%21x", Long.parseLong(h4, 2));
			
			
			outputString +="\n\t h0 = " + h0Hex;
			outputString +="\n\t h1 = " + h1Hex;
			outputString +="\n\t h2 = " + h2Hex;
			outputString +="\n\t h3 = " + h3Hex;
			outputString +="\n\t h4 = " + h4Hex; 
			outputString +="\n\t ";
			
			/*
			outputString +="\n\t h0 = 90ffc960";
			outputString +="\n\t h1 = 3ab25a00";
			outputString +="\n\t h2 = 564a501c";
			outputString +="\n\t h3 = 7b0bb259";
			outputString +="\n\t h4 = fabcfc1d";
			*/
			
			outputString +="\n ----> Pasul 16: Afisare rezultat final";
			outputString +="\n\t ";
			finalHash = h0Hex.trim() + h1Hex.trim() + h2Hex.trim() + h3Hex.trim() + h4Hex.trim();
			outputString +="\n\t " + finalHash;
			//outputString +="90ffc9603ab25a00564a501c7b0bb259fabcfc1d";
			outputString +="\n\t ";
						
			resultReturned[0] = outputString;
			resultReturned[1] = finalHash;
			
			
		} catch (Exception e) {
			outputString +="ERROR: Programul trebuie apelat cu un argument!\n";
			outputString += e.toString();
			
			resultReturned[0] = e.toString();
			resultReturned[1] = outputString;			
		}
		
		return resultReturned;
	}
	
	/**
	 * 
	 * @param B
	 * @param C
	 * @param D
	 * @return
	 */
	public static char[] firstFunction(char[] B, char[] C, char[] D) {

		char[] BandC = andGateCharArrays(B, C);
		char[] notB = notGateCharArrays(B);
		char[] notBandD = andGateCharArrays(notB, D);
		char[] result = orGateCharArrays(BandC, notBandD);

		return result;
	}
	
	/**
	 * 
	 * @param B
	 * @param C
	 * @param D
	 * @return
	 */
	public static char[] secondFunction(char[] B, char[] C, char[] D) {

		char[] BxorC = xorGateCharArrays(B, C);
		char[] BxorCxorD = xorGateCharArrays(BxorC, D);
		return BxorCxorD;
	}
	
	/**
	 * 
	 * @param B
	 * @param C
	 * @param D
	 * @return
	 */
	public static char[] thirdFunction(char[] B, char[] C, char[] D) {

		char[] BandC = andGateCharArrays(B, C);
		char[] BandD = andGateCharArrays(B, D);
		char[] CandD = andGateCharArrays(C, D);
		char[] BandCorBandD = orGateCharArrays(BandC, BandD);
		char[] result = orGateCharArrays(BandCorBandD, CandD);

		return result;
	}
	
	/**
	 * 
	 * @param charArray1
	 * @param charArray2
	 * @return
	 */
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
	
	/**
	 * 
	 * @param charArray1
	 * @param charArray2
	 * @return
	 */
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
	
	/**
	 * 
	 * @param charArray1
	 * @param charArray2
	 * @return
	 */
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
	
	/**
	 * 
	 * @param charArray
	 * @return
	 */
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
	
	/**
	 * 
	 * @param charArray
	 * @param rotateStep
	 * @return
	 */
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
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
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
	
	/**
	 * 
	 * @param s
	 * @param index
	 * @return
	 */
	public static int getBit(String s, int index) {
		if (index < 0 || index >= s.length())
			return 0;

		if (s.charAt(index) == '0')
			return 0;
		else
			return 1;

	}
	
	/**
	 * 
	 * @param string
	 * @param length
	 * @return
	 */
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
