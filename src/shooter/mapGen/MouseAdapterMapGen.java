package shooter.mapGen;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Class MouseAdapterMapGen serves for receiving mouse events.
 */
public class MouseAdapterMapGen extends MouseAdapter {

    private HandlerMapGen handler;

    /**
     * Creates mouse adapter for the map generator.
     *
     * @param handler handler
     */
    public MouseAdapterMapGen(HandlerMapGen handler) {
        this.handler = handler;
    }

    /**
     * Processes the mouse pressed events.
     *
     * @param event mouse event
     */
    @Override
    public void mousePressed(MouseEvent event) {
        int x = event.getX();
        int y = event.getY();
        if (event.getButton() == 1) {
            double ID = handler.getSelectedItemID();
            handler.addObjetOnCanvas(ID, x, y);
        }
        if (event.getButton() == 3) {
            handler.deleteObjectsAt(x, y);
        }
    }
}