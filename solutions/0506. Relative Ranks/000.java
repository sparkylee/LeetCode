class Solution {
     public class Node {
        int val;
        int i;
        int order;

        Node(int val, int i, int order) {
            this.val = val;
            this.i = i;
            this.order = order;
        }
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
            while (x < i && nodes[x].se(nodes[i])) {
                if (nodes[x].eq(nodes[i])) {
                    swapNode(nodes, x, i);
                    i--;
                } else
                    x++;
            }
            while (y > j && nodes[y].ge(nodes[j])) {
                if (nodes[y].eq(nodes[j])) {
                    swapNode(nodes, y, j);
                    j++;
                } else
                    y--;
            }
            if (nodes[x].gt(nodes[y])) {
                if (x == i) { // nodes[i]/nodes[x] is the chosen one
                    swapNode(nodes, i, y);
                    swapNode(nodes, y, j + 1);
                    i++;
                    j++;
                }
                else if (y == j) {
                    swapNode(nodes, j, x);
                    swapNode(nodes, x, i - 1);
                    i--;
                    j--;
                } else
                {
                    swapNode(nodes, x, y);
                    x++;
                    y--;
                }

            } else
                break;

        }
        int middle_first = (start + i - 1) / 2;
        int middle_second = (j + 1 + end) / 2;
        quickSort(nodes, start, middle_first, middle_first, i - 1);
        quickSort(nodes, j + 1, middle_second, middle_second, end);
    }
    private String order2Str(int o) {
        switch (o) {
            case 1: return "Gold Medal";
            case 2: return "Silver Medal";
            case 3: return "Bronze Medal";
            default: return String.valueOf(o);
        }
    }
    public String[] findRelativeRanks(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new String[0];
        }
        Node[] nodes = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nodes[i] = new Node(nums[i], i, 0);
        }
        int middle = (nodes.length - 1) / 2;
        quickSort(nodes, 0, middle, middle, (nodes.length - 1));
        for (int i = 0; i < nums.length; i++) {
            nodes[i].order = nums.length - i;
        }
        Node[] nodes_ordered = new Node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nodes_ordered[nodes[i].i] = nodes[i];
        }
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = order2Str(nodes_ordered[i].order);
        }
        return strs;
    }
}