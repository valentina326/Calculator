import java.util.ArrayList;
import java.util.List;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) throws Exception {
        this.numerator = numerator;
        if (denominator == 0) {
            throw new Exception("The denominator can't be 0!");
        } else {
            this.denominator = denominator;
        }
        reduction();
    }

    public Fraction(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public int getNumerator() {
        return this.numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public static boolean isFraction(String expression) {
        return expression.contains("/");
    }

    public static Fraction parseFraction(String expression) throws Exception {
        if (!isFraction(expression)) return null;

        expression = expression.replaceAll(" ", ""); // remove all spaces 3 4/5 -> 34/5
        String[] values = expression.split("/"); // value[0] = numerator, value[1] = denominator

        try { // check if we have two correct integer values, so we don't have any * or another illegal symbols here
            return new Fraction(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
        } catch (NumberFormatException var3) {
            return null;
        }
    }

    public void reduction() {
        List<Integer> numeratorSimpleNumbers = getSimpleNumbers(this.getNumerator());
        List<Integer> denominatorSimpleNumbers = getSimpleNumbers(this.getDenominator());

        int nsd = findNSD(numeratorSimpleNumbers, denominatorSimpleNumbers);
        this.setNumerator(this.getNumerator() / nsd);
        this.setDenominator(this.getDenominator() / nsd);
    }

    private static List<Integer> getSimpleNumbers(int number) {
        List<Integer> simpleNumbers = new ArrayList<>();

        for (int i = 2; i <= number; i++) {
            if (number % i == 0) {
                simpleNumbers.add(i);
                number /= i;
                i = 1;
            }

            if (number == 1) break;
        }

        return simpleNumbers;
    }

    private static List<Integer> findSameSimpleNumbers(List<Integer> numeratorSimpleNumbers,
                                                       List<Integer> denominatorSimpleNumbers) {
        List<Integer> sameSimpleNumbers = new ArrayList<>();
        int numeratorSimpleNumber, denominatorSimpleNumber;

        for (Integer simpleNumber : numeratorSimpleNumbers) {
            numeratorSimpleNumber = simpleNumber;

            for (int j = 0; j < denominatorSimpleNumbers.size(); j++) {
                denominatorSimpleNumber = denominatorSimpleNumbers.get(j);

                if (numeratorSimpleNumber == denominatorSimpleNumber) {
                    sameSimpleNumbers.add(denominatorSimpleNumber);
                    denominatorSimpleNumbers.remove(j);
                    break;
                }
            }
        }

        return sameSimpleNumbers;
    }

    private static int findNSD(List<Integer> numeratorSimpleNumbers, List<Integer> denominatorSimpleNumbers) {
        return findNSD(findSameSimpleNumbers(numeratorSimpleNumbers, denominatorSimpleNumbers));
    }

    private static int findNSD(List<Integer> items) {
        int nsd = 1;
        for (Integer item : items) {
            nsd *= item;
        }

        return nsd;
    }

    @Override
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }
}