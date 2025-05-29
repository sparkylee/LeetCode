class Solution {
    void predictTheWinner(int[] nums, int start, int end, int [] scores, int current_player) {
        int [] innerScores = new int[2];
        if(start>end) {
            return;
        }
        if(start==end){            
            scores[current_player] += nums[start];
            // System.out.println("One="+scores[0] + " Two="+scores[1]);
            return;
        }
        predictTheWinner(nums, start+1, end, innerScores, 1-current_player);
        int scoreLeft_current_player = innerScores[current_player] + nums[start];
        int scoreLeft_other_player = innerScores[1-current_player];

        innerScores[current_player] = 0;
        innerScores[1-current_player] = 0;
        predictTheWinner(nums, start, end-1, innerScores, 1-current_player);
        int scoreRight_current_player = innerScores[current_player] + nums[end];
        int scoreRight_other_player = innerScores[1-current_player];
        if(scoreLeft_current_player>scoreRight_current_player) {
            scores[current_player]    = scoreLeft_current_player;
            scores[1- current_player] = scoreLeft_other_player;
        } else {
            scores[current_player]    = scoreRight_current_player;
            scores[1- current_player] = scoreRight_other_player;
        }
        // System.out.println("One="+scores[0] + " Two="+scores[1]);
        return;

    }
    public boolean predictTheWinner(int[] nums) {
         int [] innerScores = new int[2];
         predictTheWinner(nums,0,nums.length-1,innerScores,0);
        //  System.out.println("One="+innerScores[0] + " Two="+innerScores[1]);
         return innerScores[0] >= innerScores[1];
    }
}