class Solution {
    public int countSegments(String s) {
           if(s==null) return 0;
            int count = 0;
            boolean inSegment = false;
            for(int i=0;i<s.length();i++)
            {
                char c = s.charAt(i);
                if(c!=' ' && c!='\t' && c!='\n' && c!='\r')
                {
                    if(!inSegment)
                    {
                        inSegment=true;
                        count++;
                    }
                }
                else
                    inSegment=false;
            }
            return count;
    }
}