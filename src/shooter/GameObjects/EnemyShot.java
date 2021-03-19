package shooter.GameObjects;

import shooter.Game.Handler;

/**
 * Class Shot creates shots that MamaZombie, the final boss, can shoot.
 */
public class EnemyShot extends MovingGameObject {

    /**
     * Creates the shot of the MamaZombie
     *
     * @param ID        ID of the object
     * @param positionX object position, X coordinate of left upper corner
     * @param positionY object position, Y coordinate of left upper corner
     * @param handler   handler
     */
    public EnemyShot(double ID, int positionX, int positionY, Handler handler) {
        super(positionX, positionY, handler);
        this.ID = ID;
        super.loadImage();
        this.destinationX = handler.getPositionPlayerX();
        this.destinationY = handler.getPositionPlayerY();
        this.width = 513 / 10;
        this.height = 173 / 10;
        this.velX = 1;
        this.velY = 1;

        // calculates the angle vectors, under which the shot will be shoot
        this.angleX = destinationX - positionX;
        this.angleY = destinationY - positionY;

        // calculates the movement vectors of the shot
        double distance = Math.sqrt(Math.pow(angleX, 2) + Math.pow(angleY, 2));
        vecX = (angleX * 20 / distance);
        vecY = (angleY * 20 / distance);
    }

    /**
     * Detects the collision with Wall X.
     */
    @Override
    public void collisionWithWallX() {
        super.collisionWithWallX();
        handler.removeObject(this);
        MamaZombie.isMamaShooting = false;
    }

    /**
     * Detects the collision with Wall Y.
     */
    @Override
    public void collisionWithWallY() {
        super.collisionWithWallY();
        handler.removeObject(this);
        MamaZombie.isMamaShooting = false;
    }

    /**
     * Performs specific actions when collision between EnemyShot and other game object occurs.
     *
     * @param gameObject game object
     */
    @Override
    public void performCollisionEvent(GameObject gameObject) {
        if (gameObject instanceof Player) {
            handler.removeObject(this);
        }
    }

    /**
     * Updates the rotation of the shot.
     */
    @Override
    public void updateRotation() {
        rotation = Math.atan2(angleY, angleX);
    }

    /**
     * Updates the direction of the shot.
     */
    @Override
    public void findDirection() {
    }
}
