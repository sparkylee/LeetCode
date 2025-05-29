class Solution {
    public int lengthOfLastWord(String s) {
        int last_space_pos = -1;
        int len = 0;
        for(int i=0;i<=s.length();i++) {
            char c;
            if(i==s.length() || (c=s.charAt(i)) == ' ') {
                int len_new = i - last_space_pos - 1;
                if(len_new!=0)
                    len = len_new;
                last_space_pos = i;
            }
        }
        return len;
    }
}