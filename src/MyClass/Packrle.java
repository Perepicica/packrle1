package MyClass;

import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Packrle {
    private final String setInput;
    private final String setOutput;

    public Packrle(String setInput, String setOutput) {
        this.setInput = setInput;
        this.setOutput = setOutput;
    }

    public int packrle(String inputName, String outputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                return packrle(inputStream, outputStream);
            }
        }
    }

    public String packrle(String source) throws IOException {
        try (StringBuffer dest = new StringBuffer() {
                    for(int i = 0; i<source.length();i++){
                int runLength = 1;
                while (i + 1 < source.length()
                        && source.charAt(i) == source.charAt(i + 1)) {
                    runLength++;
                    i++;
                }
                dest.append(runLength);
                dest.append(source.charAt(i));
            }
                    return dest.toString();
        }

    public static String decode(String source) {
        StringBuffer dest = new StringBuffer();
        Pattern pattern = Pattern.compile("[0-9]+|[a-zA-Z]");
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            int number = Integer.parseInt(matcher.group());
            matcher.find();
            while (number-- != 0) {
                dest.append(matcher.group());
            }
        }
        return dest.toString();
    }
}



