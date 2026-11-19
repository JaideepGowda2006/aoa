# Exp 2: Master Method
# Standard form: T(n) = a*T(n/b) + O(n^k)

import math
import time

def solve_master_theorem(a, b, k):
    # calculate log_b(a)
    log_val = math.log(a) / math.log(b)
    print(f"Recurrence: T(n) = {a}T(n/{b}) + O(n^{k:.1f})")
    print(f"log_b(a) = {log_val:.2f} vs k = {k:.2f}")

    if log_val > k:
        print(f"Case 1 applies: T(n) = O(n^{log_val:.2f})")
    elif abs(log_val - k) < 0.0001:
        print(f"Case 2 applies: T(n) = O(n^{k:.1f} * log n)")
    else:
        print(f"Case 3 applies: T(n) = O(n^{k:.1f})")

if __name__ == "__main__":
    start_time = time.perf_counter()

    # Example: Merge Sort -> T(n) = 2T(n/2) + O(n^1)
    solve_master_theorem(2, 2, 1.0)

    end_time = time.perf_counter()
    print(f"\nExecution Time: {(end_time - start_time) * 1e9:.0f} ns")
    print("Time Complexity: O(1)")
    print("Space Complexity: O(1)")
