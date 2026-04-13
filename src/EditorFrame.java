import javax.swing.*;
import java.awt.*;

public class EditorFrame {
    private JFrame frame;
    private TabManager tabManager;

    public EditorFrame(){
        frame = new JFrame("Lite Code");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        initMenuBar();

        tabManager = new TabManager();
        frame.add(tabManager.getPane(), BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void initMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem newTab = new JMenuItem("New");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem exit = new JMenuItem("Exit");

        newTab.addActionListener(e -> tabManager.addTab());
        exit.addActionListener(e -> System.exit(0));

        fileMenu.add(newTab);
        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.addSeparator();
        fileMenu.add(exit);

        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
    }
}
