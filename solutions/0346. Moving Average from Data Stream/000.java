class MovingAverage {
    int [] w;
    int size;
    int count;
    int i;
    int sum;
    public MovingAverage(int size) {
        w = new int[size];
        count = 0;
        i     = -1;
        sum   = 0;
        this.size = size;
    }
    public double next(int val) {
        i = (i + 1) % this.size;
        if(count == this.size) {
            sum = sum - w[i];
        } else {
            count ++;
        }
        w[i] = val;
        sum = sum + val;
        double ave = sum / (double) count;
        return ave;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */