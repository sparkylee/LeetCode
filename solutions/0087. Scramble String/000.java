class Solution {
     private int sumString(String s,int start,int end)
        {
            int sum=0;
            for(int i=start;i<=end;i++)
                sum+=s.charAt(i);
            return sum;
        }
        private int xorString(String s,int start,int end)
        {
            int xor=0;
            for(int i=start;i<=end;i++)
                xor^=s.charAt(i);
            return xor;
        }
        private boolean isScramble(String s1,int start1,int end1, String s2,int start2,int end2) {

            int len1=end1-start1+1,len2=end2-start2+1;
            if(len1<=0 || len2 <=0) return false;
            if(sumString(s1,start1,end1)!=sumString(s2,start2,end2)
                    || xorString(s1,start1,end1)!=xorString(s2,start2,end2) || len1!=len2)
                return false;
            if(len1==1) return true;
            for(int i = 0; i< len1-1; i++)
            {
                if(isScramble(s1,start1,start1+i,s2,start2,start2+i)
                        && isScramble(s1,start1+i+1,end1,s2,start2+i+1,end2)) return true;
                if(isScramble(s1,start1,start1+i,s2,end2-i,end2)
                        && isScramble(s1,start1+i+1,end1,s2,start2,end2-i-1)) return true;
            }
            return  false;
        }
        public boolean isScramble(String s1, String s2) {
            return isScramble(s1,0,s1.length()-1,s2,0,s2.length()-1);
        }
}