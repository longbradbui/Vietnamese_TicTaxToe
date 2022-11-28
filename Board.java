import java.util.Scanner;

public class Board {
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
        int row = -1;                                           // Initially, the current status of the board is -1
        for (int i = this.BOARD.length-1; i >= 0 ; i--) {
            if (this.BOARD[i][currentColumn] == 0) {
                row = i;
                break;
            }
        }
        return row;
    }

    // PRINTS OUT THE GAME BOARD AFTER EACH MOVE //
    public void printGameBoard() {
        for (int[] ints : this.BOARD) {
            System.out.println();
            for (int col = 0; col < this.BOARD[0].length; col++) {
                System.out.print("|" + ints[col] + "|");
            }
        }
        System.out.println();
    }

}





