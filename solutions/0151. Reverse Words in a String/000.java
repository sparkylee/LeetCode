public class Solution {
    public String reverseWords(String s) {
            String [] strArray = s.trim().split("\\s+" );
            StringBuffer sb =  new StringBuffer();
            for(int i=strArray.length-1;i>=0;i--)
            {
                sb.append(strArray[i]);
                if(i!=0)
                    sb.append(' ');
            }
            return sb.toString();
    }
}