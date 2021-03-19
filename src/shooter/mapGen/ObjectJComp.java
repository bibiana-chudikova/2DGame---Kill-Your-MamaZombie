package shooter.mapGen;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * Class ObjectComp adds items to custom created map.
 */
public class ObjectJComp extends JComponent {

    private HandlerMapGen handler;
    private int posX;
    private int posY;
    private Image image;
    private double ID;
    private String imagePath;
    private String IDString;

    /**
     * Adds items to the custom created map.
     *
     * @param ID            ID
     * @param posX          X coordinate
     * @param posY          Y coordinate
     * @param handlerMapGen handler of map generator
     */
    public ObjectJComp(Double ID, int posX, int posY, HandlerMapGen handlerMapGen) {
        this.ID = ID;
        this.IDString = Double.toString(this.ID);
        this.posX = posX;
        this.posY = posY;
        this.handler = handlerMapGen;
        loadImagesID();
    }

    /**
     * Loads the image of the map generator.
     */
    public void loadImagesID() {
        try (InputStream input = new FileInputStream("resources/imageID.properties")) {

            Properties prop = new Properties();
            prop.load(input);

            this.imagePath = prop.getProperty(IDString);
            image = ImageIO.read(new File(imagePath));
            image = image.getScaledInstance(handler.tileSize, handler.tileSize, Image.SCALE_FAST);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Draws the map generator object.
     *
     * @param gr graphic canvas
     */
    public void drawMapObject(Graphics gr) {
        Graphics2D g = (Graphics2D) gr.create();
        g.drawImage(image, posX, posY, null);
        g.dispose();
    }

    /**
     * Returns the X coordinate.
     *
     * @return the X coordinate
     */
    public int getPositionX() {
        return posX;
    }

    /**
     * Returns the Y coordinate.
     *
     * @return the Y coordinate
     */
    public int getPositionY() {
        return posY;
    }

    /**
     * Sets the X coordinate.
     *
     * @param x X coordinate
     */
    public void setX(int x) {
        posX = x;
    }

    /**
     * Sets the Y coordinate.
     *
     * @param y Y coordinate
     */
    public void setY(int y) {
        posY = y;
    }

    /**
     * Returns the double value of ID.
     *
     * @return the double value of ID
     */
    public Double getID() {
        return ID;
    }
}