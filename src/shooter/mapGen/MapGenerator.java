package shooter.mapGen;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class MapGenerator creates map generator window.
 */
public class MapGenerator extends JFrame {

    private JPanel contentPane;

    /**
     * Runs the app frame.
     */
    public void run() {
        MapGenerator frame = new MapGenerator();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("Map Generator");
    }

    /**
     * Create JFrame window of MapGenerator.
     */
    public MapGenerator() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1064, 960);
        contentPane = new ContentPanel();
        setContentPane(contentPane);
    }
}