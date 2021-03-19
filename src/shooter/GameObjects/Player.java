package shooter.GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;

import shooter.Game.Handler;
import shooter.Game.UserInput;

/**
 * Class Player creates main player in the game.
 */
public class Player extends MovingGameObject {
    /**
     * Creates the main player.
     *
     * @param positionX object position, X coordinate of left upper corner
     * @param positionY object position, Y coordinate of left upper corner
     * @param handler   handler
     */
    public Player(int positionX, int positionY, Handler handler) {
        super(positionX, positionY, handler);
        loadParameters();
    }

    /**
     * Loads the parameters of the player.
     */
    private void loadParameters() {
        try (InputStream input = new FileInputStream("resources/playerConfig.properties")) {
            Properties playerProp = new Properties();
            playerProp.load(input);

            image = ImageIO.read(new File(playerProp.getProperty("ImagePath")));
            image = image.getScaledInstance(32, 32, Image.SCALE_FAST);
            this.ID = Integer.parseInt(playerProp.getProperty("ID"));
            this.height = Integer.parseInt(playerProp.getProperty("Height"));
            this.width = Integer.parseInt(playerProp.getProperty("Width"));
            this.velX = Integer.parseInt(playerProp.getProperty("Speed"));
            this.velY = Integer.parseInt(playerProp.getProperty("Speed"));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Performs specific actions when collision between PLayer and other game object occurs.
     *
     * @param gameObject game object
     */
    @Override
    public void performCollisionEvent(GameObject gameObject) {
        if (gameObject instanceof Enemy) {
            handler.healthPlayer--;
            handler.damageTaken++;
            if (handler.healthPlayer <= 0) {
                handler.healthPlayer = 0;
            }
        }

        if (gameObject instanceof MamaZombie) {
            handler.healthPlayer--;
            handler.damageTaken++;
            if (handler.healthPlayer <= 0) {
                handler.healthPlayer = 0;
            }
        }

        if (gameObject instanceof EnemyShot) {
            handler.healthPlayer -= 20;
            handler.damageTaken += 20;
            if (handler.healthPlayer <= 0) {
                handler.healthPlayer = 0;
            }
        }
    }

    /**
     * Draws the player.
     *
     * @param gr graphic canvas
     */
    @Override
    public void drawObject(Graphics gr) {
        super.drawObject(gr);
        drawPlayerInfo(gr);
    }

    /**
     * Draws the information about Player; health, ammo, score, damage.
     *
     * @param gr graphic canvas
     */
    private void drawPlayerInfo(Graphics gr) {
        // draws health-bar
        if (handler.healthPlayer >= 50) {
            gr.setColor(Color.lightGray);
            gr.fillRect(32, 5, 200, 22);
            gr.setColor(Color.GREEN);
            gr.fillRect(32, 5, (int) (handler.healthPlayer * 2), 22);
            gr.setColor(Color.BLACK);
            gr.drawRect(32, 5, 200, 22);
            gr.setColor(Color.black);
            gr.drawString((int) handler.healthPlayer + "/100", 110, 22);
        } else {
            gr.setColor(Color.lightGray);
            gr.fillRect(32, 5, 200, 22);
            gr.setColor(Color.red);
            gr.fillRect(32, 5, (int) (handler.healthPlayer * 2), 22);
            gr.setColor(Color.BLACK);
            gr.drawRect(32, 5, 200, 22);
            gr.setColor(Color.black);
            gr.drawString((int) handler.healthPlayer + "/100", 110, 22);
        }

        // draws ammo
        gr.setColor(Color.white);
        gr.drawString("Ammo: " + handler.ammo, 250, 22);

        // draws score
        gr.setColor(Color.white);
        gr.drawString("Score: " + handler.score, 350, 22);

        // draws damage
        gr.setColor(Color.white);
        gr.drawString("Damage: " + handler.damageTaken, 800, 22);
    }

    /**
     * Updates the position of the Player.
     */
    @Override
    public void updateGameObject() {
        destinationX = UserInput.getMouseX();
        destinationY = UserInput.getMouseY();

        handler.setPositionPlayerX(centerX);
        handler.setPositionPlayerY(centerY);

        super.updateGameObject();
    }

    /**
     * Updates the direction of the Player.
     */
    public void findDirection() {
        if (UserInput.isUp()) vecY = -1;
        else if (!UserInput.isDown()) vecY = 0;

        if (UserInput.isDown()) vecY = 1;
        else if (!UserInput.isUp()) vecY = 0;

        if (UserInput.isRight()) vecX = 1;
        else if (!UserInput.isLeft()) vecX = 0;

        if (UserInput.isLeft()) vecX = -1;
        else if (!UserInput.isRight()) vecX = 0;
    }
}