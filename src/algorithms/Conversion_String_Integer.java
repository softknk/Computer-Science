package algorithms;

import java.util.Scanner;

/**
 * parses a string into an integer number
 */
public class Conversion_String_Integer {

    public static void main(String[] args) throws PR1Exception {
        // read the input
        String input = new Scanner(System.in).next();
        // parse the integer
        int number = parseInteger(input);
        // print number to console
        System.out.println(number);
    }

    public static int parseInteger(String item) throws PR1Exception {
        int number = 0;
        boolean negative = item.charAt(0) == '-';
        // if negative then remove the first character '-'
        if (negative)
            item = item.substring(1);
        // check if input is valid number for integer 32-bit range
        if (!isNumberValidForInteger(item, negative))
            throw new PR1Exception();
        // compute every digit
        for (int i = 0; i < item.length(); i++) {
            if (Character.isDigit(item.charAt(i)))
                number += Character.getNumericValue(item.charAt(i)) * Math.pow(10, item.length() - 1 - i);
            else
                throw new PR1Exception();
        }
        return negative ? -number : number;
    }

    private static boolean isNumberValidForInteger(String item, boolean negative) {
        String max = "2147483647";
        if (negative)
            max = "2147483648";
        if (item.length() > max.length())
            return false;
        if (item.length() < max.length())
            return true;
        for (int i = 0; i < item.length(); i++) {
            if (Character.getNumericValue(item.charAt(i)) > Character.getNumericValue(max.charAt(i)))
                return false;
        }
        return true;
    }

    private static class PR1Exception extends Exception {

        public PR1Exception() {
            System.out.println("Input invalid.");
        }
    }
}

