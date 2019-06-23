import org.junit.Test;

import java.util.stream.IntStream;


public class lc507 {
    @Test
    public void test() {
        IntStream.range(0, 30).forEachOrdered(n -> {
            t(n);
        });


    }

    private void t(int n) {
        System.out.println(checkPerfectNumber(n));
    }

    public boolean checkPerfectNumber(int num) {
        if (num <= 1) return false;
        int sum = 1;
        int ceiling = (int) Math.floor(Math.sqrt(num));
        for (int i = 2; i <= ceiling; i++) {
            if (num % i == 0) {
                int d = num / i;
                sum += i + d;
            }
        }
        return sum == num;
    }
}
