import org.junit.Test;

import java.util.Stack;

public class lc946 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0, j = 0;
        while (true) {
            if (i == pushed.length && j == popped.length && stack.empty()) return true;
            if (j >= popped.length) return false;
            while (stack.empty() || stack.peek() != popped[j]) {
                if (i >= pushed.length) return false;
                stack.push(pushed[i]);
                i++;
            }
            stack.pop();
            j++;

        }
    }
}
