public class lc821 {
    public int[] shortestToChar(String S, char C) {
        int[] distances = new int[S.length()];
        int last = -1;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == C) {
                distances[i] = 0;
                int k = 1;
                last = i;
                while (i - k >= 0) {
                    if (distances[i - k] > k || distances[i - k] == -1)
                        distances[i - k] = k;
                    else
                        break;
                    k++;
                }
            } else {
                if (last != -1)
                    distances[i] = i - last;
                else
                    distances[i] = -1;
            }
        }
        return distances;
    }
}
