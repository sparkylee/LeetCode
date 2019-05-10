import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class minimumOnStack {
    @Test
    public void test()
    {
        String [] operations = {"push 10", "min", "push 5", "min", "push 8", "min", "pop", "min", "pop", "min"};
        int [] output = minimumOnStack(operations);
        System.out.println("df");
    }

    int[] minimumOnStack(String[] operations) {
        List<Integer> stack = new ArrayList();
        List<Integer> output = new ArrayList();


        for(int i=0;i<operations.length;i++)
        {
            String op = operations[i];
            String [] opsplits = op.split(" ");
            if(opsplits.length==2)
            {
                int value = Integer.valueOf(opsplits[1]);
                stack.add(value);
            }
            else
            {
                if(opsplits[0].equals("pop"))
                {
                    if(!stack.isEmpty())
                        stack.remove(stack.size()-1);
                }
                if(opsplits[0].equals("min"))
                {
                    if(stack.isEmpty()) continue;
                   int min =stack.get(0);
                   for(int j=1;j<stack.size();j++)
                   {
                       if(stack.get(j)<min) min = stack.get(j);
                   }
                    output.add(min);
                }
            }
        }
        int [] a = new int[output.size()];
        for(int i=0;i<output.size();i++) a[i]=output.get(i);
        return a;
    }
}
