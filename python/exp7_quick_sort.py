# Exp 7: Quick Sort

import time

def partition(arr, low, high):
    pivot = arr[high]
    i = low - 1

    for j in range(low, high):
        if arr[j] < pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]

    arr[i + 1], arr[high] = arr[high], arr[i + 1]
    return i + 1

def quick_sort(arr, low, high):
    if low < high:
        pi = partition(arr, low, high)
        quick_sort(arr, low, pi - 1)
        quick_sort(arr, pi + 1, high)

if __name__ == "__main__":
    arr = [10, 80, 30, 90, 40, 50, 70]

    print("Given Array:", " ".join(map(str, arr)))

    start_time = time.perf_counter()

    quick_sort(arr, 0, len(arr) - 1)

    end_time = time.perf_counter()

    print("Sorted Array:", " ".join(map(str, arr)))

    print(f"\nExecution Time: {(end_time - start_time) * 1e9:.0f} ns")
    print("Time Complexity: O(n log n) [Worst Case: O(n^2)]")
    print("Space Complexity: O(log n)")
