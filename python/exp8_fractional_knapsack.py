# Exp 8: Fractional Knapsack using Greedy Algorithm

import time

class Item:
    def __init__(self, item_id, value, weight):
        self.id = item_id
        self.value = value
        self.weight = weight
        self.ratio = value / weight

def fractional_knapsack(items, capacity):
    # sort by ratio descending
    items.sort(key=lambda x: x.ratio, reverse=True)

    total_profit = 0.0
    rem_capacity = capacity

    print("Selected items:")
    for item in items:
        if rem_capacity == 0:
            break

        if item.weight <= rem_capacity:
            rem_capacity -= item.weight
            total_profit += item.value
            print(f"Item {item.id}: 100% (Weight: {item.weight}, Profit: {item.value})")
        else:
            fraction = rem_capacity / item.weight
            profit = item.value * fraction
            total_profit += profit
            print(f"Item {item.id}: {fraction * 100:.1f}% (Weight: {rem_capacity}, Profit: {profit:.1f})")
            rem_capacity = 0

    return total_profit

if __name__ == "__main__":
    items = [
        Item(1, 60, 10),
        Item(2, 100, 20),
        Item(3, 120, 30)
    ]
    capacity = 50.0

    print(f"Knapsack Capacity: {capacity}")

    start_time = time.perf_counter()

    max_profit = fractional_knapsack(items, capacity)

    end_time = time.perf_counter()

    print(f"Total Max Profit: {max_profit:.2f}")
    print(f"\nExecution Time: {(end_time - start_time) * 1e9:.0f} ns")
    print("Time Complexity: O(n log n)")
    print("Space Complexity: O(1)")
