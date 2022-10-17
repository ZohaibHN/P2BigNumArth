import java.io.*;
import java.util.Scanner;
public class BigNumArithmetic {
    static LStack stack = new LStack();
    static LList digitLinkList = new LList();
    public static void main(String[] args) {
        Scanner file;

        try {
            FileWriter fw = new FileWriter("outputTest.txt");

                file = new Scanner(new File("BignumInput-1.txt"));
                while (file.hasNext()) {
                    String line = file.nextLine();
                    line = line.trim();
                    /**while (line.equals("")) {
                        line = file.next();
                        line = line.trim();
                    }**/
                    Scanner singleLine = new Scanner(line);   //scanner to go through a single line's elements
                    String wholeLine;
                    while (singleLine.hasNext()) {    //loop to go through the line's characters
                        int k;
                        wholeLine = singleLine.nextLine();
                        //wholeLine = wholeLine.replaceAll("\\W", " ");  //detects & removes special characters
                        wholeLine = wholeLine.replaceAll("\\s+", " ");  //gets rid of extra whitespace

                        stack.push(wholeLine); //pushes number to stack
                        for (k = 0; k < wholeLine.length(); k++) {
                            digitLinkList.append(wholeLine.charAt(k));
                        }
                        char[] stackCharArray = stack.pop().toString().toCharArray();
                        String sCAString = new String(stackCharArray);
                        sCAString = sCAString.replaceAll("^0+(?!$)", "");
                        //char[] linkListCharArray = digitLinkList.remove().toString().toCharArray();
                        //System.out.print(stack.pop() + " hi\n"); //test to print out all digits
                        //System.out.print(linkListCharArray);   //test to print out digits of a line
                        fw.write(sCAString + "\n");

                    }

                    //System.exit(-1); //To loop through once for testing

                }
            fw.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
        }
}