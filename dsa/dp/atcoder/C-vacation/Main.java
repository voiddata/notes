import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // int[][] arr = new int[][] {
        //     {10,40,70},
        //     {20,50,80},
        //     {30,60,90}
        // };

        // int[][] arr = new int[][] {
        //     {100,10,1}
        // };

        // int[][] arr = new int[][] {
        //     {6,7,8},
        //     {8,8,3},
        //     {2,5,2},
        //     {7,8,6},
        //     {4,6,8},
        //     {2,3,4},
        //     {7,5,1}
        // };

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[][] arr = new int[n][3];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        int[][] dp = new int[n][3];

        // int ans = 0;
        // for (int i = 0; i < 3; i++) {
        //     ans = Math.max(ans, maxHappinessMemoizedTopDown(arr, i, 0, dp));
        // }

        maxHappinessTabulationBottomUp(arr, dp);

        int ans = 0;
        for (int i = 0; i < 3; i++) {
            ans = Math.max(ans, dp[0][i]);
        }
        
        System.out.println(ans);

        // System.out.println(maxHappiness(arr, -1, 0));
    }

    private static int maxHappiness(int[][] arr, int index, int day) {

        if (day >= arr.length) {
            return 0;
        }

        int max = 0;
        for (int i = 0; i < 3; i++) {

            if (i != index)
                max = Math.max(max, arr[day][i] + maxHappiness(arr, i, day+1));
        }
        return max;
    }

    private static int maxHappinessMemoizedTopDownWrong(int[][] arr, int index, int day, int[] dp) {

        if (day >= arr.length) {
            return 0;
        }

        if (dp[day] != 0) {
            return dp[day];
        }

        for (int i = 0; i < 3; i++) {

            if (i != index)
                dp[day] = Math.max(dp[day], arr[day][i] + maxHappinessMemoizedTopDownWrong(arr, i, day+1, dp));
        }

        return dp[day];
    }

    private static int maxHappinessMemoizedTopDown(int[][] arr, int index, int day, int[][] dp) {

        if (day >= arr.length) {
            return 0;
        }

        if (dp[day][index] != 0) {
            return dp[day][index];
        }

        for (int i = 0; i < 3; i++) {

            if (i != index)
                dp[day][index] = Math.max(dp[day][index], arr[day][i] + maxHappinessMemoizedTopDown(arr, i, day+1, dp));
        }

        return dp[day][index];
    }

    private static void maxHappinessTabulationBottomUp(int[][] arr, int[][] dp) {

        // base cases
        dp[arr.length - 1][0] = arr[arr.length - 1][0];
        dp[arr.length - 1][1] = arr[arr.length - 1][1];
        dp[arr.length - 1][2] = arr[arr.length - 1][2];

        for (int day = arr.length - 2; day >= 0; day--) {
            for (int task = 0; task < 3; task++) {
                for (int i = 0; i < 3; i++) {
                    if (i != task)
                        dp[day][task] = Math.max(dp[day][task], arr[day][task] + dp[day+1][i]);
                }
            }
        }
    }
}
