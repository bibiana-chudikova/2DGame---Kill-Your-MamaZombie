package shooter.GameObjects;

import shooter.Game.Handler;

/**
 * Class Grass creates the basic back-round of the game, on which players and enemies can move.
 */
public class Grass extends GameObject {

    boolean isSpawnPoint = false;

    /**
     * Creates Grass in the game.
     *
     * @param ID              ID of the object
     * @param positionX       object position, X coordinate of left upper corner
     * @param positionY       object position, Y coordinate of left upper corner
     * @param newObjectWidth  height of new object
     * @param newObjectHeight width of new object
     * @param handler         handler
     */
    public Grass(double ID, int positionX, int positionY, int newObjectWidth, int newObjectHeight, Handler handler) {
        super(positionX, positionY, handler);
        this.ID = ID;

        if (ID > 0) {
            isSpawnPoint = true;
        }

        this.width = newObjectWidth;
        this.height = newObjectHeight;
        
        super.loadImage();
        
    }

    /**
     * Returns the X coordinate of the Grass.
     *
     * @return the X coordinate of the Grass
     */
    public int getX() {
        return positionX;
    }

    /**
     * Returns the Y coordinate of the Grass.
     *
     * @return the Y coordinate of the Grass
     */
    public int getY() {
        return positionY;
    }
}
