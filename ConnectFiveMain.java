import java.util.Scanner;

public class ConnectFiveMain{
    public static String player1Name = null;      //  A variable to hold the name of Player 1
    public static String player2Name = null; //   A variable to hold the name of Player 2

    public static void main(String[] args){
        intro();                                                                            // Display intro message to players
        Scanner console = new Scanner(System.in);                                          //  Construct a Scanner to scanner for player's choice
        String userInput = console.next();                                                //   Prompt user's console
        if (userInput == null){                                                          //    Test for user's input
            System.out.println("Invalid Input, please type Y or N.");
        }
        if (userInput != null && (userInput.equals("y") || userInput.equals("Y")
               || userInput.equals("yes") || userInput.equals("Yes"))) {             // Test for user's input
            showRule();                                                             //  Show the rule as players' wish
        }
        getToKnow();                                         // Get to know our players and save player's name to the "playerName" variable
        Player player = new Player();                       //  Generate Player Object
        Board b = new Board();                             //   Generate Board Object
        int maxColumn;                                    //    Declare a variable that will hold the maximum column for each game
        int maxRow;                                      //     Declare a variable that will hold the maximum column for each game
        int playersTurn = player.assignPlayers();       //      Declare a variable that will keep track the turn
        b.generateBoard();                             //       Initially, We have to generate the Game Board
        int [][] playingBoard = b.getBoard();         //        Declare a 2D array that will save the state of the board after each move of the player
        maxColumn = playingBoard[0].length;          //         The maximum column size is the length of the child array
        maxRow = playingBoard.length;               //          The maximum column size is the length of the parent array
        boolean gameContinue = true;               //           Declare a boolean variable to validate the continuity of the game
        // GAME STARTS FROM HERE //
        while (gameContinue){         // The game will keep going until the validation becomes FALSE
            int movement = 0;        //  The movement of the Players will be reset back to 0 after each move
            try {
                if (playersTurn == 1) {   // Update new move for Player 1
                    movement = player.generateMove(playersTurn, maxColumn, player1Name, b);
                } else {                //   Update new move for Player 2
                    movement = player.generateMove(playersTurn, maxColumn, player2Name, b);
                }
            }
            catch (IllegalArgumentException e) {                                             // Catch the exception when the movement update is invalid
                System.out.println("PLEASE CHOOSE A COLUMN RANGE FROM 1 TO " + maxColumn);
            }
            if (movement != 0){
                int currentRow = b.getCurrentRow(movement-1);            // Get the suitable row for each movement
                if (currentRow == -1){                                              //  Theoretically, a free spot will hold a "O" value
                    player.errorMessage("PLEASE RE-SELECT THE COLUMN");     //   If the input was invalid, "-1" will be returned and keep running till player can find a suitable spot.
            } else {
                playingBoard[currentRow][movement-1] = playersTurn;              
                b.printGameBoard();
                if (b.checkWinner(playersTurn, currentRow, movement-1)) {
                    System.out.println("\nPlayer " + playersTurn + " wins");
                    break;
                }
                if (playersTurn == 1) {                                            // Each player alternates turn after each move
                    playersTurn = 2;
                } else {
                    playersTurn = 1;
                }
            }
            }
        }
    }
    // TO GREET PLAYERS //
    public static void intro(){
        System.out.println("""
                \tXO Welcome to Connect Five Games OX\s
                Would you like to know more about the rule?\s
                \t\t[Y]\t\t\t\t[N]""");
    }

    // TO EXPLAIN RULE TO NEW PLAYERS //
    public static void showRule (){
        System.out.println("\nTo be the first player to connect FIVE of the same dots in a row (either vertically, horizontally, or diagonally)");
        System.out.println("First, decide who will go first and decide the character to play with");
        System.out.println("Players must alternate turns, and only dot can be dropped in each turn");
        System.out.println("On your turn, drop one of your dots from the top into any of the slots");
        System.out.println("The game ends when there is a FIVE-IN-A-ROW!!!! \n");
    }

    // TO GET TO KNOW ABOUT OUR PLAYERS //
    public static void getToKnow(){
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
}
