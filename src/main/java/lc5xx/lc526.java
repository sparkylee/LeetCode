package lc5xx;

public class lc526 {
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private boolean isFitted(int[] arr, int i) {
        return (i + 1) % arr[i] == 0 || arr[i] % (i + 1) == 0;
    }

    private int countArrangement(int[] arr, int start) {
        if (start >= arr.length) return 1;
        int count4level = 0;
        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i);
            boolean first = isFitted(arr, start);
            boolean second = isFitted(arr, i);
            if (first && second) {
                count4level += countArrangement(arr, start + 1);
            }
            swap(arr, start, i);
        }
        return count4level;
    }

    public int countArrangement(int N) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }
        int count = countArrangement(arr, 0);
        return count;
    }
}
