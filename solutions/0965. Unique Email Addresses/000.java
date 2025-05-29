class Solution {
    public int numUniqueEmails(String[] emails) {
          Set<String> set = new HashSet<>();
        for (String email : emails) {
            StringBuilder sb = new StringBuilder();
            boolean ignored = false;
            boolean isDomained = false;
            for (int i = 0; i < email.length(); i++) {
                char c = email.charAt(i);
                if (isDomained || c == '@') {
                    sb.append(c);
                    isDomained = true;
                    continue;
                }
                if (ignored || c == '.') continue;
                if (c == '+') {
                    ignored = true;
                    continue;
                }
                sb.append(c);

            }
            set.add(sb.toString());
        }
        return set.size();
    }
}