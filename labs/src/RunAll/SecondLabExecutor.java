package RunAll;

import java.io.BufferedReader;
import lab2.Task8;

public class SecondLabExecutor extends BaseLabExecutor {
    public SecondLabExecutor(String name, BufferedReader reader) {
        super(name, reader);
    }

    @Override
    public void action() {
        Task8.start(reader);
    }
}
