import arc.*;
import java.awt.image.*;
import java.awt.*;
public class AdelineLueSangCPT{
	public static void main (String [] args){
		Console con = new Console () ;
		
		//EXTRA FEATURES
			//secret menu (s)
			//draw image/animation (in secret menu)
			//draw rectangle (int secret menu)
			//has instructions menu option
			//uses System.out.println to debug
			//strongertogether gives score advantage
		
		String strMenu; //String menu input
		strMenu = ("i"); //initialize variable to enter loop
		
		String strEasy [] ; //array for easy words
		
		String strScramble [] ; //array to scramble letters into
		
		String strEasyScores [][] ; //array for easy player names and scores
		
		String strHardScores [][] ; //arry for hard plaer names  
		
		String strChosenNumbers [] ; //array for already chosen words
		strChosenNumbers = new String [5];
				
		String strWord; //initialize strWord
		strWord = ("");
		
		String strScrambled; //scrambled word
		strScrambled = ("");
		
		BufferedImage imgcow1; //define and import images
		imgcow1 = con.loadImage("giphycow1.jpg");
		BufferedImage imgcow2;
		imgcow2 = con.loadImage("giphycow2.jpg");
		BufferedImage imgcow3;
		imgcow3 = con.loadImage("giphycow3.jpg");
		BufferedImage imgcow4;
		imgcow4 = con.loadImage("giphycow4.jpg");
		
		String strPlayer; //game mode (single or multi)
		String strDifficulty; //game difficulty (easy or hard)
		String strPlayerName; //name of player 
		String strChar; //reveals letter from word (hint) 
		String strGuess; //player input (guess)
		String strCheck; //checks if array is empty
		String strName; //name from input file
		String strScore; //score from input file
		String strGuessCount; //string form of # of guesses
		int intCounter; //use for bubble sorting
		int intRand; //random generated number
		int intWordLength; //length of word
		int intCount; //for loop
		int intRound; //loops rounds up to 5
		int intWordCount; //hint letter number
		
		boolean blnCheck;
		blnCheck = false;	
		
		int intNum; //number generated for random word
		intNum = -1;
		
		//define strChosen numbers as -1 to enter while loop
		for(intCount = 0; intCount < 5; intCount++){
			strChosenNumbers [intCount] = ("-1");
		}

		int intWJWOFEasyLength; //length of leaderboard (easy) file
		intWJWOFEasyLength = 0;
		
		int intWJWOFHardLength; //length of leaderboard (hard) file
		intWJWOFHardLength = 0;
		
		int intGuessCount; //player score
		intGuessCount = 0;
		
		int intCheck; //checks if array is empty
		intCheck = 0;
		
		int intLineE; //counts lines
		intLineE = 0;
		
		//BUBBLE SORTING VAARIABLES
		String strLeft;
		String strRight;
		String strTemp;
		
		//import easy word file
		TextInputFile txtEasy;
		txtEasy = new TextInputFile("easyy.txt");
		
		//import hard word file 
		TextInputFile txtHard;
		txtHard = new TextInputFile("hard.txt");
		
		//import easy score list 
		TextInputFile txtWJWOFEasy;
		txtWJWOFEasy = new TextInputFile("WJWOFEasy.txt");
		
		//import hard score list
		TextInputFile txtWJWOFHard;
		txtWJWOFHard = new TextInputFile("WJWOFHard.txt");
		
		//menu + introduction
		con.println("				Welcome to WORD JUMBLE! ");
		con.println();
		con.println("			      Type i, p, v, or q to start.");
		con.println();
		con.println(" i - Instructions");
		con.println(" p - Play");
		con.println(" v - View WJ Hall of Fame");
		con.println(" q - Quit");
		con.println();
		
		//use loop to find length of score file
		while(txtWJWOFEasy.eof() == false){
			strWord = txtWJWOFEasy.readLine () ;
			strWord = txtWJWOFEasy.readLine () ;
			intWJWOFEasyLength += 1;
		}
		
		//debugger
		System.out.println("The amount of players in Easy WJWOF is "+intWJWOFEasyLength);
		
		//score arry is length of file plus one (for player's score)
		strEasyScores = new String [2][intWJWOFEasyLength+1];
		
		//debugger
		System.out.println("The array is 2 wide and "+(intWJWOFEasyLength+1)+" long");
		
		//reset curser to top of file
		txtWJWOFEasy.close();
		txtWJWOFEasy = new TextInputFile("WJWOFEasy.txt");
		
		//put scores into an Score array
		for(intCount = 0; intCount < intWJWOFEasyLength; intCount++){
			strName = txtWJWOFEasy.readLine () ;
			strScore = txtWJWOFEasy.readLine () ;
			
			strEasyScores [0][intCount] = strName;
			strEasyScores [1][intCount] = strScore;
		}
			
		//repeat steps for hard 
		while(txtWJWOFHard.eof() == false){
			strWord = txtWJWOFHard.readLine () ;
			strWord = txtWJWOFHard.readLine () ;
			intWJWOFHardLength += 1;
		}
		
		strHardScores = new String [2][intWJWOFHardLength+1];
		
		System.out.println("The array is 2 wide and "+(intWJWOFHardLength+1)+" long");
		
		txtWJWOFHard.close();
		txtWJWOFHard = new TextInputFile("WJWOFHard.txt");
		
		for(intCount = 0; intCount < intWJWOFHardLength; intCount++){
			strName = txtWJWOFHard.readLine () ;
			strScore = txtWJWOFHard.readLine () ;
			
			strHardScores [0][intCount] = strName;
			strHardScores [1][intCount] = strScore;
		}
		
		//keep looping code while input equals i, p, v, q, or s, so player can pick another option after choosing one 
		while(strMenu.equalsIgnoreCase("i") || strMenu.equalsIgnoreCase("p") || strMenu.equalsIgnoreCase("v") || strMenu.equalsIgnoreCase("q") || strMenu.equalsIgnoreCase("s")){
		con.print(" ");
		strMenu = con.readLine () ;
		
			//instruction code
			if(strMenu.equalsIgnoreCase("i")){
				con.println(" 		 The goal of the game is to unscramble a given word ");
				con.sleep(20);
				con.println("  	 either generated by the computer (single player) or selected by a ");
				con.sleep(20);
				con.println("  	    peer (multiplayer) in the least amount of guesses possible."); 
				con.sleep(20);
				con.println();
				con.sleep(20);
				con.println(" 		   Type ‘hint’ to reveal a letter from the word. ");
				con.sleep(20);
				con.println("	    Each incorrect guess or hint will add 1 guess to your score.");
				con.sleep(20);
				con.println();
				con.sleep(20);
				con.println("		 There are five words per game, and players with the ");
				con.sleep(20);
				con.println("  		least amount of points in single player mode will be ");
				con.sleep(20);
				con.println("		   	  recorded in the WJ Hall of Fame. ");
				con.sleep(20);
				con.println("   		   (The least amount of guesses possible is 5)");
				con.sleep(10000);
				con.println();
				con.println(" i - Instructions");
				con.sleep(50);
				con.println(" p - Play");
				con.sleep(50);
				con.println(" v - View WJ Hall of Fame");
				con.sleep(50);
				con.println(" q - Quit");
				con.sleep(50);
				con.println();
				
			//quit code
			}else if(strMenu.equalsIgnoreCase("q")){
				con.println();
				con.println("				Thank you for playing!");
				strMenu = ("a");
			
			//secret menu code	
			}else if(strMenu.equalsIgnoreCase("s")){
				con.println();
				con.println(" Knock Knock! ");
				con.print(" ");
				strWord = con.readLine () ;
				
				//make sure user asks whos there before proceeding
				if(!strWord.equalsIgnoreCase("whos there") && !strWord.equalsIgnoreCase("who's there") && !strWord.equalsIgnoreCase("whos there?") && !strWord.equalsIgnoreCase("who's there?")){
					con.println(" (you have to ask whos there!)");
					con.print(" ");
					strWord = con.readLine () ;
				}
				
				con.println(" Interrupting Cow!");
				con.sleep(2000);
				con.print(" M");
				con.sleep(10);
				
				//write 81 Os
				for(intCount = 0; intCount <= 80; intCount++){
					con.print("O");
					con.sleep(10);
				}
				
				//create new console and print cow 'gif'
				//print four different cow pictures for a short amount of time with a black rectange in between 
				Console console = new Console () ;
				
				console.setDrawColor(Color.WHITE);
				console.fillRect(0,0,960,540);
				
				for(intCount = 0; intCount <= 960; intCount++){
					console.drawImage(imgcow1, 200, 100);
					console.repaint();
					con.sleep(120);
					console.setDrawColor(Color.WHITE);
					console.fillRect(0,0,960,540);
					
					console.drawImage(imgcow2, 200, 100);
					console.repaint();
					con.sleep(120);
					console.setDrawColor(Color.WHITE);
					console.fillRect(0,0,960,540);
					
					console.drawImage(imgcow3, 200, 100);
					console.repaint();
					con.sleep(120);
					console.setDrawColor(Color.WHITE);
					console.fillRect(0,0,960,540);
					
					console.drawImage(imgcow4, 200, 100);
					console.repaint();
					con.sleep(120);
					console.setDrawColor(Color.WHITE);
					console.fillRect(0,0,960,540);
				}
				
			//play code
			}else if(strMenu.equalsIgnoreCase("p")){
				
				con.println();
				con.println(" Type (s) for Single Player and (m) for Multiplayer: ");
				con.print(" ");
				strPlayer = con.readLine () ;
				
				//if single player, ask for difficulty and name
				if(strPlayer.equalsIgnoreCase("s")){
					con.println();
					con.println(" Type (e) for Easy or (h) for Hard: ");
					con.print(" ");
					strDifficulty = con.readLine () ;
					
					//easy mode code
					if(strDifficulty.equalsIgnoreCase("e")){
						
						//reset score if already played
						intGuessCount = 0;
						
						con.println();
						con.println(" Enter Player Name: ");
						con.print(" ");
						strPlayerName = con.readLine () ;
						
						//if user is strongertogether, advantage
						if(strPlayerName.equalsIgnoreCase("strongertogether")){
							con.println(" shhh... you have a secret advantage: -2 points!");
							con.sleep(1000);
						}
						
						//find number of lines in word file
						while(txtEasy.eof() == false){
							strWord = txtEasy.readLine () ;
							intLineE += 1;
						}
						//print number of lines onto system screen (debug)
						System.out.println("Number of Lines in easy.txt: "+intLineE);
						System.out.println();
						
						//make new array for all the easy words
						//array size is based on txt file size
						strEasy = new String [intLineE];
						
						//return curser to top of file (close and reopen file)
						txtEasy.close();
						txtEasy = new TextInputFile("easyy.txt");
						
						//reset intLineE to 0 for the loop
						intLineE = 0;
						
						//put each line of txt file into strEasy array
						while(txtEasy.eof()== false){
							strWord = txtEasy.readLine() ;
							strEasy [intLineE] = strWord;
							intLineE += 1;
						}
						
						//countdown
						con.println();
						con.println("			Tip: Type 'hint' to reveal a letter ");
						con.sleep(1000);
						con.println(" Ready?");							
						con.sleep(1000);
						con.println("   3");
						con.sleep(1000);
						con.println("   2");
						con.sleep(1000);
						con.println("   1");
						con.sleep(1000);
						con.println("  GO!");
						con.sleep(1000);
						
						//five rounds (five words)
						for(intRound = 1; intRound <= 5; intRound++){
							
							//generate number to pick random word from array
							//while loops make sure same word is not chosen twice
							while(intNum == Double.parseDouble(strChosenNumbers [0])){
								intNum = Random(intLineE, 0);
								strWord = strEasy [intNum];
							}
							
							while(intNum == Double.parseDouble(strChosenNumbers [0]) || intNum == Double.parseDouble(strChosenNumbers [1])){
								intNum = Random(intLineE, 0);
								strWord = strEasy [intNum];
							}
							
							while(intNum == Double.parseDouble(strChosenNumbers [0]) || intNum == Double.parseDouble(strChosenNumbers [1]) || intNum == Double.parseDouble(strChosenNumbers [2])){
								intNum = Random(intLineE, 0);
								strWord = strEasy [intNum];
							}
							
							while(intNum == Double.parseDouble(strChosenNumbers [0]) || intNum == Double.parseDouble(strChosenNumbers [1]) || intNum == Double.parseDouble(strChosenNumbers [2]) || intNum == Double.parseDouble(strChosenNumbers [3])){
								intNum = Random(intLineE, 0);
								strWord = strEasy [intNum];
							}
							
							while(intNum == Double.parseDouble(strChosenNumbers [0]) || intNum == Double.parseDouble(strChosenNumbers [1]) || intNum == Double.parseDouble(strChosenNumbers [2]) || intNum == Double.parseDouble(strChosenNumbers [3]) || intNum == Double.parseDouble(strChosenNumbers [4])){
								intNum = Random(intLineE, 0);
								strWord = strEasy [intNum];
							}
							
							//reset nessecary variables
							intWordCount = 0;
							strScrambled = ("");
							
							//insert the number chosen into chosen numbers array
							strChosenNumbers [intRound-1] = (""+intNum);
							
							//find length of chosen word
							intWordLength = strWord.length () ;
								
							//create new array to scramble chosen word
							strScramble = new String [intWordLength];	
							
							//scramble word using math.random and the strScramble array
							for(intCount = 0; intCount < intWordLength; intCount++){
								intCheck = 0;
								strChar = strWord.substring(intCount,intCount+1);
								while(intCheck == 0){
									intRand = Random(intWordLength, 0);
									strCheck = strScramble [intRand];
									if(strCheck == null){
										strScramble [intRand] = strChar;
										intCheck = 1;
									}else{
										intCheck = 0;
									}
								}
							}
								
							//assign scrambled word to strScrambled
							for(intCount = 0; intCount < intWordLength; intCount++){
								strScrambled += strScramble [intCount];
							}
							
							//print scrambled word to screen
							con.println();
							con.print(" Unscramble: ");	 
							
							con.print(strScrambled);
							con.println();
							con.print(" ");
							strGuess = con.readLine () ;
							
							//hint reveals first letter
							if(strGuess.equalsIgnoreCase("hint")){
								con.println();
								con.println(" Letter #1 is: "+(strWord.substring(intWordCount,intWordCount+1)));
								con.print(" ");
								strGuess = con.readLine () ;
								intGuessCount += 1;
								intWordCount += 1;
							}	
							
							//while answer is incorrect, keep looping
							//Add one point for each guess or hint
							while(!strGuess.equalsIgnoreCase(strWord)){
								con.println();
								
								if(strGuess.equalsIgnoreCase("hint")){
									con.println();
									con.println(" Letter #"+(intWordCount+1)+" is: "+(strWord.substring(intWordCount,intWordCount+1)));
									con.print(" ");
									strGuess = con.readLine () ;
									intWordCount += 1;
								}else{
									con.println(" Incorrect, try again: "+strScrambled);
									con.print(" ");
									strGuess = con.readLine () ;
								}
								
								intGuessCount += 1;
							}
							
							con.println();
							con.println(" Correct!");
							intGuessCount += 1;
							con.println(" Guess Count: "+intGuessCount);
							
						}
						
						//if user is strongertogether, advantage is applied
						if(strPlayerName.equalsIgnoreCase("strongertogether")){
							System.out.println("inside loop");
							intGuessCount -= 2;
						}
						
						//print final score
						con.println();
						con.println(" FINISH");
						con.println(" Total Guess Count: "+intGuessCount);
						
						//make score into string
						strGuessCount = (""+intGuessCount);
						
						//debugging
						System.out.println("strings placed into column 1 & 2 of row "+(intWJWOFEasyLength+1)); 
						
						//put player name and score into score array
						strEasyScores [0][intWJWOFEasyLength] = strPlayerName;
						strEasyScores [1][intWJWOFEasyLength] = strGuessCount;
						
						//bubble sort score array
						for(intCounter = 0; intCounter < intWJWOFEasyLength; intCounter++){
							for(intCount = 0; intCount < intWJWOFEasyLength; intCount++){
								strRight = strEasyScores [1][intCount];
								strLeft = strEasyScores [1][intCount+1];
								if(Double.parseDouble(strLeft) < Double.parseDouble(strRight)){
									strTemp = strEasyScores [1][intCount];
									strEasyScores [1][intCount] = strEasyScores [1][intCount+1];
									strEasyScores [1][intCount+1] = strTemp;
									
									strTemp = strEasyScores [0][intCount];
									strEasyScores [0][intCount] = strEasyScores [0][intCount+1];
									strEasyScores [0][intCount+1] = strTemp;
								}
							}
						}
						
						con.println();
						
						//close score input file and open as output file
						txtWJWOFEasy.close();
						
						TextOutputFile txtEasyOutput;
						txtEasyOutput = new TextOutputFile("WJWOFEasy.txt");
						
						//print sorted scores back into score txt file
						for(intCount = 0; intCount <= intWJWOFEasyLength; intCount++){
							txtEasyOutput.println(strEasyScores [0][intCount]);
							txtEasyOutput.println(strEasyScores [1][intCount]);
						}
						
						//if user made it to top 5, ask to view leaderboard
						//if not, ask to play again or quit
						if(intGuessCount < Double.parseDouble(strEasyScores [1][4])){
							con.println(" You're on the Word Jumble Wall of Fame! Type (v) to view leaderboard: ");
						}else{
							con.println(" 	   You didn't make it onto the Word Jumble Wall of Fame :(");
							con.println(" 		  Type (p) to play again or (q) to quit!");
						}
					
					//repeat same steps for hard mode
					}else if(strDifficulty.equalsIgnoreCase("h")){
						
						intGuessCount = 0;
						
						con.println();
						con.println(" Enter Player Name: ");
						con.print(" ");
						strPlayerName = con.readLine () ;
						
						if(strPlayerName.equalsIgnoreCase("strongertogether")){
							con.println(" shhh... you have a secret advantage: -2 points!");
							con.sleep(1000);
						}
						
						//find number of lines in txt file
						while(txtHard.eof() == false){
							strWord = txtHard.readLine () ;
							intLineE += 1;
						}
						
						System.out.println("Number of Lines in hard.txt: "+intLineE);
						System.out.println();
						
						String strHard [];
						strHard = new String [intLineE];
						
						txtHard.close();
						txtHard = new TextInputFile("hard.txt");
						
						intLineE = 0;
						
						while(txtHard.eof()== false){
							strWord = txtHard.readLine() ;
							strHard [intLineE] = strWord;
							intLineE += 1;
						}
						
						con.println();
						con.println(" Ready?");							
						con.sleep(1000);
						con.println("   3");
						con.sleep(1000);
						con.println("   2");
						con.sleep(1000);
						con.println("   1");
						con.sleep(1000);
						con.println("  GO!");
						con.sleep(1000);
						
						for(intRound = 1; intRound <= 5; intRound++){
							
							intWordCount = 0;
							strScrambled = ("");
							
							while(intNum == Double.parseDouble(strChosenNumbers [0])){
								intNum = Random(intLineE, 0);
								strWord = strHard [intNum];
							}
							
							while(intNum == Double.parseDouble(strChosenNumbers [0]) || intNum == Double.parseDouble(strChosenNumbers [1])){
								intNum = Random(intLineE, 0);
								strWord = strHard [intNum];
							}
							
							while(intNum == Double.parseDouble(strChosenNumbers [0]) || intNum == Double.parseDouble(strChosenNumbers [1]) || intNum == Double.parseDouble(strChosenNumbers [2])){
								intNum = Random(intLineE, 0);
								strWord = strHard [intNum];
							}
							
							while(intNum == Double.parseDouble(strChosenNumbers [0]) || intNum == Double.parseDouble(strChosenNumbers [1]) || intNum == Double.parseDouble(strChosenNumbers [2]) || intNum == Double.parseDouble(strChosenNumbers [3])){
								intNum = Random(intLineE, 0);
								strWord = strHard [intNum];
							}
							
							while(intNum == Double.parseDouble(strChosenNumbers [0]) || intNum == Double.parseDouble(strChosenNumbers [1]) || intNum == Double.parseDouble(strChosenNumbers [2]) || intNum == Double.parseDouble(strChosenNumbers [3]) || intNum == Double.parseDouble(strChosenNumbers [4])){
								intNum = Random(intLineE, 0);
								strWord = strHard [intNum];
							}
							
							strChosenNumbers [intRound-1] = (""+intNum);
							
							intWordLength = strWord.length () ;
								
							strScramble = new String [intWordLength];
								
							strScrambled = ("");
								
							for(intCount = 0; intCount < intWordLength; intCount++){
								intCheck = 0;
								strChar = strWord.substring(intCount,intCount+1);
								while(intCheck == 0){
									intRand = Random(intWordLength, 0);
									strCheck = strScramble [intRand];
									if(strCheck == null){
										strScramble [intRand] = strChar;
										intCheck = 1;
									}else{
										intCheck = 0;
									}
										
								}
							}
									
							for(intCount = 0; intCount < intWordLength; intCount++){
								strScrambled += strScramble [intCount];
							}
								
							con.println();
							con.print(" Unscramble: ");	 
							
							con.print(strScrambled);
							con.println();
							con.print(" ");
							strGuess = con.readLine () ;
							
							if(strGuess.equalsIgnoreCase("hint")){
								con.println();
								con.println(" Letter #1 is: "+(strWord.substring(intWordCount,intWordCount+1)));
								con.print(" ");
								strGuess = con.readLine () ;
								intGuessCount += 1;
								intWordCount += 1;
							}	
							
							while(!strGuess.equalsIgnoreCase(strWord)){
								con.println();
								if(strGuess.equalsIgnoreCase("hint")){
									con.println();
									con.println(" Letter #"+(intWordCount+1)+" is: "+(strWord.substring(intWordCount,intWordCount+1)));
									con.print(" ");
									strGuess = con.readLine () ;
									intWordCount += 1;
								}else{
								
								con.println(" Incorrect, try again: "+strScrambled);
								con.print(" ");
								strGuess = con.readLine () ;
								}
								
								intGuessCount += 1;
							}
							
							con.println();
							con.println(" Correct!");
							intGuessCount += 1;
							con.println(" Guess Count: "+intGuessCount);
							
						}
						
						if(strPlayerName.equalsIgnoreCase("strongertogether")){
							System.out.println("inside loop");
							intGuessCount -= 2;
						}
						
						con.println();
						con.println(" FINISH");
						con.println(" Total Guess Count: "+intGuessCount);
						
						strGuessCount = (""+intGuessCount);
						
						System.out.println("strings placed into column 1 & 2 of row "+(intWJWOFHardLength+1)); 
						
						strHardScores [0][intWJWOFHardLength] = strPlayerName;
						strHardScores [1][intWJWOFHardLength] = strGuessCount;
						
						for(intCounter = 0; intCounter < intWJWOFHardLength; intCounter++){
							for(intCount = 0; intCount < intWJWOFHardLength; intCount++){
								strRight = strHardScores [1][intCount];
								strLeft = strHardScores [1][intCount+1];
								if(Double.parseDouble(strLeft) < Double.parseDouble(strRight)){
									strTemp = strHardScores [1][intCount];
									strHardScores [1][intCount] = strHardScores [1][intCount+1];
									strHardScores [1][intCount+1] = strTemp;
									
									strTemp = strHardScores [0][intCount];
									strHardScores [0][intCount] = strHardScores [0][intCount+1];
									strHardScores [0][intCount+1] = strTemp;
								}
							}
						}

						
						con.println();
						
						txtWJWOFHard.close();
						
						TextOutputFile txtHardOutput;
						txtHardOutput = new TextOutputFile("WJWOFHard.txt");

						for(intCount = 0; intCount <= intWJWOFHardLength; intCount++){
							txtHardOutput.println(strHardScores [0][intCount]);
							txtHardOutput.println(strHardScores [1][intCount]);
						}
						
						if(intGuessCount < Double.parseDouble(strHardScores [1][4])){
							con.println(" You're on the Word Jumble Wall of Fame! Type (v) to view leaderboard: ");
						}else{
							con.println(" 	    You didn't make it onto the Word Jumble Wall of Fame :(");
							con.println(" 		   Type (p) to play again or (q) to quit!");
						}
					}
				
				//code for multiplayer mode
				}else if(strPlayer.equalsIgnoreCase("m")){
					intGuessCount = 0;
					strScrambled = ("");
					intWordCount  = 0;
					
					//ask for input from player 1
					con.println();
					con.println(" Player 1, enter word: ");
					con.print(" ");
					strWord = con.readLine () ;
					
					//open new console to hide input
					Console console = new Console () ;
					
						//find length of chosen word
						intWordLength = strWord.length () ;
						
						//create new array to scramble chosen word
						strScramble = new String [intWordLength];
						
						for(intCount = 0; intCount < intWordLength; intCount++){
							intCheck = 0;
							strChar = strWord.substring(intCount,intCount+1);
							while(intCheck == 0){
								intRand = (int)(Math.random()*((intWordLength)-0))+0;
								strCheck = strScramble [intRand];
								if(strCheck == null){
									strScramble [intRand] = strChar;
									intCheck = 1;
								}else{
									intCheck = 0;
								}
								
							}
						}
						
						//create variable for scrambled word		
						for(intCount = 0; intCount < intWordLength; intCount++){
							strScrambled += strScramble [intCount];
						}
						
						console.println();
						console.print(" Player 2, Unscramble: ");	 
						
						console.print(strScrambled);
						console.println();
						console.print(" ");
						strGuess = console.readLine () ;
						
						//follow same steps as single player mode for guessing
							if(strGuess.equalsIgnoreCase("hint")){
								console.println();
								console.println(" Letter #1 is: "+(strWord.substring(intWordCount,intWordCount+1)));
								console.print(" ");
								strGuess = console.readLine () ;
								intGuessCount += 1;
								intWordCount += 1;
							}	
							
							while(!strGuess.equalsIgnoreCase(strWord)){
								console.println();
								if(strGuess.equalsIgnoreCase("hint")){
									console.println();
									console.println(" Letter #"+(intWordCount+1)+" is: "+(strWord.substring(intWordCount,intWordCount+1)));
									console.print(" ");
									strGuess = console.readLine () ;
									intWordCount += 1;
								}else{
								
								console.println(" Incorrect, try again: "+strScrambled);
								console.print(" ");
								strGuess = console.readLine () ;
								}
								
								intGuessCount += 1;
							}
							
						console.println();
						console.println(" Correct!");
						intGuessCount += 1;
						if(intGuessCount == 1){
							console.println(" You guessed the word in 1 attempt");
						}else{
							console.println(" You guessed the word in "+intGuessCount+" attempts");			
						}		
				
				}
		
		//view leaderboard code
		}else if(strMenu.equalsIgnoreCase("v")){
			
			//import scores input txt file (hard and easy)
			txtWJWOFEasy.close();
			txtWJWOFEasy = new TextInputFile("WJWOFEasy.txt");
			
			txtWJWOFHard.close();
			txtWJWOFHard = new TextInputFile("WJWOFHard.txt");
			
			//print scores from files onto console
			con.println();
			con.println(" EASY MODE");
			con.sleep(20);
			for(intCount = 0; intCount < 5; intCount++){
				strName = (txtWJWOFEasy.readLine () + "                                   ");
				strScore = txtWJWOFEasy.readLine ();
				
				con.println(" "+strName.substring(0,20)+" | "+strScore);
				con.sleep(20);
				}
			
			con.println();
			con.println(" HARD MODE");
			con.sleep(20);
			for(intCount = 0; intCount < 5; intCount++){
				strName = (txtWJWOFHard.readLine () + "                                   ");
				strScore = txtWJWOFHard.readLine ();
				
				con.println(" "+strName.substring(0,20)+" | "+strScore);
				con.sleep(20);
				}
				
				con.sleep(5000);
				
				con.println();
				con.println(" i - Instructions");
				con.sleep(50);
				con.println(" p - Play");
				con.sleep(50);
				con.println(" v - View WJ Hall of Fame");
				con.sleep(50);
				con.println(" q - Quit");
				con.sleep(50);
			}
			
		} // while loop
	}
	
	//method generates random number between first input and second input
	public static int Random(int intmax, int intmin){
		int intRand;
		intRand = (int)(Math.random()*((intmax)-intmin))+intmin;
		return intRand;
	}
	
}


