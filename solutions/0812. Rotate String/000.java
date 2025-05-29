class Solution {
    private boolean isEqual(String A, int start, String B) {
        final int len = A.length();
        for(int i=0;i<len;i++)
        {
            int j = (start+i)%len;
            if(A.charAt(j)!=B.charAt(i))
                return false;
        }
        return true;
    }
    public boolean rotateString(String A, String B) {
        if(A==null && B==null) return true;
        if((A==null && B!=null) || (A!=null && B==null)) return false;
        if(A.length()!=B.length()) return false;
        if(A.length()==0) return true;
        final int len = A.length();
        for(int i=0;i<len;i++){
            if(isEqual(A, i, B))
                return true;
        }
        return false;
    }
}