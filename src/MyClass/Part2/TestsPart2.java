package MyClass.Part2;

import MyClass.Part2.PackRLE;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestsPart2 {
    @Test
    public void encoding() throws IOException {
        String input = "src\\MyClass\\input";
        String output = "src\\MyClass\\output";
        PackRLE rle = new PackRLE(input, output);
        rle.encoding();
    }
    @Test
    public void encoding2() throws IOException {
        String input = "src\\MyClass\\input";
        PackRLE rle = new PackRLE(input, null);
        rle.encoding();
        File file = new File("outinput");
        assertTrue(file.isFile());
        file.delete();
    }

    @Test
    public void decoding() throws IOException {
        String input = "src\\MyClass\\input2";
        String output = "src\\MyClass\\output2";
        PackRLE rle = new PackRLE(input, output);
        rle.decoding();
    }

    @Test
    public void encode() {
        assertEquals("-4A", PackRLE.encode("AAAA"));
        assertEquals("1d-4A", PackRLE.encode("1dAAAA"));
        assertEquals("-4Ahuf", PackRLE.encode("AAAAhuf"));
        assertEquals("-4A-3hj", PackRLE.encode("AAAAhhhj"));
        assertEquals("-9S-9SS", PackRLE.encode("SSSSSSSSSSSSSSSSSSS"));
        assertEquals("-4x", PackRLE.encode("-4x")); 
    }
    @Test
    public void decode() {
        assertEquals("AAAA-4", PackRLE.decode("-4A-4"));
        assertEquals("1dAAAAd", PackRLE.decode("1d-4Ad"));
        assertEquals("AAAAhuf", PackRLE.decode("-4Ahuf"));
        assertEquals("AAAAhhhj", PackRLE.decode("-4A-3hj"));
        assertEquals("xxxx", PackRLE.decode("-4x"));
    }
}
