class Solution {
    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    public String toGoatLatin(String S) {
        String[] splited = S.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < splited.length; i++) {
            char c = splited[i].charAt(0);
            if (isVowel(c)) {
                sb.append(splited[i]);
            } else {
                sb.append(splited[i].substring(1));
                sb.append(c);
            }
            sb.append("ma");
               for (int j = 0; j <= i; j++)
                sb.append('a');
            if (i < splited.length - 1)
                sb.append(" ");
        }
        return sb.toString();
    }
}