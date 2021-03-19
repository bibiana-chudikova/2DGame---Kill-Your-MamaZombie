package shooter.GameObjects;

import java.awt.Image;

import shooter.Game.Handler;
import shooter.Game.SoundEffect;

/**
 * Class Item creates different types of items in the game.
 */
public class Item extends MovingGameObject {

    public static int numberOfHealthKits, numberOfAmmoKits = 0;
    public static SoundEffect soundEffectItemPickUp = new SoundEffect();

    /**
     * Creates item in the game.
     *
     * @param ID              ID of the object
     * @param positionX       object position, X coordinate of left upper corner
     * @param positionY       object position, Y coordinate of left upper corner
     * @param newObjectHeight height of new object
     * @param newObjectWidth  width of new object
     * @param handler         handler
     */
    public Item(double ID, int positionX, int positionY, int newObjectWidth, int newObjectHeight, Handler handler) {
        super(positionX, positionY, handler);
        this.ID = ID;
        this.width = newObjectWidth;
        this.height = newObjectHeight;
        super.loadImage();
        image = image.getScaledInstance(16, 16, Image.SCALE_FAST);
    }

    /**
     * Performs specific actions when collision between Item and other game object occurs.
     *
     * @param gameObject game object
     */
    @Override
    public void performCollisionEvent(GameObject gameObject) {
        if (gameObject instanceof Player) {

            // adds 10 charges into magazine
            if (ID == 4.1) {
                handler.ammo += 5;
                handler.removeObject(this);
                numberOfAmmoKits--;

                soundEffectItemPickUp.setFileItemPickUp();
                soundEffectItemPickUp.play();
            }

            // heals player for maximum of 50 health
            if (ID == 4.2) {
                if (handler.healthPlayer <= 50) {
                    handler.healthPlayer += 50;
                } else if (handler.healthPlayer < 100) {
                    int health2 = 100 - handler.healthPlayer;
                    handler.healthPlayer += health2;
                }
                handler.removeObject(this);
                numberOfHealthKits--;

                soundEffectItemPickUp.setFileItemPickUp();
                soundEffectItemPickUp.play();
            }
        }
    }

    /**
     * Updates the direction of the Items.
     */
    @Override
    public void findDirection() {
        destinationX = centerX;
        destinationY = centerY;
    }
}
