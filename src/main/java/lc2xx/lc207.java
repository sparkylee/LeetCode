package lc2xx;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public  class lc207
{
    @Test
    public void test1() {
        tc(2, new int[][]{{1, 0}});
    }

    @Test
    public void test2() {
        tc(2,new int [][]{{1,0},{0,1}});
    }

    @Test
    public void test3() {
        tc(3,new int [][]{{1,0},{2,1},{2,0}});
    }

    @Test
    public void test4() {
        tc(0,new int [][]{});
    }

    @Test
    public void test5() {
        tc(1,new int [][]{});
    }

    private void tc(int numCourses, int[][] prerequisites) {
        Solution s = new Solution();
        int [] result = s.findOrder(numCourses,prerequisites);
        for (int i = 0; i < result.length; i++)
            System.out.print(result[i]+" ");
        System.out.println();
    }

    class Solution {
        int [] courseOrder ;
        int count = 0;

        class Course {
            boolean finished = false;
            List<Integer> requirings = new ArrayList<>();//outward arrows
            List<Integer> prerequisites = new ArrayList<>();//inward arrows
            int prerequisites_finished_count = 0;
        }

        private void takeCourse(Course[] courses, int c) {
            Course course = courses[c];
            if(course.finished || course.prerequisites_finished_count!=course.prerequisites.size())
                return;
            course.finished = true;
            courseOrder[this.count] = c;
            this.count++;
            for (int i : course.requirings) {
                courses[i].prerequisites_finished_count ++;
                takeCourse(courses,i);
            }

        }

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            if(numCourses<=0) return new int[0];
            courseOrder = new int[numCourses];

            Course [] courses = new Course[numCourses];
            for (int i = 0; i < courses.length; i++)
                courses[i] = new Course();

            for (int i = 0; i < prerequisites.length; i++) {
                int [] prereq = prerequisites[i];
                int required = prereq[1];
                int requiring   = prereq[0];
                courses[requiring].prerequisites.add(required);
                courses[required].requirings.add(requiring);
            }
            for (int i = 0; i < courses.length; i++)
                takeCourse(courses,i);

            return this.count < numCourses? new int[0] :  courseOrder;

        }
    }


}
