public class Encrypter {

    public String encrypt(String value){

        int num = Integer.parseInt(value);

        int num1 = num / 1000;
        int num2 = (num - num1*1000) /100;
        int num3 = (num - (num1*1000 + num2*100))/10;
        int num4 = (num - (num1*1000 + num2*100 + num3*10));

        int num1plus7 = num1 + 7;
        int num2plus7 = num2 + 7;
        int num3plus7 = num3 + 7;
        int num4plus7 = num4 + 7;

        int encryptednum1 = num1plus7 % 10;
        int encryptednum2 = num2plus7 % 10;
        int encryptednum3 = num3plus7 % 10;
        int encryptednum4 = num4plus7 % 10;

        String switch1 = Integer.toString(encryptednum3);
        String switch2 = Integer.toString(encryptednum4);
        String switch3 = Integer.toString(encryptednum1);
        String switch4 = Integer.toString(encryptednum2);

        String encryptedValue = ((switch1)+(switch2)+(switch3)+(switch4));

        return encryptedValue;
    }
}