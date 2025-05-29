class Solution {
     class Course
        {
            boolean finished = false;
            List<Integer> requirings = new ArrayList<>();//outward arrows
            List<Integer> prerequisites = new ArrayList<>();//inward arrows
            int prerequisites_finished_count = 0;
        }
        private void takeCourse(Course [] courses,int c)
        {
            Course course = courses[c];
            if(course.finished || course.prerequisites_finished_count!=course.prerequisites.size())
                return;
            course.finished = true;
            for(int i : course.requirings)
            {
                courses[i].prerequisites_finished_count ++;
                takeCourse(courses,i);
            }

        }
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            Course [] courses = new Course[numCourses];
            for(int i=0;i<courses.length;i++)
            {
                courses[i] = new Course();
            }
            for(int i=0;i<prerequisites.length;i++)
            {
                int [] prereq = prerequisites[i];
                int required = prereq[1];
                int requiring   = prereq[0];
                courses[requiring].prerequisites.add(required);
                courses[required].requirings.add(requiring);
            }
            ///
            ///
            for(int i=0;i<courses.length;i++)
                takeCourse(courses,i);

            for(int i=0;i<courses.length;i++)
                if(!courses[i].finished)
                    return false;
            return true;
        }
}