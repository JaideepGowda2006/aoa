# Exp 6: Merge Sort

import time

def merge(arr, l, m, r):
    L = arr[l:m + 1]
    R = arr[m + 1:r + 1]

    i = j = 0
    k = l

    while i < len(L) and j < len(R):
        if L[i] <= R[j]:
            arr[k] = L[i]
            i += 1
        else:
            arr[k] = R[j]
            j += 1
        k += 1

    while i < len(L):
        arr[k] = L[i]
        i += 1
        k += 1

    while j < len(R):
        arr[k] = R[j]
        j += 1
        k += 1

def merge_sort(arr, l, r):
    if l < r:
        m = (l + r) // 2
        merge_sort(arr, l, m)
        merge_sort(arr, m + 1, r)
        merge(arr, l, m, r)

if __name__ == "__main__":
    arr = [38, 27, 43, 3, 9, 82, 10]

    print("Given Array:", " ".join(map(str, arr)))

    start_time = time.perf_counter()

    merge_sort(arr, 0, len(arr) - 1)

    end_time = time.perf_counter()

    print("Sorted Array:", " ".join(map(str, arr)))

    print(f"\nExecution Time: {(end_time - start_time) * 1e9:.0f} ns")
    print("Time Complexity: O(n log n)")
    print("Space Complexity: O(n)")
