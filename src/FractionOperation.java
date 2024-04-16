import java.util.HashMap;
import java.util.Objects;

public class FractionOperation {

    public static HashMap<String, Integer> getComponentsFraction(Fraction firstFraction, Fraction secondFraction) {
        HashMap<String, Integer> componentsFraction = new HashMap<>();
        componentsFraction.put("numFirstFraction", firstFraction.getNumerator());
        componentsFraction.put("denFirstFraction", firstFraction.getDenominator());
        componentsFraction.put("numSecondFraction", secondFraction.getNumerator());
        componentsFraction.put("denSecondFraction", secondFraction.getDenominator());
        return componentsFraction;
    }

    public static Fraction addition(Fraction firstFraction, Fraction secondFraction) throws Exception {
        HashMap<String, Integer> map = new HashMap<>(getComponentsFraction(firstFraction,secondFraction));
        int numerator;
        int denominator;
        if (!Objects.equals(map.get("denFirstFraction"), map.get("denSecondFraction"))) {
           numerator = (map.get("numSecondFraction") * map.get("denFirstFraction")) + (map.get("numFirstFraction") * map.get("denSecondFraction"));
           denominator = map.get("denFirstFraction") * map.get("denSecondFraction");
        } else {
            numerator = map.get("numFirstFraction")  + map.get("numSecondFraction");
            denominator = map.get("denFirstFraction");
        }
        return new Fraction(numerator, denominator);
    }

    public static Fraction subtraction(Fraction firstFraction, Fraction secondFraction) throws Exception {
        HashMap<String, Integer> map = new HashMap<>(getComponentsFraction(firstFraction,secondFraction));
        int numerator;
        int denominator;
        if (!Objects.equals(map.get("denFirstFraction"), map.get("denSecondFraction"))) {
            numerator = map.get("numFirstFraction") * map.get("denSecondFraction") - map.get("numSecondFraction") * map.get("denFirstFraction");
            denominator = map.get("denFirstFraction") * map.get("denSecondFraction");
        } else {
            numerator = map.get("numFirstFraction") - map.get("denSecondFraction");
            denominator = map.get("denFirstFraction");
        }
        return new Fraction(numerator, denominator);
    }

    public static Fraction multiplication(Fraction firstFraction, Fraction secondFraction) throws Exception {
        HashMap<String, Integer> map = new HashMap<>(getComponentsFraction(firstFraction,secondFraction));
        int numerator = map.get("numFirstFraction") * map.get("numSecondFraction");
        int denominator = map.get("denFirstFraction") * map.get("denSecondFraction");
        return new Fraction(numerator, denominator);
    }

    public static Fraction division(Fraction firstFraction, Fraction secondFraction) throws Exception {
        HashMap<String, Integer> map = new HashMap<>(getComponentsFraction(firstFraction,secondFraction));
        int numerator = map.get("numFirstFraction") * map.get("denSecondFraction");
        int denominator = map.get("denFirstFraction") * map.get("numSecondFraction");
        return new Fraction(numerator, denominator);
    }

    public static Fraction reduction(Fraction fraction) throws Exception {
        Fraction result = new Fraction(1, 1);
        if (fraction.getNumerator() % fraction.getDenominator() == 0) {
            result = new Fraction(fraction.getNumerator(), fraction.getDenominator());
        } else {
            result.setNumerator(fraction.getNumerator() % fraction.getDenominator());
        }
        int wholePart = fraction.getNumerator() / fraction.getDenominator();
        if (!(wholePart <= 0)) {
            result = new Fraction(wholePart, fraction.getNumerator(), fraction.getDenominator());
        return result;
    }
}
