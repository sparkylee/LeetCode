class Solution {
    public String largestTimeFromDigits(int[] A) {
        Arrays.sort(A);
        boolean[] B = {false, false, false, false};
        for (int i = 3; i >= 0; i--) { // select Xh:mm
            if (A[i] >= 3) continue;
            B[i] = true;
            for (int j = 3; j >= 0; j--) { // select hX:mm
                if (B[j] || (A[i] == 2 && A[j] >= 4)) continue;
                B[j] = true;
                for (int k = 3; k >= 0; k--) { // select hh:Xm
                    if (B[k] || A[k] >= 6) continue;
                    B[k] = true;
                    for (int l = 3; l >= 0; l--) { // select hh:mX
                        if (!B[l])
                            return "" + A[i] + A[j] + ':' + A[k] + A[l];
                    }
                }
                B[j] = false;
            }
            B[i] = false;
        }
        return "";
    }
}