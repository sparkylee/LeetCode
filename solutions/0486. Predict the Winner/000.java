class Solution {
    int [][][] matrix;
    void predictTheWinner(int[] nums, int start, int end, int [] scores, int current_player) {  
        int index =  end - start;    
        if(index<0) {
            return;
        }
        if(this.matrix[start][index]!=null) {
            scores[current_player] = this.matrix[start][index][0];
            scores[1-current_player] = this.matrix[start][index][1];
            return;
        } 
        if(start==end){           
            this.matrix[start][index] = new int[2];
            this.matrix[start][index][0] = nums[start];
            this.matrix[start][index][1] = 0;
            scores[current_player] = this.matrix[start][index][0];
            scores[1-current_player] = this.matrix[start][index][1];

            // System.out.println("One="+scores[0] + " Two="+scores[1]);
            return;
        }
        int [] innerScoresLeftFirst = new int[2];
        predictTheWinner(nums, start+1, end, innerScoresLeftFirst, 1);
        innerScoresLeftFirst[0] += nums[start];
        
        int [] innerScoresRightFirst = new int[2];
        predictTheWinner(nums, start, end-1, innerScoresRightFirst, 1);
        innerScoresRightFirst[0] += nums[end];

        this.matrix[start][index] = new int[2];
        if(innerScoresLeftFirst[0]>=innerScoresRightFirst[0]) {
            this.matrix[start][index][0] = innerScoresLeftFirst[0];
            this.matrix[start][index][1] = innerScoresLeftFirst[1];
        } else {
            this.matrix[start][index][0] = innerScoresRightFirst[0];
            this.matrix[start][index][1] = innerScoresRightFirst[1];
        }

        scores[current_player] = this.matrix[start][index][0];
        scores[1-current_player] = this.matrix[start][index][1];
        // System.out.println("One="+scores[0] + " Two="+scores[1]);
        return;

    }
    public boolean predictTheWinner(int[] nums) {
        this.matrix = new int[nums.length][][];
        for(int i=0;i<nums.length;i++){
            int size = nums.length - i;
            this.matrix[i] = new int[size][];
            for(int j=0;j<size;j++) {
                this.matrix[i][j] = null;
            }
        }
        int [] innerScores = new int[2];
        predictTheWinner(nums,0,nums.length-1,innerScores,0);
        //  System.out.println("One="+innerScores[0] + " Two="+innerScores[1]);
        // for(int i=0;i<nums.length;i++){
        //    int size = nums.length - i;
            
        //     for(int j=0;j<size;j++) {
        //         System.out.print(this.matrix[i][j][0] +" " +this.matrix[i][j][1]+", ");
        //     }
        //     System.out.println();
        // }
        return innerScores[0] >= innerScores[1];
    }
}