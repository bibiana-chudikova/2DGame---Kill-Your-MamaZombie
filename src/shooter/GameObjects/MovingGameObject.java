package shooter.GameObjects;

import java.awt.Graphics;
import java.awt.Graphics2D;

import shooter.Game.Handler;

/**
 * Abstract class MovingGameObject represents the moving objects in game that extends GameObject class.
 */
public abstract class MovingGameObject extends GameObject {
    // variables that are used only by moving objects - shots, player, enemies
    // variables needed to calculate the angle of rotation of the image
    protected double angleX;
    protected double angleY;
    protected double rotation;
    protected double destinationX;
    protected double destinationY;
    // variables needed for object movement
    protected double vecX;
    protected double vecY;
    protected double velX;
    protected double velY;
    
    /**
     *  different types of objects behave differently when they collide with another objects
     * @param gameObject game object
     */
    public abstract void performCollisionEvent(GameObject gameObject);

    /**
     *  updates the angles depending on object type
     */
    public abstract void findDirection();

    /**
     * Creates the moving object in the game.
     *
     * @param positionX object position, X coordinate of left upper corner
     * @param positionY object position, Y coordinate of left upper corner
     * @param handler   handler
     */
    public MovingGameObject(int positionX, int positionY, Handler handler) {
        super(positionX, positionY, handler);
    }

    /**
     * Updates the direction, rotation and movement of the game object.
     */
    public void updateGameObject() {
        findDirection();
        updateRotation();
        detectCollisionOfMovingObjects();

        moveX();
        detectCollisionWithWallsX();
        moveY();
        detectCollisionWithWallsY();
        
        rectangle.setBounds(positionX, positionY, width, height);
        this.centerX = (int) rectangle.getCenterX();
        this.centerY = (int) rectangle.getCenterY();
    }

    /**
     * Updates the X coordinate of the game object.
     */
    public void moveX() {
        positionX += (int) vecX * velX;
    }

    /**
     * Updates the Y coordinate of the game object.
     */
    public void moveY() {
        positionY += (int) vecY * velY;
    }
    
    /**
     * Detects the collision with Wall X.
     */
    public void collisionWithWallX() {
        positionX -= (int) vecX * velX;
    }

    /**
     * Detects the collision with Wall Y.
     */
    public void collisionWithWallY() {
        positionY -= (int) vecY * velY;
    }
    
    /**
     * Detects the collision of moving game objects with the static ones.
     */
    public void detectCollisionWithWallsX() {
        for (int i = 0; i < handler.staticObjects.size(); i++) {
            GameObject gameObject = handler.staticObjects.get(i);
            if (gameObject instanceof Wall) {
                if (getBounds().intersects(gameObject.getBounds())) {
                    collisionWithWallX();
                }
            }
        }
    }

    /**
     * Detects the collision of moving game objects with the static ones.
     */
    public void detectCollisionWithWallsY() {
        for (int i = 0; i < handler.staticObjects.size(); i++) {
            GameObject gameObject = handler.staticObjects.get(i);
            if (gameObject instanceof Wall) {
                if (getBounds().intersects(gameObject.getBounds())) {
                    collisionWithWallY();
                }
            }
        }
    }

    /**
     * Updates the angle of object rotation.
     */
    public void updateRotation() {
        angleX = destinationX - centerX;
        angleY = destinationY - centerY;
        rotation = Math.atan2(angleY, angleX);
    }

    /**
     * Detects the collisions between the moving game objects.
     */
    private void detectCollisionOfMovingObjects() {
        for (int i = 0; i < handler.movingObjects.size(); i++) {
            GameObject gameObject = handler.movingObjects.get(i);
            if (gameObject != this) {
                if (getBounds().intersects(gameObject.getBounds())) {
                    performCollisionEvent(gameObject);
                    performCollisionEvent(this);
                }
            }
        }
    }

    /**
     * Draws the game object.
     *
     * @param gr graphic canvas.
     */
    @Override
    public void drawObject(Graphics gr) {
        Graphics2D g = (Graphics2D) gr.create();
        //g.draw(getBounds());
        g.rotate(rotation, centerX, centerY);
        super.drawObject(g);
        g.dispose();
    }
}
