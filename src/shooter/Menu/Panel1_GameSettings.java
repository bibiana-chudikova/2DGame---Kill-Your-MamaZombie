package shooter.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import shooter.Game.Handler;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * Class Panel1_PlayerSettings creates settings for player in the menu.
 */
public class Panel1_GameSettings extends JPanel {
	
	Font font = new Font("Segoe Script", Font.BOLD, 20);

	Properties playerConfig = new Properties();
	Properties gameConfig = new Properties();
	
	/**
	 * Create the panel.
	 */
	public Panel1_GameSettings() {
		try {
			FileInputStream FISplayerConfig = new FileInputStream("resources/playerConfig.properties");
			playerConfig.load(FISplayerConfig);
			FISplayerConfig.close();
			
			FileInputStream FISgameConfig = new FileInputStream("resources/gameConfig.properties");
			gameConfig.load(FISgameConfig);
			FISgameConfig.close();
			
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		setBounds(300, 50, 350, 400);
		setOpaque(false);
		setBackground(Color.white);
		setLayout(null);

		createChoosePlayerComponents();
		createChooseDifficultyComponents();
		createChooseMapComponents();

		setVisible(false);
	}

	private void createChooseMapComponents() {
		JTextArea playerChooser = new JTextArea();
		playerChooser.setBounds(20, 300, 200, 30);
		playerChooser.setFont(font);
		playerChooser.setForeground(Color.WHITE);
		playerChooser.setOpaque(false);
		playerChooser.setText("Choose map:");
		playerChooser.setEditable(false);
		add(playerChooser);

		JComboBox<String> comboBox2 = new JComboBox<>();
		List<File> MapFileList = new ArrayList<File>();
		MapFileList = loadMaps("mapy/custom");

		for (File file : MapFileList) {
			comboBox2.addItem(file.toString().substring(5));
		}
		comboBox2.setBounds(35, 350, 150, 25);
		
		ItemListener chooseMapListener = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String chosenMap = comboBox2.getSelectedItem().toString();

				try {
					gameConfig.setProperty("Map", chosenMap);
					FileOutputStream fos;
					gameConfig.store(fos = new FileOutputStream("resources/gameConfig.properties"), null);
					fos.close();
				}
				catch (IOException vynimka1) {
				}
			}
		};
		
		comboBox2.addItemListener(chooseMapListener);
		add(comboBox2);
	}
	
	private List<File> loadMaps(String dirMaps) {
		
		List<File> maps = new ArrayList<File>();
		maps.add(new File("mapy/defaultMap.txt"));
		
		//get all maps from dirrectory
		File directory = new File(dirMaps);
		File[] mapList = directory.listFiles();
		maps.addAll(Arrays.asList(mapList));
		
		return maps;
	}

	private void createChooseDifficultyComponents() {
		JTextArea difficultySetting = new JTextArea();
		difficultySetting.setBounds(20, 20, 300, 30);
		difficultySetting.setFont(font);
		difficultySetting.setForeground(Color.WHITE);
		difficultySetting.setText(" Set the difficulty level :");
		difficultySetting.setEditable(false);
		difficultySetting.setOpaque(false);
		add(difficultySetting);

		ButtonGroup bg = new ButtonGroup();

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Beginner");
		rdbtnNewRadioButton.setBounds(45, 50, 109, 23);
		rdbtnNewRadioButton.setForeground(Color.WHITE);
		rdbtnNewRadioButton.setOpaque(false);
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					gameConfig.setProperty("Difficulty", "1");

					FileOutputStream fos;
					gameConfig.store(fos = new FileOutputStream("resources/gameConfig.properties"), null);
					fos.close();
				}
				catch (IOException vynimka1) {
				}
			}
		});
		add(rdbtnNewRadioButton);

		bg.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Middle");
		rdbtnNewRadioButton_1.setOpaque(false);
		rdbtnNewRadioButton_1.setBounds(45, 73, 109, 23);
		rdbtnNewRadioButton_1.setForeground(Color.WHITE);
		rdbtnNewRadioButton_1.setOpaque(false);
		rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					gameConfig.setProperty("Difficulty", "1.3");

					FileOutputStream fos;
					gameConfig.store(fos = new FileOutputStream("resources/gameConfig.properties"), null);
					fos.close();
				}
				catch (IOException vynimka1) {
				}

			}
		});

		add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton_1);

		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Advanced");
		rdbtnNewRadioButton_2.setBounds(45, 96, 109, 23);
		rdbtnNewRadioButton_2.setForeground(Color.WHITE);
		rdbtnNewRadioButton_2.setOpaque(false);
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					gameConfig.setProperty("Difficulty", "1.5");

					FileOutputStream fos;
					gameConfig.store(fos = new FileOutputStream("resources/gameConfig.properties"), null);
					fos.close();
				}
				catch (IOException vynimka1) {
				}
			}
		});
		add(rdbtnNewRadioButton_2);
		bg.add(rdbtnNewRadioButton_2);

		double difficulty = Double.parseDouble(gameConfig.getProperty("Difficulty"));

		if (difficulty == 1) {
			rdbtnNewRadioButton.setSelected(true);
		} else if (difficulty == 1.3) {
			rdbtnNewRadioButton_1.setSelected(true);
		} else
			rdbtnNewRadioButton_2.setSelected(true);
	}

	private void createChoosePlayerComponents() {
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(playerConfig.getProperty("ImagePath")));
		lblNewLabel.setBounds(30, 230, 70, 70);
		add(lblNewLabel);

		JTextArea playerChooser = new JTextArea();
		playerChooser.setBounds(20, 150, 200, 30);
		playerChooser.setFont(font);
		playerChooser.setForeground(Color.WHITE);
		playerChooser.setOpaque(false);
		playerChooser.setText("Choose character:");
		playerChooser.setEditable(false);
		add(playerChooser);

		String meno = playerConfig.getProperty("ImagePath");
		meno = meno.substring(0, meno.lastIndexOf("/"));
		meno = meno.substring(meno.lastIndexOf("/") + 1);

		JComboBox<String> comboBox = new JComboBox<>();
		
		comboBox.addItem("civil");
		comboBox.addItem("citizen");
		comboBox.addItem("default");
		comboBox.addItem("grandpa");
		comboBox.addItem("hitman");
		comboBox.addItem("robot");
		comboBox.addItem("soldier");
		comboBox.addItem("woman");
		comboBox.setSelectedItem(meno);
		comboBox.setBounds(32, 200, 100, 23);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String player = comboBox.getItemAt(comboBox.getSelectedIndex());
				lblNewLabel.setIcon(new ImageIcon("obr/hrac/" + player + "/3.png"));
				try {
					playerConfig.setProperty("ImagePath", "obr/hrac/" + player + "/3.png");

					FileOutputStream fos;
					playerConfig.store(fos = new FileOutputStream("resources/playerConfig.properties"), null);
					fos.close();
				}
				catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		add(comboBox);
	}
}
