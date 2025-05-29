class Solution {    
    public boolean isMonotonic(int[] A) {
        if(A.length==1) return true;
        int i=1;
        while(i<A.length && A[i]==A[i-1])
            i++;
        if(i>=A.length) return true;
        boolean isIncrease = A[i]>A[i-1];
        while(i<A.length)
        {
            if( isIncrease && A[i] < A[i-1])
                return false;
            if( !isIncrease && A[i] > A[i-1])
                return false;
            i++;
        }
        return true;
    }
}