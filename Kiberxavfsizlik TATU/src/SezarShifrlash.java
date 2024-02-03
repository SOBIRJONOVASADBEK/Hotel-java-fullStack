import java.util.Scanner;

public class SezarShifrlash {
    public static String encrypt(String plainText, int shift) {
        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            char currentChar = plainText.charAt(i);
            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                char encryptedChar = (char) ( (((currentChar - base + shift) % 26)+26)%26 + base);
                cipher.append(encryptedChar);
            } else{
                cipher.append(currentChar);
            }
        }
        return cipher.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, -shift);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ochiq matn: ");
        String plaintext = scanner.nextLine();

        System.out.print("shift (siljitish qadami): ");
        int shift = scanner.nextInt();

        String ciphertext = encrypt(plaintext, shift);
        System.out.println("Shifr matn: " + ciphertext);

        String deCipher=decrypt(ciphertext,shift);
        System.out.println("deshifrlangan matn :" +deCipher);

        scanner.close();
    }
}
