class Solution {
    public String removeDuplicates(String S) {
       StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == c)
                sb.deleteCharAt(sb.length() - 1);
            else
                sb.append(c);
        }
        return sb.toString();
    }
}