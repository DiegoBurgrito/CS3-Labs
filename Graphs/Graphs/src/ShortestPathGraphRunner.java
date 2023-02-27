import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ShortestPathGraphRunner {
    public static void main(String[] args) throws IOException {
        Scanner file = new Scanner(new File("graph2.dat"));
        int howManyTimes = file.nextInt();
        file.nextLine();
        for (int x = 0; x < howManyTimes; x++) {
            ShortestPathGraph g = new ShortestPathGraph(file.nextLine());
            String s = file.nextLine();
            g.check(s.charAt(0) + "", s.charAt(1) + "", "", 0);
            System.out.println(s.charAt(0) + " connect to " + s.charAt(1) + " == " + g + "");
        }
    }
}