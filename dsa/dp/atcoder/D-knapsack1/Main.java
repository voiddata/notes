import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int W = sc.nextInt();

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

        long[][] buffer = new long[n][W+1];

        for (long[] row : buffer) {
            Arrays.fill(row, -1);
        }

        // System.out.println(bruteForceKnapSackWithoutExtraStates(arr, W, 0));
        // System.out.println(bruteForceKnapSack(arr, W, 0, 0, 0));

        System.out.println(bruteForceKnapSackWithMemoization(arr, W, 0, buffer));
    }

    private static long bruteForceKnapSackWithoutExtraStates(int[][] arr, int W, int index) {
        if (index >= arr.length || W == 0) {
            // if the current index is greater than the bounds or, 
            // if the bag capacity is already 0
            // simply not take the element into consideration.
            // and do not proceed further with any recursive calls.
            return 0;
        }

        long leave = 0 + bruteForceKnapSackWithoutExtraStates(arr, W, index+1);
        long take = Integer.MIN_VALUE;

        if (arr[index][0] <= W) {
            take = arr[index][1] + bruteForceKnapSackWithoutExtraStates(arr, W - arr[index][0], index+1);   
        }
        return Math.max(take, leave);
    }

    private static long bruteForceKnapSack(int[][] arr, int W, int index, int weightSum, long valueSum) {
        if (index >= arr.length) {
            return valueSum;
        }

        long take = weightSum + arr[index][0] <= W ? 
            bruteForceKnapSack(arr, W, index+1, weightSum + arr[index][0], valueSum + arr[index][1]) : 
            valueSum;
        long leave = bruteForceKnapSack(arr, W, index+1, weightSum, valueSum);

        return Math.max(take, leave);
    }

    private static long bruteForceKnapSackWithMemoization(int[][] arr, int W, int index, long[][] buffer) {
        if (index >= arr.length || W == 0) {
            // if the current index is greater than the bounds or, 
            // if the bag capacity is already 0
            // simply not take the element into consideration.
            // and do not proceed further with any recursive calls.
            return 0;
        }
        if (buffer[index][W] != -1) return buffer[index][W];

        long leave = 0 + bruteForceKnapSackWithMemoization(arr, W, index+1, buffer);
        long take = Integer.MIN_VALUE;

        if (arr[index][0] <= W) {
            take = arr[index][1] + bruteForceKnapSackWithMemoization(arr, W - arr[index][0], index+1, buffer);   
        }

        buffer[index][W] = Math.max(take, leave);
        return buffer[index][W];
    }
}
