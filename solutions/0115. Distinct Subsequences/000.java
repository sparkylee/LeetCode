class Solution {
     int [] [] matrix ;

        private int numDistinct(String s,int i, String t,int j) {

            if(j>=t.length())
            {

                return 1;
            }

            if(i>=s.length())
                return 0;
            int count = matrix[j][i];
            if(count>=0) {

                return count;
            }
            count = 0;
            if(s.charAt(i)==t.charAt(j))
            {
                count  += numDistinct(s,i+1,t,j+1);
            }

            count += numDistinct(s,i+1,t,j);
            matrix[j][i]=count;

            return count;
        }
        public int numDistinct(String s, String t) {
            matrix = new int[t.length()][s.length()];
            for(int i=0;i<s.length();i++)
            {
                for(int j=0;j<t.length();j++)
                    matrix[j][i]=-1;
            }
            return numDistinct(s,0,t,0);
        }
}