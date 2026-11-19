// Experiment 11: Implementation of Longest Common Subsequence (LCS) using Dynamic Programming
// Finds the longest sequence of characters that appear in both strings in the same relative order

public class Exp11_LCS {

    public static void findLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        // dp[i][j] stores length of LCS of s1[0..i-1] and s2[0..j-1]
        int[][] dp = new int[m + 1][n + 1];

        // building DP table bottom-up
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0; // base case: empty string has LCS length 0
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // if characters match, add 1 to diagonal result
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // if characters do not match, take maximum of top or left cell
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        int lcsLength = dp[m][n];
        System.out.println("Length of Longest Common Subsequence = " + lcsLength);

        // backtracking from dp[m][n] to reconstruct the actual LCS string
        StringBuilder lcsString = new StringBuilder();
        int i = m, j = n;

        while (i > 0 && j > 0) {
            // if characters are same, this character is part of LCS
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcsString.append(s1.charAt(i - 1));
                i--;
                j--;
            } 
            // otherwise move in the direction of larger value
            else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        // since we reconstructed backwards, reverse it
        lcsString.reverse();
        System.out.println("Longest Common Subsequence: \"" + lcsString.toString() + "\"");
    }

    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        System.out.println("--- Longest Common Subsequence (LCS) ---");
        System.out.println("String 1: " + s1);
        System.out.println("String 2: " + s2);

        long startTime = System.nanoTime();

        findLCS(s1, s2);

        long endTime = System.nanoTime();
        long timeTaken = endTime - startTime;

        System.out.println("\n--- Complexity & Execution Time ---");
        System.out.println("Execution Time: " + timeTaken + " ns (" + (timeTaken / 1_000_000.0) + " ms)");
        System.out.println("Time Complexity: O(m * n)  [where m and n are string lengths]");
        System.out.println("Space Complexity: O(m * n) [for 2D DP matrix]");
    }
}
