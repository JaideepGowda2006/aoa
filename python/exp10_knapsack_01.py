# Experiment 10: Implementation of 0/1 Knapsack Problem using Dynamic Programming
# In 0/1 knapsack, each item can either be taken completely or not taken at all

import time

def solve_01_knapsack(values, weights, W, n):
    # dp table of size (n + 1) x (W + 1)
    dp = [[0] * (W + 1) for _ in range(n + 1)]

    # build table bottom up
    for i in range(1, n + 1):
        for w in range(1, W + 1):
            if weights[i - 1] <= w:
                # max of including or excluding
                dp[i][w] = max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w])
            else:
                dp[i][w] = dp[i - 1][w]

    max_profit = dp[n][W]
    print(f"Maximum Profit achievable = {max_profit}")

    # backtracking to find selected items
    print("Selected items:")
    current_w = W
    for i in range(n, 0, -1):
        if max_profit != dp[i - 1][current_w]:
            print(f"- Item {i} (Weight: {weights[i - 1]}, Value: {values[i - 1]})")
            max_profit -= values[i - 1]
            current_w -= weights[i - 1]

if __name__ == "__main__":
    values = [60, 100, 120]
    weights = [10, 20, 30]
    W = 50
    n = len(values)

    print("--- 0/1 Knapsack Problem (Dynamic Programming) ---")
    print(f"Knapsack Capacity: {W}")
    print(f"Number of items: {n}")

    start_time = time.perf_counter()

    solve_01_knapsack(values, weights, W, n)

    end_time = time.perf_counter()
    elapsed_ms = (end_time - start_time) * 1000

    print("\n--- Complexity & Execution Time ---")
    print(f"Code Execution Time: {elapsed_ms:.4f} ms")
    print("Time Complexity: O(n * W)  [Pseudo-polynomial time]")
    print("Space Complexity: O(n * W) [for 2D DP matrix]")
