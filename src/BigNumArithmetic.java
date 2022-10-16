import java.io.*;
import java.util.Scanner;
public class BigNumArithmetic {
    static LStack stack = new LStack();
    static LList digitLinkList = new LList();
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
                    Scanner singleLine = new Scanner(line);   //scanner to go through the line's elements
                    while (singleLine.hasNext()) {    //loop to go through the line's characters
                        String wholeLine = singleLine.nextLine();

                        wholeLine = wholeLine.replaceAll("\\W", " ");  //detects & removes special characters
                        wholeLine = wholeLine.replaceAll("\\s+", " ");  //gets rid of extra whitespace
                        wholeLine = wholeLine.replaceFirst("0+", ""); //trims zeroes
                        for (int i = 0; i < wholeLine.length(); i++) {
                            digitLinkList.append(wholeLine.charAt(i));
                        }
                        stack.push(wholeLine); //pushes number to stack

                        //System.out.print(stack.pop() + "\n"); //test to print out format and to see if zeroes & special characters are trimmed
                        System.out.print(digitLinkList.remove());
                    }
                    //System.exit(-1); //To loop through once for testing
                }

        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}