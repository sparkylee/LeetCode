import java.util.*;

public class lc812 {
    double[][] sides;

    private double calcTriangleArea() {

        return 0.0;
    }

    private double calcDistance(int[][] points, int i, int j) {
        double dis = 0.0;
        for (int k = 0; k < 2; k++)
            dis += Math.pow(points[i][k] - points[j][k], 2);
        return Math.sqrt(dis);
    }

    public double largestTriangleArea(int[][] points) {
        sides = new double[points.length][points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dis = calcDistance(points, i, j);
                sides[i][j] = dis;
                sides[j][i] = dis;
            }
        }
        return 0.0;
    }
}
