import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.ArrayList;
import java.util.List;

public class lc501 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    ////////////////////////
    public class Result {
        List<Integer> results = new ArrayList<>();
        int totalCount = 0;
    }

    public class Element {
        int value;
        int count;
    }

    private void findMode(TreeNode root, Result result, Element element) {
        if (root == null || result == null || element == null) return;
        findMode(root.left, result, element);
        if (element.count == 0 || element.value == root.val) // the same value or the initial value
            element.count++;
        else { // a new value
            if (result.totalCount == element.count) {
                result.results.add(element.value);
            } else if (result.totalCount < element.count) {
                // result.totalCount < element.count
                result.results.clear();
                result.results.add(element.value);
                result.totalCount = element.count;
            }
            element.count = 1;
        }
        element.value = root.val;
        findMode(root.right, result, element);
    }

    public int[] findMode(TreeNode root) {

        return null;
    }

}
