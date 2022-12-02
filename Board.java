import java.util.Currency;
import java.util.Scanner;

public class Board {

    private int[][] BOARD;     // Declare a variable to hold the status of the current playing board

    // TO GENERATE THE PLAYING BOARD BASED ON USERS' CHOICE //
    public void generateBoard() {
        Scanner console2 = new Scanner(System.in);                      // Construct a Scanner
        System.out.println("How many rows would you like?");
        int boardRow = console2.nextInt();                            //   Prompting for board's ROW
        System.out.println("How many columns would you like?");
        int boardColumn = console2.nextInt();                       //     Prompting for board's COLUMN
        this.BOARD = new int[boardRow][boardColumn];               //      Return playing board with user's dimension
    }

    // TO GET THE PLAYING BOARD  //
    public int[][] getBoard() {
        return this.BOARD;
    }   // Return the current status of the gaming board

    // TO GET THE APPROPRIATE ROW FOR EACH MOVE OF THE PLAYERS //
    public int getCurrentRow(int currentColumn) {
        int row = -1;                                           // Initially, the current status of the board is -1 (INVALID)
        for (int i = this.BOARD.length - 1; i >= 0; i--) {     //  Set a loop starting from the top row of a column down to bottom
            if (this.BOARD[i][currentColumn] == 0) {          //   IF found the available row (all free spots are assigned as 0)
                row = i;                                     //    return the row
                break;                                      //     then BREAK, exit the function.
            }
        }
        return row;                                       //        Return the appropriate row
    }

    // PRINTS OUT THE GAME BOARD AFTER EACH MOVE //
    public void printGameBoard() {
        for (int[] arr : this.BOARD) {
            System.out.println();                                      // Separate by an ENTER, move to next line
            for (int col = 0; col < this.BOARD[0].length; col++) {    //   Set a loop traverse through gaming board
                System.out.print("\t");                              //    Shift by one TAB character for reading purposes
                System.out.print(" " + arr[col] + " ");             //     Printing the value at the specific coordinates
            }
        }
        System.out.println();
    }

    // CHECK WINNING CONDITION FOR VERTICAL //
    public boolean checkWinningVertical(int col, int currentPlayer) {
        int streak = 0;                                           // Declare a variable to keep track of the continuity of dots
        for (int row = 0; row < this.BOARD.length; row++) {      //  Since we are validating in a VERTICAL so only ROW param is incrementing
            if (this.BOARD[row][col] == currentPlayer) {        //   If any increment of row in a static column contains are same numbers
                streak += 1;                                   //    Then increase the steak by 1
                if (streak == 5) {                            //     Once the streak hits 5 in a row
                    return true;                             //      => Return true, we found the winner of the game (VERTICALLY)
                }                                           //       If not, then reset the streak back to 0
            } else {
                streak = 0;
            }
        }
        return false;                                 //             Return false if no winner were found
    }

    // CHECK WINNING CONDITION FOR HORIZONTAL //
    public boolean checkWinningHorizontal(int row, int currentPlayer) {
        int streak = 0;                                                         // Declare a variable to keep track of the continuity of dots
        for (int column = 0; column < this.BOARD[0].length; column++) {        //  Since we are validating in a HORIZONTAL so only COLUMN param is incrementing
            if (this.BOARD[row][column] == currentPlayer) {                   //   If any increment of row in a static column contains are same numbers
                streak += 1;                                                 //    Then increase the steak by 1
                if (streak == 5) {                                          //     Once the streak hits 5 in a row
                    return true;                                           //      => Return true, we found the winner of the game (HORIZONTALLY)
                }                                                         //       If not, then reset the streak back to 0
            } else {
                streak = 0;
            }
        }
        return false;                                                 //            Return false if no winner were found
    }

    // CHECK WINNING CONDITION FOR DIAGONAL //
    public boolean checkWinningDiagonal_DownRight(int col, int row, int currentPlayer) {
        int streak = 0;                          // Declare a variable keep track of the player's streak
        boolean moveUp = true;                  //  Declare a variable to validate whether the coordinate is by the edge of the playing board
        while (moveUp) {                       //   While we can still move up (Diagonally)
            if (row == 0 || col == 0) {       //    Either column or row hits 0
                moveUp = false;              //     Then STOP
            } else {
                row -= 1;                   //      Decrement by 1 unit
                col -= 1;                  //       Decrement by 1 unit
            }
        }

        // Set up a while loop runs until it hits the boundaries of size
        while ((row < this.BOARD.length  &&  col < this.BOARD[0].length)) {
            if (this.BOARD[row][col] == currentPlayer) {  // If the value at the coordinate equals the value assigned to player
                streak += 1;                             //  Increment the streak by 1
                if (streak == 5)                        //   If the streak hits 5
                    return true;                       //    => Return true, we found the winner of the game
            } else {                                  //     If not, then reset the streak back to 0
                streak = 0;
            }
            row += 1;                               //       Shift the value of row
            col += 1;                              //        Shift the value of column
        }
        return false;                           //           Return false if no winner were found
    }

    // CHECK WINNING CONDITION FOR DIAGONAL //
    public boolean checkWinningDiagonal_DownLeft(int col, int row, int currentPlayer) {
        int streak = 0;                          // Declare a variable keep track of the player's streak
        boolean moveUp = true;                  //  Declare a variable to validate whether the coordinate is by the edge of the playing board

        while (moveUp) {                       //   While we can still move up (Diagonally)
            if (row == 0 || col == 0) {       //    Either column or row hits 0
                moveUp = false;              //     Then STOP
            } else {
                row -= 1;                  //      Decrement by 1 unit 
                col += 1;                 //       Increment by 1 unit 
            }
            // In this case, column is going UP RIGHT to top, shifting to next column in the array, which behaves a little different than row
        }
                // Set up a while loop runs until it hits the boundaries of size

        while ((row < this.BOARD.length && row > -1) && (col > -1 &&  col < this.BOARD[0].length)) {
            if (this.BOARD[row][col] == currentPlayer) {  // If the value at the coordinate equals the value assigned to player
                streak += 1;                             //  Increment the streak by 1
                if (streak == 5)                        //   If the streak hits 5
                    return true;                       //    => Return true, we found the winner of the game
            } else {                                  //     If not, then reset the streak back to 0
                streak = 0;
            }
            row += 1;                               //       Shift the value of row
            col -= 1;                              //        Shift DOWN the value of column, we are going to the left
        }
        return false;                           //           Return false if no winner were found
    }
}









