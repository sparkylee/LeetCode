class Solution {
    
    int calBits(String word, int bits) {
        for(int i=0;i<word.length();i++) {
            char c = word.charAt(i);
            bits = bits | (0x1 << (c-'a'));
        }
        return bits;
    }
    public int maxProduct(String[] words) {
        int [] bitsArr = new int[words.length];
        for(int i=0;i<words.length;i++) {
            bitsArr[i] = calBits(words[i], bitsArr[i]);
        }
        int maxLen = 0;
        for(int i=0;i<words.length;i++){
            for(int j=i+1;j<words.length;j++) {
                if((bitsArr[i] | bitsArr[j]) == (bitsArr[i] ^ bitsArr[j])) {
                    int len = words[i].length() * words[j].length();
                    maxLen = Math.max(len, maxLen);
                }
                
            }
        }
        return maxLen;
    }
}