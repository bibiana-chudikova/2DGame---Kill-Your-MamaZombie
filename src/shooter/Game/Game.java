package shooter.Game;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JFrame;

/**
 * Class Game creates the game window.
 */
public class Game extends JFrame {

    private Properties gameProperties;
    private int WINDOW_HEIGHT;
    private int WINDOW_WIDTH;
    public static SoundEffect soundEffectBegin = new SoundEffect();

    /**
     * Creates JFrame window of the game.
     *
     * @throws FileNotFoundException FileNotFoundException
     */
    public Game() throws FileNotFoundException {
        loadGameSettings();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WINDOW_HEIGHT, WINDOW_WIDTH);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Kill Your MamaZombie");

        // creates JPanel (GameCanvas) and add it to window
        GameCanvas hra = new GameCanvas(gameProperties);
        add(hra, BorderLayout.CENTER);

        // creates and add KeyListener to window
        UserInput klavesnica = new UserInput(GameCanvas.handler);
        addKeyListener(klavesnica);

        // addapts the size of JFrame window to to size of components (their prefferedSize) in the window (JPanel - GameCanvas)
        pack();
    }

    /**
     * Loads game settings.
     */
    private void loadGameSettings() {
        try {
            InputStream input = new FileInputStream("resources/gameConfig.properties");
            gameProperties = new Properties();
            gameProperties.load(input);
            this.WINDOW_HEIGHT = Integer.parseInt(gameProperties.getProperty("WindowHeight"));
            this.WINDOW_WIDTH = Integer.parseInt(gameProperties.getProperty("WindowWidth"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs the game window.
     */
    public void run() {
        setVisible(true);
        soundEffectBegin.setFileBegin();
        soundEffectBegin.play();
    }

    /**
     * Turns off the game window.
     */
    public void shutDown() {
        setVisible(false);
    }
}
