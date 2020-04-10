package application;

public class Multithreading {

    public static void main(String[] args) {
   /*     new SimpleThread(20);
        new SimpleThread(20); */
        Value value = new Value(20);
        new OtherThread(value, "Daniel");
        new OtherThread(value, "Fritz");
    }

    static class SimpleThread implements Runnable {
        private int value;
        private transient Thread thread;

        public SimpleThread(int value) {
            this.value = value;
            thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run() {
            while (value > 0) {
                value--;
                System.out.println(value);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Value {
        int value;

        public Value(int value) {
            this.value = value;
        }
        public synchronized void decrement() {
            value--;
            System.out.println(value);
        }

        public synchronized int getValue() {
            return value;
        }
    }

    static class OtherThread implements Runnable {
        private Value value;
        private transient Thread thread;

        public OtherThread(Value value, String threadName) {
            this.value = value;
            thread = new Thread(this, threadName);
            thread.start();
        }

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                value.decrement();

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
