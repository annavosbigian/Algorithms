import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MergeSort {
    private long totalInversions;

    public MergeSort() {
        totalInversions = 0;
    }

    public long mergeStart(int[] unsorted) {
        mergeSort(unsorted, 0, unsorted.length);
        return totalInversions;
    }

    private void mergeSort(int[] unsorted, int left, int right) {
        if (left < right - 1) {
            int mid = left + (right - left) / 2;
            mergeSort(unsorted, left, mid);
            mergeSort(unsorted, mid, right);
            splitAndSortElements(unsorted, left, right);
        }
    }

    private void splitAndSortElements(int[] unsorted, int left, int right) {
        int mid = (right - left) / 2;
        int[] leftHalf = new int[mid];
        for (int i = 0; i < mid; i++) {
            leftHalf[i] = unsorted[left + i];
        }
        int[] rightHalf = new int[right - left - mid];
        for (int i = 0; i < rightHalf.length; i++) {
            rightHalf[i] = unsorted[left + mid + i];
        }
        int l = 0;
        int r = 0;
        int leftLength = leftHalf.length;
        for (int i = left; i < right; i++) {
            if (r >= rightHalf.length || (l < leftHalf.length && leftHalf[l] <= rightHalf[r])) {
                unsorted[i] = leftHalf[l];
                l++;
            } else {
                totalInversions += leftLength - l;
                unsorted[i] = rightHalf[r];
                r++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("IntegerArray.txt"));
        int[] intArray = new int[100000];
        int i = 0;
        while (sc.hasNextInt()) {
            intArray[i] = sc.nextInt();
            i++;
        }
        MergeSort merge = new MergeSort();
        //int[] intArray = {7, 5, 3, 2, 1};

        long result = merge.mergeStart(intArray);
        System.out.println("Total number of inversions in file is " + result);
    }
}
