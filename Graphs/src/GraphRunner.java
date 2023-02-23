
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GraphRunner {
    public static void main(String[] args) throws IOException {
        Scanner file = new Scanner(new File("graph1.dat"));
        int howManyTimes = file.nextInt();
        file.nextLine();
        for (int x = 0; x < howManyTimes; x++) {
			Graph g = new Graph(file.nextLine());
			String s = file.nextLine();
			g.check(s.charAt(0) + "", s.charAt(1) + "", "");
			System.out.println(s.charAt(0) + " connect to " + s.charAt(1) + " == " + g + "");
        }
    }
}