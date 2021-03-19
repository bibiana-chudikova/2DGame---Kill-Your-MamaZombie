package shooter.Game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

import shooter.GameObjects.*;

/**
 * Class MapLoader loads the images of the game.
 */
public class MapLoader {

    private Handler handler;
    private Scanner scanner;
    private File mapFile;

    // HashMaps for saving the coordinates of spawning objects
    public static HashMap<Integer, Integer> spawnPointEnemy = new HashMap<>();
    public static HashMap<Integer, Integer> spawnPointMama = new HashMap<>();
    public static HashMap<Integer, Integer> spawnPointHealthKit = new HashMap<>();
    public static HashMap<Integer, Integer> spawnPointAmmoKit = new HashMap<>();

    /**
     * Creates MapLoader.
     *
     * @param handler handler
     * @throws FileNotFoundException FileNotFoundException
     */
    public MapLoader(Handler handler){
        this.handler = handler;
        loadMapFile();
    }
    
    
    /**
     * Loads map file.
     */
    private void loadMapFile() {
    	try {
    		InputStream input = new FileInputStream("resources/gameConfig.properties");
    		Properties gameProp = new Properties();
    		gameProp.load(input);
    		
    		String mapPath = "mapy\\" + gameProp.getProperty("Map");
    		
    		handler.setDifficulty(gameProp.getProperty("Difficulty"));
    		
	    	this.mapFile = new File(mapPath);
			this.scanner = new Scanner(mapFile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
     * Loads the maps.
     */
    public void loadMap() {

        // adds game objects to the object list according to the map location
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] lineSplitField = line.split(",");            // splits the string into the array of strings ","

            double newObjectID = Double.parseDouble(lineSplitField[0]);
            int newObjectPositionX = Integer.parseInt(lineSplitField[1]);
            int newObjectPositionY = Integer.parseInt(lineSplitField[2]);
            int newObjectWidth = Integer.parseInt(lineSplitField[3]);
            int newObjectHeight = Integer.parseInt(lineSplitField[4]);

            int ID = (int) newObjectID;
            
            if (ID == 1) {
                handler.addObject(new Wall(newObjectID, newObjectPositionX, newObjectPositionY, newObjectWidth, newObjectHeight, handler));
            } else if (ID != 1) {
            	if (ID == 0) {
                handler.addObject(new Grass(newObjectID, newObjectPositionX, newObjectPositionY, newObjectWidth, newObjectHeight, handler));
            	}
                if (ID == 2) {
                    handler.addObject(new Player(newObjectPositionX, newObjectPositionY, handler));
                }
                if (ID == 3) {
                    handler.addObject(new Enemy(newObjectID, newObjectPositionX, newObjectPositionY, newObjectWidth, newObjectHeight, handler));
                    spawnPointEnemy.put(newObjectPositionX, newObjectPositionY);
                }
                if (newObjectID == 4.1) {
                    new Item(newObjectID, newObjectPositionX, newObjectPositionY, newObjectWidth, newObjectHeight, handler);
                    spawnPointAmmoKit.put(newObjectPositionX, newObjectPositionY);
                }
                if (newObjectID == 4.2) {
                    new Item(newObjectID, newObjectPositionX, newObjectPositionY, newObjectWidth, newObjectHeight, handler);
                    spawnPointHealthKit.put(newObjectPositionX, newObjectPositionY);
                }
                if (newObjectID == 6.1) {
                    new MamaZombie(newObjectID, newObjectPositionX, newObjectPositionY, newObjectWidth, newObjectHeight, handler);
                    spawnPointMama.put(newObjectPositionX, newObjectPositionY);
                }
            }
        }
    }
}
