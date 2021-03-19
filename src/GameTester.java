import java.io.*;
import java.sql.SQLException;

import javax.swing.Timer;

import shooter.Game.Handler;
import shooter.Menu.Database;
//import junit.framework.Test;
import shooter.Menu.Menu;

/**
 * Class GameTester tests the game.
 */
public class GameTester {

	public static void main(String[] args) throws IOException {
		// creates and runs the game
		Menu game = new Menu();
		game.run();
	}
}