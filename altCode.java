import java.util.Scanner;

public class pervasiveParentheses {
    public static String loopP(String s) {
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

    public static String addString(String s) {
        int len = 0;
        String first = "";
        String second = "";

        while (len != s.length() - 1) {
            first = s.substring(len, len + 1);
            if (isDigit(first)) {
                second = s.substring(len + 1, len + 2);
                if (isDigit(second)) {
                    String added = String.valueOf(Integer.parseInt(first) + Integer.parseInt(second));
                    s = (s.substring(0, len) + added + s.substring(len + 2));
                }
            }
            len += 1;
        }
        return s;
    }

    public static String findP(String s) {
        int iter = 0;
        String index = "";
        while (iter != s.length()) {
            index = s.substring(iter, iter + 1);
            if (!isDigit(index) && !index.equals("(") && !index.equals(")")) {
                return "Your string does not follow the language rules";
            }
            iter += 1;
        }
        s = addString(s);
        s = loopP(s);
        return s;
    }

    public static String generate(String s) {
        if (!isDigit(s)) {
            return "Please input a number";
        }
        String newString = "";
        int goal = Integer.parseInt(s);
        int currentNum = 0;
        while (currentNum != goal) {
            int i = 0;
            int num = goal - currentNum;
            while (num >= 10) {
                num /= 2;
                i += 1;
            }
            currentNum += num * ((int) Math.pow(2, i));
            int parens = i;
            while (parens != 0) {
                newString += "(";
                parens -= 1;
            }
            newString += num;
            parens = i;
            while (parens != 0) {
                newString += ")";
                parens -= 1;
            }
        }
        return newString;
    }

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(
                "Command guidebook:" +
                        "\n * e to evaluate an expression" +
                        "\n * g to generate an expression" +
                        "\n * s to shorten an expression" +
                        "\n * q quits the program" +
                        "\n Example: g 15 generates an expression with value 15" +
                        "\nInput a Command:");
        String num = "(99)";
        String command = scanner.nextLine();
        String key = command.substring(0, 1);
        String expression = command.substring(2);
        System.out.println(num + " = " + findP(num));
        System.out.println(generate(command));
        if (key.equals("e")) {

        }
        else if (key.equals("g")) {

        }
        else if (key.equals("s")) {

        }

    }
}
