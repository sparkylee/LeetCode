import java.util.Arrays;

public class lc1037 {
    public boolean isBoomerang(int[][] points) {
        int dx1 = points[1][0] - points[0][0];
        int dy1 = points[1][1] - points[0][1];
        if (dx1 == 0 && dy1 == 0) return false;
        int dx2 = points[2][0] - points[0][0];
        int dy2 = points[2][1] - points[0][1];
        if (dx2 == 0 && dy2 == 0) return false;
        if (dx1 == 0 && dx2 == 0) return false;
        if (dx1 == 0 || dx2 == 0) return true;
        return !(dx1 * dy2 == dx2 * dy1);
    }
}
