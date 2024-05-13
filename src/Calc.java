import java.util.Scanner;

public class Calc {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите два числа (арабские либо римские): ");
        String input = scan.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        int number1;
        int number2;
        String operation;
        String result;
        boolean isRoman;
        String[] symbols = input.split("[+\\-*/]");
        if (symbols.length != 2) throw new Exception("Должно быть введено два числа!");
        operation = detectOperation(input);
        if (operation == null) throw new Exception("Такой операции не существует!");

        //Два римских числа
        if (Roman.isRoman(symbols[0]) && Roman.isRoman(symbols[1])) {
            number1 = Roman.convertToArabian(symbols[0]);
            number2 = Roman.convertToArabian(symbols[1]);
            isRoman = true;
        }

        //Два числа арабские
        else if (!Roman.isRoman(symbols[0]) && !Roman.isRoman(symbols[1])) {
            number1 = Integer.parseInt(symbols[0]);
            number2 = Integer.parseInt(symbols[1]);
            isRoman = false;
        }

        //Одно римское, другое арабское
        else {
            throw new Exception("Должны быть одинаковые числа(ИЛИ римские, ИЛИ арабские!");
        }

        if (number1 > 10 || number2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10!");
        }
        int arabian = calc(number1, number2, operation);
        if (isRoman) {
            //Если римское число меньше или равно нулю => ошибка
            if (arabian <= 0) {
                throw new Exception("Римское число не может быть меньше 0!");
            }
            //Конвертация результата операции из арабского в римское
            result = Roman.convertToRoman(arabian);
        } else {
            //конвертация арабского числа в тип String
            result = String.valueOf(arabian);
        }
        return result;
    }

    static String detectOperation(String expression) {

        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;

    }

    static int calc(int a, int b, String operation) {
        return switch (operation) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> a / b;
        };
    }


    class Roman {
        static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
                "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
                "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
                "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
                "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
                "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
                "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
                "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
                "XCVIII", "XCIX", "C"};

        public static boolean isRoman(String value) {
            for (String s : romanArray) {
                if (value.equals(s)) {
                    return true;
                }
            }
            return false;
        }

        public static int convertToArabian(String roman) {
            for (int i = 0; i < romanArray.length; i++) {
                if (roman.equals(romanArray[i])) {
                    return i;
                }
            }
            return -1;
        }

        public static String convertToRoman(int arabian) {
            return romanArray[arabian];
        }
    }
}
