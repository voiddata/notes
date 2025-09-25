import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // int[] arr = new int[] {10, 30, 40, 50, 20}; // output - 30
        // int K = 3;

        // int[] arr = new int[] {10, 20, 10}; // output - 20
        // int K = 1;

        // int[] arr = new int[] {10, 10}; // output - 0
        // int K = 100;

        // int[] arr = new int[] {40, 10, 20, 70, 80, 10, 20, 70, 80, 60}; // output - 40
        // int K = 4;

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];

        int K = sc.nextInt();

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println(tabulationIterativeMinCost(arr, K));
    }

    private static int tabulationIterativeMinCost(int[] arr, int K) {

        int n = arr.length;

        // base cases
        if (n <= 1) return 0;

        
        int[] buffer = new int[n];
        buffer[n-1] = 0; // not required, but done for understanding

        // initializing with max value since the comparison
        // is done with the self value of i
        for (int i = 0; i < n-1; i++) {
            buffer[i] = Integer.MAX_VALUE;
        }

        for (int i = n-2; i >= 0; i--) {

            for (int j = 1; j <= K && (i+j) < arr.length; j++) {
                buffer[i] = Math.min(
                    buffer[i],
                    Math.abs(arr[i] - arr[i+j]) + buffer[i+j]
                );
            }
        }

        return buffer[0];
    }
}
