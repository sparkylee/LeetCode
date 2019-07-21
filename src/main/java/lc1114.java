import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class lc1114 {

    class Foo {
        Lock semSecond;
        Lock semThird;

        public Foo() {
            this.semSecond = new ReentrantLock();
            this.semThird = new ReentrantLock();
            this.semSecond.lock();
            this.semThird.lock();
        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            this.semSecond.unlock();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            this.semSecond.lock();
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            this.semThird.unlock();
        }

        public void third(Runnable printThird) throws InterruptedException {
            this.semThird.lock();
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}
