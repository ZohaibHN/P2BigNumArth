import java.io.*;
import java.util.Scanner;
public class BigNumArithmetic {

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
                    System.out.println(line);
                }

        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}