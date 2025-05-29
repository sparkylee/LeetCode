public class ZigzagIterator {
    List<List<Integer>> ll;
    int i = -1, j = 0;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        ll = new ArrayList<>();
        ll.add(v1);
        ll.add(v2);
        this.mvNext();
    }

    public int next() {
         int v = ll.get(i).get(j);
         this.mvNext();
         return v;
    }
    private boolean mvNext() {
        for(int k=0;k<ll.size();k++) {
            i++;
            if( i >= ll.size())
            {
                i = 0;
                j++;
            } 
            if(this.hasNext())
                return true; 
        }
        return false;    
    }
    public boolean hasNext() {
       return i >= 0 && i < ll.size() && j < ll.get(i).size();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */