package MyClass;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;


public class PackrleLauncher {
    @Option(name = "-z", required = true, usage = "Упаковывает файл")
    private String Packing;
    @Option(name = "-u", required = true, usage = "Распаковывает файл")
    private String Unpacking;
    @Option(name = "-out", usage = "Имя выходного файла")
    private String OutputFileName;
    @Argument(required = true, usage = "Имя входного файла")
    private String InputFileName;

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
        Packrle pack = new Packrle(Packing, Unpacking);
        try {
            int result = pack.packrle(InputFileName, OutputFileName);
            System.out.println("Total of " + result + "symbols recoded");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
