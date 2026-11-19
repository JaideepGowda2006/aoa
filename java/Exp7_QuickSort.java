// Exp 7: Quick Sort

public class Exp7_QuickSort {

    // partition using last element as pivot
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // place pivot at correct spot
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] arr = {10, 80, 30, 90, 40, 50, 70};

        System.out.print("Given Array: ");
        for (int x : arr) System.out.print(x + " ");
        System.out.println();

        long startTime = System.nanoTime();

        quickSort(arr, 0, arr.length - 1);

        long endTime = System.nanoTime();

        System.out.print("Sorted Array: ");
        for (int x : arr) System.out.print(x + " ");
        System.out.println();

        System.out.println("\nExecution Time: " + (endTime - startTime) + " ns");
        System.out.println("Time Complexity: O(n log n) [Worst Case: O(n^2)]");
        System.out.println("Space Complexity: O(log n)");
    }
}
