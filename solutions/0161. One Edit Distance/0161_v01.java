class Solution {
    boolean isChanged = false;
    boolean isEqualString(String s,int i,  String t, int j) {
        int k=0;
        while(i+k<s.length() && j+k< t.length()) {
            char x = s.charAt(i+k);
            char y = t.charAt(j+k);
            if(x!=y) {
                return false;
            }
            k++;
        }
        return true;
    }
    boolean isInsertOneDistrance(String s,int i,  String t, int j) {
        if(i>=s.length() || j>= t.length()) {
            return true;
        }
        char x = s.charAt(i);
        char y = t.charAt(j);
        if(x==y) {
            return isInsertOneDistrance(s,i+1, t, j+1);
        }
        return isEqualString(s,i,t,j+1);
    }
    boolean isDeleteOneDistrance(String s, int i, String t, int j) {
        if(i>=s.length() || j>= t.length()) {
            return true;
        }
        char x = s.charAt(i);
        char y = t.charAt(j);
        if(x==y) {
            return isDeleteOneDistrance(s,i+1, t, j+1);
        }
        return isEqualString(s,i+1,t,j);
    }
    boolean isReplaceOneDistrance(String s, int i, String t, int j) {
        if(i>=s.length() || j>= t.length()) {
            return false;
        }
        char x = s.charAt(i);
        char y = t.charAt(j);
        if(x==y) {
            return isReplaceOneDistrance(s,i+1, t, j+1);
        }
        return isEqualString(s,i+1,t,j+1);
    }
    public boolean isOneEditDistance(String s, String t) {
        if(s.length() == t.length()) {
            return isReplaceOneDistrance(s, 0, t, 0);
        }
        if(s.length() == t.length() - 1) {
            return isInsertOneDistrance(s,0,t,0);
        }
        if(s.length() == t.length() + 1) {
            return isDeleteOneDistrance(s,0,t,0);
        }
        return false;
    }
}