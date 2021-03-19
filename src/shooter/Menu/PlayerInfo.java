package shooter.Menu;

import shooter.Game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Class PlayerInfo collects and saves player information into the database.
 */
public class PlayerInfo extends JFrame {

    private JPanel panel;
    private JLabel lblName, lblDamageTaken, lblTime, lblScore, lblBackRound, lblGameOver;
    private JButton btnSave;
    public static JTextField txtName;
    Handler handler;
    private Database database;
    public static SoundEffect soundEffectButtonClick = new SoundEffect();

    /**
     * Creates PlayerInfo window, where player information is displayed and player name is required to be entered.
     *
     * @param handler handler
     */
    public PlayerInfo(Handler handler) {
        this.handler = handler;

        setResizable(false);
        setTitle("Player Info");
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(null);

        lblBackRound = new JLabel("");
        lblBackRound.setIcon(new ImageIcon("obr/bg.jpg"));
        lblBackRound.setBounds(0, 0, 800, 600);

        lblGameOver = new JLabel("GAME OVER");
        lblGameOver.setBounds(100, 75, 400, 40);
        lblGameOver.setForeground(Color.white);
        lblGameOver.setFont(new Font("Forte", Font.BOLD, 40));

        lblName = new JLabel("Name: ");
        lblName.setBounds(100, 150, 100, 20);
        lblName.setForeground(Color.white);

        lblScore = new JLabel("Score: " + handler.score);
        lblScore.setBounds(100, 200, 100, 20);
        lblScore.setForeground(Color.white);

        lblDamageTaken = new JLabel("Damage Taken: " + handler.damageTaken);
        lblDamageTaken.setBounds(100, 250, 200, 20);
        lblDamageTaken.setForeground(Color.white);

        lblTime = new JLabel("Time: " + GameTimer.getDdMinute() + ":" + GameTimer.getDdSecond());
        lblTime.setBounds(100, 300, 250, 20);
        lblTime.setForeground(Color.white);

        txtName = new JTextField();
        txtName.setBounds(150, 150, 100, 20);
        txtName.setDocument(new JTextFieldLimit(5));    // limituje dlzku mena na 5 znakov

        btnSave = new JButton("SAVE");
        btnSave.setBounds(100, 350, 100, 40);

        // inserts player information into database and highscore in main menu is displayed
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundEffectButtonClick.setFileButtonClick();
                soundEffectButtonClick.play();

                if (txtName.getText().isEmpty() || txtName.getText().contains(" ")) {
                    JOptionPane.showMessageDialog(null, "Name can't be empty!\nName can't contain spaces!",
                            "NAME ERROR", JOptionPane.INFORMATION_MESSAGE);
                } else {

                    try {
                        database = new Database(handler);
                        database.insertData();
                        database.resetHighScore();
                        database.selectDataScore();

                        shutDown();

                        ContentPanel.menu.run();
                        ContentPanel.panel1.setVisible(false);
                        ContentPanel.panel2.setVisible(true);
                        ContentPanel.panel3.setVisible(false);
                        Panel2_HighScore.scoreMAX[0] = true;
                        Panel3_Settings.btnSoundEffects.setBackground(Color.green);
                        Panel3_Settings.btnMusic.setBackground(Color.green);
                        Panel3_Settings.soundIsOn[0] = true;
                        Panel3_Settings.musicIsOn[0] = true;
                        ContentPanel.souneEffectContentPanel.setVolume(0);
                        Panel2_HighScore.soundEffectHighScore.setVolume(0);

                    } catch (SQLException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }

            }
        });

        panel.add(lblGameOver);
        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblScore);
        panel.add(lblDamageTaken);
        panel.add(lblTime);
        panel.add(btnSave);

        add(panel, BorderLayout.CENTER);
        panel.add(lblBackRound);
    }

    /**
     * Runs the window.
     */
    public void run() {
        setVisible(true);
    }

    /**
     * Turns off the window.
     */
    public void shutDown() {
        setVisible(false);
    }

}
