class Solution {
    
    private int compressRecursive(char[] chars,int len,int start) {
            int count = 0;
            if(start>=chars.length) return len;
            int i = start;

            for(;i<chars.length;i++)
            {
                if(chars[i]==chars[start])
                    count++;
                else break;;
            }
            chars[len] = chars[start];
            len++;
            if(count>1)
            {
                char[] countChars = ("" + count).toCharArray();
                for(int j=0;j<countChars.length;j++)
                {
                    chars[len+j] = countChars[j];
                }
                len +=  countChars.length;
            }

            return compressRecursive(chars,len,i);
        }
    public int compress(char[] chars) {
        return compressRecursive(chars,0,0);
    }
}