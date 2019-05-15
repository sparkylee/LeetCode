import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lc216 {


    @Test
    public void tet1() {
        cs2p(3, 15);
        cs2p(3, 7);
        cs2p(3, 9);
        cs2p(2, 2);
        cs2p(1, 7);
    }

    private void cs2p(int k, int n) {
        this.results.clear();
        combinationSum3(k, n);
        printeLList(this.results);
    }

    private void printeLList(List<List<Integer>> llist) {
        if (llist.isEmpty()) {
            System.out.println("[]");
            System.out.println();
            return;
        }
        for (List<Integer> list : llist) {
            for (Integer i : list) {
                System.out.print("" + i + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    ////////////////////
    private List<List<Integer>> results = new ArrayList<>();

    private void getCombinationSum3(int i, List<Integer> tmp, int k, int n) {
        if (n < 0 || tmp.size() > k) return;
        if (k == tmp.size()) {
            if (n != 0) return;
            results.add(new ArrayList<>(tmp));
            return;
        }
        if (i > 9) return;
        tmp.add(i);
        getCombinationSum3(i + 1, tmp, k, n - i);
        tmp.remove(tmp.size() - 1);

        getCombinationSum3(i + 1, tmp, k, n);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> tmp = new ArrayList<>();
        getCombinationSum3(1, tmp, k, n);
        return results;
    }

}
