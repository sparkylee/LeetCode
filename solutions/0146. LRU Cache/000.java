class LRUCache {
     class MyObj {
        int v;
        int k;
        MyObj(int k, int v) {
            this.k = k;
            this.v = v;

        }
        void set(int v) {
            this.v = v;
        }
        int get() {
            return this.v;
        }
        int getK() {
            return this.k;
        }
        MyObj next;
        MyObj prev;
        void setNext(MyObj next) {
            this.next = next;
        }
        MyObj getNext() {
            return this.next;
        }
        MyObj getPrev() {
            return this.prev;
        }
        void setPrev(MyObj prev) {
            this.prev = prev;
        }
    }
    int capacity;
    int count = 0;
    HashMap<Integer, MyObj> map;
    MyObj head, tail;
    private void addFirst(MyObj obj) {
        if (obj==null) {
            return;
        }
        if(this.head==null) {
            head = obj;
            tail = obj;
            return;
        } else {
            obj.setPrev(null);
            obj.setNext(this.head);
            this.head.setPrev(obj);
            this.head = obj;
        }
    }
    private void remove(MyObj obj) {
        if(obj==null) {
            return;
        }
        MyObj obj_prev = obj.prev;
        MyObj obj_next = obj.next;
        if (obj==this.tail) {
            this.tail = obj_prev;
        }
        if(obj==this.head){
            this.head = obj_next;
        }
        if(obj_prev != null) {
            obj_prev.setNext(obj_next);
        }
        if(obj_next != null) {
            obj_next.setPrev(obj_prev);
        }
    }
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }
    private void refresh(MyObj obj) {
        remove(obj);
        addFirst(obj);
    }
    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        MyObj obj = map.get(key);
        refresh(obj);
        return obj.get();
    }
    
    public void put(int key, int value) {
        MyObj obj = null;
        if (!map.containsKey(key)) {
            obj = new MyObj(key, value) ;
            this.count ++;
            if(this.count>this.capacity) {
                int key2remove = this.tail.getK();
                remove(this.tail);
                this.count--;
                map.remove(key2remove);
                // System.out.println("key2remove: " + key2remove);
                // System.out.println(Arrays.asList(map.keySet()).toString());
            }
            addFirst(obj);
            map.put(key, obj);
        }
        else
        {
             obj = map.get(key);
             obj.set(value);
             refresh(obj);
        }
           
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */