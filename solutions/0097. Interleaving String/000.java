class Solution {
       private int countDuplicates(String s, int index)
        {
            int count =0;
            char c =s.charAt(index);
            while(index<s.length()) {
                if(s.charAt(index)==c) count++;
                else break;
                index++;
            }
            return count;
        }
        private boolean isInterleave(String s1,int i, String s2,int j,  String s3,int k ) {
            if( k>=s3.length())
                return true;

            boolean s1Matched = (i<s1.length() && s1.charAt(i)==s3.charAt(k)),
                    s2Matched = (j<s2.length() && s2.charAt(j)==s3.charAt(k));
            if(!s1Matched && !s2Matched) return false;
            if(s1Matched && !s2Matched)
            {

                return isInterleave(s1,i+1,s2,j,s3,k+1);
            }
            if(!s1Matched)
                return isInterleave(s1,i,s2,j+1,s3,k+1);

            int count1=countDuplicates(s1,i),count2=countDuplicates(s2,j),count3=countDuplicates(s3,k),
                    leftover = count1+count2-count3;

//            if(leftover==0) return isInterleave(s1, i + count1, s2, j+count2, s3, k+count3);
            int delta_i = count1 - leftover,delta_j = count2 -leftover;
            boolean matchS1=false,matchS2=false;
            if(delta_i>=0 )
                matchS1 = isInterleave(s1, i + delta_i, s2, j+count2, s3, k+count3);
            if(matchS1) return true;
            if(leftover<=0) return false;
            if(delta_j>=0)
                return isInterleave(s1, i + count1, s2, j+delta_j, s3, k+count3);
            return false;
        }
        public boolean isInterleave(String s1, String s2, String s3) {
            if(s1.length()+s2.length()!=s3.length()) return false;
            return isInterleave(s1,0,s2,0,s3,0);
        }
}