class Solution {
   int [] p2;
        int count = 0;
        public int numSquares(int n,int k,int count_pre) {
            if(count_pre>=this.count) return this.count;
            if(k==1) return Math.min(n+count_pre,this.count);
            int p2value = p2[k-1];
            int div = n/p2value;
            int mod = n%p2value;
            if(mod ==0)
            {
                this.count =Math.min(div+count_pre,this.count);
                return this.count;
            }
            for(int i=div;i>=0;i--)
            {
                int residue = n - i*p2value;
                if(i + count_pre>=this.count)
                    return this.count;
                this.count = numSquares(residue,k-1,i + count_pre);
            }
            return this.count;
        }
        public int numSquares(int n) {

            int m = (int)Math.sqrt(n);
            if(m==1) return n;
            this.count = n;
            p2= new int[m];
            for(int i=0;i<m;i++)
                p2[i] = (i+1)*(i+1);
            return  numSquares(n,m,0);
        }
}