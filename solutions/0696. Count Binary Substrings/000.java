class Solution {
    public int countBinarySubstrings(String s) {
        int k=0;
        int count = 0;
        while (k<s.length())
        {
            while (k+1<s.length() && s.charAt(k)==s.charAt(k+1))
                k++;
            int i=0;
            while ((k-i)>=0 && (k+1+i)<s.length()
                    && s.charAt(k-i)==s.charAt(k)
                    && s.charAt(k+1+i) == s.charAt(k+1) ){
                i++;
                count ++;
            }
            k++;
        }
        return count;
    }
}