import java.io.*;
import java.util.*;

interface PerformOperation {
    boolean check(int a);
}

class MyMath {
    public static boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }

    public PerformOperation isOdd() {
        return (number) -> {
            return number % 2 != 0;
        };
    };

    public PerformOperation isPrime() {
        return (number) -> {
            Boolean isPrime = true;
            if (number < 2 || (number % 2 == 0 && number != 2))
                isPrime = false;
            for (int i = 3; i <= Math.sqrt(number); i += 2)
                if (number % i == 0)
                    isPrime = false;
            return isPrime;
        };
    };

    public PerformOperation isPalindrome() {
        return (number) -> {
            String numberAsString = "" + number;

            int i = 0;
            int n = numberAsString.length();
            while (i < n / 2) {
                if (numberAsString.charAt(i) != numberAsString.charAt(n - i - 1))
                    return false;
                i++;
            }
            return true;
        };
    };
}

public class Solution {

    public static void main(String[] args) throws IOException {
        MyMath ob = new MyMath();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        PerformOperation op;
        boolean ret = false;
        String ans = null;
        while (T-- > 0) {
            String s = br.readLine().trim();
            StringTokenizer st = new StringTokenizer(s);
            int ch = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (ch == 1) {
                op = ob.isOdd();
                ret = ob.checker(op, num);
                ans = (ret) ? "ODD" : "EVEN";
            } else if (ch == 2) {
                op = ob.isPrime();
                ret = ob.checker(op, num);
                ans = (ret) ? "PRIME" : "COMPOSITE";
            } else if (ch == 3) {
                op = ob.isPalindrome();
                ret = ob.checker(op, num);
                ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

            }
            System.out.println(ans);
        }
    }
}
