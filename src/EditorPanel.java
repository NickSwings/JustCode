import javax.swing.*;
import java.awt.*;
import java.io.File;

public class EditorPanel extends JPanel {
    private JScrollPane scrollPane;
    private JTextPane textPane;
    private File file;

    public EditorPanel(String content) {
        setLayout(new BorderLayout());
        textPane = new JTextPane();
        textPane.setText(content);
        scrollPane = new JScrollPane(textPane);
        add(scrollPane, BorderLayout.CENTER);
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
