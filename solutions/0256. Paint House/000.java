class Solution {
    boolean debug = false;
    <T> void log(T log) {
        if(debug)
            System.out.println(log);
    }
    public int minCost(int[][] costs) {
        int [] cost_last = {0,0,0};
        for(int i=0;i < costs.length;i++) {
            int [] cost = costs[i];
            int [] cost_new = new int[cost.length];
            for(int j=0;j<3;j++) {
                cost_new[j] = cost[j] + cost_last[j];
            }
            int [] cost_left = new int[cost.length];
            cost_left[0] = cost_new[0];
            for(int j=1;j<3;j++) {
               cost_left[j] = Math.min(cost_new[j], cost_left[j-1]);
            }
            log("cost_left for this house: " + Arrays.toString(cost_left));
            int [] cost_right = new int[cost.length];
            cost_right[2] = cost_new[2];
            for(int j=1;j>=0;j--) {
               cost_right[j] = Math.min(cost_new[j], cost_right[j+1]);
            }
            log("cost_right for this house: " + Arrays.toString(cost_right));
            for(int j=0;j<3;j++){
                int left_min_cost = (j-1)>=0?cost_left[j-1] : cost_right[j+1];
                int right_min_cost = (j+1)<3?cost_right[j+1] : cost_left[j-1];
                int min_cost_house = Math.min(left_min_cost, right_min_cost);
                cost_last[j] = min_cost_house;
            }
            log("cost_last for this house: " + Arrays.toString(cost_last));
        }
        int min_value = cost_last[0];
        for(int i=0;i<3;i++){
            if(cost_last[i]<min_value)
                min_value = cost_last[i];
        }
        return min_value;
       
    }
}