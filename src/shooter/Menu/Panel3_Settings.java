package shooter.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Class Panel3_Settings creates settings for the game in the menu.
 */
public class Panel3_Settings extends JPanel {

	public static JButton btnMusic, btnSoundEffects;
	private Image imageON;
	public static boolean[] musicIsOn = {true};
	public static boolean[] soundIsOn = {true};
	Font font = new Font("Segoe Script", Font.BOLD, 20);

	/**
	 * Create the panel.
	 */
	public Panel3_Settings() {

		setOpaque(false);
		setBounds(300, 50, 350, 400);
		setBackground(Color.WHITE);
		setLayout(null);
		
		JTextArea ovladanie = new JTextArea();
		ovladanie.setBounds(20, 250, 200, 30);
		ovladanie.setFont(font);
		ovladanie.setForeground(Color.WHITE);
		ovladanie.setOpaque(false);
		ovladanie.setText("Control settings:");
		ovladanie.setEditable(false);
		add(ovladanie); 
		
		JComboBox<String> vyberOvladania = new JComboBox<>();
		vyberOvladania.setBounds(35, 290, 120, 20);
		vyberOvladania.addItem("WASD");
		vyberOvladania.addItem("Arrows");
		add(vyberOvladania);
		
		JTextArea ovladaniePauzy = new JTextArea();
		ovladaniePauzy.setBounds(20, 320, 200, 30);
		ovladaniePauzy.setFont(font);
		ovladaniePauzy.setForeground(Color.WHITE);
		ovladaniePauzy.setOpaque(false);
		ovladaniePauzy.setText("Pause settings:");
		ovladaniePauzy.setEditable(false);
		add(ovladaniePauzy); 
		
		JComboBox<String> pauza = new JComboBox<>();
		pauza.setBounds(35, 360, 120, 20);
		pauza.addItem("P");
		pauza.addItem("Space");
		add(pauza);

		JTextArea speedSettings = new JTextArea();
		speedSettings.setBounds(20, 125, 200, 30);
		speedSettings.setFont(font);
		speedSettings.setForeground(Color.WHITE);
		speedSettings.setOpaque(false);
		speedSettings.setText("FPS settings:");
		speedSettings.setEditable(false);
		add(speedSettings);

		JSlider slider = new JSlider();
		slider.setBounds(35, 175, 200, 50);
		slider.setValue(20);
		slider.setMinimum(0);
		slider.setMaximum(200);
		slider.setMajorTickSpacing(50);
		slider.setMinorTickSpacing(25);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setForeground(Color.WHITE);
		slider.setOpaque(false);
		add(slider);

		JTextArea music = new JTextArea();
		music.setBounds(20, 10, 145, 30);
		music.setFont(font);
		music.setForeground(Color.WHITE);
		music.setOpaque(false);
		music.setText("Music on/off:");
		music.setEditable(false);
		add(music); 
		
		 // button for muting the menu music
        btnMusic = new JButton("");
        btnMusic.setBounds(175, 10, 30, 30);

        try {
            imageON = ImageIO.read(new File("obr/sound_on.png"));
            imageON = imageON.getScaledInstance(30, 30, Image.SCALE_FAST);
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnMusic.setIcon(new ImageIcon(imageON));
        btnMusic.setBackground(Color.green);
        btnMusic.setVisible(true);

        btnMusic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (musicIsOn[0]) {
                    Menu.soundEffect.setVolume(-80);
                    musicIsOn[0] = false;
                    btnMusic.setBackground(Color.red);

                } else {
                    Menu.soundEffect.setVolume(-20);
                    musicIsOn[0] = true;
                    btnMusic.setIcon(new ImageIcon(imageON));
                    btnMusic.setBackground(Color.green);
                }
            }
        });
        add(btnMusic);

		// button for muting the sound effects
		btnSoundEffects = new JButton("");
		btnSoundEffects.setBounds(175, 70, 30, 30);

		try {
			imageON = ImageIO.read(new File("obr/sound_on.png"));
			imageON = imageON.getScaledInstance(30, 30, Image.SCALE_FAST);
		} catch (IOException e) {
			e.printStackTrace();
		}

		btnSoundEffects.setIcon(new ImageIcon(imageON));
		btnSoundEffects.setBackground(Color.green);
		btnSoundEffects.setVisible(true);

		btnSoundEffects.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (soundIsOn[0]) {
					ContentPanel.souneEffectContentPanel.setVolume(-80);
					Panel2_HighScore.soundEffectHighScore.setVolume(-80);

					soundIsOn[0] = false;
					btnSoundEffects.setBackground(Color.red);

				} else {

					ContentPanel.souneEffectContentPanel.setVolume(0);
					Panel2_HighScore.soundEffectHighScore.setVolume(0);


					soundIsOn[0] = true;
					btnMusic.setIcon(new ImageIcon(imageON));
					btnSoundEffects.setBackground(Color.green);
				}
			}
		});
		add(btnSoundEffects);
        
    	JTextArea effects = new JTextArea();
    	effects.setBounds(20, 70, 200, 30);
    	effects.setFont(font);
    	effects.setForeground(Color.WHITE);
    	effects.setOpaque(false);
    	effects.setText("Sound effects:");
    	effects.setEditable(false);
		add(effects); 
		
		setVisible(false);
	}
}