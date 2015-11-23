import java.util.ArrayList;

public class KnightsTour {
	
	/**
	 * This subroutine displays the rules of the Open Knights Tour game whenever
	 * it is called. It also generates and example board and calls the displayBoard
	 * subroutine to display it.
	 */
	public static void displayRules () { 
		
		int[][] boardExample = new int[3][4]; 
		TextIO.putln("The Open Knights Tour\n");
		TextIO.putln("A Knights tour is the name given to a series of moves on a chessboard, using a");
		TextIO.putln("knight, so that the knight visits each square only once. If the knight ends at");
		TextIO.putln("the square it started on, the tour is a closed tour, otherwise it is open. This");
		TextIO.putln("program allows you to complete an open knights tour, as you are not required to");
		TextIO.putln("end on the square that you started on.\n");
		TextIO.putln("There is only one really important rule that you need to remember while playing.");
		TextIO.putln("You can't visit the same spot twice!\n");
		TextIO.putln("Here is a preview of the board. You would normally start on A1, though with a");
		TextIO.putln("larger board you can start wherever you want.\n");
		displayBoard(boardExample); 
		TextIO.putln("");
		TextIO.putln("If you are unfamiliar with how a Knight is supposed to move, please visit\n");
		TextIO.putln("http://en.wikipedia.org/wiki/Chess\n");
		TextIO.putln("Press any key and hit return to go back to the main menu.");
		TextIO.getln();
	}
	
	/**
	 * This subroutine takes the move the user entered and derives the Row from it. 
	 * rowParse stores the relevant character from move, minuses one from it and
	 * passes it to the variable row. This is then returned to main.  
	 * 
	 * @param move User input to be parsed.
	 * @return
	 */
	public static int getRow (String move) {
		int row;
		char rowParse;   
		rowParse = move.charAt(0); 
		row = rowParse - 'a'; 
		return row; 
	}
	
	/** 
	 * This subroutine takes the move the user entered and derives the Column from it.
	 * The subroutine removes any characters that aren't a number from the string move,
	 * minuses 1 from it, and then returns the value back to main. 
	 * 
	 * @param move User input to be parsed.
	 * @return
	 */
	public static int getColumn (String move) {
		int column;
		column = Integer.parseInt(move.replaceAll("[\\D]", ""));
		column = column - 1;
		return column;
	}
	
	/**
	 * This subroutine checks if the user has completed an Open Knights Tour. It iterates
	 * through the array and checks to see if each element has a value greater than 0.
	 * If the amount of elements holding a value greater than 0 equals the total amount
	 * of elements in the array, then the player has achieved victory. 
	 * 
	 * @param board This is the board array from main, with the current game state on it.
	 * @return Returns the boolean checkVictory to main. 
	 */
	public static boolean checkVictory (int[][] board) {
		boolean checkVictory = false;
		int counter1 = 0, counter2 = 0;
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[i].length; j++) {
				counter1++;
				if (board[i][j] != 0) {
				counter2++;
				}
			}
		}	
		if (counter1 == counter2) {
			checkVictory = true;
		}
		return checkVictory;  
	}
	
	/**
	 * This subroutine checks to see whether the user has entered a valid Knight move.
	 * If the move is valid, the boolean moveCheck is set to true. 
	 * If the move is invalid, the boolean moveCheck is set to false. 
	 * The mathematical 'formula' here is taking into account that at any given time the Knight
	 * can move two squares horizontally and one vertically, or two squares vertically and
	 * one horizontally. By using the Math.abs class, the subroutine checks whether the
	 * desired move is within this criteria. 
	 * 
	 * @param row The users intended row destination.
	 * @param column The users intended column destination.
	 * @param lastColumn The users current column destination.
	 * @param lastRow The users current row destination. 
	 * @return returns moveCheck to main. 
	 */
	public static boolean isMoveValid (int row, int column, int lastColumn, int lastRow) {
		boolean moveCheck = false;
		boolean isOneTrue = false;
		if (Math.abs(column-lastColumn) == 2 && Math.abs(row - lastRow) == 1) {
			moveCheck = true;
			isOneTrue = true;
			}
		if (Math.abs(column-lastColumn) == 1 && Math.abs(row - lastRow) == 2) {
			moveCheck = true;
			isOneTrue = true;
			}
		if (isOneTrue == false) {
			TextIO.putln("This is not a valid knight move. Please try again.");
		}
		return moveCheck;	
	}
	
    /**
     * This subroutine generates the users desired game board size.
     * 
     * @return returns the generated board to main.
     */
	public static int[][] generateBoard () {
		int rows, columns;
        TextIO.putln("Welcome to the Open Knights Tour. If you haven't read the rules");
        TextIO.putln("you probably should!\n");
        TextIO.putln("Don't forget that if you win you can save your rules to a text file!\n");
		TextIO.putln("What size board do you wish to play with? Minimum is 3x3 and maximum is 9x9");
		TextIO.putln("Enter amount of rows");
		do {
			rows = TextIO.getlnInt();
			if (rows < 3) {
				TextIO.putln("Please enter a higher value.");
			}
			if (rows > 9) {
				TextIO.putln("Please enter a lower value.");
			}
		} while (rows < 3 || rows > 9);
		TextIO.putln("Enter amount of columns. Minimum is 4.");
		do {
			columns = TextIO.getlnInt();
			if (columns < 4) {
				TextIO.putln("Please enter a higher value.");
			}
			if (columns > 9) {
				TextIO.putln("Please enter a lower value.");
			}
		} while (columns < 4 || columns > 9);
		int[][] board = new int[rows][columns];
		return board;
	}
	
	/**
	 * This subroutine is used multiple times in the program to display the current game board.
	 * It takes the board array and iterates through it, outputting a formatted display of
	 * the current state of the game board.
	 * 
	 * @param board The array holding the current state of the game. 
	 */
	public static void displayBoard(int[][] board) {
		char columnNames;
		columnNames = '`'; 							          // Sets columnNames to '`' so when it increments it will start at 'a'
		TextIO.put("   "); 							          // Help with formatting.
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[i].length; j++) {
				if (i < 1) {
					columnNames = (char) (columnNames + 1);   // Displays the names of the columns e.g. "a, b, c, d" etc.
					TextIO.put(columnNames + " "); 			  // Help with formatting.
				}
			}
		}
		for (int i=0; i<board.length; i++) {
			TextIO.putln(""); 									// Help with formatting. 
			for (int j=0; j<board[i].length; j++) {
				if (j < 1) {
				TextIO.put((i + 1) + ": "); 					// Displays the names of the rows e.g. "1, 2, 3, 4" etc.
				}
				if (board[i][j] == 0) {
				TextIO.put("-"); 								// If the space hasn't been visited it displays a '-'
				}
				else
				TextIO.put(board[i][j]) ; 						// Displays the number on the square if it has been visited.
				TextIO.put(" "); 								// Help with formatting.
				
				}
		}	
		TextIO.putln("");
	}
	
	/**
	 * This subroutine is for setting the users starting square on the game board. First it
	 * asks if the user would like to select a starting square. If the user says yes it displays
	 * the game board and allows them to input their desired starting location. If the user says no
	 * their starting position is set to the default of a1.
	 * 
	 * @param board The array holding the current state of the game.
	 * @return
	 */
	public static int[][] startingSquare(int[][] board) {
		String move;
		char squareChoice;
		int row = 0, column = 0;
		TextIO.putln("Would you like to select your starting square? Please input Y/N");
		squareChoice = TextIO.getChar();
		if (squareChoice == 'Y' || squareChoice == 'y') {
			TextIO.putln("Here is the board you wanted to play with.\n");
			displayBoard(board);
			TextIO.putln("\nWhich square would you like to start on?");
			TextIO.getlnString();           // There was a bug? After doing a getChar() it would force those char results onto the next getlnString. 
			move = TextIO.getlnString();    // This would crash the program. I'm not sure why it happened but adding another getlnString stops it.
			row = getRow(move);
			column = getColumn(move);
			board[column][row] = 1;
		}
		if (squareChoice == 'N' || squareChoice == 'n') {
			TextIO.putln("Okay!");
			TextIO.getlnString();  // Same bug? as mentioned above. This getlnString stops the getlnString in main from crashing. 
			board[column][row] = 1;
		}
		return board;
	}
	
	/**
	 * This subroutine displays the Open Knights Tour into screen. The Ascii
	 * Knight was taken from this website http://www.retrojunkie.com/asciiart/sports/chess.htm
	 */
	public static void displayIntro() {
		TextIO.putln("        ,....,");
		TextIO.putln("      ,::::::<");
		TextIO.putln("     ,::/^\"``.            _______________");
		TextIO.putln("    ,::/, `   e`.        |    O P E N    |");
		TextIO.putln("   ,::; |        '.      | K N I G H T S |");
		TextIO.putln("   ,::|   \' ___,-.  c)   |    T O U R    |");
		TextIO.putln("   ;::|    \'   '-'       |***************|");
		TextIO.putln("   ;::|     \'            |   N E I G H   |");
		TextIO.putln("   ;::|   _.=\'           |_______________|");
		TextIO.putln("   `;:|.=` _.=`\'");
		TextIO.putln("     '|_.=`   __\'");
		TextIO.putln("      \'_..==`` /");
		TextIO.putln("      .'.___.-'.");
		TextIO.putln("     /'          \'");
		TextIO.putln("    ('--......--')");
		TextIO.putln("    /'--......--'\'");
		TextIO.putln("    `'--......--'`");
		TextIO.putln("Hello and welcome to the Open Knights Tour \n");
		TextIO.putln("Main Menu.");
		TextIO.putln("1. Play the game.");
		TextIO.putln("2. Rules");
		TextIO.putln("3. Quit");
	}
	
	/**
	 * This subroutine is for saving the users game. It is only used when the user has successfully
	 * completed an Open Knights Tour. If the user chooses to save their game it outputs their moves
	 * to a text file called moves.txt by using the TextIO.writefile function. 
	 * 
	 * @param userMoves The ArrayList that holds the users moves throughout the game. 
	 */
	public static void saveGame(ArrayList<String> userMoves) {
		char saveInput;
		TextIO.putln("Congratulations! You have completed the Open Knights Tour!");
		TextIO.putln("Do you want to save your moves to a text file for future perusal?");
		TextIO.putln("Please enter Y/N");
		saveInput = TextIO.getlnChar();
		if (saveInput == 'Y' || saveInput == 'y') {
			TextIO.writeFile("moves.txt");
			for (int i=0; i<userMoves.size(); i++) {
	            TextIO.putln(userMoves.get(i));
	          }
	        TextIO.writeStandardOutput();
			}
		userMoves.clear();
		}
	

	/**
	 * This is the main body of the Open Knights Tour program and handles the main logic of
	 * the game.
	 */
	
	public static void main(String[] args) {
		int menuCounter = 0;         // This variable keeps track of the Users initial menu option. 
		int board[][];               // This is the Array that holds the board used for the game. 
		int moveTracker = 1;         // moveTracker is used for the retracting moves feature. It also stops the User retracting their first move. 
		String move;                 // The variable for storing User input. 
		String[] columnsString = {"a", "b", "c", "d", "e","f","g","h","i","j","k"};  // This is for telling the user the current position of the Knight.
		String[] rowName = {"1", "2", "3", "4", "5", "6", "7", "8","9","10"};        // This is for telling the user the current position of the Knight.
		int column = 0, row = 0;         // Column and Row store the Users current desired move/actual move.
	    int lastRow = 0, lastColumn = 0; // lastColumn and lastRow store the Users last move.
		boolean moveCheck = false;       // moveCheck is used to validate Users move. victoryCheck is for checking victory.
	    boolean victoryCheck = false;    // victoryCheck is for checking victory.
	    boolean repeatCheck = false;     // repeatCheck is used for checking if a move was correct and whether the board should be updated.
	    boolean runOnce = false;         // runOnce is a boolean check that runs once, and is used to check if the user changed starting squares.
		ArrayList<String> userMoves = new ArrayList<String>(); // This arrayList keeps track of the moves the users enter. It is used when saving
		                                                       // moves to a text file after the User successfully completes a tour.
		
		do { 	// Main loop of the program that shows the menu.
		displayIntro();
		menuCounter = TextIO.getlnInt();
		
		switch (menuCounter) {
		
		case 1: 
			
		moveTracker = 1;  	// Set variables back to their initial values in case the user is playing again after winning/quitting.
		row = 0;
		column = 0;
		board = generateBoard();
		board = startingSquare(board);
        runOnce = false;
        userMoves.clear();
		
		outerloop: 	// If the User chooses to quit this tag breaks them out of the main game loop.
		
		do { 	    // This is the main loop for the Open Knights Tour game. 
			
			if (repeatCheck == false) { 	// If the user has entered a valid move this updates the board. Otherwise, the user has inputed an invalid
				lastRow = row;          	// move and the program is just looping. 
				lastColumn = column;
			}
			
			if (board[0][0] == 0 && runOnce == false) {       // This statement is for when the user chooses their own starting space.
				for (int i=0; i<board.length; i++) {          // It searches the array for the new starting position and then sets it
					for (int j=0; j<board[i].length; j++) {   // As the users current position.
						if (board[i][j] == 1) {               
						lastRow = j;
						lastColumn = i;
						runOnce = true;
						userMoves.add(columnsString[lastRow] + rowName[lastColumn]); // Sets the users custom starting position as the first move
						break;                                                       // in the userMoves ArrayList.
						}
					}
				}		
			}
			
			if (board[0][0] == 1 && runOnce == false) { 	// This adds a1 to the userMoves arrayList that keeps track of the users moves.
				userMoves.add("a1");                        // This runs if the User didn't change their starting location. 
				runOnce = true;
			}
				
			displayBoard(board);
			TextIO.putln("The knight is currently at: " + columnsString[lastRow] + rowName[lastColumn]);
			TextIO.putln("Where would you like to move to? Please enter your move in the format of \n[Row][Column], for example a1, b2. Press q to quit.");
			if (moveTracker > 1) {
			TextIO.putln("Press r if you would like to retract your move.");
			}
			
			do { 	// This loop encompasses the User input. The User can either input 'q', 'r', or a move in the format of [Row][Column] e.g. a1, b2.
				
				move = TextIO.getlnString(); 	// Gets the Users move.
				
				if (move.equals("q")) { 	    // For when the user decides to quit. Also clears the userMoves arrayList.
			        userMoves.clear();
					break outerloop;
				}
				
				if (move.equals("r") && moveTracker > 1) {                  // For when the user wants to retract a move. 
					board[column][row] = 0;                                 // Sets current position to 0, reversing the move.  
					lastRow = getRow(userMoves.get(moveTracker - 2));       // Gets the Users previous Row by pulling their last move from userMoves.
					lastColumn = getColumn(userMoves.get(moveTracker - 2)); // Gets the Users previous Column by pulling their last move from userMoves.
					displayBoard(board);                                    // Displays the board again so that the user can reevaluate the board. 
					TextIO.putln("Move retracted.");
					TextIO.putln("Where would you like to move to? Please enter your move in the format of \n[Row][Column], for example a1, b2");
					TextIO.putln("The knight is currently at: " + columnsString[lastRow] + rowName[lastColumn]);
					move = TextIO.getlnString();          // Has the User enter their new desired move. This will then return to the main loop and
					moveTracker = moveTracker - 1;        // the users move will be calculated normally, their previous move having been reverted.
					userMoves.remove(userMoves.size() - 1);
				}
				
				row = getRow(move);           	// Parses the Row from the users input. 
				column = getColumn(move);     	// Parses the Column from the Users input.
				
				if (board[column][row] > 0) { 	// Checks to see if the square the Users wishes to move to has previous been occupied. 
					TextIO.putln("You have already been here. Please try again.");
					repeatCheck = true;       	// Sets repeatCheck to true so when the loop cycles the board won't be updated. 
				}
				
				else repeatCheck = false; 		// If the users hasn't previous visited this square, sets repeatCheck to false so the board can be updated.
				moveCheck = isMoveValid(column, row, lastRow, lastColumn); 		// Checks if the users move is a valid Knight move. 
				
			} while (moveCheck == false);
			
			if (moveCheck == true && repeatCheck == false) { 	// Checks if the move was valid, and if the board can be updated. 
				moveTracker++;                               	// Increments moveTracker.
				board[column][row] = moveTracker;            	// This updates the board with the current number of times the Knight has moved.
				userMoves.add(move);                         	// Adds the users move to the userMoves array list.
			}
			
			moveCheck = false;                        // Resets moveCheck for the next loop.
			victoryCheck = checkVictory(board);       // Runs the checkVictory subroutine to check for victory. 
			
			} while (victoryCheck == false);
        
		if (victoryCheck == true) { 	// Runs when the User wins. 
		victoryCheck = false; 			// Resets the victoryCheck variable. 
		saveGame(userMoves);       		// Asks the User whether they'd like to save their game to a text file.
		}
		
		else 
		TextIO.putln("Now quitting to the main menu."); // Message when the user inputs 'q'

		
		break;
		
		case 2: // Runs when the User selects option 2.
			
		displayRules();	// Runs the displayRules subroutine to display the game rules. 
		
		break;
		
		case 3: // Runs when the user selects option 3.
		
	    TextIO.putln("Bye! Have a good neighhhhhh!"); // A horse pun. 
			
		break;
		
		}
	} while (menuCounter < 3);
  }
}