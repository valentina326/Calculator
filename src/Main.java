public class Main {

    public static void main(String[] args) throws Exception {
        String expression = "10/2 / 2/10";
        Calculator calculator = new Calculator(expression);
        System.out.println(calculator.getResult(expression));
    }
}