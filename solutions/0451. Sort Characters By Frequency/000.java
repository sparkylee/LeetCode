class Solution {
    class CharCount {
        char c;
        int a;

        CharCount(char c, int a) {
            this.c = c;
            this.a = a;
        }
    }

    class CountComparator implements Comparator<CharCount> {
        @Override
        public int compare(CharCount x, CharCount y) {
            return y.a - x.a;
        }
    }

    public String frequencySort(String s) {
        int[] charCnt = new int[256];
        for (int i = 0; i < s.length(); i++)
            charCnt[s.charAt(i)]++;
        List<CharCount> ccList = new ArrayList<>();
        for (int i = 0; i < 256; i++)
            if (charCnt[i] != 0)
                ccList.add(new CharCount((char) i, charCnt[i]));
        ccList.sort(new CountComparator());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ccList.size(); i++) {
            CharCount cc = ccList.get(i);
            for (int j = 0; j < cc.a; j++)
                sb.append(cc.c);
        }
        return sb.toString();
    }
}