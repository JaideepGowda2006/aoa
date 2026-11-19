// Experiment 1: Implementation and Analysis of Recurrence Relations using the Recursion Tree Method
// Example recurrence: T(n) = 2T(n/2) + n  (Merge Sort type)

public class Exp1_RecursionTree {

    // function to simulate and display the recursion tree
    public static void solveRecursionTree(int n, int a, int b) {
        System.out.println("--- Recursion Tree Simulation ---");
        System.out.println("Recurrence: T(n) = " + a + "T(n/" + b + ") + n");
        System.out.println("Initial problem size (n): " + n);
        System.out.println();

        int level = 0;
        double currentSize = n;
        long nodesAtLevel = 1;
        double totalCost = 0;

        // loop till problem size becomes 1 (base condition)
        while (currentSize >= 1) {
            // cost at each node is equal to its problem size
            double costPerNode = currentSize;
            double levelCost = nodesAtLevel * costPerNode;
            totalCost += levelCost;

            System.out.println("Level " + level + ": " + nodesAtLevel + " node(s), size = " 
                               + String.format("%.1f", currentSize) 
                               + ", cost at this level = " + String.format("%.1f", levelCost));

            // update for next level
            currentSize = currentSize / b;
            nodesAtLevel = nodesAtLevel * a;
            level++;
        }

        System.out.println("\nTree Height (Depth) = " + (level - 1));
        System.out.println("Total Work / Cost calculated = " + totalCost);
    }

    public static void main(String[] args) {
        // start time
        long startTime = System.nanoTime();

        // problem parameters: n=16, a=2, b=2
        int n = 16;
        int a = 2;
        int b = 2;

        solveRecursionTree(n, a, b);

        // end time
        long endTime = System.nanoTime();
        long timeTaken = endTime - startTime;

        System.out.println("\n--- Performance & Complexity ---");
        System.out.println("Time taken by code: " + timeTaken + " ns (" + (timeTaken / 1_000_000.0) + " ms)");
        System.out.println("Time Complexity: O(n log n)  [for T(n) = 2T(n/2) + n]");
        System.out.println("Space Complexity: O(log n)  [recursion stack height]");
    }
}
