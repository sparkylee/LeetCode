class Solution {
  void swap(StringBuilder sb, int i, int j) {
        char tmp = sb.charAt(i);
        sb.setCharAt(i, sb.charAt(j));
        sb.setCharAt(j, tmp);
    }

    void reverseWord(StringBuilder sb, int start, int end) {
        if (start < 0 || start >= end || end >= sb.length()) return;
        for (int i = 0; i <= (start + end) / 2; i++) {
            if (start + i >= end - i) break;
            swap(sb, start + i, end - i);
        }
    }
    int seekEnd(StringBuilder sb, int end) {
        while(end<sb.length() && !Character.isWhitespace(sb.charAt(end)))
            end++;
        return end - 1;
    }
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder(s);
        int start =0, end =0;
        do {
            end = seekEnd(sb, end);
            reverseWord(sb, start, end );
            start = end+2;
            end = start;
        }while (end<sb.length() && start<sb.length());
        return sb.toString();
    }

}