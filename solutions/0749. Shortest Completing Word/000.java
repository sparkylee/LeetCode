class Solution {
    public String shortestCompletingWord(String licensePlate, String[] words) {
         StringBuilder sb = new StringBuilder();
        BitSet bs = new BitSet();
        int k = 0;
        for (int i = 0; i < licensePlate.length(); i++) {
            char c = licensePlate.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                c = (char) (c - 'A' + 'a');
            }
            if (c >= 'a' && c <= 'z') {
                sb.append(c);
                bs.set(k);
                k++;
            }
        }

        int mi = -1;
        for (int i = 0; i < words.length; i++) {
            BitSet bitSet = bs.get(0, k);
            for (int j = 0; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                for (int l = 0; l < sb.length(); l++) {
                    if (sb.charAt(l) == c && bitSet.get(l)) {
                        bitSet.clear(l);
                         break;
                    }
                }
            }
            if (bitSet.cardinality() == 0 &&
                    (mi == -1 || words[i].length() < words[mi].length())
            ) mi = i;
        }
        return words[mi];
    }
}