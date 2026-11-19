// Experiment 8: Implementation of Fractional Knapsack using Greedy Algorithm
// Items can be broken down into fractions to maximize profit

import java.util.Arrays;
import java.util.Comparator;

public class Exp8_FractionalKnapsack {

    // class representing each item
    static class Item {
        int id;
        double weight;
        double value;
        double ratio; // value per unit weight

        public Item(int id, double value, double weight) {
            this.id = id;
            this.value = value;
            this.weight = weight;
            this.ratio = value / weight;
        }
    }

    public static double getMaxValue(Item[] items, double capacity) {
        // sort items by ratio in descending order
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item a, Item b) {
                return Double.compare(b.ratio, a.ratio);
            }
        });

        double currentWeight = 0;
        double totalProfit = 0;

        System.out.println("Items selected:");
        for (Item item : items) {
            // if full item can fit
            if (currentWeight + item.weight <= capacity) {
                currentWeight += item.weight;
                totalProfit += item.value;
                System.out.println("- Item " + item.id + ": took 100% (Weight: " + item.weight + ", Value: " + item.value + ")");
            } 
            // if only a fraction of item can fit
            else {
                double remaining = capacity - currentWeight;
                if (remaining > 0) {
                    double fraction = remaining / item.weight;
                    totalProfit += item.value * fraction;
                    currentWeight += remaining;
                    System.out.println("- Item " + item.id + ": took " + String.format("%.2f", fraction * 100) 
                                       + "% (Weight: " + remaining + ", Value: " + String.format("%.2f", item.value * fraction) + ")");
                }
                break; // knapsack is now completely full
            }
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        // sample items: {Item(id, value, weight)}
        Item[] items = {
            new Item(1, 60, 10),
            new Item(2, 100, 20),
            new Item(3, 120, 30)
        };

        double capacity = 50;

        System.out.println("--- Fractional Knapsack ---");
        System.out.println("Knapsack Capacity: " + capacity);

        long startTime = System.nanoTime();

        double maxProfit = getMaxValue(items, capacity);

        long endTime = System.nanoTime();
        long timeTaken = endTime - startTime;

        System.out.println("\nMaximum Profit Earned = " + maxProfit);

        System.out.println("\n--- Complexity & Execution Time ---");
        System.out.println("Execution Time: " + timeTaken + " ns (" + (timeTaken / 1_000_000.0) + " ms)");
        System.out.println("Time Complexity: O(n log n) [dominated by sorting step]");
        System.out.println("Space Complexity: O(1) or O(n) [depending on sorting algorithm used]");
    }
}
