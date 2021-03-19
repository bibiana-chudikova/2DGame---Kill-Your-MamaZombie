package shooter.Game;

import shooter.Menu.Panel3_Settings;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Class SoundEffect adds various sound effects into the game.
 */
public class SoundEffect {

    private Clip clip;

    /**
    * Creates sound effect.
    */
    public SoundEffect(){
    }

    /**
     * Adds intro music into menu window.
     */
    public void setFileMenuMusic(){
        File file = new File("sound\\music_menu.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
            setVolume(-20);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds shooting sound of main player.
     */
    public void setFileShoot(){
        File file = new File("sound\\shoot_sound.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        if(!Panel3_Settings.soundIsOn[0]) {
            setVolume(-80);
        }
    }

    /**
     * Adds sound when items is picked up.
     */
    public void setFileItemPickUp(){
        File file = new File("sound\\item_pick_up.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        if(!Panel3_Settings.soundIsOn[0]) {
            setVolume(-80);
        }
    }

    /**
     * Adds sound when game is over.
     */
    public void setFileGameOver(){
        File file = new File("sound\\gameover.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        setVolume(-15);

        if(!Panel3_Settings.soundIsOn[0]) {
            setVolume(-80);
        }
    }

    /**
     * Adds sound when game is won.
     */
    public void setFileYouWon(){
        File file = new File("sound\\you_won.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        setVolume(-15);

        if(!Panel3_Settings.soundIsOn[0]) {
            setVolume(-80);
        }
    }

    /**
     * Adds sound when enemy dies.
     */
    public void setFileZomebieDeath(){
        File file = new File("sound\\zombie_death.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        if(!Panel3_Settings.soundIsOn[0]) {
            setVolume(-80);
        }
    }

    /**
     * Adds sound when final boss is spawned.
     */
    public void setFileFinalBossSpawn(){
        File file = new File("sound\\final_boss_spawn.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        if(!Panel3_Settings.soundIsOn[0]) {
            setVolume(-80);
        }
    }

    /**
     * Adds sound when button is clicked.
     */
    public void setFileButtonClick(){
        File file = new File("sound\\button_click.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        if(!Panel3_Settings.soundIsOn[0]) {
            setVolume(-80);
        }
    }
    
    /**
     * Adds sound when game is started.
     */
    public void setFileBegin(){
        File file = new File("sound\\begin.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        setVolume(-15);

        if(!Panel3_Settings.soundIsOn[0]) {
            setVolume(-80);
        }
    }
    
    /**
     * Adds another sound when game is over.
     */
    public void setFileLoser(){
        File file = new File("sound\\loser.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        if(!Panel3_Settings.soundIsOn[0]) {
            setVolume(-80);
        }
    }

    /**
     * Adds another sound when game is won.
     */
    public void setFileWinner(){
        File file = new File("sound\\winner.wav");
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        if(!Panel3_Settings.soundIsOn[0]) {
            setVolume(-80);
        }
    }

    /**
     * Increases(+) / decreases(-) volume of specific sound.
     *
     * @param value value of volume
     */
    public void setVolume(int value){
        FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        floatControl.setValue(value);
    }

    /**
     * Starts the sound.
     */
    public void play(){
        clip.setFramePosition(0);
        clip.start();
    }

    /**
     * Stop the sound.
     */
    public void stopMusic(){
        clip.stop();
    }
}
