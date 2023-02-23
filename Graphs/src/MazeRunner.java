import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MazeRunner {
    public static void main(String args[]) throws IOException {
        Scanner file = new Scanner(new File("maze.dat"));
        while (file.hasNextLine()) {

            int size = file.nextInt();
            int[][] maze = new int[size][size];
            for (int r = 0; r < size; r++) {
                for (int c = 0; c < size; c++) {
                    maze[r][c] = file.nextInt();
                }
            }
            Maze m = new Maze(maze);
            System.out.println(m + "\n");
            file.nextLine();
        }
    }
}