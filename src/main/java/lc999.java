public class lc999 {
    public int numRookCaptures(char[][] board) {
        int x = 0, y = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'R') {
                    x = i;
                    y = j;
                }
            }
        }
        int count = 0;
        int i = 0;
        while (true) {
            i++;
            if (x - i < 0) break;
            char c = board[x - i][y];
            if (c == '.') continue;
            if (c == 'p') count++;
            break;
        }
        i = 0;
        while (true) {
            i++;
            if (x + i >= board.length) break;
            char c = board[x + i][y];
            if (c == '.') continue;
            if (c == 'p') count++;
            break;
        }
        i = 0;
        while (true) {
            i++;
            if (y - i < 0) break;
            char c = board[x][y - i];
            if (c == '.') continue;
            if (c == 'p') count++;
            break;
        }
        i = 0;
        while (true) {
            i++;
            if (y + i >= board[0].length) break;
            char c = board[x][y + i];
            if (c == '.') continue;
            if (c == 'p') count++;
            break;
        }
        return count;
    }
}
