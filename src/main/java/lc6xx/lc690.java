package lc6xx;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class lc690 {
    class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    }

    ;

    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> employeeMap = new HashMap<>();
        for (Employee e : employees)
            employeeMap.put(e.id, e);
        Stack<Employee> stack = new Stack<>();
        stack.push(employeeMap.get(id));
        int sum = 0;
        while (!stack.empty()) {
            Employee e = stack.pop();
            sum += e.importance;
            for (int eid : e.subordinates)
                stack.push(employeeMap.get(eid));
        }
        return sum;
    }
}
