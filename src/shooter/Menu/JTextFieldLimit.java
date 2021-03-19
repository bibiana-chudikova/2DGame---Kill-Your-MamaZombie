package shooter.Menu;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Class JTextFieldLimit sets the max number of character, that can be inserted into textField.
 */
public class JTextFieldLimit extends PlainDocument {

    private int limit;

    /**
     * Sets the limit of characters.
     *
     * @param limit limit
     */
    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    /**
     * Processes the number of characters in textField.
     *
     * @param offset offset
     * @param str    string
     * @param attr   attribute
     * @throws BadLocationException BadLocationException
     */
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;
        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}
