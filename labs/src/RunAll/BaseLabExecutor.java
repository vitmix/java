package RunAll;

import java.io.BufferedReader;

public class BaseLabExecutor implements Runnable {
    private String name;
    private Thread t;
    protected BufferedReader reader;

    BaseLabExecutor(String threadName, BufferedReader reader) {
        this.name = threadName;
        this.t = new Thread(this, name);
        this.reader = reader;
        System.out.println("Child thread " + name + " was created");
        t.start();
    }

    @Override
    public void run() {
        System.out.println("Entering child thread with name " + name);
        action();
        System.out.println("Exiting child thread with name " + name);
    }

    public Thread getThread() {
        return t;
    }

    public void action() {
        System.out.println("Default action.");
    }
}
