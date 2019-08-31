package lc7xx;

public class lc789 {
    int[] target;

    private int calc(int[] src) {
        return Math.abs(src[0] - target[0]) + Math.abs(src[1] - target[1]);
    }

    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        this.target = target;
        int oDis = calc(new int[]{0, 0});
        for (int i = 0; i < ghosts.length; i++) {
            int dis = calc(ghosts[i]);
            if (dis <= oDis) return false;
        }
        return true;
    }
}
