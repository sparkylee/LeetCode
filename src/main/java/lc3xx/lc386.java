package lc3xx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lc386 {
    @Test
    public void test1() {

    }

    @Test
    public void testGetWidth() {
        System.out.println(getWidth(1));
        System.out.println(getWidth(27));
        System.out.println(getWidth(237));
    }

    private int getWidth(int n) {
        int i = 0;
        while (n > 0) {
            i++;
            n = n / 10;
        }
        return i;
    }

    public List<Integer> lexicalOrder(int n) {
        if (n < 1) return null;
        List<Integer> results = new ArrayList<>();
        int w = this.getWidth(n);
        int[] array = new int[w];
        Arrays.fill(array, 10);
        array[0] = 1;
        int v = 1;
        int i = 0;

        results.add(v);
        while (true) {

            if (i < w


            ) //reach the small end
            {

            }
            return results;
        }


    }


}
