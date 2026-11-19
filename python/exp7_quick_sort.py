# Experiment 7: Implementation of Quick Sort
# Divide and Conquer approach using Lomuto Partitioning

import time

def partition(arr, low, high):
    pivot = arr[high]  # choose last element as pivot
    i = low - 1        # index of smaller element

    for j in range(low, high):
        # if current element is smaller than pivot
        if arr[j] < pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]

    # place pivot in correct position
    arr[i + 1], arr[high] = arr[high], arr[i + 1]
    return i + 1

def quick_sort(arr, low, high):
    if low < high:
        # pi is partition index
        pi = partition(arr, low, high)

        # sort elements before and after partition
        quick_sort(arr, low, pi - 1)
        quick_sort(arr, pi + 1, high)

if __name__ == "__main__":
    arr = [10, 80, 30, 90, 40, 50, 70]

    print("Original Array:", arr)

    start_time = time.perf_counter()

    quick_sort(arr, 0, len(arr) - 1)

    end_time = time.perf_counter()
    elapsed_ms = (end_time - start_time) * 1000

    print("Sorted Array:  ", arr)

    print("\n--- Complexity & Execution Time ---")
    print(f"Code Execution Time: {elapsed_ms:.4f} ms")
    print("Time Complexity: Best & Average Case = O(n log n), Worst Case = O(n^2)")
    print("Space Complexity: O(log n) [Call stack memory, O(n) in worst case]")
