class Solution {
     class Trie {
            Map<Character, Trie> c2node = new HashMap<>();
            boolean isWord = false;
            /**
             * Initialize your data structure here.
             */
            public Trie() {
            }

            /**
             * Inserts a word into the trie.
             */
            public void insert(String word) {
                if (word == null || word.equals("")) return;
                Trie p = this;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (!p.c2node.containsKey(c))
                        p.c2node.put(c, new Trie());
                    p = p.c2node.get(c);
                }
                p.isWord = true;
            }

            /**
             * Returns if the word is in the trie.
             */
            public boolean search(String word) {
                Trie p = this;
                if (word == null) return false;
                for (int i = 0; i < word.length(); i++) {
                    char c = word.charAt(i);
                    if (!p.c2node.containsKey(c)) return false;
                    p = p.c2node.get(c);
                }
                return p.isWord;
            }
            public void removeSubTrie()
            {
                Iterator it = this.c2node.entrySet().iterator();
                while (it.hasNext()) {
                   Map.Entry<Character, Trie> pair = (Map.Entry)it.next();
                   if(!pair.getValue().isWord && pair.getValue().isEmpty())
                        it.remove();
                }
            }
            public boolean isEmpty()
            {
                return !this.isWord && this.c2node.isEmpty();
            }
        }
        private char getChar(char[][] board,int x,int y)
        {
            if(x<0 || x>=board[0].length || y<0 || y>=board.length) return '.';
            return board[y][x];
        }
        private void findWord(char[][] board,int x,int y,
                                 Trie trie,List<String> ws, StringBuilder sb)
        {
            if(trie.isWord)
            {
                ws.add(sb.toString());
                trie.isWord = false;
            }
            char cb = getChar(board,x,y);
            if(cb=='.') return ;
            Iterator it = trie.c2node.entrySet().iterator();
            board[y][x] = '.';
            sb.append(cb);
            while (it.hasNext()) {
                Map.Entry<Character, Trie> pair = (Map.Entry)it.next();
                if(pair.getKey().equals(cb))
                {
                    findWord(board,x-1,y,pair.getValue(),ws,sb);
                    findWord(board,x+1,y,pair.getValue(),ws,sb);
                    findWord(board,x,y-1,pair.getValue(),ws,sb);
                    findWord(board,x,y+1,pair.getValue(),ws,sb);
                }
            }
            trie.removeSubTrie();
            sb.deleteCharAt(sb.length()-1);
            board[y][x]=cb;
            return ;
        }
        private void findWord(char[][] board, Trie trie,List<String> ws)
        {
            for(int i=0;i<board[0].length;i++)
                for(int j=0;j<board.length;j++)
                {
                    StringBuilder sb = new StringBuilder();
                    findWord(board,i,j,trie,ws,sb);
                    if(trie.isEmpty()) return;
                }


        }
        public List<String> findWords(char[][] board, String[] words) {
            List<String> ws = new ArrayList<>();
            if(board==null || board.length<1) return ws;
            Trie trie = new Trie();
            for(String w: words)
                trie.insert(w);
            boolean found = trie.search("riven");
            findWord(board,trie,ws);
            return ws;

        }
}