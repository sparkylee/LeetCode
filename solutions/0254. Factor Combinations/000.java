class Solution {
    public List<List<Integer>> getFactors(int n, int start) {
        int k = start;
        List<List<Integer>> results = new ArrayList<>();        
       
        // System.out.println("checking0 n="+n+" k="+k);
        while(k*k<=n) {
            List<Integer> solePrime = new ArrayList<>();
            // System.out.println("checking1 n="+n+" k="+k);
            int nvalue = n;
            while(true) {
                int mod = nvalue % k;
                int div = nvalue / k;      
                if(mod!=0 | div < k)
                    break;                                                                                                                   
                solePrime.add(k);
                List<Integer> newFactors = new ArrayList<>(solePrime);
                newFactors.add(div);
                // System.out.println("newFactors="+ newFactors);
                results.add(newFactors);                                                 
                     
                List<List<Integer>> result1  = getFactors(div, k+1);
                // System.out.println("result1 k="+result1);
                for(List<Integer> lst: result1) {
                    lst.addAll(solePrime);
                    results.add(lst);   
                }              
                nvalue = div;                                    
            }
            k++;
        }
        return results;
    }
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> results = new ArrayList<>();        
        // return results;
       return getFactors(n, 2);
    }
}