import java.util.*;

public class lc812 {

    private double calcDistance(int[][] points, int i, int j) {
        double dis = 0.0;
        for (int k = 0; k < 2; k++)
            dis += Math.pow(points[i][k] - points[j][k], 2);
        return Math.sqrt(dis);
    }

    public double largestTriangleArea(int[][] points) {
        double[][] sides = new double[points.length][points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double dis = calcDistance(points, i, j);
                sides[i][j] = dis;
                sides[j][i] = dis;
            }
        }
        double areaMax = 0.0;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    double a = sides[i][j];
                    double b = sides[j][k];
                    double c = sides[i][k];
                    double p = (a + b + c) / 2.0;
                    double area2 = (p - a) * (p - b) * (p - c);
                    areaMax = Math.max(area2, areaMax);
                }
            }
        }
        return areaMax;
    }
}
