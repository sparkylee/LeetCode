package lc2xx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class lc208
{

    @Test
    public void tc() {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
        System.out.println(trie.search(""));

    }

    class Trie {

        class Node {
            Map<Character,Node> c2node = new HashMap<>();
            boolean isWord = false;
        }

        Node head;

        /** Initialize your data structure here. */
        public Trie() {
            this.head = new Node();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if(word==null) return;
            if (word.equals("")) {
                this.head.isWord = true;
                return;
            }
            Node p = head;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(!p.c2node.containsKey(c))
                    p.c2node.put(c,new Node());
                p=p.c2node.get(c);
            }
            p.isWord = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node p = head;
            if(word==null) return false;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(!p.c2node.containsKey(c)) return false;
                p = p.c2node.get(c);
            }
            return p.isWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Node p = head;
            if(prefix==null) return false;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if(!p.c2node.containsKey(c)) return false;
                p = p.c2node.get(c);
            }
            return true;
        }

    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */


}
