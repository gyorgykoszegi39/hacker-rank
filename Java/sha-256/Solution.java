import java.io.*;
import java.security.MessageDigest;
import java.util.*;

public class Solution {

    public static String md5(String word) {

        return "reuslt";
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] digestedMEssage = messageDigest.digest(scanner.nextLine().getBytes());

            StringBuffer result = new StringBuffer();
            for (int i = 0; i < digestedMEssage.length; i++) {
                result.append(Integer.toHexString((digestedMEssage[i] & 0xFF) | 0x100).substring(1, 3));
            }

            System.out.println(result);
        } catch (java.security.NoSuchAlgorithmException e) {
        }

        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
         * class should be named Solution.
         */
    }
}