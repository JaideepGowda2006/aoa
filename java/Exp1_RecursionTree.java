// Exp 1: Recursion Tree Method
// Example: T(n) = 2T(n/2) + n

public class Exp1_RecursionTree {

    static void solveRecursionTree(int n, int a, int b) {
        System.out.println("Recurrence: T(n) = " + a + "T(n/" + b + ") + n");
        System.out.println("Problem Size: " + n);

        int level = 0;
        int currentN = n;
        int nodes = 1;
        int totalCost = 0;

        // calculate cost at each level
        while (currentN >= 1) {
            int levelCost = nodes * currentN;
            totalCost += levelCost;
            System.out.println("Level " + level + ": " + nodes + " nodes, size = " + currentN + ", cost = " + levelCost);

            currentN = currentN / b;
            nodes = nodes * a;
            level++;
        }

        System.out.println("Tree Height: " + (level - 1));
        System.out.println("Total Cost: " + totalCost);
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        solveRecursionTree(16, 2, 2);

        long endTime = System.nanoTime();
        System.out.println("\nExecution Time: " + (endTime - startTime) + " ns");
        System.out.println("Time Complexity: O(n log n)");
        System.out.println("Space Complexity: O(log n)");
    }
}
