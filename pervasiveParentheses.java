import java.util.Scanner;

public class pervasiveParentheses {
    // main loop of evaluate
    private static String loop(String s) {
        while (true) {
            int index = s.indexOf(")");
            // safety measures
            if (index == -1) {
                if (s.indexOf("(") != -1) {
                    System.out.println("No matching closed parentheses for your open parentheses");
                    return "-1";
                } else
                    return s;
            }
            // variable to tick down from index to search String
            int i = index;
            // variable to search String one character at a time
            String search = "";
            while (!search.equals("(")) {
                search = s.substring(i - 1, i);
                i -= 1;
                if (i == 0 && !search.equals("(")) {
                    System.out.println("No matching open parentheses for your closed parentheses");
                    return "-1";
                }
            }
            // doubles what's inside parentheses
            int num = Integer.parseInt(s.substring(i + 1, index)) * 2;
            // * this next section of code will search for the Strings
            // before and after the parentheses in order to add them together
            // and replace them back in the String */
            String beforeP = ""; // placeholder variable because it's flipped
            String before = ""; // String before parentheses
            search = "";
            while (!search.equals("(")) {
                beforeP += search;
                if (i == 0) {
                    i--;
                    break;
                }
                search = s.substring(i - 1, i);
                i -= 1;
            }

            int len = beforeP.length();
            // reverses the beforeP String
            while (len != 0) {
                before += beforeP.substring(len - 1, len);
                len -= 1;
            }

            String after = "";
            search = "";
            while (!search.equals("(") && !search.equals(")")) {
                after += search;
                index += 1;
                if (index == s.length()) {
                    break;
                }
                search = s.substring(index, index + 1);
            }

            if (before.equals(""))
                before = "0";
            if (after.equals(""))
                after = "0";

            // * make String s now a version where the innermost parens
            // are evaluated and added to the ones around it to run the loop again */
            num += Integer.parseInt(before) + Integer.parseInt(after);
            s = s.substring(0, i + 1) + String.valueOf(num) + s.substring(index, s.length());
        }
    }

    // checks if input string is a digit
    private static boolean isDigit(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * addDigits combines consecutive digits before the main loop of evaluate runs
     * so that each number is actually what it looks like, rather than just x + y +
     * z.
     */
    private static String addDigits(String s) {
        int len = 0;
        String first = "";
        String next = "";
        int added = 0;
        int temp = 0;

        // runs through every character except for the last
        while (len < s.length() - 1) {
            first = s.substring(len, len + 1);
            // checks if it is a digit
            if (isDigit(first)) {
                temp = len;
                added = Integer.parseInt(first);
                // then, if the next number is a digit as well, add it to the previous one
                while (temp < s.length() - 1 && isDigit(s.substring(temp + 1, temp + 2))) {
                    next = s.substring(temp + 1, temp + 2);
                    added = added + Integer.parseInt(next);
                    temp += 1;
                }
                String after = "";
                if (temp + 1 == s.length())
                    after = "";
                else {
                    after = s.substring(temp + 1);
                }
                s = (s.substring(0, len) + added + after);
                len = temp;
            }
            len += 1;
        }
        return s;
    }

    /** the most scuffed validate you will ever see */
    public static boolean validate(String s) {
        return !(evaluate(s) == -1);
    }

    public static int evaluate(String s) {
        s += "0"; // just a fix for empty strings ;-;
        int iter = 0;
        String index = "";
        while (iter != s.length()) {
            // * really quick validate that just checks if all the characters are
            // useable in the language (parens and digits) */
            index = s.substring(iter, iter + 1);
            if (!isDigit(index) && !index.equals("(") && !index.equals(")")) {
                return -1;
            }
            iter += 1;
        }
        // runs the preliminary loop to add digits together
        s = addDigits(s);
        // runs the main loop to evaluate parens
        int value = Integer.parseInt(loop(s));
        return value;
    }

    public static String generate(int str) {
        String s = "" + str;
        String newString = "";
        String front = "";
        int goal = Integer.parseInt(s);
        int currentNum = 0;

        int i = 0;
        int num = goal;
        // four digits in a row is the most efficient, so it adds parens up until "9999"
        while (num >= 37) {
            num /= 2;
            i += 1;
        }
        // count how big your current number is
        currentNum += num * ((int) Math.pow(2, i));
        // adds 9 to the string because 9999 :)
        while (num >= 9) {
            newString += 9;
            num -= 9;
        }
        // adds that final little number in case of 9995 or something
        if (num != 0)
            newString += num;
        // adds parens for each time you divided by 2
        while (i != 0) {
            newString = "(" + newString + ")";
            i -= 1;
        }
        // repeats but goes until it finds one digit and slots it within the existing parens
        while (currentNum != goal) {
            num = goal - currentNum;
            i = 0;
            while (num >= 10) {
                num /= 2;
                i += 1;
            }
            currentNum += num * ((int) Math.pow(2, i));
            if (i == 0)
                front = "";
            else
                front = newString.substring(0, i);
            newString = front + num + newString.substring(i);
        }

        return newString;
    }

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) { // main loop
        while (true) {
            System.out.println(
                    "Command guidebook:" +
                            "\n * e to evaluate an expression" +
                            "\n * g to generate an expression" +
                            "\n * s to shorten an expression" +
                            "\n * q quits the program" +
                            "\n Example: g 15 generates an expression with value 15" +
                            "\n Input a Command:");
            String command = scanner.nextLine();
            String key = "";
            String expression = "";
            if (command.substring(0, 1).equals("q")) {
                System.out.println("Bye!");
                break;
            } else if (command.length() < 3) {
                key = "LENGTH ERROR";
            } else {
                key = command.substring(0, 1);
                expression = command.substring(2);
            }

            if (key.equals("LENGTH ERROR")) {
                System.out.println("Please input a valid command");
            } else if (key.equals("e")) {
                System.out.println(evaluate(expression));
            } else if (key.equals("g")) {
                if (isDigit(expression))
                    System.out.println(generate(Integer.parseInt(expression)));
                else
                    System.out.println("Not an integer");
            } else if (key.equals("s")) {
                System.out.println(generate(evaluate(expression)));
            } else if (key.equals("t")) { // secret testing option
                if (isDigit(expression))
                    System.out.println("  " + evaluate(generate(Integer.parseInt(expression))));
                else
                    System.out.println("Not an integer");
            } else {
                System.out.println("Please input a valid command");
            }
            System.out.println("\n\n");
        }
    }
}
