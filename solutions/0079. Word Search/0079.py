class Solution(object):
    def recurSearch(self,board,i,j,word,k,tag):
        if k >= self.l:
            return True;
        if i < 0 or i >= self.m or j < 0 or j >= self.n:
            return False;
        if board[i][j] != word[k] or tag[i][j]==1:
            return False;
        tag[i][j] = 1;
        for index in [[i-1,j],[i+1,j],[i,j-1],[i,j+1]]:
            if self.recurSearch(board,index[0],index[1],word,k+1,tag):
                return True;
        tag[i][j] = 0;
        return False;
    def exist(self, board, word):
        """
        :type board: List[List[str]]
        :type word: str
        :rtype: bool
        """
        self.m = len(board);
        if self.m == 0:
            return False;
        self.n = len(board[0]);
        if self.n == 0:
            return False;
        tag = [[0 for x in range(self.n)] for y in range(self.m)] ;
        self.l = len(word);
        for i in range(self.m):
            for j in range(self.n):
                if self.recurSearch(board,i,j,word,0,tag):
                    return True;
        return False;