// Exp 2: Master Method
// Standard form: T(n) = a*T(n/b) + O(n^k)

public class Exp2_MasterMethod {

    static void solveMasterTheorem(int a, int b, double k) {
        // calculate log_b(a)
        double logVal = Math.log(a) / Math.log(b);
        System.out.printf("Recurrence: T(n) = %dT(n/%d) + O(n^%.1f)\n", a, b, k);
        System.out.printf("log_b(a) = %.2f vs k = %.2f\n", logVal, k);

        if (logVal > k) {
            System.out.printf("Case 1 applies: T(n) = O(n^%.2f)\n", logVal);
        } else if (Math.abs(logVal - k) < 0.0001) {
            System.out.printf("Case 2 applies: T(n) = O(n^%.1f * log n)\n", k);
        } else {
            System.out.printf("Case 3 applies: T(n) = O(n^%.1f)\n", k);
        }
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        // Example: Merge Sort -> T(n) = 2T(n/2) + O(n^1)
        solveMasterTheorem(2, 2, 1.0);

        long endTime = System.nanoTime();
        System.out.println("\nExecution Time: " + (endTime - startTime) + " ns");
        System.out.println("Time Complexity: O(1)");
        System.out.println("Space Complexity: O(1)");
    }
}
