import org.junit.Test;


public class lc506 {


    //////////////
    public class Node {
        int val;
        int i;
        int order;

        boolean st(Node n) {
            return n != null && this.val < n.val;
        }

        boolean gt(Node n) {
            return n != null && this.val > n.val;
        }

        boolean eq(Node n) {
            return n != null && this.val == n.val;
        }
    }

    public void quickSort(Node[] nodes, int start, int i, int j, int end) {
        if (!(nodes != null && start <= i && i <= j && j <= end
                && (start + 1 <= end) && start >= 0 && end < nodes.length)) return;
        int x = start, y = end;
        while (nodes[x].st(nodes[i])) x++;
    }

    public String[] findRelativeRanks(int[] nums) {
        return null;
    }
}
