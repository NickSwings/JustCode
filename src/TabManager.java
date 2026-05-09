import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TabManager {
    private JTabbedPane tabbedPane;

    public TabManager() {
        tabbedPane = new JTabbedPane();
    }

    public void saveAsFile(){
        int index=tabbedPane.getSelectedIndex();
        if(index<0)
            return;
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result=chooser.showSaveDialog(null);
        if(result!=JFileChooser.APPROVE_OPTION)
            return;
        File file=chooser.getSelectedFile();
        EditorPanel editorPanel = (EditorPanel) tabbedPane.getComponentAt(index);
        String content = editorPanel.getContent();
        try{
            Files.write(file.toPath(),content.getBytes());
            editorPanel.setFile(file);
            tabbedPane.setTabComponentAt(index,createTabHeader(file.getName()));
        }catch(IOException e){e.printStackTrace();}
    }

    public void saveFile() {
        int index=tabbedPane.getSelectedIndex();
        if(index<0)
            return;

        EditorPanel editorPanel = (EditorPanel) tabbedPane.getComponentAt(index);
        String content = editorPanel.getContent();
        File file=editorPanel.getFile();
        if(file==null){
            saveAsFile();
            return;
        }

        try{
            Files.write(file.toPath(),content.getBytes());
            editorPanel.setFile(file);
            tabbedPane.setTabComponentAt(index,createTabHeader(file.getName()));
        }catch(IOException e){e.printStackTrace();}
    }

    public void openFile(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result=chooser.showOpenDialog(null);
        if(result!=JFileChooser.APPROVE_OPTION)
            return;

        File file=chooser.getSelectedFile();
        try {
            String content = new String(Files.readAllBytes(file.toPath()));
            EditorPanel editorPanel=addTab(file.getName(),content);
            editorPanel.setFile(file);
        }catch (IOException e){e.printStackTrace();}
    }

    public EditorPanel addTab(String title, String content){
        EditorPanel editorPanel=new EditorPanel(content);
        tabbedPane.addTab(title, editorPanel);
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
        tabbedPane.setTabComponentAt(
                tabbedPane.getTabCount()-1,
                createTabHeader(title)
        );
        return editorPanel;
    }

    public void addTab(){
        addTab("Untitled","");
    }

    private JPanel createTabHeader(String title){
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel titleLabel = new JLabel(title+" ");
        JButton closeBtn = new JButton("x");
        closeBtn.setMargin(new Insets(0,4,0,4));

        closeBtn.addActionListener(e -> tabbedPane.remove(tabbedPane.indexOfTabComponent(header)));

        header.add(titleLabel);
        header.add(closeBtn);
        return header;
    }

    public JTabbedPane getPane() {
        return tabbedPane;
    }
}
