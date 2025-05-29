class Solution {
    private int getByteCount(int b) {
        int mask = 0x80;
        int count = 0;
        for (int i=0; i < 4; i++) {
            if((b & mask) != 0x0) {
                count = count + 1;
            }
            else {
                break;
            }
            mask = (mask >> 1);
        }
        if(count < 2 ) {
            return -1;
        }
        mask = 0x80 >> count;
        if ( (b & mask ) != 0x0) {
            return -1;
        }
        return count > 1 ? count : -1;
    }
    private boolean validUtf8(int[] data, int start) {
        if (start >= data.length) {
            return true;
        }
        int count = 1;
        int head = data[start];
        if (( head & 0x80 ) != 0x0) {
            count = getByteCount(head);
            if (count < 2) {
                return false;
            }
            for (int i=1; i< count; i++) {
                int loc = start + i;
                if(loc >= data.length)
                {
                    return false;
                }
                int d = data[loc];
                if ( (d & 0xc0) != 0x80 ) {
                    return false;
                }
            }
        }  
        return validUtf8(data, start + count);
    }
    public boolean validUtf8(int[] data) {
        return this.validUtf8(data, 0);
    }
}