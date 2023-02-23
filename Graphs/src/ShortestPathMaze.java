//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class -
//Lab  -

public class ShortestPathMaze {
    private int[][] maze;
    private int shortest;

    public ShortestPathMaze() {
        maze = new int[10][10];
        shortest = 1;
    }

    public ShortestPathMaze(int[][] m) {
        maze = m;
        shortest = 1;
    }

    public void checkForExitPath(int r, int c, int count) {
       if(r >= 0 && r < maze.length && c >= 0 && c < maze[0].length && maze[r][c] == 1) {
           maze[r][c] = 9;
           if(c == maze[0].length - 1) {
               if(shortest == 0 || count < shortest) {
                   shortest = count;
               }
           } else {
               int down = checkForExitPath(r + 1, c, count + 1, maze);
               int up = checkForExitPath(r - 1, c, count + 1, maze);
               int right = checkForExitPath(r, c + 1, count + 1, maze);
               int left = checkForExitPath(r, c - 1, count + 1, maze);
               int smallest = smallest(down, up, right, left);
               if(smallest == down) {
                     checkForExitPath(r + 1, c, count + 1);
                } else if(smallest == up) {
                     checkForExitPath(r - 1, c, count + 1);
                } else if(smallest == right) {
                     checkForExitPath(r, c + 1, count + 1);
                } else if(smallest == left) {
                     checkForExitPath(r, c - 1, count + 1);
               }
           }

       }

    }

    private int checkForExitPath(int r, int c, int count, int[][] m) {
        if(r >= 0 && r < m.length && c >= 0 && c < m[0].length && m[r][c] == 1) {
            m[r][c] = 2;
            if(c == m[0].length - 1) {
                return count;
            } else {
                int right = checkForExitPath(r + 1, c, count + 1, m);
                int left = checkForExitPath(r - 1, c, count + 1, m);
                int up = checkForExitPath(r, c + 1, count + 1, m);
                int down = checkForExitPath(r, c - 1, count + 1, m);
                int smallest = smallest(right, left, up, down);

                if(smallest == down) {
                    checkForExitPath(r + 1, c, count + 1, m);
                } else if(smallest == up) {
                    checkForExitPath(r - 1, c, count + 1, m);
                } else if(smallest == right) {
                    checkForExitPath(r, c + 1, count + 1, m);
                } else if(smallest == left) {
                    checkForExitPath(r, c - 1, count + 1, m);
                }
            }
        }
        return -1;
    }
    
    
    private int smallest(int a, int b, int c, int d) {
        if(a < 0) {
            a = Integer.MAX_VALUE;
        }
        int smallest = a;
        if (b >= 0 && b < smallest) {
            smallest = b;
        }
        if (c >= 0 && c < smallest) {
            smallest = c;
        }
        if (d >= 0 && d < smallest) {
            smallest = d;
        }
        return smallest;
    }


    public int getShortestPath() {
        return shortest;
    }

    public String toString() {
        String out = "";
        for (int[] row : maze) {
            for (int cell : row) {
                out += cell + " ";
            }
            out += "\n";
        }
        return shortest > 0 ? out + "shortest path of " + shortest : out + "no path";
    }
}