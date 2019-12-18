package com.github.tyurinden;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class TestClass {
    private StringBuffer data;

    public TestClass() {
        this.data = new StringBuffer();
        for (long i = 0; i < 50_000_000; i++) {
            this.data.append('x');
        }
    }

    @Override
    protected void finalize() {
        System.out.println("The finalize() method is called on the TestClass object");
    }

    private static class MyPhantomReference<T> extends PhantomReference<T> {

        /**
         * Creates a new phantom reference that refers to the given object and
         * is registered with the given queue.
         *
         * <p> It is possible to create a phantom reference with a <tt>null</tt>
         * queue, but such a reference is completely useless: Its <tt>get</tt>
         * method will always return null and, since it does not have a queue, it
         * will never be enqueued.
         *
         * @param obj   the object the new phantom reference will refer to
         * @param queue the queue with which the reference is to be registered,
         */
        public MyPhantomReference(T obj, ReferenceQueue<? super T> queue) {
            super(obj, queue);
            Thread thread = new MyPollingThread<T>(queue);
            thread.start();
        }

        public void cleanup() {
            System.out.println("MyPhantomReference -> cleanup() invoked.");
            clear();
        }

        private static class MyPollingThread<T> extends Thread {
            private ReferenceQueue<? super T> referenceQueue;

            public MyPollingThread(ReferenceQueue<? super T> queue) {
                referenceQueue = queue;
            }

            @Override
            public void run() {
                System.out.println("MyPollingThread launched!");
                Reference<?> ref;
                while ((ref = referenceQueue.poll()) == null) {
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (ref instanceof MyPhantomReference<?>) {
                    ((MyPhantomReference<?>) ref).cleanup();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        System.out.println("sleep 10 sec");
        Thread.sleep(10_000);
        Reference<TestClass> ref = new MyPhantomReference<>(new TestClass(), new ReferenceQueue<>());
        System.out.println("ref = " + ref);
//        System.out.println("sleep 5 sec");
        Thread.sleep(5000);
        System.out.println("System.gc()");
        System.gc();
//        System.out.println("sleep 3 sec");
        Thread.sleep(3000);
        System.out.println("ref = " + ref);
        System.out.println("System.gc()");
        System.gc();
//        System.out.println("sleep 3 sec");
        Thread.sleep(3000);
        System.out.println("System.gc()");
        System.gc();
        System.out.println("ref = " + ref);
    }
}

