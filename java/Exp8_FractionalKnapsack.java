// Exp 8: Fractional Knapsack using Greedy Algorithm

import java.util.Arrays;

public class Exp8_FractionalKnapsack {

    static class Item {
        int id;
        double weight, value, ratio;

        Item(int id, double value, double weight) {
            this.id = id;
            this.value = value;
            this.weight = weight;
            this.ratio = value / weight;
        }
    }

    static double fractionalKnapsack(Item[] items, double capacity) {
        // sort by ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalProfit = 0;
        double remCapacity = capacity;

        System.out.println("Selected items:");
        for (Item item : items) {
            if (remCapacity == 0) break;

            if (item.weight <= remCapacity) {
                // take whole item
                remCapacity -= item.weight;
                totalProfit += item.value;
                System.out.printf("Item %d: 100%% (Weight: %.1f, Profit: %.1f)\n", item.id, item.weight, item.value);
            } else {
                // take fraction
                double fraction = remCapacity / item.weight;
                totalProfit += item.value * fraction;
                System.out.printf("Item %d: %.1f%% (Weight: %.1f, Profit: %.1f)\n", item.id, fraction * 100, remCapacity, item.value * fraction);
                remCapacity = 0;
            }
        }
        return totalProfit;
    }

    public static void main(String[] args) {
        Item[] items = {
            new Item(1, 60, 10),
            new Item(2, 100, 20),
            new Item(3, 120, 30)
        };
        double capacity = 50;

        System.out.println("Knapsack Capacity: " + capacity);

        long startTime = System.nanoTime();

        double maxProfit = fractionalKnapsack(items, capacity);

        long endTime = System.nanoTime();

        System.out.printf("Total Max Profit: %.2f\n", maxProfit);

        System.out.println("\nExecution Time: " + (endTime - startTime) + " ns");
        System.out.println("Time Complexity: O(n log n)");
        System.out.println("Space Complexity: O(1)");
    }
}
