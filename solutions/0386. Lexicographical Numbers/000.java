class Solution {
    void lexicalOrder(List<Integer> results, int n, int lead_num) {
        lead_num *=10;
        for(int i=0;i<=9;i++) {            
            if(lead_num>n) {
               return;
            }
            results.add(lead_num);
            lexicalOrder(results,n,lead_num);
            lead_num ++;
        }
        
    }
    public List<Integer> lexicalOrder(int n) {
        List<Integer> results = new ArrayList<>();    
        for(int lead_num = 1; lead_num <=9; lead_num ++) {
            if(lead_num>n) 
               break;
            results.add(lead_num);
            lexicalOrder(results,n,lead_num);
        }
        return results;
    }
}