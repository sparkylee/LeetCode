class Solution {
   char [] i2c = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        public String toHex(int num) {
                if(num==0) return "0";
            StringBuilder sb = new StringBuilder();
            boolean leadingZero=true;
            for(int i = 7; i >=0; i --)
            {
                int v =(num >> (i*4)) & 0xf;
                if(v!=0) leadingZero =  false;
                if(!leadingZero)
                    sb.append(i2c[v]);
            }
            return sb.toString();
        }
}