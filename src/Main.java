public class Main {

    public static void main(String[] args) throws Exception {
        String expression = "13/2 / 3/1";
        Calculator calculator = new Calculator(expression);
        System.out.println(calculator.getResult(expression));
    }
}