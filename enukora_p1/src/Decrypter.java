public class Decrypter {

    public String decrypt(String value){

        int num = Integer.parseInt(value);

        int num1 = num / 1000;
        int num2 = (num - num1*1000) /100;
        int num3 = (num - (num1*1000 + num2*100))/10;
        int num4 = (num - (num1*1000 + num2*100 + num3*10));

        int num1plus7 = num1 + 10 - 7;
        int num2plus7 = num2 + 10 - 7;
        int num3plus7 = num3 + 10 - 7;
        int num4plus7 = num4 + 10 - 7;

        int decryptednum1 = num1plus7 % 10;
        int decryptednum2 = num2plus7 % 10;
        int decryptednum3 = num3plus7 % 10;
        int decryptednum4 = num4plus7 % 10;

        String switch1 = Integer.toString(decryptednum3);
        String switch2 = Integer.toString(decryptednum4);
        String switch3 = Integer.toString(decryptednum1);
        String switch4 = Integer.toString(decryptednum2);

        String decryptedValue = ((switch1)+(switch2)+(switch3)+(switch4));
        return decryptedValue;
    }
}