class Solution {
     void initC2R(int [] c2r)
        {
            c2r['Q'] = 0;c2r['q'] = 0;
            c2r['W'] = 0;c2r['w'] = 0;
            c2r['E'] = 0;c2r['e'] = 0;
            c2r['R'] = 0;c2r['r'] = 0;
            c2r['T'] = 0;c2r['t'] = 0;
            c2r['Y'] = 0;c2r['y'] = 0;
            c2r['U'] = 0;c2r['u'] = 0;
            c2r['I'] = 0;c2r['i'] = 0;
            c2r['O'] = 0;c2r['o'] = 0;
            c2r['P'] = 0;c2r['p'] = 0;
            c2r['A'] = 1;c2r['a'] = 1;
            c2r['S'] = 1;c2r['s'] = 1;
            c2r['D'] = 1;c2r['d'] = 1;
            c2r['F'] = 1;c2r['f'] = 1;
            c2r['G'] = 1;c2r['g'] = 1;
            c2r['H'] = 1;c2r['h'] = 1;
            c2r['J'] = 1;c2r['j'] = 1;
            c2r['K'] = 1;c2r['k'] = 1;
            c2r['L'] = 1;c2r['l'] = 1;
            c2r['Z'] = 2;c2r['z'] = 2;
            c2r['X'] = 2;c2r['x'] = 2;
            c2r['C'] = 2;c2r['c'] = 2;
            c2r['V'] = 2;c2r['v'] = 2;
            c2r['B'] = 2;c2r['b'] = 2;
            c2r['N'] = 2;c2r['n'] = 2;
            c2r['M'] = 2;c2r['m'] = 2;

        }
        public String[] findWords(String[] words) {
            int [] c2r = new int[256];
            initC2R(c2r);
            List<String> strList = new ArrayList<>();
            for(int i=0;i<words.length;i++)
            {
                boolean isOneRow = true;
                int row = c2r[words[i].charAt(0)];
                for(int j=0;j<words[i].length();j++)
                {
                    char c = words[i].charAt(j);
                    int row1 = c2r[c];
                    if(row1!= row) {
                        isOneRow = false;
                        break;
                    }
                }
                if(isOneRow)
                    strList.add(words[i]);
            }
            return strList.toArray(new String[0]);
        }
}