class Solution {
     private char getChar(char[][] board,int x,int y)
        {
            if(x<0 || x>=board[0].length || y<0 || y>=board.length) return '.';
            return board[y][x];
        }
        private boolean findWord(char[][] board,int x,int y, String word,int i)
        {
            if(i>=word.length()) return true;
            char cb = getChar(board,x,y);
            char cs = word.charAt(i);
            if(cb!=cs) return false;
            board[y][x]='.';
            boolean found = false;
            found = findWord(board,x-1,y,word,i+1);
            if(found)
            {
                board[y][x] = cb;
                return true;
            }
            found = findWord(board,x+1,y,word,i+1);
            if(found)
            {
                board[y][x] = cb;
                return true;
            }
            found = findWord(board,x,y-1,word,i+1);
            if(found)
            {
                board[y][x] = cb;
                return true;
            }
            found = findWord(board,x,y+1,word,i+1);
            if(found)
            {
                board[y][x] = cb;
                return true;
            }
            board[y][x]=cb;
            return false;
        }
        private boolean findWord(char[][] board, String word)
        {
            if(word==null || word.equals("")) return false;
            for(int i=0;i<board[0].length;i++)
            {
                for(int j=0;j<board.length;j++)
                {
                    if(findWord(board,i,j,word,0))
                        return true;
                }
            }
            return false;
        }
        public List<String> findWords(char[][] board, String[] words) {
            List<String> ws = new ArrayList<>();
            if(board==null || board.length<1) return ws;
            for(int i = 0;i<words.length;i++)
            {
                boolean isDuplicate = false;
                for(int j=0;j<i;j++)
                {
                    if(words[j].equals(words[i]))
                    {
                        isDuplicate=true;
                        break;
                    }
                }
                if(isDuplicate) continue;

                if(findWord(board,words[i]))
                    ws.add(words[i]);
            }
            return ws;

        }
}