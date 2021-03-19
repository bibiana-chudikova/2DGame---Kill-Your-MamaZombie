package shooter.GameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;

import shooter.Game.Handler;

/**
 * Class Wall creates the game object - Wall, that players and enemies can't get through.
 */
public class Wall extends GameObject {

    /**
     * Creates the wall in the game.
     *
     * @param ID              ID of the object
     * @param positionX       object position, X coordinate of left upper corner
     * @param positionY       object position, Y coordinate of left upper corner
     * @param newObjectHeight height of new object
     * @param newObjectWidth  width of new object
     * @param handler         handler
     */
    public Wall(double ID, int positionX, int positionY, int newObjectWidth, int newObjectHeight, Handler handler) {
        super(positionX, positionY, handler);
        this.ID = ID;
        this.width = newObjectWidth;
        this.height = newObjectHeight;
        super.loadImage();
    }

    /**
     * Draws the Wall.
     *
     * @param g2 graphic canvas
     */
    @Override
    public void drawObject(Graphics g2) {
        Graphics2D g = (Graphics2D) g2.create();
        g.drawImage(image, positionX, positionY, null);
        g.dispose();
    }
}