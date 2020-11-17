import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class QuickSort {

    private int totalComparisonsUsingFirstInt;
    private int totalComparisonsUsingLastInt;
    private int totalComparisonsUsingMedianOfThree;

    public QuickSort() {
        this.totalComparisonsUsingFirstInt = 0;
        this.totalComparisonsUsingLastInt = 0;
        this.totalComparisonsUsingMedianOfThree = 0;
    }

    public void firstIntQuickSortImplementation(int[] unsorted, int start, int end) {
        //use the first int as the partitioner
        //move the #s so the partitioning element is in its correct place
        this.totalComparisonsUsingFirstInt += end - start - 1;
        int pivot = start;
        int currentInt = start;

        while (currentInt <= end) {
            if (unsorted[pivot] > unsorted[currentInt]) {
                //three way swap with the next in line
                int temp = unsorted[currentInt];
                unsorted[currentInt] = unsorted[pivot + 1];
                unsorted[pivot + 1] = unsorted[pivot];
                unsorted[pivot] = temp;
                pivot++;
            }
            currentInt++;
        }

        if (start < pivot) {
            firstIntQuickSortImplementation(unsorted, start, pivot - 1);
        }
        if (pivot < end) {
            firstIntQuickSortImplementation(unsorted, pivot + 1, end);
        }
    }

    public void lastIntQuickSortImplementation(int[] unsorted, int start, int end) {
        //use the first int as the partitioner
        //move the #s so the partitioning element is in its correct place
        this.totalComparisonsUsingLastInt += end - start - 1;
        int pivot = end;
        int currentInt = start;

        while (currentInt < pivot) {
            if (unsorted[pivot] < unsorted[currentInt]) {
                //three way swap with the next in line
                int temp = unsorted[currentInt];
                unsorted[currentInt] = unsorted[pivot - 1];
                unsorted[pivot - 1] = unsorted[pivot];
                unsorted[pivot] = temp;
                pivot--;
            } else {
                currentInt++;
            }
        }

        if (start < pivot) {
            lastIntQuickSortImplementation(unsorted, start, pivot - 1);
        }
        if (pivot < end) {
            lastIntQuickSortImplementation(unsorted, pivot + 1, end);
        }
    }

    public void quickSortImplementation(int[] unsorted, int start, int end) {
        //use the first int as the partitioner
        //move the #s so the partitioning element is in its correct place
        this.totalComparisonsUsingMedianOfThree += end - start - 1;
        int pivot = start;
        int median = unsorted[start + ((end - start) / 2)];
        if ((median > unsorted[start] && median < unsorted[end]) || (median < unsorted[start] && median > unsorted[end])) {
            pivot = start + ((end - start) / 2);
        } else if ((unsorted[end] > median && unsorted[end] < unsorted[start]) || (unsorted[end] < median && unsorted[end] > unsorted[start])) {
            pivot = end;
        }
        int currentInt = start;
        //compare all things to left of the pivot
        while (currentInt < pivot) {
            if (unsorted[pivot] < unsorted[currentInt]) {
                //three way swap with the next in line
                int temp = unsorted[currentInt];
                unsorted[currentInt] = unsorted[pivot - 1];
                unsorted[pivot - 1] = unsorted[pivot];
                unsorted[pivot] = temp;
                pivot--;
            } else {
                currentInt++;
            }
        }
        /*for (int i = 0; i < currentInt; i++) {
            System.out.println(unsorted[i]);
        }*/
        //compare all things to the right
        while (currentInt <= end) {
            if (unsorted[pivot] > unsorted[currentInt]) {
                //three way swap with the next in line
                int temp = unsorted[currentInt];
                unsorted[currentInt] = unsorted[pivot + 1];
                unsorted[pivot + 1] = unsorted[pivot];
                unsorted[pivot] = temp;
                pivot++;
            } else {
                currentInt++;
            }
        }
        if (start < pivot) {
            quickSortImplementation(unsorted, start, pivot - 1);
        }
        if (pivot < end) {
            quickSortImplementation(unsorted, pivot + 1, end);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("IntegerArray.txt"));
        int[] unsorted1 = new int[100000];
        int[] unsorted2 = new int[100000];
        int[] unsorted3 = new int[100000];
        int i = 0;
        while (sc.hasNextInt()) {
            int j = sc.nextInt();
            unsorted1[i] = j;
            unsorted2[i] = j;
            unsorted3[i] = j;
            i++;
        }

        QuickSort qs = new QuickSort();
        qs.firstIntQuickSortImplementation(unsorted1, 0, unsorted1.length - 1);
        qs.lastIntQuickSortImplementation(unsorted2, 0, unsorted2.length - 1);
        qs.quickSortImplementation(unsorted3, 0, unsorted3.length - 1);

        System.out.println("Total comparisons when using the first int of the array " + qs.totalComparisonsUsingFirstInt);
        System.out.println("Total comparisons when using the last int of the array " + qs.totalComparisonsUsingLastInt);
        System.out.println("Total comparisons when using the median int of the first, middle and last ints of the array " + qs.totalComparisonsUsingMedianOfThree);
    }
}
