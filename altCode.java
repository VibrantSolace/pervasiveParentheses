import java.util.Scanner;

public class pervasiveParentheses {
    public static String loop(String s) {
        while (true) {
            int index = s.indexOf(")");
            if (index == -1) { // safety measures
                if (s.indexOf("(") != -1) {
                    System.out.println("No matching closed parentheses for your open parentheses");
                    return "error";
                } else
                    return s;
            }
            int i = index; // variable to tick down from index to search String
            String search = ""; // variable to search String one character at a time
            while (!search.equals("(")) {
                search = s.substring(i - 1, i);
                i -= 1;
                if (i == 0 && !search.equals("(")) {
                    System.out.println("No matching open parentheses for your closed parentheses");
                    return "error";
                }
            }
            int num = Integer.parseInt(s.substring(i + 1, index)) * 2; // doubles what's inside parentheses
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
            while (len != 0) { // reverses the beforeP String
                before += beforeP.substring(len - 1, len);
                len -= 1;
            }

            String after = ""; // don't need a placeholder for this one
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

            num += Integer.parseInt(before) + Integer.parseInt(after);
            s = s.substring(0, i + 1) + String.valueOf(num) + s.substring(index, s.length());
        }
    }

    public static boolean isDigit(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String addDigits(String s) {
        int len = 0;
        String first = "";
        String next = "";
        int added = 0;
        int temp = 0;

        while (len < s.length() - 1) {
            first = s.substring(len, len + 1);
            if (isDigit(first)) {
                temp = len;
                added = Integer.parseInt(first);
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

    public static String evaluate(String s) {
        int iter = 0;
        String index = "";
        while (iter != s.length()) {
            index = s.substring(iter, iter + 1);
            if (!isDigit(index) && !index.equals("(") && !index.equals(")")) {
                return "Your string does not follow the language rules";
            }
            iter += 1;
        }
        s = addDigits(s);
        s = loop(s);
        return s;
    }

    public static String generate(String s) {
        if (!isDigit(s)) {
            return "Please input a number";
        }
        String newString = "";
        String front = "";
        int goal = Integer.parseInt(s);
        int currentNum = 0;

        int i = 0;
        int num = goal;
        while (num >= 10) {
            num /= 2;
            i += 1;
        }
        currentNum += num * ((int) Math.pow(2, i));
        newString += num;
        while (i != 0) {
            newString = "(" + newString + ")";
            i -= 1;
        }
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
            newString = front + num + newString.substring(i + 1);
        }

        return newString;
    }

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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
            if (command.length() < 3) {
                key = "ERROR";
            } else {
                key = command.substring(0, 1);
                expression = command.substring(2);
            }

            if (key.equals("ERROR")) {
                System.out.println("Please input a valid command");
            } else if (key.equals("e")) {
                System.out.println(evaluate(expression));
            } else if (key.equals("g")) {
                System.out.println(generate(expression));
            } else if (key.equals("s")) {
                expression = evaluate(expression);
                System.out.println(generate(expression));
            } else if (key.equals("q")) {
                System.out.println("Bye!");
                break;
            }
            System.out.println("\n\n");
        }
    }
}
