class Solution {
   public class Division {
        int div;
        int mod;
    }
    public void div7( Division d) {
        if(d==null) return;
        d.mod = d.div % 7;
        d.div = d.div / 7;
    }
    public String convertToBase7(int num) {
        Division d = new Division();
        boolean isNegative = num < 0;
        num = isNegative? -num: num;
        d.div = num;
        d.mod = 0;
        StringBuilder sb = new StringBuilder();
        do {
            div7(d);
            sb.append((char)(d.mod + '0'));
        } while (d.div!=0);
        if(isNegative) sb.append('-');
        return sb.reverse().toString();
    }
}