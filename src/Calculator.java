import java.util.*;

public class Calculator {
    private String expression;

    Calculator(String expression) {
        this.expression = expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    public ArrayList<String> getTokens(String expression) {
        ArrayList<String> tokens = new ArrayList<>();
        List<String> operand = Arrays.asList("+", "-", "*", "/", "^", "(", ")");

        for (int i = 0; i < expression.length(); i++) {
            String symbol = String.valueOf(expression.charAt(i));

            if (operand.contains(symbol)) {
                tokens.add(symbol);
            } else if ((isNumeric(symbol) || operand.contains(symbol)) && i == expression.length() - 1) {
                tokens.add(symbol);
            } else if (isNumeric(symbol) && !isNumeric(String.valueOf(expression.charAt(i + 1))) && !String.valueOf(expression.charAt(i + 1)).equals("/")
                    && !String.valueOf(expression.charAt(i + 1)).equals(".")) {
                tokens.add(symbol);
            } else if (isNumeric(symbol)) {
                StringBuilder concatenationNumber = new StringBuilder(symbol);
                while (i < expression.length() - 1 && (isNumeric(String.valueOf(expression.charAt(i + 1))) || String.valueOf(expression.charAt(i + 1)).equals("/"))) {
                    symbol = String.valueOf(expression.charAt(i + 1));
                    concatenationNumber.append(symbol);
                    i++;
                }
                tokens.add(String.valueOf(concatenationNumber));
            }
        }
        return tokens;
    }

    private int priorityOperation(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 4;
            case "^" -> 6;
            default -> 0;
        };
    }

    private Fraction result(ArrayList<String> tokens) throws Exception {
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if (isNumeric(token) || Fraction.isFraction(token)) {
                stack.push(token);
            } else {
                Fraction operand2 = Fraction.parseFraction(stack.pop());
                Fraction operand1 = Fraction.parseFraction(stack.pop());
                String result = performOperation(token, operand1, operand2);
                stack.push(result);
            }
        }
        return Fraction.parseFraction(stack.pop());
    }

    public ArrayList<String> polishReverseRecord(ArrayList<String> tokens) {
        ArrayList<String> string = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (String token : tokens) {
            if (isNumeric(token) || Fraction.isFraction(token)) {
                string.add(token);
            } else if (Objects.equals(token, "(")) {
                stack.add(token);
            } else if (Objects.equals(token, ")")) {
                while (!Objects.equals(stack.peek(), "(")) {
                    string.add(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.empty() && (priorityOperation(token) <= priorityOperation(stack.peek()))) {
                    string.add(stack.pop());
                }
                stack.add(token);
            }
        }
        while (!stack.empty()) {
            string.add(stack.pop());
        }
        return string;
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String performOperation(String operator, Fraction operand1, Fraction operand2) throws Exception {

        switch (operator) {
            case "+" -> {
                return FractionOperation.addition(operand1, operand2).toString();
            }
            case "-" -> {
                return FractionOperation.subtraction(operand1, operand2).toString();
            }
            case "*" -> {
                return FractionOperation.multiplication(operand1, operand2).toString();
            }
            case "/" -> {
                return FractionOperation.division(operand1, operand2).toString();
            }
            default -> {
                return "Error, non-existent operator!";
            }
        }
    }

    public Fraction getResult(String expression) throws Exception {
        return result(polishReverseRecord(getTokens(expression)));
    }
}

