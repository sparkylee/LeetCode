package lc5xx;

import org.junit.Test;

import java.util.stream.IntStream;

public class lc509 {
    @Test
    public void t() {
        IntStream.range(0, 10).forEachOrdered(n -> {
            System.out.println(fib(n));
        });
    }

    public int fib(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        int a = 0, b = 1, c = 1;
        for (int i = 2; i <= N; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
