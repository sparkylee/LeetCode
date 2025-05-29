class Solution {
      // return the index
    int tryDecodeStringPlain(String s, int i, StringBuilder string_decode) {
        int start = i;
        while (i < s.length()) {
            char c = s.charAt(i);
            if ((c < '0' || c > '9') && c != '[' && c != ']') {
                i++;
                continue;
            }
            break;
        }
        String plaintext = s.substring(start, i);
        string_decode.append(plaintext);
        return i;

    }
    // return the index
    int decodeStringNum(String s, int i, int [] countA) {
        int count = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                break;
            }
            int c_val = c - '0';
            count = count * 10 + c_val;
            i++;

        }
        countA[0] = count;
        return i;
    }
    // return the index
    int tryDecodeStringRecursive(String s, int i, StringBuilder string_decode) {
        int [] countA = new int[1];
        int start = i;
        i = decodeStringNum(s, i, countA);
        if(i == start || i >= s.length()) {
            return i;
        }
        char c = s.charAt(i);
        if (c != '[') {
            return i;
        }
        i ++;
        StringBuilder stringBuilder = new StringBuilder();
        i = decodeString(s, i , stringBuilder);
        string_decode.append(String.valueOf(stringBuilder).repeat(Math.max(0, countA[0])));
        return i;
    }
    // return the index
    public int decodeString(String s, int start, StringBuilder string_decode) {
        int i = start;
        while (i < s.length()) {
            if(s.charAt(i)==']'){
                return i+1;
            }
            i = tryDecodeStringPlain(s, i, string_decode);
            i = tryDecodeStringRecursive(s,i,string_decode);
        }
        return i;
    }
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        decodeString(s, 0, sb);
        return sb.toString();
    }
}