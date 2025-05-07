class Solution(object):
    def searchMatrix(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        m = len(matrix);
        if m == 0:
            return False;
        n = len(matrix[0]);
        if n ==0:
            return  False;
        a = m - 1;
        b = 0;
        x = b;
        while True:
            if a==b:
                x = a;
                break;
            if a < b:
                return False;
            x = (a+b+1)//2;
            if matrix[x][0]==target:
                return True;
            elif matrix[x][0]> target:
                a = x -1;
                continue;
            else:
                b = x;
        a = n -1;
        b = 0;
        y  = b;
        while True:
            if a < b:
                return False;
            y = (a+b+1)//2;
            if matrix[x][y]==target:
                return True;
            elif matrix[x][y]> target:
                a = y -1;
                continue;
            else:
                b = y+1;
        return False;
