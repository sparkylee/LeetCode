class Solution {
     int [] c = new int[256];
        public int longestPalindrome(String s) {
            for(int i=0;i<s.length();i++)
                c[s.charAt(i)%256] ++;
            int len = 0;
            boolean containOdd = false;
            for(int i =0;i<256;i++)
            {
                if(c[i]%2==0) len+=c[i];
                else
                {
                    len +=(c[i]-1);
                    containOdd = true;
                }
            }
            if(containOdd) len++;
            return len;
        }
}