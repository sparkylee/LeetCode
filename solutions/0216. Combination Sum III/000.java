class Solution {
     private List<List<Integer>> results = new ArrayList<>();

    private void getCombinationSum3(int i, List<Integer> tmp, int k, int n)
    {
        if(n < 0 || tmp.size() > k) return;
        if(k == tmp.size()) {
         if(n!=0) return;
         results.add(new ArrayList<>(tmp));
         return ;
        }
        if(i>9) return;
        tmp.add(i);
        getCombinationSum3(i+1,tmp,k,n-i);
        tmp.remove(tmp.size()-1);

        getCombinationSum3(i+1,tmp,k,n);
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> tmp = new ArrayList<>();
        getCombinationSum3(1,tmp,k,n);
        return results;
    }
}