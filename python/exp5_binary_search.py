# Experiment 5: Implementation of Binary Search Algorithm

import time

def binary_search(arr, target):
    low = 0
    high = len(arr) - 1

    while low <= high:
        # calculate mid point
        mid = low + (high - low) // 2

        # if found at mid
        if arr[mid] == target:
            return mid
        # if target is smaller, look in left half
        elif arr[mid] > target:
            high = mid - 1
        # if target is larger, look in right half
        else:
            low = mid + 1

    # target not found
    return -1

if __name__ == "__main__":
    # array must be sorted
    arr = [2, 5, 8, 12, 16, 23, 38, 56, 72, 91]

    print("Input Array:", arr)
    target1 = 23
    target2 = 50

    start_time = time.perf_counter()

    res1 = binary_search(arr, target1)
    res2 = binary_search(arr, target2)

    end_time = time.perf_counter()
    elapsed_ms = (end_time - start_time) * 1000

    print(f"Searching for {target1}: {'Found at index ' + str(res1) if res1 != -1 else 'Not Found'}")
    print(f"Searching for {target2}: {'Found at index ' + str(res2) if res2 != -1 else 'Not Found'}")

    print("\n--- Complexity & Execution Time ---")
    print(f"Code Execution Time: {elapsed_ms:.4f} ms")
    print("Time Complexity: Best Case = O(1), Average/Worst Case = O(log n)")
    print("Space Complexity: O(1) [Iterative implementation]")
