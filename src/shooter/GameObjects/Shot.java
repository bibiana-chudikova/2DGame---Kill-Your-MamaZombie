package shooter.GameObjects;

import java.awt.Image;

import shooter.Game.Handler;
import shooter.Game.UserInput;

/**
 * Class Shot creates shots that Player can shoot.
 */
public class Shot extends MovingGameObject {

    /**
     * Creates the shot of the Player.
     *
     * @param ID        ID of the object
     * @param positionX object position, X coordinate of left upper corner
     * @param positionY object position, Y coordinate of left upper corner
     * @param handler   handler
     */
    public Shot(double ID, int positionX, int positionY, Handler handler) {
        super(positionX, positionY, handler);
        this.ID = ID;
        super.loadImage();
        image = image.getScaledInstance(15, 5, Image.SCALE_FAST);
        
        this.destinationX = UserInput.getMouseX();
        this.destinationY = UserInput.getMouseY();
        this.width = 15;
        this.height = 5;
        this.velY = 1;
        this.velX = 1;

        // calculates the angle vectors, under which the shot will be shoot
        this.angleX = destinationX - positionX;
        this.angleY = destinationY - positionY;

        // calculates the movement vectors of the shot
        double distance = Math.sqrt(Math.pow(angleX, 2) + Math.pow(angleY, 2));
        vecX =  (angleX * 20 / distance);
        vecY =  (angleY * 20 / distance);
        this.rectangle.setBounds(positionX, positionY, 10, 10);
    }

    /**
     * Detects the collision with Wall X.
     */
    @Override
    public void collisionWithWallX() {
        handler.removeObject(this);
    }

    /**
     * Detects the collision with Wall Y.
     */
    @Override
    public void collisionWithWallY() {
        handler.removeObject(this);
    }

    /**
     * Updates the direction of the shot.
     */
    public void findDirection() {
    }

    /**
     * Updates the rotation of the shot.
     */
    @Override
    public void updateRotation() {
        rotation = Math.atan2(angleY, angleX);
    }

    /**
     * Performs specific actions when collision between Shot and other game object occurs.
     *
     * @param gameObject game object
     */
    @Override
    public void performCollisionEvent(GameObject gameObject) {
        if (gameObject instanceof Enemy) {
            handler.removeObject(this);
        }
    }
}