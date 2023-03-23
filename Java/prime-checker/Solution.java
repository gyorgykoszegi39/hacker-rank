import java.io.*;
import java.util.*;

class Prime {
    public void checkPrime(int... numbers) {
        StringBuilder primeNumbers = new StringBuilder();
        for (int number : numbers) {
            Boolean isPrime = true;
            if (number <= 1 || (number % 2 == 0 && number != 2)) {
                isPrime = false;
            } else {
                for (int i = 3; i <= Math.sqrt(number); i = i + 2) {
                    if (number % i == 0) {
                        isPrime = false;
                    }
                }
            }

            if (isPrime) {
                primeNumbers.append(number + " ");
            }
        }

        System.out.println(primeNumbers);
    }
}

public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Prime prime = new Prime();
        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        int n3 = scanner.nextInt();
        int n4 = scanner.nextInt();
        int n5 = scanner.nextInt();

        prime.checkPrime(n1);
        prime.checkPrime(n1, n2);
        prime.checkPrime(n1, n2, n3);
        prime.checkPrime(n1, n2, n3, n4, n5);

        scanner.close();
    }
}
