import java.util.Random;
import java.util.Scanner;

public class Player {

    // ASSIGNS PLAYER'S TURN //
    public int assignPlayers(){
        Random turn = new Random();
        int playerTurn = turn.nextInt(1,3);
        if (playerTurn == 1){
            return 1; // Player 1 goes
        }
        return 2;   // Players 2 goes
    }

    // GENERATES MOVES FOR PLAYERS //
    public int generateMove (int playersTurn, int maxColumn, String name){
        System.out.println("\t\t\t------*****------");
        System.out.println("It is " + name + "'s [Player " + playersTurn + "]"  + " turn. Please choose a column.");
        Scanner console2 = new Scanner(System.in);
        int playerMove = console2.nextInt();
            if (playerMove > 0 && playerMove <= maxColumn) {
                return playerMove;
            }
            throw new IllegalArgumentException();
    }

    // PRINTS OUT ERROR MESSAGE WITH SPECIFIC ERROR //
    public void errorMessage (String custom){
        System.out.println("Your input was invalid. Please try again. \n" + custom);
    }
}
