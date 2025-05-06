class Solution {
    public int lengthOfLastWord(String s) {
        int last_space_pos = s.length();
        int len = 0;
        for(int i=s.length()-1;i>=-1;i--) {
            char c;
            if(i==-1 || (c=s.charAt(i)) == ' ') {
                len = (last_space_pos - i -  1);
                if(len>0)
                    return len;
                last_space_pos =  i;
            }
        }
        return len;
    }
}