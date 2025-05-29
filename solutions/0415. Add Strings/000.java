class Solution {
    public String addStrings(String num1, String num2) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            int cv = 0;
            while(true)
            {
                if(i>=num1.length() && i>= num2.length()) break;

                char x = i>=num1.length()?'0': num1.charAt(num1.length()-1-i);
                int xv = x - '0';
                char y = i>= num2.length()?'0': num2.charAt(num2.length()-1-i);
                int yv = y - '0';
                int sv = (xv+yv+cv)%10;
                cv = (xv+yv+cv)/10;
                char sc = (char)(sv + '0');
                sb.append(sc);
                i++;
            }
            if(cv!=0) sb.append('1');
            sb.reverse();
            return sb.toString();
        }
}