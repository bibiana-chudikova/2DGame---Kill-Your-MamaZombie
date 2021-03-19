package shooter.mapGen;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Class ItemsPanel provides the selection of all the map generator objects.
 */
public class ItemsPanel extends JPanel {

    private Timer timer = new Timer(60, e -> repaint());
    private HandlerMapGen handler;
    private List<File> ImageFileList;
    private ArrayList<ObjectBtn> itemsList = new ArrayList<>();

    /**
     * Creates the panel for map items.
     *
     * @param handler handler.
     */
    public ItemsPanel(HandlerMapGen handler) {
        this.handler = handler;
        setLayout(new GridLayout(0, 15, 10, 10));

        ImageFileList = new ArrayList<>();
        ImageFileList = loadImages("obr/objektyHry");

        //writeImagesToProperties();
        createMapGenObjectBtns();
        timer.start();
    }

    /**
     * Loads images into the list - ImageFileLIst
     *
     * @param directoryName directory name
     * @return the list of images
     */
    private List<File> loadImages(String directoryName) {
        File directory = new File(directoryName);
        List<File> resultList = new ArrayList<>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isDirectory()) {
                resultList.addAll(loadImages(file.getPath()));
            }
        }
        return resultList;
    }

    /**
     * Updates the images of the game.
     */
    private void writeImagesToProperties() {
        try {
            OutputStream output = new FileOutputStream("resources/imageID.properties");

            Properties imageSrc = new Properties();

            for (File file : ImageFileList) {
                String imagePath = file.getPath();
                String imageID = file.getPath();
                String[] imageIDSubStrings = imageID.split("\\\\");
                String imageTyp = imageIDSubStrings[2];
                imageID = imageIDSubStrings[imageIDSubStrings.length - 1];
                if (!Character.isDigit(imageID.charAt(0))) {
                    continue;
                }
                imageID = imageID.substring(0, imageID.length() - 4);

                if (imageTyp.equalsIgnoreCase("trava")) {
                    imageTyp = "0.";
                } else if (imageTyp.equalsIgnoreCase("stena")) {
                    imageTyp = "1.";
                } else if (imageTyp.equalsIgnoreCase("hrac")) {
                    imageTyp = "2.";
                } else if (imageTyp.equalsIgnoreCase("enemy")) {
                    imageTyp = "3.";
                } else if (imageTyp.equalsIgnoreCase("item")) {
                    imageTyp = "4.";
                }
                imageID = imageTyp + imageID;

                imageSrc.setProperty(imageID, imagePath);
            }
            imageSrc.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates objects from imageID.Properties .
     */
    private void createMapGenObjectBtns() {
        try (InputStream input = new FileInputStream("resources/imageID.properties")) {
            Properties imageSrc = new Properties();
            imageSrc.load(input);

            imageSrc.forEach((key, value) -> addObject(key.toString(), value.toString()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Adds map objects to the list of items.
     *
     * @param ID        ID
     * @param imagePath image path
     */
    private void addObject(String ID, String imagePath) {
        itemsList.add(new ObjectBtn(ID, imagePath, handler));

        for (int i = 0; i < itemsList.size(); i++) {
            add(itemsList.get(i));
        }
    }
}