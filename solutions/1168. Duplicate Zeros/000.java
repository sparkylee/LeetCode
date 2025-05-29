class Solution {
 
    private void shiftValue(int[] arr, int i, int shift) {
        int j = i + shift;
        if (j >= 0 && j < arr.length)
            arr[j] = arr[i];
    }

    public void duplicateZeros(int[] arr) {
        int shift = 0;
        int i = 0;
        for (; i < arr.length - 1; i++) {
            if (arr[i] == 0)
                shift++;
        }
        i = arr.length - 2;
        for (; i >= 0; i--) {
            shiftValue(arr, i, shift);
            if (arr[i] == 0) {
                shift--;
                shiftValue(arr, i, shift);
            }
        }

    }
}