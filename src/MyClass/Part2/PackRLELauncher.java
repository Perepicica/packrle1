package MyClass.Part2;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;


public class PackRLELauncher {
    @Option(name = "-z", usage = "Упаковывает файл")
    private boolean encoding;
    @Option(name = "-u", usage = "Распаковывает файл", forbids = {"-z"})
    private boolean decoding;
    @Option(name = "-out", usage = "Имя выходного файла")
    private String outputFileName;
    @Argument(required = true, usage = "Имя входного файла")
    private String inputFileName;

    public static void main(String[] args) {
        new PackRLELauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Packrle.jar -z -u -out ");
            parser.printUsage(System.err);
            return;
        }
        PackRLE rle = new PackRLE(inputFileName, outputFileName);
        if (encoding) {
            try {
                rle.encoding();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (decoding) {
            try {
                rle.decoding();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("There is no such option");
            System.exit(0);
        }
    }
}
