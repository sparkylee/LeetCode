class Solution {
    public boolean detectCapitalUse(String word) {
     if(word==null || word.length() <1) return false;
        if(word.length()==1) return true;
        boolean isFLUpper = (word.charAt(0) < 'a');
        boolean isSLUpper = (word.charAt(1) < 'a');
        if(!isFLUpper && isSLUpper) return false;

        for(int i=1;i<word.length();i++) {
            if(isSLUpper) // upper case
            {
                if (word.charAt(i) >= 'a') // lower case
                    return false;
            }
            else // lower case
            {
                if (word.charAt(i) < 'a') // upper case
                    return false;
            }
        }
        return true;
    }
}