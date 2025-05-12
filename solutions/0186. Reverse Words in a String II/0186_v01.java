class Solution {
    void shiftOneCharForward(char[] s, int start, int end) {
        for(int i=end; i>start;i--) {
            s[i] = s[i-1];
        }
    }
    int exchangeOneWord(char[] s, int [] range) {
        int start = range[0];
        int end   = range[1];
        if(start>=end) {
            return 0;
        }
        int si = end;
        while(true) {
            char c = s[si];
            if(c==' ') {
                break;
            }
            if(si<=start) {
                return 0;
            }
            si--;
        }

        int count = end - si + 1;
        shiftOneCharForward(s,start,si);
        s[start] = ' ';
        si++;
        char x ;
        for(int j=si;j<=end;j++) {
            x = s[j];
            shiftOneCharForward(s, start,j);
            s[start] = x;
            start++;
        }
        range[0] = start + 1;
        return count;
    }
    public void reverseWords(char[] s) {
        int word_len = 0;
        int [] range = {0, s.length-1};
        do {
            word_len = exchangeOneWord(s, range);
            // System.out.println(s);
            // System.out.println("word_len="+word_len);

        }while( word_len>0);

    }
}