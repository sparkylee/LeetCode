import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public  class lc212
{


    @Test
    public void tet1()
    {
        char [][] board = {{'o','a','a','n'}, {'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String [] words = {"oath","pea","eat","rain","","aak","rvlf"};
        //String [] words = {"oath","pea","eat","rain"};
//        String [] words = {""};
        tc(board,words);
    }
    @Test
    public void test3()
    {
        char [][] board = {{'s','e','e','n','e','w'},{'t','m','r','i','v','a'},{'o','b','s','i','b','d'},{'w','m','y','s','e','n'},{'l','t','s','n','s','a'},{'i','e','z','l','g','n'}};
        String [] words = this.readFile();
       // String [] words = new String[]{"riven"};
        tc(board,words);
    }
    private String [] readFile()
    {
        String fileName = "src/main/java/lc212_1.txt";

        // This will reference one line at a time
        StringBuilder lines = new StringBuilder();

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                lines.append(line);
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        String [] words = lines.toString().split(" ");
        return words;
    }
    @Test
    public void test2()
    {
        String [] words1 = {"seen","seer","stob","stow","wots","embow","bowl","myst","teil","riva","rive","riven","rine","reest","reem","renew","sise","neem","irene","inerm","send","vire","vine","viner","bend","benda","bena","besa","besan","sane","sand","sang","wene","wave","wadna","avine","avener","daven","anes","anesis","anda","nane","nanes"};
        String [] words2 = {"anda","anes","anesis","avener","avine","bena","bend","benda","besa","besan","bowl","daven","embow","inerm","irene","myst","nane","nanes","neem","reem","reest","renew","rine","riva","rive","riven","sand","sane","sang","seen","seer","send","sise","stob","stow","teil","vine","viner","vire","wadna","wave","wene","wots"};

        for(String w1: words1)
        {
            boolean found = false;
            for(String w2: words2)
            {
                if(w2.equals(w1))
                {
                    found = true;
                    break;
                }
            }
            if(!found) System.out.println(w1);
        }

    }
    private void tc(char[][] board, String [] words)
    {
        Solution sol = new Solution();
        List<String> ws = sol.findWords(board,words);
        for(String str: ws)
        {
            System.out.print("\""+str+"\",");
        }
        System.out.println();
    }

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
}
