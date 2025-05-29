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
    public class Result {
        List<Integer> results = new ArrayList<>();
        int totalCount = 0;
    }

    public class Element {
        int value;
        int count;
    }

    private void add2Result(Result result, Element element) {
        if (result.totalCount == element.count && element.count > 0) {
            result.results.add(element.value);
        } else if (result.totalCount < element.count) {
            // result.totalCount < element.count
            result.results.clear();
            result.results.add(element.value);
            result.totalCount = element.count;
        }
    }

    private void checkNode(TreeNode root, Result result, Element element) {
        if (element.count == 0 || element.value == root.val) // the same value or the initial value
            element.count++;
        else { // a new value
            add2Result(result, element);
            element.count = 1;
        }
        element.value = root.val;
    }
    private void findMode(TreeNode root, Result result, Element element) {
        if (root==null || result == null || element == null) return;
        findMode(root.left, result, element);
        checkNode(root, result, element);
        findMode(root.right, result, element);
    }

    public int[] findMode(TreeNode root) {
        Result result = new Result();
        Element element = new Element();
        findMode(root, result, element);
        // at the final exit need to check the remaining element
        add2Result(result, element);
        return result.results.stream().mapToInt(i -> i).toArray();
    }
}