import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class RSAShifrlash {
    public static void main(String...args){

        int p=53, q=61;

        System.out.println("p = " + p);
        System.out.println("q = " + q);

        System.out.print("n = " + p+"*" +q +"=");
        int n = p*q ;
        System.out.println(n);

        System.out.print("phi_n = " + (p-1) +"*" + (q-1) +"=");
        int phi_n =(p-1)*(q-1);
        System.out.println(phi_n);

        int e =17;

        System.out.println("e = " + e);

        int d = privateExponent(e,phi_n) ; //d shunday sonki  (e*d) mod phi_n = 1 shartni qanoatlantiradi

        System.out.println("d = " + d);

        String plainText="Asadbek";
        int[] cipherAsArray = encrypt(plainText, e, n);
        System.out.println("Shifr matn :"+ Arrays.toString(cipherAsArray));
        String decryptedText=decrypt(cipherAsArray,d,n);
        System.out.println("decryptedText = " + decryptedText);
    }

    public static  int[] encrypt(String plainText, int e , int n )// shifr matnni sonli array'da saqlagan ma'qul
    {
        byte[] plainTextBytes = plainText.getBytes();
        System.out.println("Ochiq matn ASCII qiymati : " +  Arrays.toString(plainTextBytes));
        int[] cipherText = new int[plainText.length()]; //shifr matn uzunligi ochiq matn uzunligiga teng bo'ladi

        for (int i = 0; i < plainTextBytes.length; i++)
        {
            cipherText[i]= calculateModule(plainTextBytes[i],e,n);
        }
        return  cipherText;
    }

    public static  String decrypt(int[] cipherText , int d , int n ) //Ochiq matnni bemalol String ko'rinishda qaytarsak bo'ladi
    {
        StringBuffer plainText = new StringBuffer();
        for (int i : cipherText)
        {
            plainText.append( (char) calculateModule(i,d,n));
        }
        return  plainText.toString();
    }


    private static int calculateModule(int base,int power , int module)
    {
        BigInteger bigIntegerBase = new BigInteger(String.valueOf(base));
        BigInteger bigIntegerPower = new BigInteger(String.valueOf(power));
        BigInteger bigIntegerModule = new BigInteger(String.valueOf(module));

        BigInteger result = bigIntegerBase.modPow(bigIntegerPower,bigIntegerModule);
        return  result.intValue();
    }

    public static int privateExponent(int e, int phi_n)
    {
        BigInteger bigE = new BigInteger(String.valueOf(e));
        BigInteger bigPhi_n = new BigInteger(String.valueOf(phi_n));
        BigInteger d = bigE.modInverse(bigPhi_n);
        return d.intValue();
    }

}
