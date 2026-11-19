# Exp 5: Binary Search Algorithm

import time

def binary_search(arr, target):
    low, high = 0, len(arr) - 1

    while low <= high:
        mid = (low + high) // 2

        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            low = mid + 1
        else:
            high = mid - 1

    return -1

if __name__ == "__main__":
    arr = [2, 5, 8, 12, 16, 23, 38, 56, 72, 91]

    print("Array:", " ".join(map(str, arr)))

    start_time = time.perf_counter()

    res1 = binary_search(arr, 23)
    res2 = binary_search(arr, 50)

    end_time = time.perf_counter()

    print("Search 23:", f"Found at index {res1}" if res1 != -1 else "Not Found")
    print("Search 50:", f"Found at index {res2}" if res2 != -1 else "Not Found")

    print(f"\nExecution Time: {(end_time - start_time) * 1e9:.0f} ns")
    print("Time Complexity: O(log n)")
    print("Space Complexity: O(1)")
