import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // int[] arr = new int[] {10, 30, 40, 20}; // output - 30
        // int[] arr = new int[] {10, 10}; // output - 0
        // int[] arr = new int[] {30, 10, 60, 10, 60, 50}; // output - 40

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int[] buffer = new int[n];
        Arrays.fill(buffer, -1);

        // System.out.println(bruteforceRecursiveMinCost(arr, 0));
        System.out.println(memoizedBruteforceRecursiveMinCost(arr, 0, buffer));
    }

    private static int bruteforceRecursiveMinCost(int[] arr, int index) {
        int leftTreeMinCost = index + 1 < arr.length ? 
            Math.abs(arr[index] - arr[index + 1]) + bruteforceRecursiveMinCost(arr, index + 1) : -1;
        int rightTreeMinCost = index + 2 < arr.length ? 
            Math.abs(arr[index] - arr[index + 2]) + bruteforceRecursiveMinCost(arr, index + 2) : -1;

        if (leftTreeMinCost != -1 && rightTreeMinCost != -1) {
            return Math.min(leftTreeMinCost, rightTreeMinCost);
        } else if (leftTreeMinCost == -1 && rightTreeMinCost != -1) {
            return rightTreeMinCost;
        } else if (leftTreeMinCost != -1 && rightTreeMinCost == -1) {
            return leftTreeMinCost;
        } else {
            return 0;
        }
    }

    private static int memoizedBruteforceRecursiveMinCost(int[] arr, int index, int[] buffer) {

        if (buffer[index] != -1) {
            return buffer[index];
        }

        int leftTreeMinCost = -1;
        if (index + 1 < arr.length) {
            if (buffer[index + 1] != -1) {
                leftTreeMinCost = Math.abs(arr[index] - arr[index + 1]) + buffer[index + 1];
            } else {
                leftTreeMinCost = Math.abs(arr[index] - arr[index + 1]) + memoizedBruteforceRecursiveMinCost(arr, index + 1, buffer);
            }
        }

        int rightTreeMinCost = -1;
        if (index + 2 < arr.length) {
            if (buffer[index + 2] != -1) {
                rightTreeMinCost = Math.abs(arr[index] - arr[index + 2]) + buffer[index + 2];
            } else {
                rightTreeMinCost = Math.abs(arr[index] - arr[index + 2]) + memoizedBruteforceRecursiveMinCost(arr, index + 2, buffer);
            }
        }

        if (leftTreeMinCost != -1 && rightTreeMinCost != -1) {
            int currentIndexMinCost = Math.min(leftTreeMinCost, rightTreeMinCost);
            buffer[index] = currentIndexMinCost;
            return currentIndexMinCost;
        } else if (leftTreeMinCost == -1 && rightTreeMinCost != -1) {
            buffer[index] = rightTreeMinCost;
            return rightTreeMinCost;
        } else if (leftTreeMinCost != -1 && rightTreeMinCost == -1) {
            buffer[index] = leftTreeMinCost;
            return leftTreeMinCost;
        } else {
            buffer[index] = 0;
            return 0;
        }
    }
}
