public class lc709 {
    public String toLowerCase(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                c = (char) ((c - 'A') + 'a');
            }
            sb.append(sb);
        }
        return sb.toString();
    }
}
