# Experiment 8: Implementation of Fractional Knapsack using Greedy Algorithm
# Items can be divided into fractions to maximize profit

import time

class Item:
    def __init__(self, item_id, value, weight):
        self.id = item_id
        self.value = value
        self.weight = weight
        self.ratio = value / weight  # profit per unit weight

def fractional_knapsack(items, capacity):
    # sort items by ratio in descending order
    items.sort(key=lambda x: x.ratio, reverse=True)

    current_weight = 0.0
    total_profit = 0.0

    print("Items selected:")
    for item in items:
        # if entire item can be taken
        if current_weight + item.weight <= capacity:
            current_weight += item.weight
            total_profit += item.value
            print(f"- Item {item.id}: took 100% (Weight: {item.weight}, Value: {item.value})")
        # take fraction of item
        else:
            remaining = capacity - current_weight
            if remaining > 0:
                fraction = remaining / item.weight
                profit_gained = item.value * fraction
                total_profit += profit_gained
                current_weight += remaining
                print(f"- Item {item.id}: took {fraction * 100:.2f}% (Weight: {remaining}, Value: {profit_gained:.2f})")
            break  # knapsack is full

    return total_profit

if __name__ == "__main__":
    items = [
        Item(1, 60, 10),
        Item(2, 100, 20),
        Item(3, 120, 30)
    ]
    capacity = 50.0

    print("--- Fractional Knapsack ---")
    print(f"Knapsack Capacity: {capacity}")

    start_time = time.perf_counter()

    max_profit = fractional_knapsack(items, capacity)

    end_time = time.perf_counter()
    elapsed_ms = (end_time - start_time) * 1000

    print(f"\nMaximum Profit Earned = {max_profit:.2f}")

    print("\n--- Complexity & Execution Time ---")
    print(f"Code Execution Time: {elapsed_ms:.4f} ms")
    print("Time Complexity: O(n log n) [due to sorting by value/weight ratio]")
    print("Space Complexity: O(1) or O(n) [depending on sort implementation]")
