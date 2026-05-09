import javax.swing.text.StyledDocument;

public interface LanguageSyntax {
    void applyHighlighting(StyledDocument doc,String text);
}
