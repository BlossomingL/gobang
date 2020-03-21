package Model;

import javax.swing.*;

/**
 * Created by Blossoming on 2016/12/5.
 */
public class timerThread extends Thread{
    private JLabel label;
    public timerThread(JLabel label)
    {
        this.label=label;
    }
    public void run() {
        long startTime=System.currentTimeMillis();
        while(true)
        {
            long currentTime=System.currentTimeMillis();
            long time=currentTime-startTime;
            label.setText(format(time));
            if(this.isInterrupted())
            {
                break;
            }
        }
    }
    private String format(long elapsed) {
        int hour, minute, second, milli;

        milli = (int) (elapsed % 1000);
        elapsed = elapsed / 1000;

        second = (int) (elapsed % 60);
        elapsed = elapsed / 60;

        minute = (int) (elapsed % 60);
        elapsed = elapsed / 60;

        hour = (int) (elapsed % 60);

        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
