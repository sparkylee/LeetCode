class Solution {
    public int[] diStringMatch(String S) {
        int [] A = new int[S.length()+1];        
        A[0] = 0;
        int p = 0, n = 0;
        for(int i=1;i<S.length()+1;i++)
        {
            if(S.charAt(i-1)=='D') {
                n--;
                A[i] = n;
            }                
            else {
                p++;
                A[i] = p;            
            }
                
        }
             
        for(int i=0;i<S.length()+1;i++)
            A[i] = A[i] - n;
        return A;
    }
}