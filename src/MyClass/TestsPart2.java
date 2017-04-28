package MyClass;

import org.junit.Test;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class TestsPart2 {
    @Test
    public void encoding() throws IOException {
        String input = "C:\\Users\\Gangsta\\IdeaProjects\\untitled\\src\\MyClass\\input";
        String output = "C:\\Users\\Gangsta\\IdeaProjects\\untitled\\src\\MyClass\\output";
        Packrle rle = new Packrle(input, output);
        rle.encoding();
    }

    @Test
    public void decoding() throws IOException {
        String input = "C:\\Users\\Gangsta\\IdeaProjects\\untitled\\src\\MyClass\\input2";
        String output = "C:\\Users\\Gangsta\\IdeaProjects\\untitled\\src\\MyClass\\output2";
        Packrle rle = new Packrle(input, output);
        rle.decoding();
    }

    @Test
    public void encode() {
        assertEquals("-4A", Packrle.encode("AAAA"));
        assertEquals("1d-4A", Packrle.encode("1dAAAA"));
        assertEquals("-4Ahuf", Packrle.encode("AAAAhuf"));
        assertEquals("-4A-3hj", Packrle.encode("AAAAhhhj"));
    }
    @Test
    public void decode() {
        assertEquals("AAAA", Packrle.decode("-4A"));
        assertEquals("1dAAAA", Packrle.decode("1d-4A"));
        assertEquals("AAAAhuf", Packrle.decode("-4Ahuf"));
        assertEquals("AAAAhhhj", Packrle.decode("-4A-3hj"));
    }
}
