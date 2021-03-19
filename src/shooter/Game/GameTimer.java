package shooter.Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Class GameTimer simulates the real timer, which measures the time of game played in the format of minutes:seconds.
 */
public class GameTimer {

    JLabel timeLabel;
    Timer timer;
    static int second;
    int minute;
    static String ddSecond, ddMinute;

    // formats the time to 00:00
    DecimalFormat decimalFormat = new DecimalFormat("00");
    private Handler handler;

    /**
     * Creates the timer.
     *
     * @param gameCanvas game canvas
     * @param handler handler
     */
    public GameTimer(GameCanvas gameCanvas, Handler handler) {
        this.handler = handler;
        timeLabel = new JLabel();
        timeLabel.setBounds(890, 5, 100, 20);
        timeLabel.setBackground(Color.cyan);
        timeLabel.setOpaque(true);

        gameCanvas.add(timeLabel);
        timeLabel.setText("           00:00");
        second = 0;
        minute = 0;

        Timer();
        timer.start();
    }

    /**
     * Starts the timer, which measures the time of game played.
     */
    public void Timer() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (handler.isRunning()) {
                    second++;
                    ddSecond = decimalFormat.format(second);
                    ddMinute = decimalFormat.format(minute);

                    timeLabel.setText("           " + ddMinute + ":" + ddSecond);

                    if (second == 60) {

                        second = 0;
                        minute++;

                        ddSecond = decimalFormat.format(second);
                        ddMinute = decimalFormat.format(minute);

                        timeLabel.setText("           " + ddMinute + ":" + ddSecond);
                    }
                }
            }
        });
    }

    /**
     * Returns the nubmer of seconds.
     *
     * @return the nubmer of seconds
     */
    public static String getDdSecond() {
        return ddSecond;
    }

    /**
     * Returns the nubmer of minutes.
     *
     * @return the nubmer of minutes.
     */
    public static String getDdMinute() {
        return ddMinute;
    }

    /**
     * Returns the nubmer of seconds - int.
     *
     * @return the nubmer of seconds - int
     */
    public static int getSecond(){
        return second;
    }
}
