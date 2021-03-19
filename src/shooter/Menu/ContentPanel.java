package shooter.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;

import shooter.Game.Game;
import shooter.Game.Handler;
import shooter.Game.SoundEffect;
import shooter.mapGen.MapGenerator;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * Class ContentPanel serves as a skeleton for whole menu and its components; panel, buttons etc.
 */
public class ContentPanel extends JPanel {

    public static Panel1_GameSettings panel1;
    public static Panel2_HighScore panel2;
    public static Panel3_Settings panel3;
    public static Menu menu;
    public static Game game;
    private Database database;
    public static SoundEffect souneEffectContentPanel;
    Handler handler;

    /**
     * Creates the main panel.
     *
     * @param menu menu
     */
    public ContentPanel(Menu menu) {
        ContentPanel.menu = menu;
        setLayout(null);
        setBounds(0, 0, 800, 600);

        loadPanels();
        createButtons();
        loadBackround();
    }

    /**
     * Loads all the panels.
     */
    private void loadPanels() {
        panel1 = new Panel1_GameSettings();
        panel2 = new Panel2_HighScore();
        panel3 = new Panel3_Settings();

        add(panel1);
        add(panel2);
        add(panel3);
    }

    /**
     * Loads the backround of menu window.
     */
    private void loadBackround() {
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("obr/bg.jpg"));
        lblNewLabel.setBounds(0, 0, 800, 600);
        add(lblNewLabel);
    }

    /**
     * Creates the buttons of the menu window.
     */
    private void createButtons() {
        souneEffectContentPanel = new SoundEffect();
        souneEffectContentPanel.setFileButtonClick();

        JButton btnNewButton = new JButton("New Game");
        btnNewButton.setBounds(100, 150, 150, 30);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                souneEffectContentPanel.play();

                menu.shutDown();
                try {
                    game = new Game();
                    game.run();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Game Settings");
        btnNewButton_1.setBounds(100, 200, 150, 30);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                souneEffectContentPanel.play();

                panel1.setVisible(true);
                panel2.setVisible(false);
                panel3.setVisible(false);
            }
        });
        add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("High Score");
        btnNewButton_2.setBounds(100, 250, 150, 30);
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                souneEffectContentPanel.play();

                panel1.setVisible(false);
                panel2.setVisible(true);
                panel3.setVisible(false);

                try {
                    database = new Database(handler);
                    database.resetHighScore();
                    database.selectDataScore();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        add(btnNewButton_2);

        JButton btnNewButton_3 = new JButton("Settings");
        btnNewButton_3.setBounds(100, 300, 150, 30);
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                souneEffectContentPanel.play();

                panel1.setVisible(false);
                panel2.setVisible(false);
                panel3.setVisible(true);
            }
        });
        add(btnNewButton_3);

        JButton btnNewButton_4 = new JButton("Map Generator");
        btnNewButton_4.setBounds(100, 350, 150, 30);
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                souneEffectContentPanel.play();

                menu.setVisible(false);
                MapGenerator mapGen;
                mapGen = new MapGenerator();
                mapGen.run();
            }
        });
        add(btnNewButton_4);

        JButton btnNewButton_5 = new JButton("Exit");
        btnNewButton_5.setBounds(100, 400, 150, 30);
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        add(btnNewButton_5);
    }
}
