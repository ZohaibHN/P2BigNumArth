import java.io.*;
import java.util.Scanner;
public class BigNumArithmetic {
    private static LStack stack = new LStack();
    private static LList digitLinkList = new LList();
    private static int operatorNum = 0;
    private static int operandNum = 0;
    private static StringBuilder expressionResult = new StringBuilder();
    public static String removeZero(String s) { //function to remove leading zeros
        int i = 0;  //iteration variable
        while (i < s.length() && s.charAt(i) == '0')    //while not at end and current == 0
            i++;    //skip 0
        StringBuffer sb = new StringBuffer(s);  //StringBuffer used to replace 0's
        sb.replace(0,i,""); //replace '0' with "" at index i
        return sb.toString();   //return string version of StringBuffer
    }

    public static StringBuilder addNum(String num, String num2) {
        System.out.println("Method called");
        LList list = new LList();
        LList list2 = new LList();
        LList result = new LList();
        //StringBuilder stringTest = new StringBuilder();
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
            expressionResult.append(result.getValue());
            result.next();
        }
        System.out.println(expressionResult+ " Has been operated and pushed");
        stack.push(expressionResult.toString());
        return expressionResult;
    }

    public static void main(String[] args) {
        Scanner file;   //scanner for input file
        try {
            FileWriter fw = new FileWriter("outputTest.txt");   //fileWriter to write to output file
            file = new Scanner(new File("BignumInput-1.txt"));  //Scanner to read in input file
            while (file.hasNext()) {    //loops until EOF
                String line = file.nextLine();
                line = line.trim(); //gets rid of excess whitespace at the beginning
                String lineWithOperators = line;
                line = line.replaceAll("\\W", " ");  //detects & removes special characters
                line = line.replaceAll("\\s+", " ");  //gets rid of extra whitespace
                line = removeZero(line);

                String individuals = "";    //string for individual characters not including operators
                String individualsWithOperators = "";   //string for individual characters including operators

                int k;  //iteration variable
                for (k = 0; k < lineWithOperators.length(); k++) {  //looping through line
                    Scanner individualsOfWholeLine = new Scanner(line);    //scanner for line without operators
                    Scanner individualsOfWholeLineWithOperators = new Scanner(lineWithOperators);   //scanner for line including operators
                    individualsWithOperators = individualsOfWholeLineWithOperators.nextLine();  //assignment to line
                    individuals = individualsOfWholeLine.nextLine();    //assignment to line
                }
                stack.push(individualsWithOperators); //line
                for (k = 0; k < line.length(); k++) {
                    digitLinkList.append(individuals.charAt(k));    //adds digit at index k to LinkedList
                }
                for (k = 0; k < line.length(); k++) {
                    if (individualsWithOperators.equals("+")) {
                        operatorNum++;
                        String popA = (String) stack.pop();
                        String popB = (String) stack.pop();
                        expressionResult = addNum(popA, popB);
                    } else {
                        operandNum++;
                        stack.push(individualsWithOperators);
                    }
                    if ((operandNum - operatorNum) > 1) {
                        expressionResult = new StringBuilder("hi");
                    }
                }

                char[] stackCharArray = stack.pop().toString().toCharArray();
                String sCAString = new String(stackCharArray);
                sCAString = removeZero(sCAString);
                sCAString = sCAString.replaceAll("\\s+", " ");  //gets rid of extra whitespace
                char[] linkListCharArray = digitLinkList.remove().toString().toCharArray();

                //System.out.print(linkListCharArray);   //test to print out digits of a line

                if (!line.isEmpty()) {
                    System.out.print(sCAString + " =  "+ expressionResult +" \n"); //test to print out all digits
                    fw.write(sCAString + " = " + expressionResult + "\n"); //test to output to file
                }
            }
            //System.exit(-1); //To loop through once for testing
            file.close();
            fw.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}