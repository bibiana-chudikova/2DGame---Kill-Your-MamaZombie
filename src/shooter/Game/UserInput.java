package shooter.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Class UserIpnut processes the user input mouse / keyboard.
 * Combines KeyListener and MouseAdapter.
 */
public class UserInput extends MouseAdapter implements KeyListener {

    private static boolean up = false, down = false, left = false, right = false;
    private static int mouseX;
    private static int mouseY;
    private Handler handler;
    PopupWindow popupWindow;

    /**
     * Creates handler for user input.
     *
     * @param handler handler
     */
    public UserInput(Handler handler) {
        this.handler = handler;
    }

    /**
     * Performs commands after key is typed.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Performs commands if key is being pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // updates the movement vectors according to pressed key
        char keyPressed = e.getKeyChar();
        if (keyPressed == 'a') {
            setLeft(true);
        }
        if (keyPressed == 'd') {
            setRight(true);
        }
        if (keyPressed == 'w') {
            setUp(true);
        }
        if (keyPressed == 's') {
            setDown(true);
        }
        if (keyPressed == 'p') {
            popupWindow = new PopupWindow(this, handler);
        }
    }

    /**
     * Performs commands if key is released.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        // resets the movement vectors
        char keyReleased = e.getKeyChar();
        if (keyReleased == 'a') {
            setLeft(false);
        }
        if (keyReleased == 'd') {
            setRight(false);
        }
        if (keyReleased == 'w') {
            setUp(false);
        }
        if (keyReleased == 's') {
            setDown(false);
        }
    }

    /**
     * Performs command, if the mouse is being moved but none of the mouse buttons are clicked.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        setMouseX(e.getX());
        setMouseY(e.getY());
    }

    /**
     * Performs command, if the mouse is clicked over the component and mouse is being moved.
     * Stops the performance after the mouse button is released.
     */
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Performs command after the mouse button is clicked and unclicked.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Performs command after the mouse button is clicked.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        handler.playerShoot();
    }

    /**
     * Performs command after the mouse button is unclicked.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Performs command after the mouse enters the component.
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Performs command after the mouse leaves the component.
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Returns true if key is up.
     *
     * @return true if key is up.
     */
    public static boolean isUp() {
        return up;
    }

    /**
     * Sets the key to up.
     *
     * @param up up
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    /**
     * Returns true if key is down.
     *
     * @return true if key is down.
     */
    public static boolean isDown() {
        return down;
    }

    /**
     * Sets the key to down.
     *
     * @param down down
     */
    public void setDown(boolean down) {
        this.down = down;
    }

    /**
     * Returns true if key is left.
     *
     * @return true if key is left.
     */
    public static boolean isLeft() {
        return left;
    }

    /**
     * Sets the key to left.
     *
     * @param left left
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * Returns true if key is right.
     *
     * @return true if key is right.
     */
    public static boolean isRight() {
        return right;
    }

    /**
     * Sets the key to right.
     *
     * @param right right
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     * Returns X position of mouse.
     *
     * @return X position of mouse
     */
    public static int getMouseX() {
        return mouseX;
    }

    /**
     * Sets the X position of mouse.
     *
     * @param mouseX the X position of mouse
     */
    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    /**
     * Returns Y position of mouse.
     *
     * @return Y position of mouse
     */
    public static int getMouseY() {
        return mouseY;
    }

    /**
     * Sets the Y position of mouse.
     *
     * @param mouseY the Y position of mouse
     */
    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }
}
