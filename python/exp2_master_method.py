# Experiment 2: Implementation and Analysis of Recurrence Relations using the Master Method
# Recurrence form: T(n) = a*T(n/b) + O(n^k)

import math
import time

def solve_master_theorem(a, b, k):
    print("--- Master Method Analysis ---")
    print(f"Recurrence: T(n) = {int(a)}T(n/{int(b)}) + O(n^{int(k)})")

    # calculate log_b(a)
    log_b_a = math.log(a) / math.log(b)
    print(f"Step 1: Calculate log_b(a) = log_{int(b)}({int(a)}) = {log_b_a:.2f}")
    print(f"Step 2: Value of k = {k}")

    diff = log_b_a - k

    print("Step 3: Conclusion -> ", end="")
    if abs(diff) < 1e-4:
        # Case 2: log_b(a) == k
        print("Case 2 applies (log_b(a) == k)")
        if k == 0:
            print("Result: Time Complexity is O(log n)")
        elif k == 1:
            print("Result: Time Complexity is O(n log n)")
        else:
            print(f"Result: Time Complexity is O(n^{int(k)} log n)")
    elif diff > 0:
        # Case 1: log_b(a) > k
        print("Case 1 applies (log_b(a) > k)")
        print(f"Result: Time Complexity is O(n^{log_b_a:.2f})")
    else:
        # Case 3: log_b(a) < k
        print("Case 3 applies (log_b(a) < k)")
        print(f"Result: Time Complexity is O(n^{int(k)})")

if __name__ == "__main__":
    start_time = time.perf_counter()

    # Recurrence for Merge Sort: T(n) = 2T(n/2) + O(n^1)
    a, b, k = 2, 2, 1
    solve_master_theorem(a, b, k)

    print("\nAnother quick check:")
    # Binary Search: T(n) = 1T(n/2) + O(1)
    solve_master_theorem(1, 2, 0)

    end_time = time.perf_counter()
    elapsed_ms = (end_time - start_time) * 1000

    print("\n--- Complexity & Execution Time ---")
    print(f"Code Execution Time: {elapsed_ms:.4f} ms")
    print("Time Complexity of Solver: O(1)")
    print("Space Complexity: O(1)")
