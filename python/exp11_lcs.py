# Experiment 11: Implementation of Longest Common Subsequence (LCS) using Dynamic Programming
# Finds the longest sequence appearing in both strings in the same order

import time

def find_lcs(s1, s2):
    m = len(s1)
    n = len(s2)

    # dp table of size (m + 1) x (n + 1)
    dp = [[0] * (n + 1) for _ in range(m + 1)]

    # build table bottom up
    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if s1[i - 1] == s2[j - 1]:
                # if characters match, diagonal + 1
                dp[i][j] = 1 + dp[i - 1][j - 1]
            else:
                # else maximum of top or left
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1])

    lcs_length = dp[m][n]
    print(f"Length of Longest Common Subsequence = {lcs_length}")

    # backtracking to reconstruct the LCS string
    lcs_chars = []
    i, j = m, n
    while i > 0 and j > 0:
        if s1[i - 1] == s2[j - 1]:
            lcs_chars.append(s1[i - 1])
            i -= 1
            j -= 1
        elif dp[i - 1][j] > dp[i][j - 1]:
            i -= 1
        else:
            j -= 1

    lcs_string = "".join(reversed(lcs_chars))
    print(f"Longest Common Subsequence: \"{lcs_string}\"")

if __name__ == "__main__":
    s1 = "AGGTAB"
    s2 = "GXTXAYB"

    print("--- Longest Common Subsequence (LCS) ---")
    print(f"String 1: {s1}")
    print(f"String 2: {s2}")

    start_time = time.perf_counter()

    find_lcs(s1, s2)

    end_time = time.perf_counter()
    elapsed_ms = (end_time - start_time) * 1000

    print("\n--- Complexity & Execution Time ---")
    print(f"Code Execution Time: {elapsed_ms:.4f} ms")
    print("Time Complexity: O(m * n)  [where m and n are string lengths]")
    print("Space Complexity: O(m * n) [for 2D DP matrix]")
