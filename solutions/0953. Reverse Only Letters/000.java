class Solution {
   private boolean isLetter(char c) {
        if (c >= 'a' && c <= 'z') return true;
        if (c >= 'A' && c <= 'Z') return true;
        return false;
    }

    public String reverseOnlyLetters(String S) {
        StringBuilder sb = new StringBuilder(S);
        int i = 0, j = sb.length() - 1;
        while (true) {
            while (i < j && !isLetter(sb.charAt(i)))
                i++;
            while (i < j && !isLetter(sb.charAt(j)))
                j--;
            if (i < j) {
                char ci = sb.charAt(i);
                char cj = sb.charAt(j);
                sb.setCharAt(i, cj);
                sb.setCharAt(j, ci);
                i++;
                j--;
            } else
                break;
        }
        return sb.toString();
    }
}