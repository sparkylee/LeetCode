public class lc733 {

    public void floodFill(int[][] image, int i, int j, int oldColor, int newColor) {
        if (i < 0 || j < 0 || i >= image.length || j >= image[0].length
                || image[i][j] != oldColor) return;
        image[i][j] = newColor;
        floodFill(image, i - 1, j, oldColor, newColor);
        floodFill(image, i + 1, j, oldColor, newColor);
        floodFill(image, i, j - 1, oldColor, newColor);
        floodFill(image, i, j + 1, oldColor, newColor);
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        floodFill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

}
