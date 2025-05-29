class Solution {
     public boolean checkPerfectNumber(int num) {
        if(num<=1) return false;
        int sum = 1;
        int ceiling  = (int) Math.floor(Math.sqrt(num));
        for(int i=2;i<=ceiling;i++)
        {
            if(num%i==0) {
                int d = num / i;
                sum += i+d;
            }
        }
        return sum==num;
    }
}