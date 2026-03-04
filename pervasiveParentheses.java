public class pervasiveParentheses {
    public static String findP(String s) {
        int index = s.indexOf(")");
        int i = index;
        String search = "";
        if (i == -1 && s.indexOf("(") == -1) {
            return s;
        } else if (i == -1 && s.indexOf("(") != -1) {
            return "error";
        } else {
            while (!search.equals("(")) {
                search = s.substring(i - 1, i);
                i -= 1;
            }
            return s.substring(i+1, index);
        }
    }

    public static String evalExpression (String ex) {
        int i = 0;
        int evaluated = 0;
        while (i+1<ex.length()) {
            try {
                evaluated += Integer.parseInt(ex.substring(i,i+1));
            } catch (Exception NumberFormatException) {
                return "error";
            }
            i += 1;
        }
        return Integer.toString(evaluated);
    }

    public static void main(String[] args) {
        System.out.print(findP("(123)"));
    }
}







