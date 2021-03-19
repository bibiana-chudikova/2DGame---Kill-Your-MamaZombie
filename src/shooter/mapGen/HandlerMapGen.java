package shooter.mapGen;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * Class HandlerMapGen loads and processes the map generator objects.
 */
public class HandlerMapGen {

    private ArrayList<ObjectJComp> defaultMap;
    private ArrayList<ObjectJComp> currentMap;
    private ArrayList<ObjectJComp> objectToRemove;
    protected int tileSize = 32;
    private double SelectedItemID = 1.1;
    private PrintWriter writer;

    /**
     * Creates new handler with new lists and loads default map.
     */
    public HandlerMapGen() {
        defaultMap = new ArrayList<>();
        currentMap = new ArrayList<>();
        objectToRemove = new ArrayList<>();
        loadDefaultMap();
    }

    /**
     * Loads items for default map.
     */
    private void loadDefaultMap() {
        File mapFile = new File("mapy/defaultMapGenMap.txt");
        try {
            Scanner scanner = new Scanner(mapFile);

            // adds game objects to the object list according to the map location
            while (scanner.hasNextLine()) {
                String[] splitLine = scanner.nextLine().split(",");            // splits the string into the array of strings ","

                double newObjectID = Double.parseDouble(splitLine[0]);
                int newObjectPozX = Integer.parseInt(splitLine[1]);
                int newObjectPozY = Integer.parseInt(splitLine[2]);

                defaultMap.add(new ObjectJComp(newObjectID, newObjectPozX, newObjectPozY, this));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds object on the canvas.
     *
     * @param ID ID of object
     * @param x  X coordinate
     * @param y  Y coordinate
     */
    public void addObjetOnCanvas(double ID, int x, int y) {
        SelectedItemID = ID;
        int pozX = x / tileSize;
        int pozY = y / tileSize;
        currentMap.add(new ObjectJComp(SelectedItemID, pozX * tileSize, pozY * tileSize, this));
    }

    /**
     * Deletes the object.
     *
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void deleteObjectsAt(int x, int y) {
        int pozX = x / tileSize;
        int pozY = y / tileSize;
        for (ObjectJComp object : currentMap) {
            ObjectJComp obj = object;
            if (obj.getPositionX() / tileSize == pozX && obj.getPositionY() / tileSize == pozY) {
                objectToRemove.add(object);
            }
        }
        currentMap.removeAll(objectToRemove);
    }

    /**
     * Sets the default map.
     */
    public void setDefaultMap() {
        deleteCurrentMap();
        currentMap.addAll(defaultMap);
    }

    /**
     * Deletes current map.
     */
    public void deleteCurrentMap() {
        currentMap.removeAll(currentMap);
    }

    /**
     * Draws the objects.
     *
     * @param g2 graphic canvas
     */
    public void drawObjects(Graphics2D g2) {
        for (ObjectJComp object : currentMap) {
            object.drawMapObject(g2);
        }
    }

    /**
     * Returns the ID of selected item.
     *
     * @return the ID of selected item
     */
    public double getSelectedItemID() {
        return SelectedItemID;
    }

    /**
     * Sets the ID to the one of selected item.
     *
     * @param SelectedItemID item ID
     */
    public void setSelectedItemID(double SelectedItemID) {
        this.SelectedItemID = SelectedItemID;
    }

    /**
     * Saves the map.
     */
    public void saveMap() {
        String newMapName = JOptionPane.showInputDialog("Choose map name:");
        File newMap = new File("mapy/custom/" + newMapName + ".txt");

        try {
            newMap.createNewFile();
            writer = new PrintWriter(newMap);

        for (ObjectJComp object : currentMap) {
            writer.write(object.getID() + "," + object.getPositionX() + "," + object.getPositionY() + "," + tileSize + "," + tileSize + "\n");
        }
        writer.flush();
        writer.close();
        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}