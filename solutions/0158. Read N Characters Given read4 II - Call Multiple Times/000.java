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
    char [] buf4 = new char[5];
    int start_index = 0, end_index = 0;
    boolean fileEnd = false;
    public int read(char[] buf, int n) {
        if (n<=0 || fileEnd) {
            return 0;
        }
        int read_count_total = 0;
        while(true) {
            int buf4_len = end_index - start_index;
            int remain_chars = n - read_count_total;
            if(buf4_len>0)
            {
                int read_count = remain_chars < buf4_len ? remain_chars: buf4_len; 
                System.arraycopy(this.buf4, start_index, buf, read_count_total, read_count);
                read_count_total += read_count;
                this.start_index += read_count;
                // System.out.print("start_index="+this.start_index);
                if(this.start_index == this.end_index) {
                    this.start_index = this.end_index = 0;
                }

                if(n==read_count_total)
                    return read_count_total;
            }
            int read4_count = this.read4(buf4);
            if(read4_count==0) {
                fileEnd = true;
                return read_count_total;
            }
            this.end_index = read4_count;
        }
     
   
           
          
    }
}