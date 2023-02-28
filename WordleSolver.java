import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordleSolver {
	
	private final static int listSize = 5757;

	
	public static String YellowPos1 = "";
	public static String YellowPos2 = "";
	public static String YellowPos3 = "";
	public static String YellowPos4 = "";
	public static String YellowPos5 = "";

	public static String[] abcYellowPos = {YellowPos1, YellowPos2, YellowPos3, YellowPos4, YellowPos5};

	public static String abcYellowList = "";

	public static String abcBlackList = "";

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		//Blacklist plositions that do not work.
		
		
		
		File file = new File("wordList.txt");
				
				//String abc = "abcdefghijklmnopqrstuvwxyz";
				//int abcSize = abc.length();
				
				Scanner lineCounter = new Scanner(file);
				
				String[] wordList[] = new String[listSize][];
				int lineNum = 0;
				//Puts words into a list for easy accsess.
				while(lineCounter.hasNextLine()) {
				//	String[] wordStr = data.split("(?!^)");
					String[] wordStr= lineCounter.nextLine().replaceAll("\\s+","").split("(?!^)");
					
					wordList[lineNum] = wordStr;
					
					lineNum++;
				}
				lineCounter.close();
				
				
		/*		for(int i = 0; i < 10; i++) {
					for(int z = 0; z < 5; z++) {
						System.out.print(wordList[i][z]);
					}
					System.out.println("");
				}*/
	
				
		//		String abcBlackList = "";



				String[] wordGuess = new String[6];
				
				
				String findWord = "alert";
				
				String[] splitFindWord = findWord.split("(?!^)");
				
				boolean wordFound = true;
				
				
				String[][] newWordList = new String[listSize][]; 
				
				Scanner scanner = new Scanner(System.in); 
				
				
				while(wordFound) {
				
					
					//aScanner scanner = new Scanner(System.in); 
					
					
					while(true) {
						
						 
						
						
						System.out.print("Enter the word you have entered into Wordle (Press Y to finish): ");
						
						//System.out.println("");
						String guessedWord = scanner.nextLine().toLowerCase();
						
						//System.out.println("Have You Found the Word Yet? (Y/N): ");
					//	String haveFound = scanner.nextLine().toUpperCase();
				//		scann.close();
						if(guessedWord.equals("y")) {
							System.out.println("Awesome! Good job!");
							wordFound = false;
							break;
						}
						
						
						if(guessedWord.length() == 5) {
							
						
							
							System.out.println("Now put the color on the wordle under the word");
							System.out.println("G: Green, Y: Yellow, N: Not in word");
							System.out.println(guessedWord);
							String[] guessedWords = guessedWord.split("(?!^)");
							
							String[] colorString = scanner.nextLine().toUpperCase().split("(?!^)");
							
							for(int i = 0; i < 5; i++) {
								if(colorString[i].equals("G")) {
									boolean addToList = true;
									
									String[] tempYellow = abcYellowList.split("(?!^)");
									for(int z = 0; z < abcYellowList.length(); z++) {
										if(tempYellow[z].equals(guessedWords[i])) {
											//abcYellowList += guessedWords[i];
											addToList = false;
										}
									}
									
									if(addToList) {
										abcYellowList += guessedWords[i];
									}
									wordGuess[i] = guessedWords[i];
								}
								else if(colorString[i].equals("Y")) {
									boolean addToList = true;
									
									//This part checks if yellow is already in the list
									String[] tempYellow = abcYellowList.split("(?!^)");
									for(int z = 0; z < abcYellowList.length(); z++) {
										if(tempYellow[z].equals(guessedWords[i])) {
											//abcYellowList += guessedWords[i];
											addToList = false;
										}
									}
									//This adds Yellow to the various lists
									if(addToList) {
										abcYellowList += guessedWords[i];
									}
									abcYellowPos[i] += guessedWords[i];
								}
								else if(colorString[i].equals("N")) {
								//	abcBlackList += guessedWords[i];
									boolean addToList = true;
									
									String[] tempBlack = abcBlackList.split("(?!^)");
									for(int z = 0; z < abcBlackList.length(); z++) {
										if(tempBlack[z].equals(guessedWords[i])) {
											addToList = false;
										}
										
										
									}
									
									if(addToList) {
										abcBlackList += guessedWords[i];
									}
									
								}
								
								
							}
							
							break;
						}
						else {
							System.out.println("Error! Word not entered correctly\n");
						}
					}
					
					
					if(!wordFound) {
						break;
					}
					
					
					for(int i = 0; i < 5; i++) {
						System.out.print(wordGuess[i]);
					}
					
					System.out.println("\n\n\n");
					
					
					//TODO
					//This takes letters out of the blackList if the yellow list has it
					
					
					String[] abcYellow = abcYellowList.split("(?!^)");
					String[] abcBlack = abcBlackList.split("(?!^)");
					for(int blackNum = 0; blackNum < abcBlack.length; blackNum++) {
						for(int yellowNum = 0; yellowNum < abcYellow.length; yellowNum++) {
							
							if(abcYellow[yellowNum].equals(abcBlack[blackNum])) {
								abcBlack[blackNum] = "";
								
															
							}
						}
					}
					
					abcBlackList = "";
					for(int z = 0; z < abcBlack.length; z++) {
						abcBlackList += abcBlack[z];
					}
					abcBlackList.replaceAll("\\s", "");
					
					
					//This part creates a new list of possible words based on the word list given earlier.
					newWordList = wordList;
					
					if(wordGuess[0] != null) {
						newWordList = charChecker(newWordList, wordGuess, 0);
					}
					if(wordGuess[1] != null) {
						newWordList = charChecker(newWordList, wordGuess, 1);
					}
					if(wordGuess[2] != null) {
						newWordList = charChecker(newWordList, wordGuess, 2);
					}
					if(wordGuess[3] != null) {
						newWordList = charChecker(newWordList, wordGuess, 3);
					}
					if(wordGuess[4] != null) {
						newWordList = charChecker(newWordList, wordGuess, 4);
					}
					
					
					newWordList = yellowChecker(newWordList, wordGuess, abcYellowList);
					
						
					newWordList = blackChecker(newWordList, wordGuess, abcBlackList);
					
					System.out.println("- - - - - - - - - - -");
					
					
					

				}
				
				
			
				scanner.close();
				
				
	}

	
	
	public static String[][] charChecker(String[][] wordList, String[] wordGuess, int num) {
		
		int newListCount = 0;
		
		String[][] newWordList = new String[listSize][];
		
		
			
		
			for(int z = 0; z < wordList.length; z++) {
				
			//	System.out.println(z);
				if(wordList[z] == null) {
					break;
				}
					
				else if(wordList[z][num].equals(wordGuess[num])) {
				//	System.out.println(wordList[z][num]);
					
					newWordList[newListCount] = wordList[z];
					
					newListCount++;
					
				//	System.out.println(wordList[z]);
					
					//for(int p = 0; p < 5; p++) {
						//System.out.print(wordList[z][p]);
					///}/
					//S/ystem.out.println("");
				}

			
			}
		
	
		
		return newWordList;
	}
	
	public static String[][] yellowChecker(String[][] wordList, String[] wordGuess, String abcYellow){
		
		System.out.println("- - - Word List - - -");
		System.out.println("Yellow List: " + abcYellow);
		System.out.println("Yellow Pos1: " + abcYellowPos[0]);
		System.out.println("Yellow Pos2: " + abcYellowPos[1]);
		System.out.println("Yellow Pos3: " + abcYellowPos[2]);
		System.out.println("Yellow Pos4: " + abcYellowPos[3]);
		System.out.println("Yellow Pos5: " + abcYellowPos[4]);
		
		String[] abcYellowList = abcYellow.split("(?!^)");
		boolean wordHas = false;
		String[][] newWordList = new String[listSize][];
		
		int newWordCount = 0;
		int wordHasNum = 0;

		//abcYellowPos

		for(int i = 0; i < wordList.length; i++) {
			wordHas = false;
			boolean badPos = false;

			//Creates a tempYellowABC so we can see occurences of letters
			String[] tempYellowABC = abcYellowList.clone();


			for(int z = 0; z < 5; z++) {
				//Parses through the yellow

				/*
				Ok so this is the psudocode for what we actually need to do!
				for each yellowPos
					go through every yellow
						if yellow matches what is in pos in word, skip it
				then do the normal thing
				*/
				if(wordList[i] == null){
					break;
				}

				for(int x = 0; x < abcYellowPos[z].split("(?!^)").length; x++){

					if(wordList[i][z].equals(abcYellowPos[z].split("(?!^)")[x])){
						badPos = true;
					}
						
				}

				for(int yellowNum = 0; yellowNum < abcYellow.length(); yellowNum++) {
					
					if(wordList[i] == null) {
						break;
					}
					
					else if(wordList[i][z].equals(tempYellowABC[yellowNum])) {
						wordHasNum++;
						tempYellowABC[yellowNum] = "";
						break;
					}

				}
			}
			//Checks to see if the yellows in word has the same number as what we actually have
			if(wordHasNum == abcYellow.length() && !badPos) {
				newWordList[newWordCount] = wordList[i];
				newWordCount++;
				
			//	for(int p = 0; p < 5; p++) {
				//	System.out.print(wordList[i][p]);
				//}
			//	System.out.println("");
				
			}
			
			wordHasNum = 0;
			
		}
		
		
		
		
		return newWordList;
	}
	
	
	
	public static String[][] blackChecker(String[][] wordList, String[] wordGuess, String abcBlack){
		
		System.out.println("Black List: " + abcBlack);
		//System.out.println("- - - - - - - - - - -");
		
		String[] abcBlackList = abcBlack.split("(?!^)");
		boolean wordHas = false;
		String[][] newWordList = new String[listSize][];
		
		int actualListLength = 0;
		try {
			while(wordList[actualListLength] != null) {
				
				actualListLength++;
				
				
			}
		}
		catch(Exception e){
			
		}
		
		int newWordCount = 0;
		
		for(int i = 0; i < actualListLength; i++) {
			wordHas = false;
			for(int z = 0; z < 5; z++) {
				for(int yellowNum = 0; yellowNum < abcBlack.length(); yellowNum++) {
					
					if(wordList[i] == null) {
						break;
					}
					
					else if(wordList[i][z].equals(abcBlackList[yellowNum])) {
						wordHas = true;
					}

				}
			}
			if(!wordHas) {
				newWordList[newWordCount] = wordList[i];
				newWordCount++;
				
				for(int p = 0; p < 5; p++) {
					System.out.print(wordList[i][p]);
				}
				System.out.println("");
				
			}
			
		}
		
		
		
		
		return newWordList;
	}
	
	
	
	
	
}
