//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -  
//Class -
//Lab  -

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class BiDirectionalGraphRunner {
    public static void main(String[] args) throws IOException {
		Scanner file = new Scanner(new File("bidgraph.dat"));
		int howManyTimes = file.nextInt();
		file.nextLine();
		for (int x = 0; x < howManyTimes; x++) {
			BiDirectionalGraph g = new BiDirectionalGraph(file.nextLine());
			String[] s = file.nextLine().split(" ");
			g.check(s[0], s[1], new TreeSet<>());
			System.out.println(s[0] + " CONNECTS TO " + s[1] + " == " + g);
		}
    }
}