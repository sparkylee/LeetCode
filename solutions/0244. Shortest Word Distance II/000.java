class WordDistance {
    Map<String, List<Integer>> map;
    int MaxValue ;
    public WordDistance(String[] wordsDict) {
        this.MaxValue = wordsDict.length -1;
        this.map = new HashMap<>();
        for(int i=0;i<wordsDict.length;i++) {
            List<Integer> indices ;
            if( this.map.containsKey(wordsDict[i])) {
                indices = this.map.get(wordsDict[i]);
                indices.add(i);
            }  else {
                indices = new ArrayList<>();
                indices.add(i);
                this.map.put(wordsDict[i], indices);
            }            
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> indices1 = this.map.get(word1);
        List<Integer> indices2 = this.map.get(word2);
        int mv = this.MaxValue;
        for(int i =0;i<indices1.size();i++) {
            for(int j =0;j<indices2.size();j++) {
                int value = indices2.get(j) - indices1.get(i) ;
                int value_abs = Math.abs(value);
                // System.out.println("value_abs="+value_abs);
                mv = Math.min(value_abs,mv);
                if(value >=0) {
                    break;
                }
            }
        }
        return mv;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(wordsDict);
 * int param_1 = obj.shortest(word1,word2);
 */