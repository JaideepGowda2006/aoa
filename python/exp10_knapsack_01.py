# Exp 10: 0/1 Knapsack Problem using Dynamic Programming

import time

def knapsack(wt, val, W, n):
    dp = [[0] * (W + 1) for _ in range(n + 1)]

    # fill dp table
    for i in range(1, n + 1):
        for w in range(1, W + 1):
            if wt[i - 1] <= w:
                dp[i][w] = max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w])
            else:
                dp[i][w] = dp[i - 1][w]

    # backtrack for selected items
    res = dp[n][W]
    w = W
    print("Selected items:")
    for i in range(n, 0, -1):
        if res != dp[i - 1][w]:
            print(f"Item {i} (Weight: {wt[i - 1]}, Value: {val[i - 1]})")
            res -= val[i - 1]
            w -= wt[i - 1]

    return dp[n][W]

if __name__ == "__main__":
    val = [60, 100, 120]
    wt = [10, 20, 30]
    W = 50

    print(f"Knapsack Capacity: {W}")

    start_time = time.perf_counter()

    max_val = knapsack(wt, val, W, len(val))

    end_time = time.perf_counter()

    print(f"Maximum Profit: {max_val}")
    print(f"\nExecution Time: {(end_time - start_time) * 1e9:.0f} ns")
    print("Time Complexity: O(n * W)")
    print("Space Complexity: O(n * W)")
