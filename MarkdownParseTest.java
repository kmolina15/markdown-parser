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
    public void testFile2() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("https://something.com");
        expected.add("some-page.html");
        
        Path fileName = Path.of("test-file2.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        
        assertEquals(expected, links);
    }

    @Test
    public void testFile3() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        Path fileName = Path.of("test-file3.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);

        assertEquals(expected, links);
    }

    @Test
    public void testBackticks() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("`google.com");
        expected.add("google.com");
        expected.add("ucsd.edu");

        Path fileName = Path.of("week8-test1.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);

        assertEquals(expected, links);
    }

    @Test
    public void testNested() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("a.com");
        expected.add("a.com(())");
        expected.add("example.com");

        Path fileName = Path.of("week8-test2.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);

        assertEquals(expected, links);
    }

    @Test
    public void testLineBreaks() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule");

        Path fileName = Path.of("week8-test3.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);

        assertEquals(expected, links);
    }

    @Test
    public void testMyFile1() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("some-link.html");
        
        Path fileName = Path.of("my-test.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        
        assertEquals(expected, links);
    }

    @Test
    public void testMyFile2() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        
        Path fileName = Path.of("my-test2.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        
        assertEquals(expected, links);
    }

    @Test
    public void testMyFile3() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("example.com");
        expected.add("example.html");
        expected.add("sample.com");
        
        Path fileName = Path.of("my-test3.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        
        assertEquals(expected, links);
    }

    @Test
    public void testMyFile4() throws IOException {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("real-link.html");
        
        Path fileName = Path.of("my-test4.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(content);
        
        assertEquals(expected, links);
    }
}