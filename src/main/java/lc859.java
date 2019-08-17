public class lc859 {
    public boolean buddyStrings(String A, String B) {
        if (A.length() < 2 || B.length() < 2 || A.length() != B.length())
            return false;
        final int Len = A.length();
        int count = 0;
        int[] indice = new int[4];
        for (int i = 0; i < Len; i++) {
            char a = A.charAt(i);
            char b = B.charAt(i);
            if (a != b) {
                indice[count] = i;
                count++;
                if (count > 2) return false;

            }
        }
        if (count != 0 && count != 2) return false;
        if (count == 0) {
            int[] charCount = new int[26];
            for (int i = 0; i < 26; i++)
                charCount[i] = 0;
            for (int i = 0; i < Len; i++) {
                char a = A.charAt(i);
                int index = a - 'a';
                charCount[index]++;
                if (charCount[index] >= 2)
                    return true;
            }
            return false;
        }
        int i0 = indice[0], i1 = indice[1];
        return A.charAt(i0) == B.charAt(i1) && A.charAt(i1) == B.charAt(i0);
    }
}
