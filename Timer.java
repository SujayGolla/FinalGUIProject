import java.util.concurrent.TimeUnit;
import javax.swing.*;
import java.awt.*;

public class Timer {

    private long startTime;
    private long timePassed;
    private long secondsPassed;
    private long minutes;
    private JLabel time;
    private boolean startTimer;
    private long previousTimePassed;

    public Timer() throws InterruptedException{

        if (startTimer) {
            minutes = 0;
            startTime = System.currentTimeMillis();

            while(previousTimePassed/1000 + timePassed/1000 != 300) {
                TimeUnit.SECONDS.sleep(1);
                timePassed = System.currentTimeMillis() - startTime;
                secondsPassed = timePassed/1000;

                if (secondsPassed == 60) {
                    startTime = System.currentTimeMillis();
                    secondsPassed = 0;
                }

                if (secondsPassed % 60 == 0)
                    minutes++;
            }

            time = new JLabel(minutes+":"+secondsPassed);
        }
    }

    public long[] getTimePassed(){
        long[] timeP = {minutes, secondsPassed};
        return timeP;
    }

    public void startTimer() {
        startTimer = true;
    }

    public void pauseTimer() {
        setTimePassed();
        this.previousTimePassed = timePassed;
        startTimer = false;
    }

    public void setTimePassed() {
        if (startTimer) {
            this.timePassed = System.currentTimeMillis() - startTime + previousTimePassed;
        }
    }

}
