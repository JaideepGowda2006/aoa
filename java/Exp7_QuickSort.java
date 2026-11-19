// Experiment 7: Implementation of Quick Sort
// Divide and Conquer using partitioning

public class Exp7_QuickSort {

    // helper function to swap two elements in array
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // partition function (using last element as pivot)
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // choose last element as pivot
        int i = low - 1;       // index of smaller element

        for (int j = low; j < high; j++) {
            // if current element is smaller than pivot
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        // place pivot at its correct sorted position
        swap(arr, i + 1, high);
        return i + 1;
    }

    // quick sort recursive function
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // pi is partitioning index
            int pi = partition(arr, low, high);

            // recursively sort left and right subarrays
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // print array helper
    public static void printArray(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {10, 80, 30, 90, 40, 50, 70};

        System.out.print("Original Array: ");
        printArray(arr);

        long startTime = System.nanoTime();

        quickSort(arr, 0, arr.length - 1);

        long endTime = System.nanoTime();
        long timeTaken = endTime - startTime;

        System.out.print("Sorted Array:   ");
        printArray(arr);

        System.out.println("\n--- Complexity & Execution Time ---");
        System.out.println("Execution Time: " + timeTaken + " ns (" + (timeTaken / 1_000_000.0) + " ms)");
        System.out.println("Time Complexity: Best & Average Case = O(n log n), Worst Case = O(n^2)");
        System.out.println("Space Complexity: O(log n) [Call stack memory, O(n) in worst case]");
    }
}
