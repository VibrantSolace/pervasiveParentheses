public class Tests {
    private static int totalRun = 0;
    private static int totalPassed = 0;

    public static void testValidate(String expr, boolean expected) {
        totalRun++;
        boolean actual = pervasiveParentheses.validate(expr);
        if (actual == expected)
            totalPassed++;
        else
            System.out
                    .println("Validate test failed, expr: " + expr + ", expected: " + expected + ", actual: " + actual);
    }

    public static void testEvaluate(String expression, int expected) {
        totalRun++;
        int actual = pervasiveParentheses.evaluate(expression);
        if (actual == expected)
            totalPassed++;
        else
            System.out.println(
                    "Evaluate test failed, expr: " + expression + ", expected: " + expected + ", actual: " + actual);

    }

    public static void testGenerate(int value, String expected) {
        totalRun++;
        String actual = pervasiveParentheses.generate(value);
        if (actual.equals(expected))
            totalPassed++;
        else
            System.out
                    .println("Generate test failed, val: " + value + ", expected: " + expected + ", actual: " + actual);

    }

    public static void testConsistency(int value) {
        // This first generates an expression with the given value,
        // then evaluates that generated expression, then compares
        // that with the original value.
        totalRun++;
        String exp = pervasiveParentheses.generate(value);
        int actual = pervasiveParentheses.evaluate(exp);
        if (actual == value)
            totalPassed++;
        else
            System.out.println(
                    "Consistency test failed, value: " + value + ", generated: " + exp + ", evaluated: " + actual);
    }

    public static void main(String[] args) {
        // example validate tests with invalid expressions
        testValidate("(0)(", false);
        testValidate("(0))1((2)", false);
        testValidate("1(x)", false);

        // example validate tests with valid expressions
        testValidate("", true);
        testValidate("01234(5)6789", true);
        testValidate("(12(34)56(78)9)0", true);

        testEvaluate(null, -1);

        testGenerate(12408, "8((((7(((((996)))))))))");

        // example consistency test, add moore
        testConsistency(10235);
        testConsistency(12344);
        testConsistency(6234);
        testConsistency(123456789);
        testConsistency(5673);
        testConsistency(98739);
        for (int i = 0; i < 5000; i += (int) (Math.random() * 10) + 1)
            testConsistency(i);

        System.out.println("Total tests run: " + totalRun);
        System.out.println("Total tests passed: " + totalPassed);
    }
}
