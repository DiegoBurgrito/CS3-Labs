import java.util.Scanner;

import static java.lang.System.*;

public class ShortestPathMaze {
    private int[][] maze;
    private int shortest;

    public ShortestPathMaze() {
        maze = new int[10][10];
        shortest = 0;
    }

    public ShortestPathMaze(int[][] m) {
        maze = m;
        shortest= 0;
    }


    public void checkForExitPath(int r, int c, int path) {
        if(isValid(r, c)) {
            maze[r][c] = 2;
            if(c == maze[0].length - 1) {
                maze[r][c] = 9;
                if(shortest == 0 || path < shortest) {
                    shortest = path + 1;
                }
                return;
            }
            else {
                checkForExitPath(r + 1, c, path + 1);
                int down = shortest;
                checkForExitPath(r, c + 1, path + 1);
                int right = shortest;
                checkForExitPath(r - 1, c, path + 1);
                int up = shortest;
                checkForExitPath(r, c - 1, path + 1);
                int left = shortest;
            }
            maze[r][c] = 1;
        }
    }

    private int smallest(int down, int right, int up, int left) {
        int smallest = down;
        if(right < smallest) {
            smallest = right;
        }
        if(up < smallest) {
            smallest = up;
        }
        if(left < smallest) {
            smallest = left;
        }
        return smallest;
    }
    private boolean isValid(int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 1;
    }
    public int getShortestPath() {
        return shortest;
    }

    public String toString() {
        String output = "";
        for (int[] row : maze) {
            for (int cell : row) {
                output += cell + " ";
            }
            output += "\n";

        }
        return shortest > 0 ? output + "exit found in " + shortest + " steps" : output + "exit not found";
    }
}