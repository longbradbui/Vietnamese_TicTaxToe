import java.util.Scanner;

public class ConnectFiveMain {
    public static String player1Name = null;      //  A variable to hold the name of Player 1
    public static String player2Name = null;     //   A variable to hold the name of Player 2

    // TO GREET PLAYERS //
    public static void intro() {
        System.out.println("""
                \tXO Welcome to Connect Five Games OX\s
                Would you like to know more about the rule?\s
                \t\t[Y]\t\t\t\t[N]""");
    }

    // TO EXPLAIN RULE TO NEW PLAYERS //
    public static void showRule() {
        System.out.println("\nTo be the first player to connect FIVE of the same dots in a row (either vertically, horizontally, or diagonally)");
        System.out.println("Players must alternate turns, only ONE dot can be dropped in each turn");
        System.out.println("On your turn, drop your move from the top into any of the slots");
        System.out.println("Your dot will be placed at the FREE-MOST BOTTOM spot in the column that you have chosen");
        System.out.println("The game ends when there is a FIVE-IN-A-ROW!!!! \n");
    }

    // TO GET TO KNOW ABOUT OUR PLAYERS //
    public static void getToKnow() {
        Scanner console1 = new Scanner(System.in);
        System.out.print("First player:\t");
        String playerOne = console1.next();
        System.out.print("Second player:\t");
        String playerTwo = console1.next();
        System.out.println("\n[" + playerOne + "]" + " *VS* " + "[" + playerTwo + "]");
        player1Name = playerOne;
        player2Name = playerTwo;
        System.out.println("\nSounds good. Shall we start now!!!!\n");
    }

    // ASKING IF PLAYERS KNOW THE GAME RULE //
    public static void getStarted (){
        intro();                                                                  // Display intro message to players
        Scanner console = new Scanner(System.in);                                //  Construct a Scanner to scanner for player's choice
        String userInput = console.next();                                      //   Prompt user's console
        if (userInput == null) {                                               //    Test for user's input
            System.out.println("Invalid Input, please type Y or N.");
        }
        if (userInput != null && (userInput.equals("y") || userInput.equals("Y") || userInput.equals("yes") || userInput.equals("Yes"))) {
            showRule();
        }
    }

    // LOGIC AND DRIVE OF THE GAME //
    public static void gameMain(){
        getToKnow();                                         //    Get to know our players and save player's name to the "playerName" variable
        Player player = new Player();                       //     Generate Player Object
        Board b = new Board();                             //      Generate Board Object
        int maxColumn;                                    //       Declare a variable that will hold the maximum column for each game
        int maxRow;                                      //        Declare a variable that will hold the maximum column for each game
        int playersTurn = player.assignPlayers();       //         Declare a variable that will keep track the turn
        b.generateBoard();                             //          Initially, We have to generate the Game Board
        int[][] playingBoard = b.getBoard();          //           Declare a 2D array that will save the state of the board after each move of the player
        maxColumn = playingBoard[0].length;          //            The maximum column size is the length of the child array
        maxRow = playingBoard.length;               //             The maximum column size is the length of the parent array
        boolean gameContinue = true;               //              Declare a boolean variable to validate the continuity of the game
        while (gameContinue) {                    //               The game will keep going until the validation becomes FALSE
            int movement = 0;                    //                The movement of the Players will be reset back to 0 after each move
            try {
                if (playersTurn == 1) {   // Update new move for Player 1
                    movement = player.generateMove(playersTurn, maxColumn, player1Name, b);
                } else {                //   Update new move for Player 2
                    movement = player.generateMove(playersTurn, maxColumn, player2Name, b);
                }
            } catch (
                    IllegalArgumentException e) {                                             // Catch the exception when the movement update is invalid
                System.out.println("PLEASE CHOOSE A COLUMN RANGE FROM 1 TO " + maxColumn);
            }
            if (movement != 0) {
                int currentRow = b.getCurrentRow(movement - 1);                 // Get the suitable row for each movement
                if (currentRow == -1) {                                                    //  Theoretically, a free spot will hold a "O" value
                    player.errorMessage("PLEASE RE-SELECT THE COLUMN");            //   If the input was invalid, "-1" will be returned and keep running till player can find a suitable spot.
                } else {
                    playingBoard[currentRow][movement - 1] = playersTurn;                //    Fill the spot with player's assigned number. Update the playing board with the obtained row and column.
                    b.printGameBoard();                                                 //     The [column] is [movement-1] because array is zero based index
                    // AFTER EACH MOVE, CHECK THE WINNING STATUS OF PLAYERS //
                    // Check if the current player in wining horizontally
                    if (b.checkWinningHorizontal(currentRow, playersTurn)) {
                        int rowInBoard = currentRow + 1;
                        System.out.println("\nPlayer " + playersTurn + " wins by Horizontal - [ROW " + rowInBoard + "] from top");
                        System.out.println("\t\t------*****------\n");
                        break; // Exits out of the method if winner were found //
                    }
                    // Check if the current player in wining vertically
                    if (b.checkWinningVertical(movement - 1, playersTurn)) {
                        System.out.println("\nPlayer " + playersTurn + " wins by Vertical - [COLUMN " + movement + "] from left");
                        System.out.println("\t\t------*****------\n");
                        break; // Exits out of the method if winner were found //
                    }
                    // Check if the current player in wining diagonally
//                    if (b.checkWinningDiagonal(movement, currentRow, playersTurn)) {
//                        System.out.println("\nPlayer " + playersTurn + " wins by Diagonal");
//                        System.out.println("\t\t------*****------\n");
//                        break;
//                    }
                    // If no winner were found, each player alternates their turn //
                    if (playersTurn == 1) {
                        playersTurn = 2;
                    } else {
                        playersTurn = 1;
                    }
                }
            }
        }
    }

    // MAIN //
    public static void main(String[] args) {
        boolean playAgain;
        getStarted();
        do{
            gameMain();
            Scanner cs = new Scanner(System.in);
            System.out.println("Would you like top play another game? ");
            String answer = cs.next();
            if ((answer.equals("y") || answer.equals("Y") || answer.equals("yes") || answer.equals("Yes"))) {
                playAgain = true;
            } else {
                playAgain = false;
                System.out.println("Thanks For Playing With Us Today!!!");
            }
        } while (playAgain);
    }
}
