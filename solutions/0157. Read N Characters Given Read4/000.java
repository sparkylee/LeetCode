/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf4);
 */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        char [] buf4 = new char[5];
        int count = 0;
        int m = 0;
        int i = 0;
        while(true) {
            if(m==n) {
                return m;
            }
            count = read4(buf4);
            if(count == 0) {
                return m;
            }
            for(int j=0;j<count;j++) {
                if(i>=n) {
                    return i;
                }
                buf[i] = buf4[j];
                i++;
            }
            m += count;
        }
    }
}