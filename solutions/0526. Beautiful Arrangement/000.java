class Solution {
     private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private boolean isFitted(int x, int y) {
        return (y % x == 0) || (x % y == 0);
    }

    private int countArrangement(int[] arr, int start) {
        if (start >= arr.length) return 1;
        int count4level = 0;
        for (int i = start; i < arr.length; i++) {
            if (!isFitted(arr[i], start + 1)) continue;
            swap(arr, start, i);
            count4level += countArrangement(arr, start + 1);
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