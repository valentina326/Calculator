public class Main {

    public static void main(String[] args) throws Exception {
        String expression = "18/9";
        Calculator calculator = new Calculator(expression);
        System.out.println(calculator.getResult(expression));
    }
}