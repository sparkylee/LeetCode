class Solution {
    String [] c2s = {".-","-...","-.-.","-..",".","..-.","--.",
            "....","..",".---","-.-",".-..",
            "--","-.","---",".--.","--.-",".-.",
            "...","-","..-","...-",".--","-..-","-.--","--.."};
    private String c2sFunc(String word) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<word.length();i++) {
            sb.append(c2s[word.charAt(i)-'a']);
        }
        return sb.toString();
    }
    public int uniqueMorseRepresentations(String[] words) {

        Set<String> set = new HashSet<>();
        for(String w: words)
        {
            String snew = c2sFunc(w);
            set.add(snew);
        }
        return set.size();
    }
}