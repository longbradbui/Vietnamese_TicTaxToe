import java.util.Currency;
import java.util.Scanner;

public class Board {

    private int[][] BOARD;     // Declare a variable to hold the status of the current board

    // TO GENERATE THE PLAYING BOARD BASED ON USERS' CHOICE //
    public void generateBoard() {
        Scanner console2 = new Scanner(System.in);                      // Construct a Scanner
        System.out.println("How many rows would you like?");
        int boardRow = console2.nextInt();                            //   Prompting for board's row
        System.out.println("How many columns would you like?");
        int boardColumn = console2.nextInt();                       //     Prompting for board's column
        this.BOARD = new int[boardRow][boardColumn];               //      Return playing board with user's dimension
    }

    // TO GET THE PLAYING BOARD  //
    public int[][] getBoard() {
        return this.BOARD;
    }   // Return the current status of the gaming board

    // TO GET THE APPROPRIATE ROW FOR EACH MOVE OF THE PLAYERS //
    public int getCurrentRow(int currentColumn) {
        int row = -1;                                           // Initially, the current status of the board is -1 (INVALID)
        for (int i = this.BOARD.length - 1; i >= 0; i--) {      //  Set a loop starting from the top row of a column down to bottom
            if (this.BOARD[i][currentColumn] == 0) {          //   IF found the available row (all free spots are assigned as 0)
                row = i;                                     //    return the row
                break;                                      //     then Break, exit the function.
            }
        }
        return row;                                       //        Return the row
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

    // CHECK WINNING CONDITION FOR VERTICAL //
    public boolean checkWinningVertical(int col, int currentPlayer) {
        int streak = 0;                                           // Declare a variable to keep track of the continuity of dots
        for (int row = 0; row < this.BOARD.length; row++) {      //  Since we are validating in a VERTICAL so only ROW param is incrementing
            if (this.BOARD[row][col] == currentPlayer) {        //   If any increment of row in a static column contains are same numbers
                streak += 1;                                   //    Then increase the steak by 1
                if (streak == 5) {                            //     Once the streak hits 5 in a row
                    return true;                             //      Return true, we found the winner of the game
                }
            } else {                                       //        If not, then reset the streak back to 0
                streak = 0;
            }
        }
        return false;                                 //             Return false, we did not find any winner
    }

    // CHECK WINNING CONDITION FOR HORIZONTAL //
    public boolean checkWinningHorizontal(int row, int currentPlayer) {
        int streak = 0;                                                         // Declare a variable to keep track of the continuity of dots
        for (int column = 0; column < this.BOARD[0].length; column++) {        //  Since we are validating in a HORIZONTAL so only COLUMN param is incrementing
            if (this.BOARD[row][column] == currentPlayer) {                   //   If any increment of row in a static column contains are same numbers
                streak += 1;                                                 //    Then increase the steak by 1
                if (streak == 5) {                                          //     Once the streak hits 5 in a row
                    return true;                                           //      Return true, we found the winner of the game
                }
            } else {                                                     //        If not, then reset the streak back to 0
                streak = 0;
            }
        }
        return false;                                                 //             Return false, we did not find any winner
    }

    // CHECK WINNING CONDITION FOR DIAGONAL //
    public boolean checkWinningDiagonal(int col, int row, int currentPlayer) {
        int streak = 0;

        while (row != 0 && col != 0) {
            col --;
            row --;
            System.out.println(col);
            System.out.println(row);
        }
        while (row < this.BOARD.length && col < this.BOARD[0].length){
            if (this.BOARD[row][col] == currentPlayer){
                streak++;
                row++;
                col++;
                if (streak == 5){
                    return true;
                }
            } else {
                streak = 0;
            }
        }
//        copyRow = row;
//        copyCol = col;
//        while (copyRow != 0 && copyCol !=0) {
//            copyRow --;
//            copyCol ++;
//        }
//        while (copyRow < this.BOARD.length && copyCol < this.BOARD[0].length){
//            if (this.BOARD[copyRow][copyCol] == currentPlayer){
//                streak++;
//                copyRow--;
//                copyCol++;
//                if (streak == 5){
//                    return true;
//                }
//            } else {
//                streak = 0;
//            }
//        }
        return false;
    }


}





