class Solution {
      private int seekNextDot(String IP, int start) {
        while (start < IP.length()) {
            char c = IP.charAt(start);
            if (c == '.')
                return start;
            if (c < '0' || c > '9') return -1;
            start++;
        }
        return start;
    }

    boolean toIPV4(String IP) {
        int start = 0, end = 0;
        for (int k = 0; k < 4; k++) {
            end = seekNextDot(IP, start);
            if (end == -1 || start == end) return false;
            String sub = IP.substring(start, end);
            if (sub.length() > 1 && sub.charAt(0) == '0') return false;
             if (sub.length() > 3) return false;
            int value = Integer.parseInt(sub);
            if (value > 255) return false;
            start = end + 1;
        }
        return end == IP.length();
    }

    private int seekNextColon(String IP, int start) {
        while (start < IP.length()) {
            char c = IP.charAt(start);
            if (c == ':')
                return start;
            boolean valid = (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
            if (!valid) return -1;
            start++;
        }
        return start;
    }

    boolean toIPV6(String IP) {
        int start = 0, end = 0;
        for (int k = 0; k < 8; k++) {
            end = seekNextColon(IP, start);
            if (end == -1 || start == end) return false;
            String sub = IP.substring(start, end);
            if (sub.length() > 4) return false;
            start = end + 1;
        }
        return end == IP.length();
    }

    public String validIPAddress(String IP) {
        if (IP == null || "".equals(IP)) return "Neither";
        if (toIPV4(IP)) return "IPv4";
        if (toIPV6(IP)) return "IPv6";
        return "Neither";
    }
}