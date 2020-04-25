package RunAll;

import lab1.Task2;

import java.io.BufferedReader;

public class FirstLabExecutor extends BaseLabExecutor {
    public FirstLabExecutor(String name, BufferedReader reader) {
        super(name, reader);
    }

    @Override
    public void action() {
        Task2.start(reader);
    }
}
