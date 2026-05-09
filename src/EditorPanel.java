import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.File;

public class EditorPanel extends JPanel {
    private final JScrollPane scrollPane;
    private final JTextPane textPane;
    private LineNumberPanel lineNumberPanel;
    private File file;
    private LanguageSyntax syntax;

    public EditorPanel(String content) {
        setLayout(new BorderLayout());
        textPane = new JTextPane();
        System.out.println(textPane.getFont().getSize());
        textPane.setFont(new Font("", Font.PLAIN, 20));
        //textPane.setFont((textPane.getFont()).deriveFont(textPane.getFont().getSize() + 10));
        textPane.setText(content);
        scrollPane = new JScrollPane(textPane);
        lineNumberPanel = new LineNumberPanel(textPane);
        scrollPane.setRowHeaderView(new LineNumberPanel(textPane));//lineNumberPanel);
        add(scrollPane, BorderLayout.CENTER);
        textPane.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                scrollPane.repaint();
                triggerHighlight();
            }
            public void removeUpdate(DocumentEvent e) {
                scrollPane.repaint();
                triggerHighlight();
            }
            public void changedUpdate(DocumentEvent e) {}
        });
        file=null;
        triggerHighlight();
    }

    private void triggerHighlight() {
        SwingUtilities.invokeLater(() -> SyntaxHighlighter.highlight(textPane,syntax));
    }
    public JTextPane getTextPane() {
        return textPane;
    }
    public String getContent() {
        return textPane.getText();
    }
    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
        this.syntax = SyntaxFactory.getSyntax(getFileExtension());
        triggerHighlight();
    }
    private String getFileExtension() {
        if (file == null)
            return null;

        String name = file.getName();
        int lastIndex = name.lastIndexOf('.');

        if (lastIndex == -1 || lastIndex == name.length() - 1)
            return null;
        return name.substring(lastIndex + 1);
    }
}
