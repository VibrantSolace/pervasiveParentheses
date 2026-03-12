import java.util.Scanner;
public class pervasiveParenthesesTwo {

    public static String evaluateEquation (String s) {
        return s;
    }
    public static String constructEquation(int s) {
        int left = s;
        int parens = 0;
        int curVal = 0;
        //final string, permanent
        String a = "";
        while (left != 0) {
            curVal = left;
            parens = 0;
            while  (curVal >= 10) {
                curVal /= 2;
                parens += 1;
            }
            left -= curVal*Math.pow(2,parens);
            a += "(".repeat(parens)+Integer.toString(curVal)+")".repeat(parens);
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner (System.in);
        while (true) {
            System.out.println("e {expression} evaluates, g {number} generates, q quits. Don't add spaces before, and always add a space before your expression.");
            String input = scan.nextLine();
            if (input.length()<2) {
                System.out.println("You didn't input anything!");
            } else if (input.substring(0,1).equals("e")) {
                if (input.substring(1,2).equals(" ")) {
                    if (evaluateEquation(input.substring(2)) != "error") {
                        System.out.println("The value of " + input.substring(2) + " is " + evaluateEquation(input.substring(2))) ;
                    } else {
                        System.out.println("That's not a valid equation!");
                    }
                } else {
                    System.out.println("I told you to add a space before the expression!");
                }
            } else if (input.substring(0,1).equals("g")) {
                if (input.substring(1,2).equals(" ")) {
                    if (constructEquation(Integer.parseInt(input.substring(2))) != "error") {
                        System.out.println("An expression with value " + input.substring(2) + " is " + constructEquation(Integer.parseInt(input.substring(2)))) ;
                    } else {
                        System.out.println("That's not a valid number!");
                    }
                } else {
                    System.out.println("I told you to add a space before the expression!");
                }
            } else if (input.substring(0,1).equals("q")) {
                System.out.println("Bye!");
                break;
            } else {
                System.out.println("Invalid input!");
            }
        }
    }
}

