class WordDictionary {

     class Node
        {
            Map<Character,Node> c2node = new HashMap<>();
            boolean isWord = false;
        }
        Node head;
        /** Initialize your data structure here. */
        public WordDictionary() {
            this.head = new Node();
        }

        /** Inserts a word into the trie. */
        public void addWord(String word) {
            if(word==null) return;
            if(word.equals(""))
            {
                this.head.isWord = true;
                return;
            }
            Node p = head;
            for (int i=0;i<word.length();i++)
            {
                char c = word.charAt(i);
                if(!p.c2node.containsKey(c))
                    p.c2node.put(c,new Node());
                p=p.c2node.get(c);
            }
            p.isWord = true;
        }


        public boolean search(String word) {

            return this.search(this.head,word,0);
        }
        private boolean search(Node p,String word,int i) {

            if(word==null || i>word.length()) return false;
            if(i==word.length()) return p.isWord;
            char c = word.charAt(i);
            if(c=='.')
            {
                Iterator it = p.c2node.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Character, Node> pair = (Map.Entry)it.next();
                    if(search(pair.getValue(),word,i+1)) return true;
                }
                return false;
            }
            if(!p.c2node.containsKey(c)) return false;
            return search(p.c2node.get(c),word,i+1);

        }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */