// Exp 10: 0/1 Knapsack Problem using Dynamic Programming

public class Exp10_Knapsack01 {

    static int knapsack(int[] wt, int[] val, int W, int n) {
        int[][] dp = new int[n + 1][W + 1];

        // fill dp table
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= W; w++) {
                if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // print selected items
        int res = dp[n][W];
        int w = W;
        System.out.println("Selected items:");
        for (int i = n; i > 0 && res > 0; i--) {
            if (res != dp[i - 1][w]) {
                System.out.printf("Item %d (Weight: %d, Value: %d)\n", i, wt[i - 1], val[i - 1]);
                res -= val[i - 1];
                w -= wt[i - 1];
            }
        }

        return dp[n][W];
    }

    public static void main(String[] args) {
        int[] val = {60, 100, 120};
        int[] wt = {10, 20, 30};
        int W = 50;

        System.out.println("Knapsack Capacity: " + W);

        long startTime = System.nanoTime();

        int maxVal = knapsack(wt, val, W, val.length);

        long endTime = System.nanoTime();

        System.out.println("Maximum Profit: " + maxVal);
        System.out.println("\nExecution Time: " + (endTime - startTime) + " ns");
        System.out.println("Time Complexity: O(n * W)");
        System.out.println("Space Complexity: O(n * W)");
    }
}
