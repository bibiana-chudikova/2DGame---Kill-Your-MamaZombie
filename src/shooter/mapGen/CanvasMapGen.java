package shooter.mapGen;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Class CanvasMapGen serves as plain back-round for custom map.
 */
public class CanvasMapGen extends JPanel {

    private Timer timer = new Timer(60, e -> repaint());
    private MouseAdapterMapGen mouseAdapter;
    private HandlerMapGen handler;

    /**
     * Creates plain canvas for custom map.
     *
     * @param handler handler.
     */
    public CanvasMapGen(HandlerMapGen handler) {
        this.handler = handler;
        setBounds(10, 110, 1024, 800);
        setLayout(null);
        timer.start();

        this.mouseAdapter = new MouseAdapterMapGen(handler);
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }

    /**
     * Paints the map generator components.
     *
     * @param g graphic canvas
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paint(g2);
        handler.drawObjects(g2);
    }
}