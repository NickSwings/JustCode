import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TabManager {
    private JTabbedPane tabbedPane;

    public TabManager() {
        tabbedPane = new JTabbedPane();
    }

    public void saveAsFile() {
        int index=tabbedPane.getSelectedIndex();
        if(index<0)
            return;

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result=chooser.showSaveDialog(null);
        if(result!=JFileChooser.APPROVE_OPTION)
            return;
        File file=chooser.getSelectedFile();

        JPanel panel = (JPanel) tabbedPane.getComponentAt(index);
        JScrollPane scroll = (JScrollPane) panel.getComponent(0);
        JTextPane textPane = (JTextPane) scroll.getViewport().getView();
        String content = textPane.getText();

        try{
            Files.write(file.toPath(),content.getBytes());
        }catch(IOException e){e.printStackTrace();}
    }

    public void openFile(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result=chooser.showOpenDialog(null);
        if(result==JFileChooser.APPROVE_OPTION){
            File file=chooser.getSelectedFile();
            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                addTab(file.getName(),content);
            }catch (IOException e){e.printStackTrace();}
        }
    }

    public void addTab(String title, String content){
        JPanel panel = new JPanel(new BorderLayout());
        JTextPane textPane = new JTextPane();
        textPane.setText(content);
        JScrollPane scrollPane = new JScrollPane(textPane);

        panel.add(scrollPane,BorderLayout.CENTER);
        tabbedPane.addTab(title, panel);
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
        tabbedPane.setTabComponentAt(
                tabbedPane.getTabCount()-1,
                createTabHeader(title)
        );
    }

    public void addTab(){
        addTab("Untitled","");
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
