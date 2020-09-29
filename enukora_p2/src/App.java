import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

        while (moreInput()) {
            double height = getUserHeight();
            double weight = getUserWeight();

            BodyMassIndex bmi = new BodyMassIndex(height, weight);
            bmiData.add(bmi);

            displayBmiInfo(bmi);
        }

        displayBmiStatistics(bmiData);
    }

    public static boolean moreInput() {
        boolean token = true;
        int flag = 0;

        Scanner choice = new Scanner(System.in);

        if (flag == 0) {
            System.out.println("Would you like to calculate your BMI? (Y/N)");

            String choiceString = choice.next();

            char stringChar = choiceString.charAt(0);

            if (stringChar == 'Y' || stringChar == 'y') {
                token = true;
                flag = flag + 1;
            } else if (stringChar == 'N' || stringChar == 'n') {
                token = false;
                System.out.println("Program will end.");
                System.exit(0);
            } else {

                System.out.println("Please provide a Y or N.");

            }
        } else if (flag != 0) {

            System.out.println("Would you like to calculate another BMI? (Y/N)");

            String choiceString = choice.next();

            char stringChar = choiceString.charAt(0);

            if (stringChar == 'Y' || stringChar == 'y') {
                token = true;
                flag = flag + 1;
            } else if (stringChar == 'N' || stringChar == 'n') {
                token = false;
                System.out.println("Program will end.");
                System.exit(0);
            } else {

                System.out.println("Please provide a Y or N.");

            }
        }
        return token;
    }

    public static double getUserHeight() {

        Scanner getUserHeight = new Scanner(System.in);

        double height = 0;

        while (height <= 0) {
            System.out.println("Please provide your Height (in inches):");
            height = getUserHeight.nextDouble();

            if (height < 0) {
                System.out.println("Please provide a positive value.");
            }
        }
        return height;
    }

    public static double getUserWeight() {

        Scanner getUserWeight = new Scanner(System.in);

        double weight = 0;

        while (weight <= 0) {
            System.out.println("Please provide your Weight (in inches):");
            weight = getUserWeight.nextDouble();

            if (weight < 0) {
                System.out.println("Please provide a positive value.");
            }
        }
        return weight;
    }

    public static void displayBmiInfo(BodyMassIndex bmi) {
        System.out.println("Your BMI is: " + bmi);
        System.out.println("Your BMI category:" + " " + BodyMassIndex.bmiCat());
        System.out.println();
    }

    public static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData) {

        Integer sum = 0;
        if (!bmiData.isEmpty()) {
            for (BodyMassIndex bmiAverage : bmiData) { ;
            }
            System.out.println(sum.doubleValue() / bmiData.size());
        }
    }
}