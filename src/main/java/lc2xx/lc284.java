package lc2xx;

import org.junit.Test;

import java.util.Iterator;

public  class lc284
{

    @Test
    public void test1() {
        testcase(new int[]{1,3,4,2,2});
    }

    @Test
    public void test2() {
        testcase(new int[]{3,1,3,4,2});
    }

    @Test
    public void test3() {
        testcase(new int[]{3,3,3,4,2});
    }

    @Test
    public void test4() {
        testcase(new int[]{1,1});
    }

    @Test
    public void test5() {
        testcase(new int[]{26,2,9,20,31,7,14,32,37,15,29,6,12,38,48,22,19,45,42,40,1,12,25,36,39,30,35,4,
                27,12,49,16,47,3,44,41,8,17,21,23,10,43,12,34,24,28,33,13,46,11});
    }

    private void testcase(int[] nums) {

    }

    // Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
    class PeekingIterator implements Iterator<Integer> {

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.

        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return 0;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            return 0;
        }

        @Override
        public boolean hasNext() {
            return false;
        }
    }
}
