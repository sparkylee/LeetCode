class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {    
        Arrays.sort(B);
        int diff = Arrays.stream(A).sum() - Arrays.stream(B).sum();
        if (diff % 2 != 0)
            return null;
        int delta = diff / 2;
        for (int a : A) {
            int b = a - delta;
            int index = Arrays.binarySearch(B, b);
            if (index >= 0) {
                return new int[]{a, b};
            }
        }

        return null;
    }
}