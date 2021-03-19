package shooter.Game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Class GameCanvas draws the game objects.
 */

public class GameCanvas extends JPanel {

    // timer responsible for updating and drawing of the game objects
    private Timer timer;
    protected static Handler handler;

    private Properties gameProperties;
    private int FPS;
    private int Height;
    private int Width;

    /**
     * Creates panel for the game.
     *
     * @param gameProperties properties of the game
     * @throws FileNotFoundException FileNotFoundException
     */
    public GameCanvas(Properties gameProperties) throws FileNotFoundException {
        this.gameProperties = gameProperties;
        this.FPS = Integer.parseInt(this.gameProperties.getProperty("FPS"));
        this.Height = Integer.parseInt(this.gameProperties.getProperty("WindowHeight"));
        this.Width = Integer.parseInt(this.gameProperties.getProperty("WindowWidth"));

        this.timer = new Timer(1000/FPS, e -> repaint());

        setLayout(null);
        setPreferredSize(new Dimension(Height, Width));

        // creates handler
        GameCanvas.handler = new Handler(timer);

        // loads map and the game objects
        MapLoader mapLoader = new MapLoader(handler);
        mapLoader.loadMap();

        // creates the MouseListener and KeyListener and adds it to the canvas
        UserInput UserInput = new UserInput(handler);
        addMouseListener(UserInput);
        addMouseMotionListener(UserInput);

        // creates and adds GameTimer
        new GameTimer(this, handler);

        // runs the game
        run();
    }

    /**
     * Draws the canvas components.
     */
    protected void paintComponent(Graphics g2) {
        Graphics2D g = (Graphics2D) g2;
        super.paintComponent(g);
        handler.updateGameObjects();
        handler.drawGameObjects(g);
        handler.drawPlayer(g);
    }

    /**
     * Runs the game.
     */
    public void run() {
        timer.start();
    }
}
