public class Fraction {

    private int numerator;
    private int denominator;
    private int wholePart;

    public Fraction(int numerator, int denominator) throws Exception {
        this.numerator = numerator;
        if (denominator == 0) {
            throw new Exception("The denominator can't be 0!");
        } else {
            this.denominator = denominator;
        }
    }

    public Fraction(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
    }

    public Fraction(int wholePart, int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.wholePart = wholePart;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getWholePart() {
        return wholePart;
    }

    public void setWholePart(int wholePart) {
        this.wholePart = wholePart;
    }

    public String toString() {
        return numerator + "/" + denominator;
    }

    public static boolean isFraction(String expression) {
        return expression.contains("/");
    }

    public static Fraction parseFraction(String expression) throws Exception {
        String symbol = String.valueOf(expression.charAt(0));
        Fraction fraction = new Fraction(1, 1);
        if (isFraction(expression)) {
            for (int i = 0; i < expression.length(); i++) {
                StringBuilder concatenationNumberNum = new StringBuilder(symbol);
                while (!String.valueOf(expression.charAt(i + 1)).equals("/")) {
                    symbol = String.valueOf(expression.charAt(i + 1));
                    concatenationNumberNum.append(symbol);
                    i++;
                }
                fraction.setNumerator(Integer.parseInt(String.valueOf(concatenationNumberNum)));
                if (i < expression.length() - 1 && String.valueOf(expression.charAt(i + 1)).equals("/")) {
                    i += 1;
                    StringBuilder concatenationNumberDen = new StringBuilder();
                    while (i < expression.length() - 1) {
                        symbol = String.valueOf(expression.charAt(i + 1));
                        concatenationNumberDen.append(symbol);
                        i++;
                    }
                    fraction.setDenominator(Integer.parseInt(String.valueOf(concatenationNumberDen)));
                }
            }
        } else {
            fraction.setNumerator(Integer.parseInt(symbol));
            fraction.setDenominator(1);
        }
        return fraction;
    }
}