import java.io.*;
import java.util.Scanner;
public class BigNumArithmetic {
    static LStack stack = new LStack();
    static LList digitLinkList = new LList();
    public static void addNum(String num, String num2) {
        System.out.println("Method called");
        LList list = new LList();
        LList list2 = new LList();
        LList result = new LList();
        String stringTest = "";
        String tempString = num;

        if (num.length() < num2.length()) {
            num = num2;
            num2 = tempString;
        }
        for (int i = 0; i < num.length(); i++) {
            //System.out.println(num.charAt(i) + " TETTT");
            int newInt = Integer.parseInt(String.valueOf(num.charAt(i)));
            //System.out.println(newInt + "  NUM1 TEST"); //print testing
            list.insert(newInt);
        }
        System.out.println();
        for (int i = 0; i < num2.length(); i++) {
            //System.out.println(num.charAt(i) + " TETTT");
            int newInt = Integer.parseInt(String.valueOf(num2.charAt(i)));
            //System.out.println(newInt + " TEST"); //print testing
            list2.insert(newInt);
        }
        //System.out.println(); //format testing

        list.moveToStart();
        list2.moveToStart();
        int carry = 0;
        while (list.returnCurr() != null || list2.returnCurr() != null) {
            int sum = carry;
            if (list.returnCurr() != null) {
                sum += (Integer) (list.returnCurr());
                list.next();
            }

            if (list2.returnCurr() != null) {
                sum += (Integer) (list2.returnCurr());
                list2.next();
            }
            System.out.println(sum + " SUM"); //print test
            //System.out.println(carry + "CARRY"); //print test
            result.insert(sum % 10);
            carry = sum / 10;
            //System.out.println(carry + "REMAIN"); //print test
            //System.out.println(sum + "TTTTTST"); //print test
        }

        System.out.println();
        //System.out.println(carry + "CAD"); //print test
        for (int i = 0; i <result.length(); i++) {
            System.out.println(result.getValue());
            result.next();
        }
        if (carry > 0) {
            result.moveToStart();
            result.insert(carry);
        }
        System.out.println();
        result.moveToStart();
        for (int i = 0; i < result.length(); i++) {
            //System.out.println(result.getValue());
            stringTest = stringTest + String.valueOf(result.getValue());
            result.next();
        }
        System.out.println(stringTest+ " Has been operated and pushed");
        stack.push(stringTest);
    }
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
                    fw.write(sCAString + " = " + "\n");

                }

                //System.exit(-1); //To loop through once for testing

            }
            fw.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}