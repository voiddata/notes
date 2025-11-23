import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int W = sc.nextInt();

        int[][] arr = new int[n][2];

        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();

            maxValue = Math.max(maxValue, arr[i][1]);
        }

        int[][] buffer = new int[n][maxValue+1];

        System.out.println(iterativeTopDown(arr, W, buffer));

        for (int i = 0; i < buffer.length; i++) {
            System.out.println(Arrays.toString(buffer[i]));
        }
    }


    private static long iterativeTopDown(int[][] arr, int W, int[][] buffer) {

        for (int i = arr[0][1]; i < buffer[0].length; i++) {
            buffer[0][i] = arr[0][1];
        }
        
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < buffer[0].length; j++) {
                
            }
        }

        
        return buffer[arr.length-1][buffer[0].length-1];
    }
}
