package lc11xx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class lc1114 {

    class Foo {

        private int c = 0;

        public Foo() {
            c = 0;
        }

        private void wait(int x) {
            while (!test(x)) {
            }
        }

        private synchronized boolean test(int x) {
            return x == this.c;
        }

        private synchronized void set(int x) {
            this.c = x;
        }

        public void first(Runnable printFirst) throws InterruptedException {
            wait(0);
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            set(1);
        }

        public void second(Runnable printSecond) throws InterruptedException {
            wait(1);
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            set(2);
        }

        public void third(Runnable printThird) throws InterruptedException {
            wait(2);
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            set(0);
        }
    }
}
