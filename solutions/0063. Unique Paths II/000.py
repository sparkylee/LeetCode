class Solution(object):
    def uniquePathsWithObstacles(self, obstacleGrid):
        """
        :type obstacleGrid: List[List[int]]
        :rtype: int
        """

        height = len(obstacleGrid);
        if height == 0:
            return 0;
        width = len(obstacleGrid[0]);
        if width == 0:
            return 0;
        self.pathGrid = [[0 for i in range(width)] for i in range(height)];
        #self.print(self.pathGrid);
        for i in reversed(range(height)):
            for j in reversed(range(width)):
                if obstacleGrid[i][j] == 1:
                    self.pathGrid[i][j] = 0;
                    continue;
                if i==height-1 and j == width -1:
                    self.pathGrid[i][j] = 1;
                    continue;
                bottom_paths = 0;
                right_paths = 0;
                if i+1 < height:
                    bottom_paths = self.pathGrid[i+1][j];
                if j+1 < width:
                    right_paths = self.pathGrid[i][j+1];
                self.pathGrid[i][j] = bottom_paths + right_paths;
        #self.print(obstacleGrid);
        #self.print(self.pathGrid);
        return self.pathGrid[0][0];
        