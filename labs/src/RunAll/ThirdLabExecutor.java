package RunAll;

import MyClock.MyClockAnimated;

import java.io.BufferedReader;

public class ThirdLabExecutor extends BaseLabExecutor {
    public ThirdLabExecutor(String name, BufferedReader reader) {
        super(name, reader);
    }

    @Override
    public void action() {
        MyClockAnimated.main(new String[0]);
    }
}
