package MyClock;

import java.awt.HeadlessException;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

public class MyClockAnimated extends JFrame {

    public static void main(String[] args) {

        MyClockAnimated clock = new MyClockAnimated();
    }

    public MyClockAnimated() {
        MyClock clock = new MyClock();
        MyClockPanel clockPanel = new MyClockPanel(clock);

        ///Set up a timer to allow for constant repaints
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){

            @Override
            public void run() {
                clock.update();
                clockPanel.repaint();
            }

        }, 0, 1000);


        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("My Demo Clock");
        this.add(clockPanel);
        this.setMinimumSize(clockPanel.getMinimumSize());
        this.pack();
    }
}
