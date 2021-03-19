package shooter.GameObjects;

import shooter.Game.Handler;

import java.awt.*;
import java.util.Random;

/**
 * Class MamaZombie represents the final boss in the game.
 */
public class MamaZombie extends Enemy {

    public static int positionEnemyX, positionEnemyY;
    public static boolean isMamaShooting = false;
    public int mamaZombieMaxHealth;

    /**
     * Creates MamaZombie in the game.
     *
     * @param ID              ID of the object
     * @param positionX       object position, X coordinate of left upper corner
     * @param positionY       object position, Y coordinate of left upper corner
     * @param newObjectWidth  height of new object
     * @param newObjectHeight width of new object
     * @param handler         handler
     */
    public MamaZombie(double ID, int positionX, int positionY, int newObjectWidth, int newObjectHeight, Handler handler) {
        super(ID, positionX, positionY, newObjectWidth, newObjectHeight, handler);
        this.radius = 50000000;
        this.healthZombie = (int)(handler.healthMama*handler.difficulty);
        this.mamaZombieMaxHealth = (int)(handler.healthMama*handler.difficulty);
    }

    @Override
    public void performCollisionEvent(GameObject gameObject) {
        if (gameObject instanceof Shot) {
            handler.healthMama -= 50;
            if (handler.healthMama <= 0) {
                handler.removeObject(this);
                handler.score += 25;
                handler.finalScore += 100;
            }
            handler.removeObject(gameObject);
        }
    }

    /**
     * Returns the X coordinate of Enemy.
     *
     * @return the X coordinate of Enemy
     */
    public static int getPositionEnemyX() {
        return positionEnemyX;
    }

    /**
     * Returns the Y coordinate of Enemy.
     *
     * @return the Y coordinate of Enemy
     */
    public static int getPositionEnemyY() {
        return positionEnemyY;
    }

    /**
     * Draws the MamaZombie.
     *
     * @param gr graphic canvas.
     */
    public void drawObject(Graphics gr) {
        super.drawObject(gr);
        drawMamaZombieInfo(gr);
    }

    /**
     * Draws the information about MamaZombie; health.
     *
     * @param gr graphic canvas
     */
    private void drawMamaZombieInfo(Graphics gr) {
        // draws health-bar
        gr.setColor(Color.lightGray);
        gr.fillRect(462, 5, 200, 22);
        gr.setColor(Color.GREEN);
        gr.fillRect(462, 5, (handler.healthMama / 5), 22);
        gr.setColor(Color.BLACK);
        gr.drawRect(462, 5, 200, 22);

        gr.setColor(Color.black);
        gr.drawString(handler.healthMama + " / " + mamaZombieMaxHealth, 530, 22);

        Random random = new Random();
        int counter = 0;
        int random2 = random.nextInt(60);

        // shoots random shots of MamaZombie
        if (counter == random2) {
            handler.enemyShoot();
            isMamaShooting = true;
        }
    }

    /**
     * Updates the MamaZombie object.
     */
    @Override
    public void updateGameObject() {
        positionEnemyX = centerX;
        positionEnemyY = centerY;
        super.updateGameObject();
    }
}
