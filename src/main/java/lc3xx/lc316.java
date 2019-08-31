package lc3xx;

import org.junit.Test;

public  class lc316
{

    @Test
    public void test1() {
        testcase("bcabc");
        testcase("cbacdcbc");
    }

    private void testcase(String str) {
        Solution s = new Solution();
        String result = s.removeDuplicateLetters(str);
        System.out.println(result);
    }

    class Solution {
        public String removeDuplicateLetters(String s) {
            boolean [] selected  = new boolean[26];
            int [] count4Char = new int[26];
            int [] countAtPos = new int[s.length()];
            for (int i = s.length() - 1; i >= 0; i--) {
                char c = s.charAt(i);
                count4Char[c-'a']++;
                countAtPos[i]=count4Char[c-'a'];
            }
            int k=0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                while((count4Char[k]==0 || selected[k])&&k<26)
                    k++;
                if(k>=26) break;
                char c = s.charAt(i);
                if (c == k + 'a' || countAtPos[i] == 1) {
                    sb.append(c);
                    selected[c-'a'] = true;
                }
            }
            return sb.toString();
        }
    }

}
