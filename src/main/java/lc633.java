import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class lc633 {
    public boolean judgeSquareSum(int c) {
        int s = (int) Math.floor(Math.sqrt(c));
        Set<Integer> squares = new HashSet<>();
        for (int i = 0; i <= s; i++)
            squares.add(i * i);
        for (Integer x : squares)
            if (squares.contains(c - x))
                return true;
        return false;
    }
}
