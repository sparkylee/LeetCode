public class lc836 {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if (rec2[1] >= rec1[3]) return false;
        if (rec2[3] <= rec1[1]) return false;
        if (rec2[0] >= rec1[2]) return false;
        if (rec2[2] <= rec1[0]) return false;
        return true;
    }
}
