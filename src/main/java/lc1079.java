import java.util.ArrayList;
import java.util.List;

public class lc1079 {

    public int numTilePossibilities(String tiles) {
        int[] counts = new int[26];
        for (int i = 0; i < tiles.length(); i++)
            counts[tiles.charAt(i) - 'A']++;

        return 0;
    }
}
