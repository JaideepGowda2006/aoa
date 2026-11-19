# Exp 11: Longest Common Subsequence (LCS) using Dynamic Programming

import time

def find_lcs(s1, s2):
    m, n = len(s1), len(s2)
    dp = [[0] * (n + 1) for _ in range(m + 1)]

    # build dp table
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if s1[i - 1] == s2[j - 1]:
                dp[i][j] = 1 + dp[i - 1][j - 1]
            else:
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])

    print(f"LCS Length: {dp[m][n]}")

    # backtrack to find string
    lcs = []
    i, j = m, n
    while i > 0 and j > 0:
        if s1[i - 1] == s2[j - 1]:
            lcs.append(s1[i - 1])
            i -= 1
            j -= 1
        elif dp[i - 1][j] > dp[i][j - 1]:
            i -= 1
        else:
            j -= 1

    print(f"LCS String: {''.join(reversed(lcs))}")

if __name__ == "__main__":
    s1 = "AGGTAB"
    s2 = "GXTXAYB"

    print(f"String 1: {s1}")
    print(f"String 2: {s2}")

    start_time = time.perf_counter()

    find_lcs(s1, s2)

    end_time = time.perf_counter()
    print(f"\nExecution Time: {(end_time - start_time) * 1e9:.0f} ns")
    print("Time Complexity: O(m * n)")
    print("Space Complexity: O(m * n)")
