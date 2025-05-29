  class Solution {
        int [] f , g;
        private int countDigitOne(int n,int len,int base) {
            if(n<=9) return n>=1?1:0;

            int d = n/base, residue = n%base;
            int count = f[len-2]*d;
            if(d>1)
                count += g[len-2];
            else if (d==1)
                count+=(residue+1);
            count += countDigitOne(residue,len-1, base/10);
            return count;
        }
        public int countDigitOne(int n) {
            int i =0,base=1;
            while(n/base>=10)
            {
                base=base*10;
                i++;
            }
            int len = i+1;
            f = new int [len];
            g=new int [len];
            g[0]=10; f[0]=1;

            for(i=1;i<len;i++)
                g[i]=10*g[i-1];
            for(i=1;i<len;i++)
                f[i]=10*f[i-1]+g[i-1];
            return countDigitOne(n,len,base);
        }
    }