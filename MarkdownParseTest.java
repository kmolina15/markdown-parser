import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.*;

public class MarkdownParseTest {

    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void testTwoLinks() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("https://something.com");
        expected.add("some-page.html");
        
        Path fileName = Path.of("test-file2.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        
        assertEquals(expected, links);
    }

}