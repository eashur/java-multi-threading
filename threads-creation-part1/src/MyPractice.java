public class MyPractice {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //code that runs in this thread
                System.out.println("this is thread: " +Thread.currentThread().getName());

            }
        });

        thread.setName("New Worker Thread");
        System.out.println("Before new Thread started ====>" +Thread.currentThread().getName());

        thread.start();

        Thread.sleep(10000);
        System.out.println("After new Thread started ====>" +Thread.currentThread().getName());


        Thread thread1 = new Thread(() -> {

            System.out.println("this is thread1 " + Thread.currentThread().getName());
            throw new RuntimeException("Intentional exception on thread1");
        });

        thread1.setName("Misbehaving Thread");

        thread1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){


            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("A critical error happened in thread " + t.getName()
                        + " the error is " + e.getMessage());
            }
        });
    thread1.start();
    }
}
