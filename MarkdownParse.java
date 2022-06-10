//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkdownParse {

    public static Map<String, List<String>> getLinks(File dirOrFile) throws IOException {
        Map<String, List<String>> result = new HashMap<>();
        if(dirOrFile.isDirectory()) {
            for(File f: dirOrFile.listFiles()) {
                result.putAll(getLinks(f));
            }
            return result;
        }
        else {
            Path p = dirOrFile.toPath();
            int lastDot = p.toString().lastIndexOf(".");
            if(lastDot == -1 || !p.toString().substring(lastDot).equals(".md")) {
                return result;
            }
            ArrayList<String> links = getLinks(Files.readString(p));
            result.put(dirOrFile.getPath(), links);
            return result;
        }
    }

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            int openBracket = markdown.indexOf("[", currentIndex);
            int closeBracket = markdown.indexOf("]", openBracket);
            int openParen = markdown.indexOf("(", closeBracket);
            while (markdown.indexOf("[", openParen) < markdown.indexOf(")", openParen) 
                && markdown.indexOf("[", openParen) != -1
                && openParen != -1) {
                    openBracket = markdown.indexOf("[", openParen);
                    closeBracket = markdown.indexOf("]", openBracket);
                    openParen = markdown.indexOf("(", closeBracket);
                }
            int closeParen = markdown.indexOf(")", openParen);
            if (openBracket == -1 || closeBracket == -1 || 
                openParen == -1 || closeParen == -1) {
                return toReturn;
            }
            if (openParen - 1 == closeBracket) {
                if (openBracket == 0) {
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                }
                else if (markdown.charAt(openBracket - 1) != '!') {
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                }
            }
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        File fileName = new File(args[0]);
        Map<String, List<String>> links = getLinks(fileName);
	    System.out.println(links);
    }
}
