import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Tester {
    public static void main(String[] args) throws FileNotFoundException {
        int index = 1;

        String[] data = new String[10];
        Scanner sc = new Scanner(new File("HeapData.in"));

        while(sc.hasNextLine()) {
            if(data.length == index) {
                data = Arrays.copyOf(data, data.length * 2);
            }
            data[index] = sc.nextLine();
            index++;
        }

        int line = 1;
        for (int i = 1; i < index; i++) {
            if(i > Math.pow(2, line) - 1) {
                System.out.println();
                line++;
            }
            System.out.print(data[i] + " ");
        }

        System.out.println();
        System.out.println();
        HeapPrinter.printHeap(data, index);
    }

    static class HeapPrinter {
        static void printHeap(String[] data, int size) {
            int levels = (int)(Math.log(size) / Math.log(2)) + 1; // levels in the tree
            int currentLevel = 1; // current level
            int leadSpaces = (int) (Math.pow(2, levels - 1)) - 1; // leadSpaces before each number
            int innerSpaces = 0; // spaces between numbers

            System.out.print(" ".repeat(leadSpaces) + data[1]); //print first number

            for (int i = 2; i < size; i++) {
                if(Math.log(i) / Math.log(2) >= currentLevel) {

                    innerSpaces = (int) Math.pow(2, levels - currentLevel) - 1;
                    currentLevel++;
                    leadSpaces = (int) Math.pow(2, levels - currentLevel) - 1;

                    System.out.print("\n" + " ".repeat(leadSpaces));
                }
                System.out.print(data[i] + " ".repeat(innerSpaces));
            }
        }
    }
}
