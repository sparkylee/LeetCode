class Solution {
    public boolean lemonadeChange(int[] bills) {
        int [] billCnt = {0,0};
        for(int i = 0; i< bills.length;i++) {
            int b = bills[i];
            if(b==5)
            {
                billCnt[0]++;
                continue;
            }
            if(b==10) {
                billCnt[1]++;
                if(billCnt[0]>=1){
                billCnt[0] --;
                continue;    
                }            
                return false;
                
            }
            if(b==20)
            {
                if(billCnt[1]>0){
                    billCnt[1] --;
                    b = b - 10;
                }
                b = b - 5;
                if(billCnt[0]*5>=b)
                {
                    billCnt[0] -= b/5;
                    continue;
                }
                return false;
            }
        }
        return true;
        
    }
}