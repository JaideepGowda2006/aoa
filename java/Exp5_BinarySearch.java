// Exp 5: Binary Search Algorithm

public class Exp5_BinarySearch {

    static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) return mid;
            if (arr[mid] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1; // not found
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};

        System.out.print("Array: ");
        for (int x : arr) System.out.print(x + " ");
        System.out.println();

        long startTime = System.nanoTime();

        int res1 = binarySearch(arr, 23);
        int res2 = binarySearch(arr, 50);

        long endTime = System.nanoTime();

        System.out.println("Search 23: " + (res1 != -1 ? "Found at index " + res1 : "Not Found"));
        System.out.println("Search 50: " + (res2 != -1 ? "Found at index " + res2 : "Not Found"));

        System.out.println("\nExecution Time: " + (endTime - startTime) + " ns");
        System.out.println("Time Complexity: O(log n)");
        System.out.println("Space Complexity: O(1)");
    }
}
