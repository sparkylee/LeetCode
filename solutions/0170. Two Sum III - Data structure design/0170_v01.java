class TwoSum {
    Map<Integer, Integer> myMap;
    public TwoSum() {
        myMap = new HashMap<Integer, Integer>();
    }

    public void add(int number) {
        myMap.merge(number, 1, Integer::sum);
    }

    public boolean find(int value) {
        for(Integer num : myMap.keySet()) {
            int diff = value - num;
            if (! myMap.containsKey(diff)) {
                continue;
            }
            if (diff==num) {
                int count = myMap.get(num);
                if (count < 2) {
                    continue;
                }
            }
            return true;
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */