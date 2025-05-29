class Solution {
    boolean isConcatenatedWord(String word, Set<String> words, int [] sizes, int [] table) {
        int index = table.length - word.length();
        if(words.contains(word)) {
            table[index] = 1;
            return true;
        }
            
        for(Integer size: sizes) {
            if(word.length()<=size)
            {
                table[index] = -1;
                return false;
            }
            
            String word1 = word.substring(0, size);
            String word2 = word.substring(size);
            // System.out.println("checking "+word1 +" " + word2);
            int index_word2 = table.length - word2.length();
            if(table[index_word2]==-1)
                continue;
            if(table[index_word2]==1)
            {
              table[index] = 1;
                return true;  
            }
            if(words.contains(word1) && isConcatenatedWord(word2, words, sizes,table)) {
                table[index] = 1;
                return true;
            }
        }
        table[index] = -1;
        return false;
    }
    public List<String> findAllConcatenatedWordsInADict(String[] words) {        
        Set<Integer> set_size = new HashSet<>();
        Set<String> set = new HashSet<>();
        for(int i=0;i<words.length;i++) {
            set_size.add(words[i].length());
            set.add(words[i]);
        }
        int [] sizes = new int[set_size.size()];
        int index = 0;
        for(Integer value: set_size) {
            sizes[index] = value;
            index++;
        }
        Arrays.sort(sizes);
        List<String> lst =  new ArrayList<>();
        for(int i=0;i<words.length;i++) {      
            set.remove(words[i]);
            int [] table = new int[words[i].length()];            
            if(isConcatenatedWord(words[i],set,sizes,table)) {
                lst.add(words[i]);
            }
            set.add(words[i]);
        }
        return lst;
    }
}