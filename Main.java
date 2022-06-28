package com.company;

import java.util.Scanner;

public class Main {

    private static final String[] ROMAN = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

    public static void main(String[] args) {
        System.out.println("Enter line for calculating: ");
        Scanner scanner = new Scanner(System.in);
        String[] blocks = scanner.nextLine().split(" ");
        char operation = blocks[1].charAt(0);
        int number1, number2;
        boolean flag = false, flag2 = false;

        for (String s : ROMAN) {
            if (s.equals(blocks[0])) {
                flag = true;
                break;
            }
        }
        for (String s : ROMAN) {
            if (s.equals(blocks[2])) {
                flag2 = true;
                break;
            }
        }
        if (flag && flag2) {
            number1 = romanToInteger(blocks[0]);
            number2 = romanToInteger(blocks[2]);
            System.out.println("Result: " + integerToRoman(calculate(number1, number2, operation)));

        } else {
            number1 = Integer.parseInt(blocks[0]);
            number2 = Integer.parseInt(blocks[2]);
            System.out.println("Result: " + calculate(number1, number2, operation));
        }
        if ((number1 > 10 || number1 < 1) || (number2 > 10 || number2 < 1)) {
            throw new IllegalArgumentException("Entered values don't fit, you should use number less than 10 and more than 1");
        }
    }

    static int calculate(int number1, int number2, char operation) {
        if (operation == '+')
            return number1 + number2;
        if (operation == '-')
            return number1 - number2;
        if (operation == '*')
            return number1 * number2;
        if (operation == '/')
            return number1 / number2;
        throw new IllegalArgumentException("Entered operations can't be applied");
    }

    static int romanToInteger(String roman) {
        int result = 0;
        for (int i = 0; i < roman.length(); i++) {
            int str1 = value(roman.charAt(i));
            if (i + 1 < roman.length()) {
                int str2 = value(roman.charAt(i + 1));
                if (str1 >= str2) {
                    result += str1;
                } else {
                    result += str2 - str1;
                    i++;
                }
            } else {
                result += str1;
            }
        }
        return result;
    }

    static String integerToRoman(int num) {
        int[] values = {10, 9, 5, 4, 1};
        String[] romanLiterals = {"X", "IX", "V", "IV", "I"};
        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString();
    }

    static int value(char roman) {
        if (roman == 'I')
            return 1;
        if (roman == 'V')
            return 5;
        if (roman == 'X')
            return 10;
        throw new IllegalArgumentException("Unknown value for Roman integer, you should use 'I', 'V', 'X'");
    }
}