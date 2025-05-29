class Solution {
   boolean debug = false;
    <T> void log(T... parameters) {
        if(debug)
            System.out.println(parameters);
    }
    public int minCostII(int[][] costs) {
        int k = costs[0].length;
        int n = costs.length;
        int [] cost_last = new int [k];
        for(int i=0;i < n;i++) {
            int [] cost = costs[i];
            int [] cost_new = new int[k];
            for(int j=0;j<k;j++) {
                cost_new[j] = cost[j] + cost_last[j];
            }
            int [] cost_left = new int[k];
            cost_left[0] = cost_new[0];
            for(int j=1;j<k;j++) {
               cost_left[j] = Math.min(cost_new[j], cost_left[j-1]);
            }
            log("cost_left for this house: " + Arrays.toString(cost_left));
            int [] cost_right = new int[k];
            cost_right[k-1] = cost_new[k-1];
            for(int j=k-2;j>=0;j--) {
               cost_right[j] = Math.min(cost_new[j], cost_right[j+1]);
            }
            log("cost_right for this house: " + Arrays.toString(cost_right));
            for(int j=0;j<k;j++){
                int left_min_cost = (j-1)>=0?cost_left[j-1] : cost_right[j+1];
                int right_min_cost = (j+1)<k?cost_right[j+1] : cost_left[j-1];
                int min_cost_house = Math.min(left_min_cost, right_min_cost);
                cost_last[j] = min_cost_house;
            }
            log("cost_last for this house: " + Arrays.toString(cost_last));
        }
        int min_value = cost_last[0];
        for(int i=0;i<k;i++){
            if(cost_last[i]<min_value)
                min_value = cost_last[i];
        }
        return min_value;
       
    }
}