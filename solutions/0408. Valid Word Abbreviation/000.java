class Solution {
    boolean validWordAbbreviation(String word, int i, String abbr, int j) {
        if(i==word.length() && j==abbr.length()) {
            return true;
        }
        if(i>=word.length() ||  j >= abbr.length()) {
            return false;
        }
 
        char w = word.charAt(i);
        char a = abbr.charAt(j);
        if(!Character.isDigit(a)){
            if( w==a)
                return validWordAbbreviation(word,i+1,abbr,j+1);
            return false;
        }
        if(a=='0') {
            return false;
        }
        int k = j;
        while(k< abbr.length() && Character.isDigit(abbr.charAt(k))) {
                k++;
        }
        String num_str = abbr.substring(j,k);
        int num  = Integer.parseInt(num_str);
        i += num;
        return validWordAbbreviation(word,i,abbr,k);
    }
    public boolean validWordAbbreviation(String word, String abbr) {
        return validWordAbbreviation(word, 0, abbr, 0);
    }
}