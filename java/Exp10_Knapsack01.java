// Experiment 10: Implementation of 0/1 Knapsack Problem using Dynamic Programming
// In 0/1 knapsack, items cannot be broken (either take whole item or leave it)

public class Exp10_Knapsack01 {

    public static void solve01Knapsack(int[] val, int[] wt, int W, int n) {
        // dp[i][w] stores max value with first i items and capacity w
        int[][] dp = new int[n + 1][W + 1];

        // build DP table from bottom up
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                // base case: 0 items or 0 capacity
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } 
                // if weight of current item can fit in capacity w
                else if (wt[i - 1] <= w) {
                    // max of including the item OR excluding the item
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
                } 
                // if item weight is more than current capacity
                else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        int maxProfit = dp[n][W];
        System.out.println("Maximum Profit achievable = " + maxProfit);

        // find which items were selected (backtracking)
        System.out.println("Selected items:");
        int currentW = W;
        for (int i = n; i > 0 && maxProfit > 0; i--) {
            // if value came from including this item
            if (maxProfit != dp[i - 1][currentW]) {
                System.out.println("- Item " + i + " (Weight: " + wt[i - 1] + ", Value: " + val[i - 1] + ")");
                maxProfit -= val[i - 1];
                currentW -= wt[i - 1];
            }
        }
    }

    public static void main(String[] args) {
        int[] val = {60, 100, 120};
        int[] wt = {10, 20, 30};
        int W = 50;
        int n = val.length;

        System.out.println("--- 0/1 Knapsack Problem (Dynamic Programming) ---");
        System.out.println("Knapsack Capacity: " + W);
        System.out.println("Number of items: " + n);

        long startTime = System.nanoTime();

        solve01Knapsack(val, wt, W, n);

        long endTime = System.nanoTime();
        long timeTaken = endTime - startTime;

        System.out.println("\n--- Complexity & Execution Time ---");
        System.out.println("Execution Time: " + timeTaken + " ns (" + (timeTaken / 1_000_000.0) + " ms)");
        System.out.println("Time Complexity: O(n * W)  [Pseudo-polynomial time]");
        System.out.println("Space Complexity: O(n * W) [For 2D DP table]");
    }
}
