import java.io.*;
import java.util.Scanner;
public class BigNumArithmetic {
    static LStack stack = new LStack();

    public static void main(String[] args) {
        Scanner file;
        //PrintWriter pw = new PrintWriter("output.txt");
        try {
            file = new Scanner(new File("BignumInput-1.txt"));
            while (file.hasNext()) {
                String line = file.nextLine();
                line = line.trim();

                while (line.equals("")) {
                    line = file.nextLine();
                    line = line.trim();
                }
                line = line.replaceAll("\\s+", " "); //gets rid of excess whitespace
                Scanner word = new Scanner(line);

                while (word.hasNext()) {
                    String nextWord = word.next();
                    if (!nextWord.equals("+") && !(nextWord.equals("*")) && !(nextWord.equals("^"))) {
                        nextWord = nextWord.replaceFirst("^0+(?!$)", ""); //trims zeroes
                        stack.push(nextWord); //pushes number to stack
                    }
                    System.out.print(nextWord + " "); //test to print out format and to see if zeroes are trimmed
                }
                System.exit(-1); //To loop through once for testing
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}