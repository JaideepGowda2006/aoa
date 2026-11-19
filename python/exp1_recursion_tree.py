# Experiment 1: Implementation and Analysis of Recurrence Relations using the Recursion Tree Method
# Example recurrence: T(n) = 2T(n/2) + n  (Merge Sort type)

import time

def solve_recursion_tree(n, a, b):
    print("--- Recursion Tree Simulation ---")
    print(f"Recurrence: T(n) = {a}T(n/{b}) + n")
    print(f"Initial problem size (n): {n}\n")

    level = 0
    current_size = float(n)
    nodes_at_level = 1
    total_cost = 0.0

    # loop until problem size reduces to 1 (base case)
    while current_size >= 1.0:
        cost_per_node = current_size
        level_cost = nodes_at_level * cost_per_node
        total_cost += level_cost

        print(f"Level {level}: {nodes_at_level} node(s), size = {current_size:.1f}, cost at this level = {level_cost:.1f}")

        # update for next level
        current_size = current_size / b
        nodes_at_level = nodes_at_level * a
        level += 1

    print(f"\nTree Height (Depth) = {level - 1}")
    print(f"Total Work / Cost calculated = {total_cost}")

if __name__ == "__main__":
    start_time = time.perf_counter()

    # n = 16, a = 2, b = 2
    n, a, b = 16, 2, 2
    solve_recursion_tree(n, a, b)

    end_time = time.perf_counter()
    elapsed_ms = (end_time - start_time) * 1000

    print("\n--- Performance & Complexity ---")
    print(f"Code Execution Time: {elapsed_ms:.4f} ms")
    print("Time Complexity: O(n log n)  [for T(n) = 2T(n/2) + n]")
    print("Space Complexity: O(log n)  [recursion stack height]")
