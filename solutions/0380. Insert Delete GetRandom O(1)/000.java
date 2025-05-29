class RandomizedSet {
    List<Integer> arr;
    Map<Integer, Integer> map;
    public RandomizedSet() {
        this.arr = new ArrayList<>();
        this.map = new HashMap<>();
    }
    
    public boolean insert(int val) {
        if(this.map.containsKey(val))
            return false;
        this.arr.add(val);
        this.map.put(val, this.arr.size()-1);
        return true;
    }
    
    public boolean remove(int val) {
        if(!this.map.containsKey(val))
            return false;
        int index = this.map.get(val);
        if(index!=this.arr.size()-1) {
            int lastValue = this.arr.getLast();
            this.arr.set(index, lastValue);
            this.map.put(lastValue, index);       
        }
        this.arr.removeLast();
        this.map.remove(val);
        return true;
    }
    
    public int getRandom() {
        int randomIndex = (int)(Math.random() * this.arr.size());
        return this.arr.get(randomIndex);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */