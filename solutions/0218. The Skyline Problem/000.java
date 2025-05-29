class Solution {
     class Point
        {
            int i,h;
            boolean isLeft;
            Point next;
            Point(int i,int h,boolean isLeft)
            {
                this.i=i;
                this.h=h;
                this.isLeft = isLeft;
            }
        }
        private boolean isPointInsideBuilding(Point head,int [] building)
        {
            return head!=null && head.h<=building[2] && head.i >= building[0] && head.i <= building[1];
        }
        private Point addNewBuilding( List<int []> kpList,Point head,Point tail, int [] building)
        {
            int li = building[0],ri=building[1],h=building[2];
            Point enter=null,p=null, exit = null;
            enter = (!isPointInsideBuilding(head,building)) ? head:null;
            while (enter!=null && !isPointInsideBuilding(enter.next,building))
                enter = enter.next;
            exit= (enter==null)? head: enter.next;
            while(exit!=null && isPointInsideBuilding(head,building))
                exit = exit.next;
            if(head==null)
            {
                Point n1 = new Point(li,h,true),n2=new Point(ri,h,false),
                        n3=new Point(ri,0,true);
                n1.next = n2;
                n2.next=n3;
                return n1;
            }
            if(enter==null && exit==null  )
                return head;
            Point enter_intercept = enter==null? null: new Point(enter.i, h, true);
            Point exit_intercept = exit==null? null: new Point(ri,exit.h, true);
            Point corner = new Point(ri,h,true);

            if(enter!=null )
            {
                if(enter.i<ri) {
                    enter.next=
                    enter = enter.next;
                }
            }
            return head;
        }
        private Point addNewBuilding1(Point head, int [] building)
        {
            int li = building[0],ri=building[1],h=building[2];
            Point top_head=null,top_tail=null, LR_head = null;
            Point p = head;

            if(p!=null && p.h>h)
            {
                top_head=p;
                top_tail=p;
                while (p!=null && p.h>h)
                {
                    top_tail = p;
                    p=p.next;
                }
            }

            while (isPointInsideBuilding(p,building))
                p=p.next;
            LR_head = p;
            if(top_tail==null )
            {
                //include the left top corner point
                Point corner_tl = new Point(li,h,true);
                top_head = corner_tl;
                top_tail = corner_tl;
            }
            if(top_tail.h>h && top_tail.i<ri)
            {
                //include the top intercept point
                top_tail.next = new Point(top_tail.i,h,true);
                top_tail=top_tail.next;
            }

            if(top_tail.i < ri && (LR_head==null || LR_head.h!=h))
            {
                //include the top right corner point
                top_tail.next =  new Point(ri,h,false);
                top_tail = top_tail.next;
            }
            if(LR_head!=null )
            {
                if( LR_head.i != top_tail.i){
                    //include the right intercept point
                    top_tail.next = new Point(ri,LR_head.h,true);
                    top_tail = top_tail.next;
                }
                top_tail.next  = LR_head;
            }
            else
                top_tail.next = new Point(ri,0,true);


            return top_head;
        }
        public List<int[]> getSkyline(int[][] buildings) {
            List<int []> kpList = new ArrayList<>();
            Point head = null;
            for(int i=0;i<buildings.length;i++)
            {
                int [] building = buildings[i];
                while (head!=null && head.i< building[0])
                {
                    if(head.isLeft)
                    {
                        boolean diffFromPre = kpList.isEmpty() || kpList.get(kpList.size()-1)[1]!=head.h;
                        if(diffFromPre)
                            kpList.add(new int[]{head.i,head.h});
                    }
                    head=head.next;
                }
                head=addNewBuilding1(head,building);

            }
            while (head!=null )
            {
                if(head.isLeft)
                {
                    boolean diffFromPre = kpList.isEmpty() || kpList.get(kpList.size()-1)[1]!=head.h;
                    if(diffFromPre)
                        kpList.add(new int[]{head.i,head.h});
                }
                head=head.next;
            }
            return kpList;
        }
}