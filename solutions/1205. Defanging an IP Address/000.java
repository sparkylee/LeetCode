class Solution {
    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<address.length();i++)
        {
            char c = address.charAt(i);
            if(c!='.')
                sb.append(c);
            else    
                sb.append("[.]");    
        }
        return sb.toString();
    }
}