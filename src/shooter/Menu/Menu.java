package shooter.Menu;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import shooter.Game.Game;
import shooter.Game.SoundEffect;
import shooter.mapGen.MapGenerator;

/**
 * Class Menu creates the main frame for menu window.
 */
public class Menu extends JFrame {

    private ContentPanel contentPane;
    public static SoundEffect soundEffect;

    /**
     * Creates the window for menu.
     *
     * @throws IOException IOException
     */
    public Menu() throws IOException {
        setResizable(false);
        setTitle("Kill Your MamaZombie");
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        contentPane = new ContentPanel(this);
        setContentPane(contentPane);
    }

	/**
	 * Runs the menu.
	 */
	public void run() {
        setVisible(true);
        soundEffect = new SoundEffect();
        soundEffect.setFileMenuMusic();
        soundEffect.play();
    }

    /**
     * Turns off the menu.
     */
    public void shutDown() {
        setVisible(false);
        soundEffect.stopMusic();
    }

    /**
     * Runs the game without menu.
     */
    public void runWithoutMenu() {
        try {
            Game game = new Game();
            game.run();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Runs the map generator.
     */
    public void runMapGen() {
    	MapGenerator game = new MapGenerator();
        game.run();
    }
}
