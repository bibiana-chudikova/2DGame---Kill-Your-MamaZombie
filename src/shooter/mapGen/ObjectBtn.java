package shooter.mapGen;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Class ObjectBtn adds buttons for map generator items selection.
 */
public class ObjectBtn extends JButton implements ActionListener {

    private HandlerMapGen handler;
    private Icon imageIcon;
    private Image image;
    private double ID;
    private String imagePath;

    /**
     * Returns string value of ID.
     *
     * @return string value of ID
     */
    public String getID() {
        return String.valueOf(ID);
    }

    /**
     * Returns path of the image.
     *
     * @return path of the image
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Creates buttons for map generator objects.
     *
     * @param ID            ID
     * @param imagePath     path of the image
     * @param handlerMapGen handler of map generator
     */
    public ObjectBtn(String ID, String imagePath, HandlerMapGen handlerMapGen) {
        super();
        this.ID = Double.parseDouble(ID);
        this.handler = handlerMapGen;
        this.imagePath = imagePath;
        this.handler = handlerMapGen;
        loadImage();

        setIcon(imageIcon);
        setPreferredSize(new Dimension(handlerMapGen.tileSize, handlerMapGen.tileSize));
        setSize(getPreferredSize());
        addActionListener(this);
    }

    /**
     * Loads the image of the map generator.
     */
    private void loadImage() {
        try {
            image = ImageIO.read(new File(this.imagePath));
            image = image.getScaledInstance(handler.tileSize, handler.tileSize, Image.SCALE_FAST);
            imageIcon = new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Detects the actions events.
     *
     * @param e action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        handler.setSelectedItemID(this.ID);
    }
}