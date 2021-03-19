package shooter.Game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

import shooter.GameObjects.*;

/**
 * Class Handler loads and processes the game objects.
 * - adding / removing of the objects
 * - drawing / updating of the objects
 */
public class Handler {

    private int positionPlayerX, positionPlayerY;

    public ArrayList<GameObject> staticObjects = new ArrayList<>();
    public ArrayList<GameObject> movingObjects = new ArrayList<>();
    public int healthPlayer = 100;
    public int healthMama = 1000;
    public int ammo = 10;
    public int score = 0;
    public int finalScore = 100;
    public int blockSize = 32;
    public int damageTaken = 0;
    public double difficulty = 1;
    private boolean isRunning = true;
    private Timer timer;
    PopupWindow popupWindow;
	private double enemySpeed = 1;
	
	public static SoundEffect soundEffectShoot = new SoundEffect();
    public static SoundEffect soundEffectFinalBosssPawn = new SoundEffect();

    /**
     * Creates handler.
     * @param timer timer
     */
    public Handler(Timer timer) {
        this.timer = timer;
    }

    /**
     * Player shoots the shot.
     */
    public  void playerShoot() {
        if (ammo > 0) {
            addObject(new Shot(5, positionPlayerX, positionPlayerY, this));
            ammo--;

            soundEffectShoot.setFileShoot();
            soundEffectShoot.play();
        }
    }

    /**
     * MamaZombie shoots the shot.
     */
    public void enemyShoot() {
        addObject(new EnemyShot(7, MamaZombie.getPositionEnemyX(), MamaZombie.getPositionEnemyY(), this));
    }

    /**
     * Updates the game objects.
     */
    public void updateGameObjects() {
        for (int i = 0; i < movingObjects.size(); i++) {
            GameObject newObject = movingObjects.get(i);
            if (newObject instanceof MovingGameObject) {
                ((MovingGameObject) newObject).updateGameObject();
            }
        }
    }

    /**
     * Draws all the objects from the object list; first static, then moving.
     *
     * @param g graphic canvas
     */
    public void drawGameObjects(Graphics g) {
        for (int i = 0; i < staticObjects.size(); i++) {
            GameObject newObject = staticObjects.get(i);
            newObject.drawObject(g);
        }

        for (int i = 0; i < movingObjects.size(); i++) {
            GameObject newObject = movingObjects.get(i);
            if (newObject instanceof Player) {

            } else {
                newObject.drawObject(g);
            }
        }

        // spawns random healthKit if the healthPlayer is equal or lower than 50
        if (healthPlayer <= 50) {
            Random randomHealthKit = new Random();

            int counter = 0;
            int random = randomHealthKit.nextInt(MapLoader.spawnPointHealthKit.size());

            for (Integer i : MapLoader.spawnPointHealthKit.keySet()) {
                if (counter == random && Item.numberOfHealthKits <= 0) {
                    addObject(new Item(4.2, i, MapLoader.spawnPointHealthKit.get(i), blockSize, blockSize, this));
                    Item.numberOfHealthKits++;
                }
                counter++;
            }
        }

        // calls game-over pop-up window, if player dies
        if (healthPlayer <= 0) {
            popupWindow = new PopupWindow(this);
        }

        // calls youWon pop-up window, if player beats mamaZombie
        if (healthMama <= 0) {
            popupWindow = new PopupWindow(this, healthMama);
        }

        // spawns random ammoKit if the ammo is equal or lower than 5
        if (ammo <= 5) {
            Random randomAmmoKit = new Random();

            int counter = 0;
            int random = randomAmmoKit.nextInt(MapLoader.spawnPointAmmoKit.size());

            for (Integer i : MapLoader.spawnPointAmmoKit.keySet()) {
                if (counter == random && Item.numberOfAmmoKits <= 0) {
                    addObject(new Item(4.1, i, MapLoader.spawnPointAmmoKit.get(i), blockSize, blockSize, this));
                    Item.numberOfAmmoKits++;
                }
                counter++;
            }
        }
    }

    /**
     * Draws player.
     *
     * @param g graphic canvas
     */
    public void drawPlayer(Graphics g) {
        for (int i = 0; i < movingObjects.size(); i++) {
            GameObject newObject = movingObjects.get(i);
            if (newObject instanceof Player) {
                newObject.drawObject(g);
            }
        }
    }

    /**
     * Returns true if game is running.
     *
     * @return true if game is running
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Adds object to the game.
     *
     * @param newObject new object
     */
    public void addObject(GameObject newObject) {
        GameObject addObject = newObject;
        if (addObject instanceof MovingGameObject) {
            movingObjects.add(addObject);
        } else {
            staticObjects.add(addObject);
        }
    }

    /**
     * Removes object from the game.
     *
     * @param newObject new object
     */
    public void removeObject(GameObject newObject) {
        GameObject addObject = newObject;
        if (addObject instanceof MovingGameObject) {
            movingObjects.remove(addObject);
        } else {
            staticObjects.remove(addObject);
        }

        int enemyCounter = 0;
        for (GameObject gameObjects : movingObjects) {
            if (gameObjects instanceof Enemy) {
                enemyCounter++;
            }
        }
        if (newObject instanceof Enemy && enemyCounter <= 10) {

            // spawns MamaZombie
            if (score == 24) {
                for (Integer i : MapLoader.spawnPointMama.keySet()) {
                    addObject(new MamaZombie(6.1, i, MapLoader.spawnPointMama.get(i), blockSize, blockSize, this));

                    soundEffectFinalBosssPawn.setFileFinalBossSpawn();
                    soundEffectFinalBosssPawn.play();
                }
            }

            // spawns enemies
            if (score <= 14) {
                Random randomEnemy = new Random();
                int counter = 0;
                int random = randomEnemy.nextInt(MapLoader.spawnPointEnemy.size());

                for (Integer i : MapLoader.spawnPointEnemy.keySet()) {

                    if (counter == random) {
                        addObject(new Enemy(3.1, i, MapLoader.spawnPointEnemy.get(i), blockSize, blockSize, this));
                    }

                    // speeds up the enemy movement
                    if (score >= 9) {
                        this.enemySpeed  = 2;
                    }

                    // speeds up the enemy movement even more
                    if (score >= 9 && score % 2 == 0) {
                    	this.enemySpeed = 2.5;
                    }
                    counter++;
                }
            }
        }
    }

    /**
     * Returns position X of the player.
     *
     * @return position X of the player
     */
    public int getPositionPlayerX() {
        return positionPlayerX;
    }

    /**
     * Returns position Y of the player.
     *
     * @return position Y of the player.
     */
    public int getPositionPlayerY() {
        return positionPlayerY;
    }

    /**
     * Sets the X position of the player.
     *
     * @param positionPlayerX position X of the player
     */
    public void setPositionPlayerX(int positionPlayerX) {
        this.positionPlayerX = positionPlayerX;
    }

    /**
     * Sets the Y position of the player.
     *
     * @param positionPlayerY position Y of the player
     */
    public void setPositionPlayerY(int positionPlayerY) {
        this.positionPlayerY = positionPlayerY;
    }

    /**
     * Pauses the game.
     */
    public void pauseGame() {
        if (isRunning) {
            isRunning = false;
            timer.stop();

        } else {
            isRunning = true;
            timer.start();
        }
    }

    /**
     * Returns the speed of Enemy.
     * 
     * @return enemySpeed returns the speed of Enemy
     */
    public double getEnemySpeed() {
		return enemySpeed;
	}

    /**
     * Sets the difficulty of the game.
     * @param difficulty difficulty
     */
	public void setDifficulty(String difficulty) {
		this.difficulty = Double.parseDouble(difficulty);
	}
}
