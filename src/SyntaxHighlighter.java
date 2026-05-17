import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class SyntaxHighlighter {

    private static final SimpleAttributeSet DEFAULT_ATTR = new SimpleAttributeSet();

    static {
        StyleConstants.setForeground(DEFAULT_ATTR, Color.BLACK);
        StyleConstants.setBold(DEFAULT_ATTR, false);
    }

    public static void highlight(JTextPane textPane, LanguageSyntax syntax) {
        StyledDocument doc = textPane.getStyledDocument();
        String text;

        try {
            text = doc.getText(0, doc.getLength());
        }
        catch (BadLocationException e) {
            e.printStackTrace();
            return;
        }

        doc.setCharacterAttributes(0, text.length(), DEFAULT_ATTR, true);

        if (syntax != null) {
            syntax.applyHighlighting(doc, text);
        }
    }
}