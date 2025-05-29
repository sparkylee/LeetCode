class Queue {
public:
    static const int MAX_SIZE = 10000;
    int q[MAX_SIZE];
    int i = 0;
    int j = 0;
    int incr(int k) 
    {
        k = k + 1;
        if(k>=MAX_SIZE)
        {
            k = k - MAX_SIZE;
        }
        return k;
    }
    int decr(int k) 
    {
        k = k - 1;
        if(k < 0)
        {
            k = k + MAX_SIZE;
        }
        return k;
    }
    // Push element x to the back of queue.
    void push(int x) {
        q[j] = x;
        j = decr(j);
    }

    // Removes the element from in front of queue.
    void pop(void) {
        i = decr(i);
    }

    // Get the front element.
    int peek(void) {
        return q[i];
    }

    // Return whether the queue is empty.
    bool empty(void) {
        return i == j;
    }
};