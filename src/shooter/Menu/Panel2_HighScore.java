package shooter.Menu;

import shooter.Game.Handler;
import shooter.Game.SoundEffect;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.*;

/**
 * Class Panel2_HighScore - high score section in the menu that displays score of players.
 */
public class Panel2_HighScore extends JPanel {

    private JLabel lblNumber, lblName, lblScore, lblDamageTaken, lblTime, lblFilter;
    private JPanel pnlPanelInfo;
    private JButton btnScore, btnDamageTaken, btnTime;
    public static JTextArea txtPlayerNumber, txtPlayerName, txtScore, txtDamageTaken, txtTime;
    public static SoundEffect soundEffectHighScore;
    public static final boolean[] scoreMAX = {true};
    public static final boolean[] damageTakenMIN = {true};
    public static final boolean[] timeMIN = {true};
    Handler handler;
    private Database database;
    Font font = new Font("Segoe Script", Font.BOLD, 12);

    {
        try {
            database = new Database(handler);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Creates the panel.
     */
    public Panel2_HighScore() {
        setBackground(Color.lightGray);
        setBounds(300, 50, 450, 400);
        setLayout(null);
        setVisible(false);

        soundEffectHighScore = new SoundEffect();
        soundEffectHighScore.setFileButtonClick();

        pnlPanelInfo = new JPanel();
        pnlPanelInfo.setBounds(0, 0, 450, 30);
        pnlPanelInfo.setLayout(null);
        pnlPanelInfo.setVisible(true);

        lblNumber = new JLabel("N.", SwingConstants.CENTER);
        lblNumber.setBounds(5, 5, 50, 20);
        lblNumber.setFont(font);
        lblNumber.setBorder(BorderFactory.createEtchedBorder());

        lblName = new JLabel("Name", SwingConstants.CENTER);
        lblName.setBounds(55, 5, 100, 20);
        lblName.setFont(font);
        lblName.setBorder(BorderFactory.createEtchedBorder());

        lblScore = new JLabel("Score", SwingConstants.CENTER);
        lblScore.setBounds(155, 5, 100, 20);
        lblScore.setFont(font);
        lblScore.setBorder(BorderFactory.createEtchedBorder());

        lblDamageTaken = new JLabel("DMG Taken", SwingConstants.CENTER);
        lblDamageTaken.setBounds(255, 5, 100, 20);
        lblDamageTaken.setFont(font);
        lblDamageTaken.setBorder(BorderFactory.createEtchedBorder());

        lblTime = new JLabel("Time", SwingConstants.CENTER);
        lblTime.setBounds(355, 5, 90, 20);
        lblTime.setOpaque(false);
        lblTime.setFont(font);
        lblTime.setBorder(BorderFactory.createEtchedBorder());

        lblFilter = new JLabel("CHOOSE FILTER: ");
        lblFilter.setBounds(30, 370, 100, 20);
        lblFilter.setFont(new Font("Segoe Script", Font.BOLD, 10));

        txtPlayerNumber = new JTextArea();
        txtPlayerNumber.setBounds(5, 30, 50, 335);
        txtPlayerNumber.setBackground(Color.lightGray);
        txtPlayerNumber.setEditable(false);
        txtPlayerNumber.setBorder(BorderFactory.createEtchedBorder());

        txtPlayerName = new JTextArea();
        txtPlayerName.setBounds(55, 30, 100, 335);
        txtPlayerName.setBackground(Color.lightGray);
        txtPlayerName.setEditable(false);
        txtPlayerName.setBorder(BorderFactory.createEtchedBorder());

        txtScore = new JTextArea();
        txtScore.setBounds(155, 30, 100, 335);
        txtScore.setBackground(Color.lightGray);
        txtScore.setEditable(false);
        txtScore.setBorder(BorderFactory.createEtchedBorder());

        txtDamageTaken = new JTextArea();
        txtDamageTaken.setBounds(255, 30, 100, 335);
        txtDamageTaken.setBackground(Color.lightGray);
        txtDamageTaken.setEditable(false);
        txtDamageTaken.setBorder(BorderFactory.createEtchedBorder());

        txtTime = new JTextArea();
        txtTime.setBounds(355, 30, 90, 335);
        txtTime.setBackground(Color.lightGray);
        txtTime.setEditable(false);
        txtTime.setBorder(BorderFactory.createEtchedBorder());

        btnScore = new JButton("SCORE");
        btnScore.setBounds(155, 365, 100, 30);
        btnScore.setFont(font);

        btnScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundEffectHighScore.play();

                try {
                    database.resetHighScore();
                    database.selectDataScore();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        btnDamageTaken = new JButton("DMG");
        btnDamageTaken.setBounds(255, 365, 100, 30);
        btnDamageTaken.setFont(font);
        btnDamageTaken.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundEffectHighScore.play();

                try {
                    database.resetHighScore();
                    database.selectDataDamage();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        btnTime = new JButton("TIME");
        btnTime.setBounds(355, 365, 90, 30);
        btnTime.setFont(font);
        btnTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundEffectHighScore.play();

                try {
                    database.resetHighScore();
                    database.selectDataTime();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        pnlPanelInfo.add(lblNumber);
        pnlPanelInfo.add(lblName);
        pnlPanelInfo.add(lblScore);
        pnlPanelInfo.add(lblDamageTaken);
        pnlPanelInfo.add(lblTime);
        add(lblFilter);
        add(txtPlayerNumber);
        add(txtPlayerName);
        add(txtScore);
        add(txtDamageTaken);
        add(txtTime);
        add(pnlPanelInfo);
        add(btnScore);
        add(btnDamageTaken);
        add(btnTime);
    }

    /**
     * Loads properties.
     */
    public void loadProperties() {
        try {
            InputStream input = new FileInputStream("resources/playerConfig.properties");
            Properties playerProp = new Properties();
            playerProp.load(input);
            playerProp.setProperty("test", "5");

            int test = Integer.parseInt(playerProp.getProperty("test"));
            System.out.println(test);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
