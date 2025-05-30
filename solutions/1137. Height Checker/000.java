class Solution {
    public int heightChecker(int[] heights) {
        int[] heightsSorted = heights.clone();
        Arrays.sort(heightsSorted);
        int count = 0;
        for (int i = 0; i < heights.length; i++)
            count += heights[i] == heightsSorted[i] ? 0 : 1;
        return count;
    }
}