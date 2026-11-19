// Experiment 6: Implementation of Merge Sort
// Divide and Conquer approach

public class Exp6_MergeSort {

    // function to merge two sorted subarrays
    public static void merge(int[] arr, int left, int mid, int right) {
        // sizes of two subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // temporary arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // copy data to temp arrays
        for (int i = 0; i < n1; i++) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = arr[mid + 1 + j];
        }

        // initial indexes of subarrays
        int i = 0, j = 0;
        int k = left; // initial index of merged array

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // copy remaining elements of L if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // copy remaining elements of R if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // main merge sort function
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            // find middle point
            int mid = left + (right - left) / 2;

            // sort first and second halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    // helper function to print array
    public static void printArray(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};

        System.out.print("Original Array: ");
        printArray(arr);

        long startTime = System.nanoTime();

        mergeSort(arr, 0, arr.length - 1);

        long endTime = System.nanoTime();
        long timeTaken = endTime - startTime;

        System.out.print("Sorted Array:   ");
        printArray(arr);

        System.out.println("\n--- Complexity & Execution Time ---");
        System.out.println("Execution Time: " + timeTaken + " ns (" + (timeTaken / 1_000_000.0) + " ms)");
        System.out.println("Time Complexity: O(n log n) in Best, Average, and Worst cases");
        System.out.println("Space Complexity: O(n) [Auxiliary space for temporary arrays]");
    }
}
