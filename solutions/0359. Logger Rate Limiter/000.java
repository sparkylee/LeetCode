class Logger {
    class T2M {
        public int timestamp;
        public HashSet<String> messages;
        T2M() {
            this.timestamp = 0;
            messages = new HashSet<>();
        }
    }
    ArrayList<T2M> t2mList;
    int i;
    public Logger() {
        t2mList = new ArrayList<T2M>(10);
        for (int i = 0; i < 10; i++) {
            t2mList.add(new T2M());
        }
        i = 0;
    }
    // void print(){
    //     for(T2M t2m: t2mList) {
    //         if(!t2m.messages.isEmpty()) {
    //             System.out.print(t2m.timestamp+": ");
    //             for(String str: t2m.messages) {
    //                   System.out.print(str+" ");
    //             }
    //             System.out.println();
    //         }
    //     }
    // }
    public boolean shouldPrintMessage(int timestamp, String message) {
        // System.out.println("i="+i+" timestamp="+timestamp+" message="+message);
        T2M t2m = t2mList.get(i);
        int delta = timestamp - t2m.timestamp;
        // if(timestamp==10) {
        //     print();
        // }
        int i_next = (i+delta)%10;
        for(int j = 0; j < 10; j++) {
            int k = (i_next + 10 - j) % 10;
            // if(timestamp==10) {
            //     System.out.println("k="+k);
            // }
            t2m = t2mList.get(k);
            if(timestamp - t2m.timestamp < 10 && t2m.messages.contains(message)) {
                return false;
            }
            // if(timestamp - t2m.timestamp >= 10 && !t2m.messages.isEmpty()) {
            //    break;
            // }
        }
        T2M t2m_next = t2mList.get(i_next);
        if(t2m_next.timestamp != timestamp) {
            t2m_next.messages.clear();
        }
        t2m_next.timestamp = timestamp;
        t2m_next.messages.add(message);
        i  = i_next;
        return true;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */