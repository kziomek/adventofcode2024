package advent.day1;

public class MergeSort {

    public void sort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        sort(arr, 0, arr.length - 1);
    }

    public void sort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int m = l + (r - l) / 2;

        sort(arr, l, m);
        sort(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    private void merge(int[] arr, int l, int m, int r) {
        int[] a1 = new int[m - l + 1];
        int[] a2 = new int[r - m];

        System.arraycopy(arr, l, a1, 0, m - l + 1);
        System.arraycopy(arr, m + 1, a2, 0, r - m);

        int i = 0, // index of a1
            j = 0; // index of a2
        int k = l; // index of arr

        while (i < a1.length && j < a2.length) {
            if (a1[i] < a2[j]) {
                arr[k++] = a1[i++];
            } else {
                arr[k++] = a2[j++];
            }
        }

        while (i < a1.length) {
            arr[k++] = a1[i++];
        }

        while (j < a2.length) {
            arr[k++] = a2[j++];
        }
    }
}
