package semaphores;

import java.util.concurrent.Semaphore;

public final class AABCC {

    private static final int COUNT = 30;
    private static int COUNTER = 0;
    private static final int STEP = 5;
    private static final Semaphore a = new Semaphore(0, true);
    private static final Semaphore b = new Semaphore(0, true);
    private static final Semaphore c = new Semaphore(0, true);

    public static void main(String[] args) {
        new A().start();
        new B().start();
        new C().start();
    }

    private static final class A extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < COUNT; i++) {
                    a.acquire();
                    myPrint("A ");
                    b.release();
                }
            } catch (InterruptedException ex) {
            }
        }
    }

    private static final class B extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < COUNT; i++) {
                    a.release();
                    b.acquire();
                    a.release();
                    b.acquire();
                    myPrint("B ");
                    c.release();
                    b.acquire();
                    c.release();
                    b.acquire();
                }
            } catch (InterruptedException ex) {
            }
        }
    }

    private static final class C extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < COUNT; i++) {
                    c.acquire();
                    myPrint("C ");
                    b.release();
                }
            } catch (InterruptedException ex) {
            }
        }
    }

    private static synchronized void myPrint(String s) {
        COUNTER++;
        System.out.print(s);
        if (COUNTER == STEP) {
            COUNTER = 0;
            System.out.println();
        }
    }

}
