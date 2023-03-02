package org.example;

import java.util.*;

class Main {
    public static String calc(String input) {
        int result, a, b;
        String[] tokens = input.split(" ");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Invalid input format");
        }
        String left = tokens[0];
        String operator = tokens[1];
        String right = tokens[2];

        if (isRoman(left) && isRoman(right)) {
            a = NumberInRoman(left);
            b = NumberInRoman(right);
            result = Mach(operator, a, b);
            return ParseAnswerToRoman (result);
        } else {
            a = parseNumber(left);
            b = parseNumber(right);
            isArabic(a);
            isArabic(b);
            result = Mach(operator, a, b);
            return String.valueOf(result);
        }
    }

    private static int parseNumber(String str) {  //преобразуем из строки в инт
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format");
        }
    }

    private static boolean isRoman(String s) {  //проверяем что введены римские цифры
        try {
            return s.equals("I") || s.equals("II") || s.equals("III") || s.equals("IV") ||
                    s.equals("V") || s.equals("VI") || s.equals("VII") || s.equals("VIII") || s.equals("IX") ||
                    s.equals("X");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format");
        }
    }

    private static boolean isArabic(int n) {  //проверяем что цифры в диапазоне от 1 до 10
        if (n < 1 || n > 10) {
            throw new IllegalArgumentException("Invalid number range");
        }
        return true;
    }

    private static int NumberInRoman(String str) { //проверяем какие числа введены на римском
        int number = 0;
        String[] symbols = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for (int i = 1; i <= 10; i++) {
            if (str.equals(symbols[i])) number = i;
        }
        return number;
    }

    private static String ParseAnswerToRoman(int n) { //получаем ответ из арабских в римские
        String[] symbols = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        StringBuilder sb = new StringBuilder();
        if(n < 1){
            throw new IllegalArgumentException("Invalid number range");
        }
        if(n > 10){
            n = n-10;
            sb.append(symbols[10]);
            sb.append(symbols[n]);
            return sb.toString();
        } else {
            sb.append(symbols[n]);
        }

        return sb.toString();
    }

    private static int Mach (String operator, int a, int b){

        int result;

            switch (operator) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    result = a / b;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator");
            }
            return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter expression: ");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            }

            try {
                String result = calc(input);
                System.out.println("Result: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}