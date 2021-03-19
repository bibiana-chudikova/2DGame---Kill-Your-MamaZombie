package shooter.Game;

import javax.swing.*;

import shooter.Menu.ContentPanel;
import shooter.Menu.PlayerInfo;

/**
 * Class PopWindow creates different pop-up windows depending on different actions in game.
 */
public class PopupWindow {

    UserInput userInput;
    Handler handler;
    public static SoundEffect soundEffectGameOver = new SoundEffect();
    public static SoundEffect soundEffectLoser = new SoundEffect();
    public static SoundEffect soundEffectButtonClick = new SoundEffect();
    public static SoundEffect soundEffectYouWon = new SoundEffect();
    public static SoundEffect soundEffectWinner = new SoundEffect();

    /**
     * Creates pop-up window for paused game.
     *
     * @param userInput user input
     * @param handler   handler
     */
    public PopupWindow(UserInput userInput, Handler handler) {
        this.userInput = userInput;
        this.handler = handler;
        handler.pauseGame();

        JOptionPane.showMessageDialog(null, "Game is paused!\n" +
                "Press 'OK' to continue.", "PAUSE", JOptionPane.INFORMATION_MESSAGE);

        handler.pauseGame();
    }

    /**
     * Creates pop-up window for game over.
     *
     * @param handler handler
     */
    public PopupWindow(Handler handler) {
        this.handler = handler;
        handler.pauseGame();

        soundEffectGameOver.setFileGameOver();
        soundEffectGameOver.play();
        soundEffectLoser.setFileLoser();
        soundEffectLoser.play();

        JOptionPane.showMessageDialog(null, "You lost!\n" +
                "Press 'OK' to continue.", "GAME OVER", JOptionPane.INFORMATION_MESSAGE);

        soundEffectButtonClick.setFileButtonClick();
        soundEffectButtonClick.play();

        ContentPanel.game.shutDown();
        PlayerInfo playerInfo = new PlayerInfo(handler);
        playerInfo.run();
    }

    /**
     * Creates pop-up window for won game.
     *
     * @param handler handler.
     * @param zombieMamaHP life of final boss
     */
    public PopupWindow(Handler handler, int zombieMamaHP) {
        this.handler = handler;
        handler.pauseGame();

        soundEffectYouWon.setFileYouWon();
        soundEffectYouWon.play();
        soundEffectWinner.setFileWinner();
        soundEffectWinner.play();

        JOptionPane.showMessageDialog(null, "YOU WON!\n" +
                "Press 'OK' to continue.", "YOU WON!", JOptionPane.INFORMATION_MESSAGE);

        soundEffectButtonClick.setFileButtonClick();
        soundEffectButtonClick.play();

        ContentPanel.game.shutDown();
        PlayerInfo playerInfo = new PlayerInfo(handler);
        playerInfo.run();
    }
}
