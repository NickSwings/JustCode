import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import java.awt.*;

public class LineNumberPanel extends JComponent {
    private final JTextPane pane;

    public LineNumberPanel(JTextPane pane) {
        setBackground(Color.LIGHT_GRAY);
        setOpaque(true);

        this.pane = pane;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(pane.getFont());
        FontMetrics fm = g.getFontMetrics();
        FontMetrics metrics = pane.getFontMetrics(pane.getFont());
        int fontHeight = metrics.getHeight();
        int fontAscent = metrics.getAscent();

        int lineHeight = fm.getHeight();
        int startLine = 1;

        Element root = pane.getDocument().getDefaultRootElement();
        int lineCount = root.getElementCount();

        for (int i = 0; i < lineCount; ) {
            try {
                int offset = root.getElement(i++).getStartOffset();
                Rectangle rect = pane.modelToView(offset);

                if (rect != null) {
                    String lineNum = String.valueOf(i);
                    g.drawString(lineNum, 5, rect.y + fontAscent);
                }
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        // Dynamically adjust width based on total lines
        int lineCount = pane.getDocument().getDefaultRootElement().getElementCount();
        int width = pane.getFontMetrics(pane.getFont()).stringWidth(lineCount + " ") + 10;
        return new Dimension(Math.max(width, 30), pane.getHeight());
    }
}
