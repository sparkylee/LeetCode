/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
   class Level {
        long sum;
        int count;
        Level(long sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }
    public void averageOfLevels(TreeNode root, List<Level> levels, int h) {
        if(root==null) return;
        if(h==levels.size())
            levels.add(new Level(root.val, 1));
        else {
            levels.get(h).sum += root.val;
            levels.get(h).count++;
        }
        averageOfLevels(root.left, levels, h+1);
        averageOfLevels(root.right, levels, h+1);
    }
    public List<Double> averageOfLevels(TreeNode root) {
        List<Level> levels = new ArrayList<>();
        averageOfLevels(root, levels, 0);
        List<Double> results = new ArrayList<>();
        for(Level l: levels)
            results.add((double)l.sum / (double) l.count);
        return results;
    }
}