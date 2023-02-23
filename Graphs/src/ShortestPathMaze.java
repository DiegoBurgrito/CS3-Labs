import java.util.Arrays;

public class ShortestPathMaze {
    private int[][] maze;
    private int shortest;
    private int[][] copy;

    public ShortestPathMaze() {
        maze = new int[10][10];
        copy = new int[10][10];
        shortest = 0;
    }

    public ShortestPathMaze(int[][] m) {
        maze = m;
        copy = new int[m.length][m[0].length];
        updateCopy();
        shortest= 0;
    }

    private void updateCopy() {
        for (int x = 0; x < maze.length; x++) {
            System.arraycopy(maze[x], 0, copy[x], 0, copy[0].length);
        }
    }

    public void checkForExitPath(int r, int c, int path) {
        if(isValid(r, c)) {
            maze[r][c] = 9;
            if(c == maze[0].length - 1) {
                maze[r][c] = 9;
                if(shortest == 0 || path < shortest) {
                    shortest = path + 1;
                    updateCopy();
                }
            }
            else {
                checkForExitPath(r + 1, c, path + 1);
                checkForExitPath(r, c + 1, path + 1);
                checkForExitPath(r - 1, c, path + 1);
                checkForExitPath(r, c - 1, path + 1);
           
            }
            maze[r][c] = 1;
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 1;
    }
    public int getShortestPath() {
        return shortest;
    }

    public String toString() {
        String output = "";
        for (int[] row : copy) {
            for (int cell : row) {
                output += cell + " ";
            }
            output += "\n";

        }
        return shortest > 0 ? output + "shortest path in " + shortest : output + "exit not found";
    }

}