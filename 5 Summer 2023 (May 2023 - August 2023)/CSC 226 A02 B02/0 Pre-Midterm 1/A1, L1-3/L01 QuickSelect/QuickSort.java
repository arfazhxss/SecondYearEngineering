import java.util.Arrays;

public class quicksort {
    private static int partition(int arr[], int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }

    public static void quickSort(int arr[]) {
        int begin = 0;
        int end = arr.length - 1;
        quickSortRec(arr, begin, end);
    }

    public static void quickSortRec(int arr[], int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSortRec(arr, begin, partitionIndex - 1);
            quickSortRec(arr, partitionIndex + 1, end);
        }
    }

    public static void main(String[] args) {
        int testArr[] = { 22, 0, 57, 68, 48, 70, 16, 44, 12, 24, 94, 51, 53, 21, 92, 25, 61, 32, 89, 84, 1, 65, 39, 74,
                8, 56, 42, 81, 96, 2, 88, 59, 29, 38, 30, 10, 43, 79, 54, 71, 4, 35, 11, 63, 23, 93, 90, 97, 87, 99, 7,
                73, 52, 100, 60, 58, 6, 50, 66, 67, 46, 19, 37, 26, 91, 55, 34, 77, 14, 31, 62, 28, 17, 78, 40, 47, 85,
                5, 72, 45, 3, 49, 18, 64, 83, 13, 41, 27, 76, 86, 69, 33, 75, 82, 36, 95, 9, 80, 98, 15
        };
        System.out.println("\nBefore:\t" + Arrays.toString(testArr));
        quickSort(testArr);
        System.out.println("\n\nAfter:\t" + Arrays.toString(testArr));
    }
}