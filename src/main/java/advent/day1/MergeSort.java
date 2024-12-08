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

        for (int i = 0; i < m - l + 1; i++) {
            a1[i] = arr[l + i];
        }
        for (int i = 0; i < r - m; i++) {
            a2[i] = arr[m + 1 + i];
        }
        System.out.println();

        int i = 0, j = 0;

        int pos = l;
        while (i < a1.length && j < a2.length) {
            if (a1[i] < a2[j]) {
                arr[pos] = a1[i];
                i++;
            } else {
                arr[pos] = a2[j];
                j++;
            }
            pos++;
        }

        while (i < a1.length) {
            arr[pos] = a1[i];
            i++;
            pos++;
        }

        while (j < a2.length) {
            arr[pos] = a2[j];
            j++;
            pos++;
        }
    }
}
