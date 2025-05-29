class Solution {
    Random rand;

    double radius, x_center, y_center;
    public Solution(double radius, double x_center, double y_center) {
        this.rand = new Random();
        this.radius = radius;
        this.x_center = x_center;
        this.y_center = y_center;
    }
    private double [] randPointPC(){
        int IM_full = 0x20000000;
        long IM = IM_full - 1;        
        while(true) {
            long x = this.rand.nextInt(IM_full);
            long y = this.rand.nextInt(IM_full);
            if(((2*x-IM)*(2*x-IM) + (2*y-IM)*(2*y-IM))<=(IM*IM)) {
                // System.out.println("x="+x+" y="+y);
                return new double[] {(double)2*x/IM-1.0,(double)2*y/IM-1.0};
            }
        }
        
    }
    public double[] randPoint() {       
        double [] pc = randPointPC();
        // System.out.println("pc="+pc[0]+" "+pc[1] + " radius="+Math.sqrt(pc[0]*pc[0]+pc[1]*pc[1]));
        double y = this.y_center + this.radius * pc[1];
        double x = this.x_center +  + this.radius * pc[0];
        return new double[] {x,y};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(radius, x_center, y_center);
 * double[] param_1 = obj.randPoint();
 */