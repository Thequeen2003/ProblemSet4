**********************************************************************
 * @file ConnectFour.java
 * @brief I do not know how to really check whether or not all possible gameplays work properly especially for diagonal wins
 * @author Alantis Green
 * @date: 10/31/2022
 * @acknowledgement: Dr. Pauca's Office Hours (I altered the Tic Tac Toe code)
 ***********************************************************************/
import java.awt.*;
import java.util.Scanner;

public class ConnectFour {

    public static void main(String[] args) {
        System.out.println("Welcome to Connect 4!");
        System.out.println("Click on one of the green boxes to choose your column.");
        System.out.println("Gravity will take your chip to the lowest open spot!");
        System.out.println("First player to get 4 in a row vertically, horizontally, or diagonally wins!");
        int moves;
        int currentPlayer = 1;
        // Integers in board denote three possible states:
        // 0 : empty, 1 : Blue, and 2 : Red
        int[][] board = new int[7][7];
        clickSpace(board);
        boolean winnerExists = false;
        Scanner kb = new Scanner(System.in);
        char choice = 'y';
        double x, y;
        int [] overallScores = new int[2];  //[0] is the # of wins for player 1 and [1] is the # of wins for player 2


        StdDraw.setScale(-0.1, 7.1);

        while (choice == 'y') {
            // Clear the canvas before every game and reset all
            // the game variables
            StdDraw.clear();
            moves = 0;
            // Reset the board by creating a new 2-D array
            board = new int[7][7];
            clickSpace(board);
            currentPlayer = 1;
            winnerExists = false;

            drawGrid();

            // Play a game of Connect Four
            while (moves < 42) {
                // Has the user click the mouse?
                if (StdDraw.isMousePressed() == true) {
                    x = StdDraw.mouseX();
                    y = StdDraw.mouseY();
                    //System.out.println(x + ", " + y);

                    // Was the mouse click inside the top part of the board?
                    if (x >= 0 && x <= 7 && y >= 6 && y < 7) {
                        board = placeChip(board, (int) x, (int) y, currentPlayer);
                        // is currentPlayer the winner?
                        if (isWinner(board, currentPlayer)) {
                            winnerExists = true;
                            break;
                        }
                        // Switch players
                        if (currentPlayer == 1) {
                            currentPlayer = 2;
                        } else {
                            currentPlayer = 1;
                        }
                        moves = moves + 1;
                        StdDraw.pause(300);
                    }
                }
            }

                if (winnerExists) {
                    System.out.println("Player " + currentPlayer + " won the game!");
                    if(currentPlayer == 1){
                        overallScores[0]++;
                    }
                    else{
                        overallScores[1]++;
                    }
                } else {
                    System.out.println("It's a tie.");
                }
                System.out.print("Do you want to play again (y/n)? ");
                choice = kb.next().charAt(0);
            }

        int winnerOverall = overallWinner(overallScores);
        if(winnerOverall>0) {
            System.out.println("The winner overall is Player " + winnerOverall);
        }
        else{
            System.out.println("The game overall is a tie!");
        }
        System.out.println("Congrats! Play again soon!");
    }
    // Closes the canvas windows and terminates the application
    //System.exit(0);


    private static boolean isWinner(int[][] board, int currentPlayer) {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == currentPlayer && board[i][j + 1] == currentPlayer && board[i][j + 2] == currentPlayer && board[i][j + 3] == currentPlayer) {
                    return true;
                }
            }
        }
        for(int i = 0; i<6; i++){
            for(int j = 0; j<4; j++) {
                if (board[j][i] == currentPlayer && board[j + 1][i] == currentPlayer && board[j + 2][i] == currentPlayer && board[j + 3][i] == currentPlayer) {
                    return true;
                }
            }
        }
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 3; y++) {
                if (board[x][y] == currentPlayer && board[x + 1][y + 1] == currentPlayer && board[x + 2][y + 2] == currentPlayer && board[x + 3][y + 3] == currentPlayer) { //I need help with the diagonal
                    return true;
                }
            }
        }
        for (int x = 3; x < 7; x++) {
            for (int y = 0; y < 3; y++) {
                if (board[x][y] == currentPlayer && board[x - 1][y + 1] == currentPlayer && board[x - 2][y + 2] == currentPlayer && board[x - 3][y + 3] == currentPlayer) {
                    return true;
                }
            }
        }

        return false;

    }

    private static void drawGrid() {
        int i;
        StdDraw.setPenColor(Color.black);
        for (i = 0; i < 8; i++) {
            StdDraw.line(i, 0, i, 8);
            StdDraw.line(0, i, 7, i);
        }

    }

    public static int[][] placeChip(int[][] boardGrid, int x, int y, int player) {//This method places the chip in the correct spot assuming gravity works
        int[][] board = new int[7][7];
        board = boardGrid;
        int bx, by;
        bx = (int) x;
        by = (int) y;
        // What position in that column is empty?
        int positionY;      //How many Y positions it moves down from the clickable part of the board
        for (positionY = 6; positionY > -1; positionY--) {
            if (board[bx][by - positionY] == 0) {
                if (player == 1) {
                    StdDraw.setPenColor(Color.BLUE);
                    StdDraw.filledCircle(bx + 0.5, (by - positionY) + 0.5, 0.45);
                    board[bx][by - positionY] = player;
                    // Mark the [bx][by] position selected by the current player
                } else {
                    StdDraw.setPenColor(Color.RED);
                    StdDraw.filledCircle(bx + 0.5, (by - positionY) + 0.5, 0.45);
                    board[bx][by - positionY] = player;
                }
                break;
            }
        }
        return board;
    }

    public static int overallWinner(int[] wins){
        if(wins[0]>wins[1]){
            return 1;
        }
        else if (wins[0]<wins[1]){
            return 2;
        }
        else{
            return 0;
        }
    }
    public static void clickSpace(int[][] entireBoard){ //This colors in the area where the player should click
        for(int i =0; i<7; i++){
            StdDraw.setPenColor(Color.GREEN);
            StdDraw.filledSquare(i+.5, 6.5, .5);
        }
    }
}
