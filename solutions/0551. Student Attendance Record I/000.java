class Solution {
    public boolean checkRecord(String s) {
         int ac = 0;
        int lc = 0;
        for(int i=0; i< s.length(); i++)
        {
            char c = s.charAt(i);
            if(c=='A') ac++;
            if(c=='L' ) {
              lc++;
            } 
            else {
                lc = 0;
            }
            if(ac>1 || lc>2) return false;
        }
        return true;
    }
}