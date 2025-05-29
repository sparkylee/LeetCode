class Solution {
    public boolean validWordSquare(List<String> words) {
        int maxLen = words.size();
        for(int i=0;i<words.size();i++) {
            String word = words.get(i);
            if (maxLen < word.length()){
                maxLen = word.length();
            }
        }
        int listLen = words.size();
        for(int i=0;i<maxLen;i++) {
            for(int j=i+1; j < maxLen;j++) {
                if(listLen <= i && listLen <= j)
                {
                    continue;
                }
                if(listLen <= i || listLen <= j)
                {
                    return false;
                }
                String words_i = words.get(i);
                String words_j =  words.get(j);
                if (words_i.length() <= j && words_j.length() <= i) {
                    continue;
                }
                if(words_i.length() <= j || words_j.length() <= i) {
                    return false;
                }
                char x = words.get(i).charAt(j);
                char y = words.get(j).charAt(i);
                if (x!=y) {
                    return false;
                }       
            }
        }
        return true;
    }
}