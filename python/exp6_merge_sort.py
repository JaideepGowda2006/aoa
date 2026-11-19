# Experiment 6: Implementation of Merge Sort
# Divide and Conquer approach

import time

def merge(arr, left, mid, right):
    # temporary copies of left and right subarrays
    L = arr[left:mid + 1]
    R = arr[mid + 1:right + 1]

    i = 0  # index for L
    j = 0  # index for R
    k = left  # index for merged array

    while i < len(L) and j < len(R):
        if L[i] <= R[j]:
            arr[k] = L[i]
            i += 1
        else:
            arr[k] = R[j]
            j += 1
        k += 1

    # copy remaining elements of L
    while i < len(L):
        arr[k] = L[i]
        i += 1
        k += 1

    # copy remaining elements of R
    while j < len(R):
        arr[k] = R[j]
        j += 1
        k += 1

def merge_sort(arr, left, right):
    if left < right:
        mid = left + (right - left) // 2

        # sort left and right halves
        merge_sort(arr, left, mid)
        merge_sort(arr, mid + 1, right)

        # merge sorted halves
        merge(arr, left, mid, right)

if __name__ == "__main__":
    arr = [38, 27, 43, 3, 9, 82, 10]

    print("Original Array:", arr)

    start_time = time.perf_counter()

    merge_sort(arr, 0, len(arr) - 1)

    end_time = time.perf_counter()
    elapsed_ms = (end_time - start_time) * 1000

    print("Sorted Array:  ", arr)

    print("\n--- Complexity & Execution Time ---")
    print(f"Code Execution Time: {elapsed_ms:.4f} ms")
    print("Time Complexity: O(n log n) in Best, Average, and Worst cases")
    print("Space Complexity: O(n) [Auxiliary space for subarray copies]")
