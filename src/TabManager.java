import javax.swing.*;
import java.awt.*;

public class TabManager {
    private JTabbedPane tabbedPane;

    public TabManager() {
        tabbedPane = new JTabbedPane();
    }

    public void addTab(){
        JPanel panel = new JPanel(new BorderLayout());
        JTextPane textPane = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(textPane);

        panel.add(scrollPane,BorderLayout.CENTER);
        tabbedPane.addTab("Untitled", panel);
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
        tabbedPane.setTabComponentAt(
                tabbedPane.getTabCount()-1,
                createTabHeader("Untitled")
        );
    }

    private JPanel createTabHeader(String title){
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel titleLabel = new JLabel(title+" ");
        JButton closeBtn = new JButton("x");
        closeBtn.setMargin(new Insets(0,4,0,4));

        closeBtn.addActionListener(e -> {
            int index = tabbedPane.indexOfTabComponent(header);
            //if(tabbedPane.getTabCount()>1)
                tabbedPane.remove(index);
        });

        header.add(titleLabel);
        header.add(closeBtn);
        return header;
    }

    public JTabbedPane getPane() {
        return tabbedPane;
    }
}
