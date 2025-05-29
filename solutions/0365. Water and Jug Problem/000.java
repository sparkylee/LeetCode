class Solution {
    Set<Integer> states;
    List<Integer> states2try;
    int x, y, target;

    int createState(int xv, int yv) {
        return (xv << 16) | yv;
    }

    boolean addNewState(int xv, int yv) {
        if (xv + yv == this.target)
            return true;

        int state_new = createState(xv, yv);
        // System.out.println("xv="+xv+" yv="+yv+" state_new=" + state_new );
        if (!states.contains(state_new)) {
            // System.out.println("xv=" + xv + "  yv=" + yv);
            states.add(state_new);
            states2try.add(state_new);
        }
        return false;
    }

    boolean canMeasureWater() {
        int size = this.states2try.size();
        if (size == 0) {
            return false;
        }
        int index = size - 1;
        int state = this.states2try.get(index);
        this.states2try.remove(index);
        int xv = (state & 0xffff0000) >> 16;
        int yv = (state & 0x0000ffff);

        if (addNewState(this.x, yv))
            return true;
        if (addNewState(xv, this.y))
            return true;
        if (addNewState(0, yv))
            return true;
        if (addNewState(xv, 0))
            return true;
        int x_margin = this.x - xv, y_margin = this.y - yv;
        if (xv <= y_margin) {
            if (addNewState(0, xv + yv))
                return true;
        } else {
            if (addNewState(xv - y_margin, this.y))
                return true;
        }
        if (yv <= x_margin) {
            if (addNewState(xv + yv, 0))
                return true;
        } else {
            if (addNewState(this.x, yv - x_margin))
                return true;
        }
        return canMeasureWater();
    }

    public boolean canMeasureWater(int x, int y, int target) {
        this.states = new HashSet<Integer>();
        this.states2try = new ArrayList<Integer>();
        this.states.add(0);
        this.states2try.add(0);
        this.x = x;
        this.y = y;
        this.target = target;
        return canMeasureWater();
    }
}