public class Maze {
    private int[][] maze;

    public Maze() {
		maze = new int[10][10];
    }

    public Maze(int[][] m) {
		maze = m;
    }

    public boolean checkForExitPath(int r, int c) {
		if(r >= 0 && r < maze.length && c >= 0 && c < maze[0].length && maze[r][c] == 1) {
			maze[r][c] = 2; // mark as visited
			if(c == maze[0].length - 1) {
				return true;
			}
			return checkForExitPath(r + 1, c) || checkForExitPath(r, c + 1) || checkForExitPath(r - 1, c) || checkForExitPath(r, c - 1);
		}
        return false;
    }

    public String toString() {
        String out = "";
		for(int[] row : maze) {
			for (int cell : row) {
				out += cell + " ";
			}
			out += "\n";
		}
        return checkForExitPath(0, 0) ? out + "exit found" : out + "exit not found";
    }
}