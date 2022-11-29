import java.util.Scanner;

public class Board {

    // Declare a variable toi hold the status of the current board
    private int[][] BOARD;

    // TO GENERATE THE PLAYING BOARD BASED ON USERS' CHOICE
    public void generateBoard() {
        Scanner console2 = new Scanner(System.in);                      // Construct a Scanner
        System.out.println("How many rows would you like?");
        int boardRow = console2.nextInt();                            //   Prompting for board's row
        System.out.println("How many columns would you like?");
        int boardColumn = console2.nextInt();                       //     Prompting for board's column
        this.BOARD = new int[boardRow][boardColumn];               //      Return playing board with user's dimension
    }

    // TO GET THE PLAYING BOARD  //
    public int[][] getBoard () {
        return this.BOARD;
    }   // Return the current status of the gaming board

    // TO GET THE APPROPRIATE ROW FOR EACH MOVE OF THE PLAYERS //
    public int getCurrentRow(int currentColumn) {
        int row = -1;                                           // Initially, the current status of the board is -1 (INVALID)
        for (int i = this.BOARD.length-1; i >= 0 ; i--) {      //  Set a loop starting from the top row of a column down to bottom
            if (this.BOARD[i][currentColumn] == 0) {          //   IF found the available row (all free spots are assigned as 0)
                row = i;                                     //    return the row
                break;                                      //     then Break, exit the function.
            }
        }
        return row;                                       //        IF there is no free row (aka column is full)
    }

    // PRINTS OUT THE GAME BOARD AFTER EACH MOVE //
    public void printGameBoard() {
        for (int[] ints : this.BOARD) {                                 // Separate by an ENTER
            System.out.println();
            for (int col = 0; col < this.BOARD[0].length; col++) {    //   Set a loop prints out the status of all spots
                System.out.print("|" + ints[col] + "|");             //    in the game board
            }
        }
        System.out.println();
    }

}





