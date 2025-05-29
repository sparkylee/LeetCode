class ValidWordAbbr {
    Map<String, Set<String>> map;
    String conv2Abbr(String str) {
        if(str.length()<=2)
            return str;      
        return str.charAt(0) + Integer.toString(str.length() - 2) + str.charAt(str.length()-1);        
    }
    public ValidWordAbbr(String[] dictionary) {
        this.map = new HashMap<>();
        for(String str : dictionary) {
            String abbr = conv2Abbr(str);           
            Set<String> set = this.map.getOrDefault(abbr, new HashSet<>());
            set.add(str);
            this.map.putIfAbsent(abbr, set);
        }
    }
    
    public boolean isUnique(String word) {
        String abbr = conv2Abbr(word);    
        if(!this.map.containsKey(abbr))
            return true;
        Set<String> set = this.map.get(abbr);
        return set.size() == 1 && set.iterator().next().equals(word);
    }
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * ValidWordAbbr obj = new ValidWordAbbr(dictionary);
 * boolean param_1 = obj.isUnique(word);
 */