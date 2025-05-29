class Solution {
    int [] dolled ;
        public int maxEnvelopes(int[][] envelopes,int current)
        {
            if(dolled[current]!=0) return dolled[current];
            int [] env_c = envelopes[current];
            int m = 0;
            for(int i=0;i<envelopes.length;i++)
            {
                if(i==current ) continue;
                int [] env_i = envelopes[i];
                if(env_i[0]>=env_c[0] || env_i[1] >= env_c[1]) continue;
                int n = maxEnvelopes(envelopes,i);
                m=Math.max(m,n);
            }
            dolled[current] =m+1;
            return dolled[current];
        }
        public int maxEnvelopes(int[][] envelopes) {
            if(envelopes.length<=1) return envelopes.length;
            dolled = new int[envelopes.length];
            for(int i=0;i<envelopes.length;i++)
                maxEnvelopes(envelopes,i);
            int m =0;
            for(int i=0;i<dolled.length;i++)
                m = Math.max(dolled[i],m);
            return m;
        }
}