import org.junit.Test;

public class lc937 {
    @Test
    public void test() {
        String[] logs = {"a1 9 2 3 1", "g1 act car", "zo4 4 7", "ab1 off key dog", "a8 act zoo"};
        reorderLogFiles(logs);
    }

    private int getStart(String log) {
        int i = 0;
        while (log.charAt(i) != ' ') i++;
        return i + 1;
    }

    private boolean isDigitLog(String log, int start) {
        char c = log.charAt(start);
        return c >= '0' && c <= '9';
    }

    private int look4DigitLog(String[] logs, int[] starts, int i) {
        while (i >= 0) {
            if (isDigitLog(logs[i], starts[i]))
                return i;
            i--;
        }
        return i;
    }

    private void swap(String[] logs, int[] starts, int i, int j) {
        String tmp = logs[i];
        logs[i] = logs[j];
        logs[j] = tmp;
        int x = starts[i];
        starts[i] = starts[j];
        starts[j] = x;
    }

    private void sort(String[] logs, int[] starts, int end) {
        for (int i = 0; i < end; i++) {
            for (int j = i + 1; j <= end; j++) {
                if (compare(logs, starts, i, j))
                    swap(logs, starts, i, j);
            }
        }
    }

    private boolean compare(String[] logs, int[] starts, int i, int j) {
        String li = logs[i];
        String lj = logs[j];
        int ii, jj;
        int k = 0;
        while (true) {
            ii = starts[i] + k;
            jj = starts[j] + k;
            if (ii == li.length() - 1 && jj == lj.length() - 1)
                break;
            if (ii == li.length() - 1) return false;
            if (jj == lj.length() - 1) return true;
            if (li.charAt(ii) > lj.charAt(jj)) return true;
            if (li.charAt(ii) < lj.charAt(jj)) return false;
            k++;
        }
        k = 0;
        while (true) {
            char ci = li.charAt(k);
            char cj = lj.charAt(k);
            if (ci == ' ' && cj == ' ') return false;
            if (cj == ' ') return true;
            if (ci == ' ') return false;
            if (ci > cj) return true;
            if (ci < cj) return false;
            k++;
        }
    }

    public String[] reorderLogFiles(String[] logs) {
        int[] starts = new int[logs.length];
        for (int i = 0; i < logs.length; i++)
            starts[i] = getStart(logs[i]);
        int i = logs.length - 1;
        while (i >= 0) {
            int j = look4DigitLog(logs, starts, i);
            if (j == -1)
                break;
            if (i != j)
                swap(logs, starts, i, j);
            i--;
        }
        int end = i;
        sort(logs, starts, end);
        return logs;
    }
}
