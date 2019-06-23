import org.junit.Test;


public class lc506 {


    //////////////
    public class Node {
        int val;
        int i;
        int order;

        boolean se(Node n) {
            return n != null && this.val <= n.val;
        }
        boolean st(Node n) {
            return n != null && this.val < n.val;
        }
        boolean gt(Node n) {
            return n != null && this.val > n.val;
        }

        boolean ge(Node n) {
            return n != null && this.val >= n.val;
        }
        boolean eq(Node n) {
            return n != null && this.val == n.val;
        }
    }

    private void swapNode(Node[] nodes, int i, int j) {
        Node tmp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = tmp;
    }
    public void quickSort(Node[] nodes, int start, int i, int j, int end) {
        if (!(nodes != null && start <= i && i <= j && j <= end
                && (start + 1 <= end) && start >= 0 && end < nodes.length)) return;
        int x = start, y = end;
        while (true) {
            while (nodes[x].se(nodes[i])) {
                if (nodes[x].eq(nodes[i])) {
                    swapNode(nodes, x, i);
                    i--;
                } else
                    x++;
            }

            while (nodes[y].ge(nodes[j])) {
                if (nodes[y].eq(nodes[j])) {
                    swapNode(nodes, y, j);
                    j++;
                } else
                    y--;
            }

        }
    }

    public String[] findRelativeRanks(int[] nums) {
        return null;
    }
}
