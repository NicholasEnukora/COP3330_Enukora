public class BodyMassIndex {

    public static double bmi;

    public BodyMassIndex(double height, double weight) {
        bmi = (weight * 703) / (height * height);
    }

    @Override
    public String toString() {
        return String.valueOf(bmi);
    }

    public static String bmiCat() {

        String bmiCat = new String(" ");

        if (bmi < 18.5) {

            bmiCat = ("You are underweight.");

        } else if (bmi > 18.5 && bmi < 24.9) {

            bmiCat = ("You are a normal weight.");

        } else if (bmi > 25 && bmi < 29.9) {

            bmiCat = ("You are overweight.");

        } else if (bmi >= 30) {

            bmiCat = ("You are obese.");
        }

        return bmiCat;
    }
}