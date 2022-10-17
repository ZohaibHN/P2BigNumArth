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
                        line = file.next();
                        line = line.trim();
                    }
                    Scanner singleLine = new Scanner(line);   //scanner to go through a single line's elements
                    String wholeLine = "";
                    while (singleLine.hasNext()) {    //loop to go through the line's characters
                        int k;
                        wholeLine = singleLine.nextLine();
                        wholeLine = wholeLine.replaceAll("\\W", " ");  //detects & removes special characters
                        wholeLine = wholeLine.replaceAll("\\s+", " ");  //gets rid of extra whitespace
                        wholeLine = wholeLine.replaceFirst("0+", ""); //trims zeroes
                        stack.push(wholeLine); //pushes number to stack
                        for (k = 0; k < wholeLine.length(); k++) {
                            digitLinkList.append(wholeLine.charAt(k));
                        }
                        //System.out.print(stack.pop() + " hi\n"); //test to print out all digits
                        System.out.print(digitLinkList.remove());   //test to print out digits of a line
                    }

                    //System.exit(-1); //To loop through once for testing

                }

        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}