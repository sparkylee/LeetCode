class RandomizedCollection {
    Map<Integer, Set<Integer>> map;
    List<Integer> lst;
    Random rand;
    public RandomizedCollection() {
        this.map=new HashMap<>();
        this.rand = new Random();
        this.lst = new ArrayList<>();
    }
    
    public boolean insert(int val) {
        boolean contained = this.map.containsKey(val);
        Set<Integer> set = contained? this.map.get(val): new HashSet<>();
        this.lst.add(val);
        set.add(this.lst.size()-1);
        this.map.putIfAbsent(val, set);
        return !contained;
    }
    
    public boolean remove(int val) {
        // System.out.println(this.lst);
        boolean contained = this.map.containsKey(val);
        if(!contained)
            return false;
        Set<Integer> set = this.map.get(val);
        if(set.isEmpty())
            return false;
        int index = set.iterator().next();
        int lastVal = this.lst.getLast();
        if(lastVal==val) {
            set.remove(this.lst.size()-1);
            if(set.isEmpty())
                this.map.remove(val);
            this.lst.removeLast();
            return contained;
        }
        Set<Integer> set_last = this.map.get(lastVal);
        this.lst.set(index, lastVal);        
        set_last.remove(this.lst.size()-1);
        set_last.add(index);
        set.remove(index);
        if(set.isEmpty()) {
            this.map.remove(val);
        }
        this.lst.removeLast();
        return contained;
    }
    
    public int getRandom() {
        int index = this.rand.nextInt(this.lst.size());
        return this.lst.get(index);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */