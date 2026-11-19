# Exp 1: Recursion Tree Method
# Example: T(n) = 2T(n/2) + n

import time

def solve_recursion_tree(n, a, b):
    print(f"Recurrence: T(n) = {a}T(n/{b}) + n")
    print(f"Problem Size: {n}")

    level = 0
    current_n = n
    nodes = 1
    total_cost = 0

    # calculate cost at each level
    while current_n >= 1:
        level_cost = nodes * current_n
        total_cost += level_cost
        print(f"Level {level}: {nodes} nodes, size = {current_n}, cost = {level_cost}")

        current_n = current_n // b
        nodes = nodes * a
        level += 1

    print(f"Tree Height: {level - 1}")
    print(f"Total Cost: {total_cost}")

if __name__ == "__main__":
    start_time = time.perf_counter()

    solve_recursion_tree(16, 2, 2)

    end_time = time.perf_counter()
    print(f"\nExecution Time: {(end_time - start_time) * 1e9:.0f} ns")
    print("Time Complexity: O(n log n)")
    print("Space Complexity: O(log n)")
