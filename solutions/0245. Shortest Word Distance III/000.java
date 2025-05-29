class Solution {
    Map<String, List<Integer>> map;
    int MaxValue;
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
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

        List<Integer> indices1 = this.map.get(word1);
        List<Integer> indices2 = this.map.get(word2);
        int mv = this.MaxValue;
        boolean isSame = word1.equals(word2);
        for(int i =0;i<indices1.size();i++) {
            int j = isSame ? i + 1 : 0;
            for(;j<indices2.size();j++) {
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