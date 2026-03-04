public class pervasiveParentheses {
    public static String findP(String s) {
        int index = s.indexOf(")");
        int i = index;
        String search = "";
        while (search != "(") {
            search = s.substring(index - 1, index);
            i -= 1;
        }
        return s.substring(i, index);
    }

    public static void main(String[] args) {
        System.out.print(findP("(123)"));
    }
}
