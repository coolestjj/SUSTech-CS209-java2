package practice.lab3;

import java.util.ArrayList;
import java.util.Scanner;

public class Practice3Answer {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Please input the function No:\n" +
                    "1 - Get even numbers\n" +
                    "2 - Get odd numbers\n" +
                    "3 - Get prime numbers\n" +
                    "4 - Get prime numbers that are bigger than 5\n" +
                    "0 - Quit");
            int function = input.nextInt();
            if (function == 0) {
                break;
            }
            System.out.println("Input size of the list:");
            int size = input.nextInt();
            System.out.println("Input elements of the list:");
            ArrayList<Integer> array = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                array.add(input.nextInt());
            }
            System.out.println("Filter results:");
            if (function == 1) {
                array.removeIf(o -> o % 2 != 0);
            } else if (function == 2) {
                array.removeIf(o -> o % 2 == 0);
            } else if (function == 3) {
                array.removeIf(o -> isNotPrime(o));
            } else if (function == 4) {
                array.removeIf(o -> isNotPrime(o) || o <= 5);
            }
            System.out.println(array);
        }

    }

    public static boolean isNotPrime(int num) {
        int i;
        for (i = 2; i < num; i++) {
            if (num % i == 0) {
                return true;
            }
        }
        if (num == i) {
            return false;
        }
        return true;
    }
}
