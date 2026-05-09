public class SyntaxFactory {

    public static LanguageSyntax getSyntax(String extension) {
        if (extension == null)
            return null;

        switch (extension.toLowerCase()) {
            case "c":
            case "h":
                return new CSyntax();
//            case "java":
//                return new JavaSyntax();
//            case "cpp":
//                return new CppSyntax();
            default:
                return null;
        }
    }
}