// Experiment 5: Implementation of Binary Search Algorithm

public class Exp5_BinarySearch {

    // iterative binary search function
    public static int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            // find mid to avoid overflow
            int mid = low + (high - low) / 2;

            // if element found at mid
            if (arr[mid] == target) {
                return mid;
            }

            // if target is smaller, search in left half
            if (arr[mid] > target) {
                high = mid - 1;
            } 
            // if target is greater, search in right half
            else {
                low = mid + 1;
            }
        }

        // target not present in array
        return -1;
    }

    public static void main(String[] args) {
        // array MUST be sorted for binary search
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};

        System.out.print("Input Array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        int target1 = 23;
        int target2 = 50;

        long startTime = System.nanoTime();

        int result1 = binarySearch(arr, target1);
        int result2 = binarySearch(arr, target2);

        long endTime = System.nanoTime();
        long timeTaken = endTime - startTime;

        System.out.println("Searching for " + target1 + ": " + (result1 != -1 ? "Found at index " + result1 : "Not Found"));
        System.out.println("Searching for " + target2 + ": " + (result2 != -1 ? "Found at index " + result2 : "Not Found"));

        System.out.println("\n--- Complexity & Execution Time ---");
        System.out.println("Execution Time: " + timeTaken + " ns (" + (timeTaken / 1_000_000.0) + " ms)");
        System.out.println("Time Complexity: Best Case = O(1), Average/Worst Case = O(log n)");
        System.out.println("Space Complexity: O(1) [Iterative implementation]");
    }
}
