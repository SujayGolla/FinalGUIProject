import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TimerClass {
    private Timer timer;
    private long startTime;
    private long pausedTime;
    private boolean isRunning;
    private JPanel panel;

    public TimerClass(JPanel panel) {
        this.panel = panel;
        timer = new Timer();
        isRunning = false;
    }

    public void startTime() {
        if (!isRunning) {
            startTime = System.currentTimeMillis();
            timer.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    if (getTime()/1000 == 10) {
                        panel.repaint();
                    }
                }
            }, 0, 1000);
            isRunning = true;
        }
    }

    // other methods are the same

    public long getTime() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - startTime) / 1000;
    }
    public void pauseTime() {
        if (isRunning) {
            pausedTime = System.currentTimeMillis();
            timer.cancel();
            isRunning = false;
        }
    }

    public String getTimer() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;
        int minutes = (int) (elapsedTime / 1000) / 60;
        int seconds = (int) (elapsedTime / 1000) % 60;
        return minutes + ":" + seconds;
    }

    public boolean ifRunning() {
        return isRunning;
    }

    public void saveTime(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        writer.write(getTimer());
        writer.close();
    }
}