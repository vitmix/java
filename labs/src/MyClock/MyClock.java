package MyClock;

import java.util.Calendar;

public class MyClock {
    private int hours;
    private int minutes;
    private int seconds;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    void update() {
        Calendar cal = Calendar.getInstance();

        hours = cal.get(Calendar.HOUR_OF_DAY);
        minutes = cal.get(Calendar.MINUTE);
        seconds = cal.get(Calendar.SECOND);
    }
}
