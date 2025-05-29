class Solution {
    int checkNextValidSerialization(String preorder,int start) {
        if(start>=preorder.length())
            return start;
        char c;
        c = preorder.charAt(start);
        if(c=='#')
        {                 
            return start + 2;
        }
        while(true) {
              c = preorder.charAt(start);
              if(c==',') {
                start ++;
                break;
              }                             
              start ++;      
              if(start>=preorder.length())
                break;    
        }
        start = checkNextValidSerialization(preorder, start);
        if(start==-1 || start>=preorder.length())
            return -1;
        start = checkNextValidSerialization(preorder, start);        
        return start;
    }
    public boolean isValidSerialization(String preorder) {
        int next_pos = checkNextValidSerialization(preorder,0);
        return next_pos>=preorder.length();
    }
}