public class Main {

    public static void main(String[] args) throws Exception {
        String expression = "1/3 + 1/3 * 2/13 - 4/1 / 16/3";
        Calculator calculator = new Calculator(expression);
        System.out.println(calculator.getResult(expression));
    }
}