package MyClass;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;


public class PackrleLauncher {
    @Option(name = "-z", required = true, usage = "Упаковывает файл")
    private boolean encoding;
    @Option(name = "-u", required = true, usage = "Распаковывает файл")
    private boolean decoding;
    @Option(name = "-out", required = true, usage = "Имя выходного файла")
    private String outputFileName;
    @Argument(required = true, usage = "Имя входного файла")
    private String inputFileName;

    public static void main(String[] args) {
        new PackrleLauncher().launch(args);
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
        Packrle rle = new Packrle(inputFileName, outputFileName);
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
