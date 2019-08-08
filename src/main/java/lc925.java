public class lc925 {
    class CharBlock {
        char c;
        int count;

        CharBlock(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }

    private CharBlock getCharBlock(String s, int i) {
        if (i >= s.length()) return null;
        char c = s.charAt(i);
        CharBlock cb = new CharBlock(c, 0);
        for (int j = i; j < s.length(); j++) {
            if (s.charAt(j) == c)
                cb.count++;
            else
                break;
        }
        return cb;
    }

    public boolean isLongPressedName(String name, String typed) {
        int i = 0, j = 0;
        while (true) {
            CharBlock ncb = getCharBlock(name, i);
            CharBlock tcb = getCharBlock(name, j);
            if (ncb == null && tcb == null)
                return true;
            if (ncb == null || tcb == null ||
                    ncb.c != tcb.c || ncb.count > tcb.count)
                return false;
            i += ncb.count;
            j += tcb.count;
        }
    }
}
