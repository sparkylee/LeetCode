class Solution {
    public boolean judgeCircle(String moves) {
       int r=0,l=0,u=0,d=0;
        for(int i=0;i<moves.length();i++) {
            char x = moves.charAt(i);
            if(x=='R' || x=='r') r++;
            if(x=='L' || x=='l') l++;
            if(x=='U' || x=='u') u++;
            if(x=='D' || x=='d') d++;
        }
        return r==l && u==d;
    }
}