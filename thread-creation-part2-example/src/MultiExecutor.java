import java.util.ArrayList;
import java.util.List;

public class MultiExecutor {
    private final List<Runnable> tasks;

    public MultiExecutor(List<Runnable> tasks) {
        this.tasks = tasks;
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        List<Thread> threads = new ArrayList<>(tasks.size());

        for (Runnable task: tasks) {

            Thread t = new Thread(task);
            threads.add(t);
        }
        for (Thread thread:threads) {
            thread.start();
        }
    }



    public static void main(String[] args) {
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Runnable r = () -> System.out.println("Hi from Thread");
            tasks.add(r);
        }



      MultiExecutor m = new MultiExecutor(tasks);
      m.executeAll();

    }
}