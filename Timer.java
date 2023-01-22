import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import java.awt.*;

public class Timer {

    private long startTime;
    private long timePassed;
    private long secondsPassed;
    private long minutes;
    private boolean isRunning;
    public Timer() throws InterruptedException{

        if (isRunning) {
            minutes = 0;
            startTime = System.currentTimeMillis();

            while(timePassed/1000 != 300) {
                TimeUnit.SECONDS.sleep(1);
                
            }
        }
    }

    public long[] getTimePassed(){
        long[] timeP = {minutes, secondsPassed};
        return timeP;
    }

    public void startTimer() {

        isRunning = true;
    }

    public void pauseTimer() {

        isRunning = false;

    }
    
    public String getTime() {
        timePassed = System.currentTimeMillis() - startTime;
        secondsPassed = timePassed/1000;

        if (secondsPassed == 60) {
            startTime = System.currentTimeMillis();
            secondsPassed = 0;
        }

        if (secondsPassed % 60 == 0)
            minutes++;

        return minutes+":"+secondsPassed;
    }

    public boolean ifRunning() {
        return isRunning;
    }

    public void saveTime(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(getTime());
        writer.close();
    }


}
