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
            while (search != "(") {
                search = s.substring(index - 1, index);
                i -= 1;
            }
            return s.substring(i+1, index);
        }
    }

    public static void main(String[] args) {
        System.out.print(findP("(123)"));
    }
}

