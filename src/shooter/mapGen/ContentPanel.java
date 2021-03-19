package shooter.mapGen;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JScrollPane;

/**
 * Creates content panel for map generator app.
 */
public class ContentPanel extends JPanel {

    private HandlerMapGen handler;

    /**
     * Creates the panel.
     */
    public ContentPanel() {
        setBackground(Color.GRAY);
        setBounds(0, 0, 1024, 910);
        setLayout(null);
        handler = new HandlerMapGen();

        createMapGenButtons();
        createMapGenItems();
        createMapGenCanvas();
    }

    /**
     * Creates items for user to choose from, while creating custom map.
     */
    private void createMapGenItems() {
        ItemsPanel itemsPanel = new ItemsPanel(handler);
        add(itemsPanel);

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setBounds(200, 10, 650, 80);
        add(scrollPane);
    }

    /**
     * Creates buttons to load, delete and save map.
     */
    private void createMapGenButtons() {
        JButton loadMapButton = new JButton("Load Default Map");
        loadMapButton.addActionListener(e -> handler.setDefaultMap());
        loadMapButton.setBounds(10, 10, 175, 20);
        add(loadMapButton);

        JButton deleteMapButton = new JButton("Delete Map");
        deleteMapButton.setBounds(10, 40, 175, 20);
        deleteMapButton.addActionListener(e -> handler.deleteCurrentMap());
        add(deleteMapButton);

        JButton saveMapButton = new JButton("Save Map");
        saveMapButton.addActionListener(e -> handler.saveMap());
        saveMapButton.setBounds(10, 70, 175, 20);
        add(saveMapButton);
    }

    /**
     * Creates the canvas for new map.
     */
    private void createMapGenCanvas() {
        CanvasMapGen mapGenCanvas = new CanvasMapGen(handler);
        add(mapGenCanvas);
    }
}