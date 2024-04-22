/**********************************************************************
 * @file RandomWalk.java
 * @brief I think it is possible that there are some useless variables throughout my code left behind as I corrected the code's logic.
 * Regular Random Walks: (N=20): 127   (N=60): 1047   (N=100): 2957
 * Self Avoiding Random Walks: (N=20): Escape:25 Stuck:19   (N=60): Escape:77 Stuck:68  (N=100): Escape:300 Stuck:64
 * @author Alantis Green
 * @date: 10/31/2022
 * @acknowledgement: Dr. Paul Pauca's GrubHub comments
 ***********************************************************************/


import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class RandomWalk {
    //Part 1
    public static void drawGrid(int N) {
        StdDraw.setPenColor(Color.red);
        //Method with drawing squares, not horizontal and vertical lines
        int i, j;
        for (i = 0; i < N; i++) {
            for (j = 0; j < N; j++) {
                StdDraw.square(i + 0.5, j + .5, .5);    //i + .5, i + 0.5 is the center of the square and we build the lengths around
            }
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner scnr = new Scanner(System.in);
        int N;
        int k;
        System.out.print("How many road blocks do you want? ");
        k = scnr.nextInt();

        N = 20;
        StdDraw.setXscale(-.5, N + .5);
        StdDraw.setYscale(-.5, N + 2);
        drawGrid(N);
        StdDraw.setPenRadius(0.007);
        int[][] gridModel20 = new int[N + 1][N + 1];  //Keep track of every intersection of the grid. Has the robot been there?
        //NxN is number of boxes. N+1xN+1 is number of intersection
        setRoadBlocks(gridModel20, k);
        int steps20;
        int steps20sum = 0;
        for (int i = 0; i <= 50; i++) {
            steps20sum = steps20sum + randomWalk(gridModel20, N / 2, N / 2);
        }
        steps20 = steps20sum / 50;
        System.out.println("Average steps for grid size 20: " + steps20);

        int[] escapeOrStuck20 = new int[2];
        int stepsToEscapeSum20 = 0;
        int stepsToStuckSum20 = 0;
        int numStuck20 = 0;
        int numEscape20 = 0;
        for(int i = 0; i<= 50; i++){
            escapeOrStuck20 = selfAvoidingRandomWalk(gridModel20, N/2, N/2);
            if(escapeOrStuck20[0] == 0){
                numEscape20++;
                stepsToEscapeSum20 += escapeOrStuck20[1];
            }
            else {
                numStuck20++;
                stepsToStuckSum20 += escapeOrStuck20[1];
            }

        }
        int stepsToEscape20 = stepsToEscapeSum20/numEscape20;
        int stepsToStuck20 = stepsToStuckSum20/numStuck20;
        System.out.println("Average steps to escape for grid size 20: " + stepsToEscape20);
        System.out.println("Average steps to get stuck for grid size 20: " + stepsToStuck20);
        StdDraw.clear();

        N = 60;
        StdDraw.setXscale(-.5, N + .5);
        StdDraw.setYscale(-.5, N + 2);
        drawGrid(N);
        StdDraw.setPenRadius(0.007);
        int[][] gridModel60 = new int[N + 1][N + 1];  //Keep track of every intersection of the grid. Has the robot been there?
        //NxN is number of boxes. N+1xN+1 is number of intersection
        setRoadBlocks(gridModel60, k);
        int steps60;
        int steps60sum = 0;
        for (int i = 0; i <= 50; i++) {
            steps60sum = steps60sum + randomWalk(gridModel60, N / 2, N / 2);
        }
        steps60 = steps60sum / 50;
        System.out.println("Average steps for grid size 60: " + steps60);

        int[] escapeOrStuck60 = new int[2];
        int stepsToEscapeSum60 = 0;
        int stepsToStuckSum60 = 0;
        int numStuck60 = 0;
        int numEscape60 = 0;
        for(int i = 0; i<= 50; i++){
            escapeOrStuck60 = selfAvoidingRandomWalk(gridModel60, N/2, N/2);
            if(escapeOrStuck60[0] == 0){
                numEscape60++;
                stepsToEscapeSum60 += escapeOrStuck60[1];
            }
            else{
                numStuck60++;
                stepsToStuckSum60 += escapeOrStuck60[1];
            }
        }
        int stepsToEscape60 = stepsToEscapeSum60/numEscape60;
        int stepsToStuck60 = stepsToStuckSum60/numStuck60;
        System.out.println("Average steps to escape for grid size 60: " + stepsToEscape60);
        System.out.println("Average steps to get stuck for grid size 60: " + stepsToStuck60);
        StdDraw.clear();

        N = 100;
        StdDraw.setXscale(-.5, N + .5);
        StdDraw.setYscale(-.5, N + 2);
        drawGrid(N);
        StdDraw.setPenRadius(0.007);
        int[][] gridModel100 = new int[N + 1][N + 1];  //Keep track of every intersection of the grid. Has the robot been there?
        //NxN is number of boxes. N+1xN+1 is number of intersection
        setRoadBlocks(gridModel100, k);
        int steps100;
        int steps100sum = 0;
        for (int i = 0; i <= 50; i++) {
            steps100sum = steps100sum + randomWalk(gridModel100, N / 2, N / 2);
        }
        steps100 = steps100sum / 50;
        System.out.println("Average steps for grid size 100: " + steps100);

        int[] escapeOrStuck100 = new int[2];
        int stepsToEscapeSum100 = 0;
        int stepsToStuckSum100 = 0;
        int numStuck100 = 0;
        int numEscape100 = 0;
        for(int i = 0; i<= 50; i++){
            escapeOrStuck100 = selfAvoidingRandomWalk(gridModel100, N/2, N/2);
            if(escapeOrStuck100[0] == 0){
                numEscape100++;
                stepsToEscapeSum100 += escapeOrStuck100[1];
            }
            else{
                numStuck100++;
                stepsToStuckSum100 += escapeOrStuck100[1];
            }
        }
        int stepsToEscape100 = stepsToEscapeSum100/numEscape100;
        int stepsToStuck100 = stepsToStuckSum100/numStuck100;
        System.out.println("Average steps to escape for grid size 100: " + stepsToEscape100);
        System.out.println("Average steps to get stuck for grid size 100: " + stepsToStuck100);
        StdDraw.clear();
    }

    public static void drawBlock(int x, int y) {
        StdDraw.setPenColor(Color.pink);
        StdDraw.filledSquare(x + .5, y + .5, .5);       //Based on the center of the square and we start at lower left corner
    }

    public static void setRoadBlocks(int[][] grid, int k) {
        Random rand = new Random();
        int gridSize = grid.length;
        //Set a road block at intersections (3,6), (4,6), (4,7), (3,7)
        int open = 0;
        int blocked = -1;
        //5 Manual Road Blocks
        int x = 3;
        int y = 6;
        drawBlock(x, y);
        //Changing the model to set blocked intersections to -1
        grid[x][y] = blocked;
        grid[x + 1][y] = blocked;
        grid[x + 1][y + 1] = blocked;
        grid[x][y + 1] = blocked;
        x = 7;
        y = 12;
        drawBlock(x, y);
        grid[x][y] = blocked;
        grid[x + 1][y] = blocked;
        grid[x + 1][y + 1] = blocked;
        grid[x][y + 1] = blocked;
        x = 15;
        y = 10;
        drawBlock(x, y);
        grid[x][y] = blocked;
        grid[x + 1][y] = blocked;
        grid[x + 1][y + 1] = blocked;
        grid[x][y + 1] = blocked;
        x = 2;
        y = 2;
        drawBlock(x, y);
        grid[x][y] = blocked;
        grid[x + 1][y] = blocked;
        grid[x + 1][y + 1] = blocked;
        grid[x][y + 1] = blocked;
        x = 19;
        y = 17;
        drawBlock(x, y);
        grid[x][y] = blocked;
        grid[x + 1][y] = blocked;
        grid[x + 1][y + 1] = blocked;
        grid[x][y + 1] = blocked;
        //Random Road Blocks
        for (int i = 0; i <= k - 5; i++) {
            x = rand.nextInt(gridSize - 1);     //Set to go up to (19,19)
            y = rand.nextInt(gridSize - 1);
            boolean avoidCenter = true;
            if ((x == gridSize / 2 - 1 && y == gridSize / 2) || (x == gridSize / 2 && y == gridSize / 2) || (x == gridSize / 2 - 1 && y == gridSize / 2 - 1) || (x == gridSize / 2 && y == gridSize / 2 - 1)) {
                avoidCenter = false;
            }
            if (avoidCenter) {       //Makes sure a road block is not placed in a block that would block the center point
                drawBlock(x, y);
                grid[x][y] = blocked;
                grid[x + 1][y] = blocked;
                grid[x + 1][y + 1] = blocked;
                grid[x][y + 1] = blocked;
            }
        }

    }

    public static int randomWalk(int[][] grid, int x, int y) {
        int steps = 0;
        int currX = x;
        int currY = y;
        int prevX;
        int prevY;
        int direction;
        while (currX > 0 && currX < x * 2 && currY > 0 && currY < y * 2) {        //As long as robot is in the grid
            Random rand = new Random();
            prevX = currX;
            prevY = currY;
            direction = rand.nextInt(4);
            //Before we move, we need to check if it is blocked

            if (direction == 0) {
                if (grid[currX - 1][currY] == 0) {        //Is the position to my left open?
                    currX--;    //Moves left
                    steps++;
                }
            } else if (direction == 1) {
                if (grid[currX + 1][currY] == 0) {
                    currX++;     //Moves right
                    steps++;
                }
            } else if (direction == 2) {
                if (grid[currX][currY - 1] == 0) {
                    currY--;    //Moves down
                    steps++;
                }
            } else {           //direction == 3
                if (grid[currX][currY + 1] == 0) {
                    currY++;    //Moves up
                    steps++;
                }
            }
            StdDraw.setPenColor(Color.blue);
            StdDraw.line(prevX, prevY, currX, currY);
        }
        return steps;
    }
    public static int[] selfAvoidingRandomWalk(int [][]gridOG, int x, int y){
        Random rand = new Random();
        int [] counters = new int[2];           //counters[0] == 1 means that the robot got stuck and 0 means it escaped
                                                //counters[1] is the counter for the number of steps
        int [][] gridNew = new int[gridOG.length][gridOG.length];
        for (int i = 0; i <gridOG.length; i++) {
            for (int j = 0; j < gridOG.length; j++) {
                gridNew[i][j] = gridOG[i][j];
            }
        }
        int visited = 1;
        int notVisited = 0;
        int stuck = 2;
        int currX = x;
        int currY = y;
        int prevX;
        int prevY;
        int direction;
        while (currX > 0 && currX < x * 2 && currY > 0 && currY < y * 2) {        //As long as robot is in the grid
            prevX = currX;
            prevY = currY;
            gridNew[currX][currY] = stuck;
            direction = rand.nextInt(4);
            //Before we move, we need to check if it is blocked
            if((gridNew[currX - 1][currY] !=notVisited) && (gridNew[currX + 1][currY] != notVisited) && (gridNew[currX][currY - 1] != notVisited) && (gridNew[currX][currY + 1] != notVisited)){
                gridNew[currX][currY] = stuck;
                counters[0] = 1;
                break;
            }
            else if (direction == 0) {
                if (gridNew[currX - 1][currY] == notVisited) {        //Is the position to my left open and not visited?
                    currX--;    //Moves left
                    gridNew[currX][currY] = visited;                        //Location becomes visited
                    counters[1]++;
                }

            } else if (direction == 1) {
                if (gridNew[currX + 1][currY] == notVisited) {
                    currX++;     //Moves right
                    gridNew[currX][currY] = visited;
                    counters[1]++;
                }

            } else if (direction == 2) {
                if (gridNew[currX][currY - 1] == notVisited) {
                    currY--;    //Moves down
                    gridNew[currX][currY] = visited;
                    counters[1]++;
                }

            } else{
                if (gridNew[currX][currY + 1] == notVisited) {
                    currY++;    //Moves up
                    gridNew[currX][currY] = visited;
                    counters[1]++;
                }

            }
            StdDraw.setPenColor(Color.CYAN);
            StdDraw.line(prevX, prevY, currX, currY);
        }

        return counters;

    }
}
