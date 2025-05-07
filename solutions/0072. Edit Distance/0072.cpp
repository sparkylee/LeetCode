class Solution {
     public int minDistance(String word1,int i, String word2,int j,int [][] ops) {
            if(i>=word1.length() && j>=word2.length()) return 0;
            if(i>=word1.length()) return word2.length() - j;
            if(j>=word2.length()) return word1.length() - i;
            int op = ops[j][i];
            if(op>=0) return op;

            op = minDistance(word1, i + 1, word2, j + 1, ops);
            if(word1.charAt(i)!=word2.charAt(j))
            {
                op++;
                int count1 = 1+minDistance(word1,i+1,word2,j,ops);
                op = op<count1?op:count1;
                int count2 = 1+minDistance(word1,i,word2,j+1,ops);
                op = op<count2?op:count2;
            }
            ops[j][i]=op;
            return op;
        }
        public int minDistance(String word1, String word2) {
            int [][] ops=new int[word2.length()][word1.length()];
            for(int i=0;i<word1.length();i++)
                for(int j=0;j<word2.length();j++)
                    ops[j][i]=-1;

            return minDistance(word1,0,word2,0,ops);
        }
}