class HitCounter {
    int [][] counters;
    int index;
    int LookBack = 300;
    public HitCounter() {
        this.counters = new int[LookBack][2];
        this.index = 0;
    }
    
    public void hit(int timestamp) {
        int now = counters[this.index][0];
        if(now!=timestamp) {
            this.index = (this.index+1)%LookBack;
            counters[this.index][0] = timestamp;
            counters[this.index][1] = 0;
        }
        counters[this.index][1]++;
    }
    
    public int getHits(int timestamp) {
        int i = this.index;
        int hits = 0;
        while(counters[i][0] > 0 && counters[i][0] > (timestamp-LookBack)) {
            hits += counters[i][1];
            i = (i+LookBack-1)%LookBack;
        }
        return hits;
    }
}

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */