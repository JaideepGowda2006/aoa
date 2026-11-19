// Experiment 2: Implementation and Analysis of Recurrence Relations using the Master Method
// Standard form: T(n) = a*T(n/b) + O(n^k)

public class Exp2_MasterMethod {

    // function to apply master theorem
    public static void solveMasterTheorem(double a, double b, double k) {
        System.out.println("--- Master Method Analysis ---");
        System.out.println("Recurrence: T(n) = " + (int)a + "T(n/" + (int)b + ") + O(n^" + (int)k + ")");

        // calculate log_b(a) using natural log: log(a) / log(b)
        double log_b_a = Math.log(a) / Math.log(b);
        System.out.println("Step 1: Calculate log_b(a) = log_" + (int)b + "(" + (int)a + ") = " + String.format("%.2f", log_b_a));
        System.out.println("Step 2: Value of k = " + k);

        // compare log_b_a with k
        double diff = log_b_a - k;

        System.out.print("Step 3: Conclusion -> ");
        if (Math.abs(diff) < 0.0001) {
            // Case 2: log_b(a) == k
            System.out.println("Case 2 applies (log_b(a) == k)");
            if (k == 0) {
                System.out.println("Result: Time Complexity is O(log n)");
            } else if (k == 1) {
                System.out.println("Result: Time Complexity is O(n log n)");
            } else {
                System.out.println("Result: Time Complexity is O(n^" + (int)k + " log n)");
            }
        } else if (diff > 0) {
            // Case 1: log_b(a) > k
            System.out.println("Case 1 applies (log_b(a) > k)");
            System.out.println("Result: Time Complexity is O(n^" + String.format("%.2f", log_b_a) + ")");
        } else {
            // Case 3: log_b(a) < k
            System.out.println("Case 3 applies (log_b(a) < k)");
            System.out.println("Result: Time Complexity is O(n^" + (int)k + ")");
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        // Example: Merge sort recurrence T(n) = 2T(n/2) + O(n^1)
        // a = 2, b = 2, k = 1
        double a = 2;
        double b = 2;
        double k = 1;

        solveMasterTheorem(a, b, k);

        System.out.println("\nAnother quick check:");
        // Binary search recurrence: T(n) = 1T(n/2) + O(1)
        solveMasterTheorem(1, 2, 0);

        long endTime = System.nanoTime();
        long timeTaken = endTime - startTime;

        System.out.println("\n--- Complexity & Execution Time ---");
        System.out.println("Code Execution Time: " + timeTaken + " ns (" + (timeTaken / 1_000_000.0) + " ms)");
        System.out.println("Time Complexity of Master Theorem Solver: O(1)");
        System.out.println("Space Complexity: O(1)");
    }
}
