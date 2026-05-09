import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSyntax implements LanguageSyntax {
//    private static final String[] KEYWORDS = {
//            "auto", "break", "case", "char", "const", "continue", "default", "do",
//            "double", "else", "enum", "extern", "float", "for", "goto", "if",
//            "int", "long", "register", "return", "short", "signed", "sizeof", "static",
//            "struct", "switch", "typedef", "union", "unsigned", "void", "volatile", "while"
//    };
//
//    private static final String KEYWORD_PATTERN = "\\b("+String.join("|", KEYWORDS)+")\\b";
//    private static final Pattern PATTERN = Pattern.compile("\"[^\"]*\"" + "|" + "//.*" + "|" +  KEYWORD_PATTERN + "|" + "#[a-zA-Z]+");
//    private static final Pattern PATTERN = Pattern.compile("\"[^\"]*\"|//.*|auto|break|case|char|const|continue|default|do|double|else|enum|extern|float|for|goto|if|int|long|register|return|short|signed|sizeof|static|struct|switch|typedef|union|unsigned|void|volatile|while|#[a-zA-Z]+");
    private static final Pattern PATTERN = getPattern();
    private static final SimpleAttributeSet
            DEFAULT_ATTR = new SimpleAttributeSet(),
            KEYWORD_ATTR = new SimpleAttributeSet(),
            COMMENT_ATTR = new SimpleAttributeSet(),
            STRING_ATTR = new SimpleAttributeSet(),
            PREPROC_ATTR = new SimpleAttributeSet();

    static {
        StyleConstants.setForeground(DEFAULT_ATTR, Color.BLACK);
        StyleConstants.setBold(DEFAULT_ATTR, false);

        StyleConstants.setForeground(KEYWORD_ATTR, Color.MAGENTA);
        StyleConstants.setForeground(COMMENT_ATTR, new Color(0,150,0));
        StyleConstants.setForeground(STRING_ATTR, Color.BLUE);
        StyleConstants.setForeground(PREPROC_ATTR, new Color(150,0,150));
    }

    @Override
    public void applyHighlighting(StyledDocument doc, String text) {
        //System.out.println(KEYWORD_PATTERN);
        Matcher matcher = PATTERN.matcher(text);
        while (matcher.find()) {
            int start = matcher.start();
            int length = matcher.end() - start;
            String match = text.substring(start, matcher.end());

            if (match.startsWith("\""))
                doc.setCharacterAttributes(start, length, STRING_ATTR, false);
            else if (match.startsWith("//"))
                doc.setCharacterAttributes(start, length, COMMENT_ATTR, false);
            else if (match.startsWith("#"))
                doc.setCharacterAttributes(start, length, PREPROC_ATTR, false);
            else
                doc.setCharacterAttributes(start, length, KEYWORD_ATTR, false);
        }
    }
    private static Pattern getPattern(){
        String[] KEYWORDS = {
            "auto", "break", "case", "char", "const", "continue", "default", "do",
            "double", "else", "enum", "extern", "float", "for", "goto", "if",
            "int", "long", "register", "return", "short", "signed", "sizeof", "static",
            "struct", "switch", "typedef", "union", "unsigned", "void", "volatile", "while"
    };

    String KEYWORD_PATTERN = "\\b("+String.join("|", KEYWORDS)+")\\b";
    return Pattern.compile("\"[^\"]*\"" + "|" + "//.*" + "|" +  KEYWORD_PATTERN + "|" + "#[a-zA-Z]+");
    }
}
