import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.File;

public class EditorPanel extends JPanel {
    private JScrollPane scrollPane;
    private JTextPane textPane;
    private LineNumberPanel lineNumberPanel;
    private File file;

    public EditorPanel(String content) {
        setLayout(new BorderLayout());
        textPane = new JTextPane();
        textPane.setText(content);
        scrollPane = new JScrollPane(textPane);
        lineNumberPanel = new LineNumberPanel(textPane);
        scrollPane.setRowHeaderView(lineNumberPanel);
        add(scrollPane, BorderLayout.CENTER);
        textPane.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {scrollPane.repaint();}
            public void removeUpdate(DocumentEvent e) {scrollPane.repaint();}
            public void changedUpdate(DocumentEvent e) {}
        });
        file=null;
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
    }
}
