package MyClass;

import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Packrle {
    private final String setInput;
    private final String setOutput;

    public Packrle(String setInput, String setOutput) {
        this.setInput = setInput;
        if (setOutput == null) {
            this.setOutput = setOutput + ".out";
        } else {
            this.setOutput = setOutput;
        }
    }

    public void encoding() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(setInput))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(setOutput))) {
                String str;
                while ((str = reader.readLine()) != null) {
                    writer.write(encode(str));
                }
            }
        }
    }

    public void decoding() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(setInput))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(setOutput))) {
                String str;
                while ((str = reader.readLine()) != null) {
                    writer.write(decode(str));
                }
            }
        }
    }

    public static String encode(String str) {
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < str.length() - 1; i++) {
            int runLength = 1;
            if (str.charAt(i) != str.charAt(i + 1)) {
                out.append(str.charAt(i));
                if (i + 1 == str.length() - 1) out.append(str.charAt(i + 1));
            } else {
                while (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
                    runLength++;
                    i++;
                }
                if (runLength >= 3) {
                    out.append("-");
                    out.append(runLength);
                    out.append(str.charAt(i));
                } else {
                    out.append(str.charAt(i));
                    out.append(str.charAt(i));
                }
                if (i + 1 == str.length() - 1) out.append(str.charAt(i + 1));
            }
        }
        return out.toString();
    }

    public static String decode(String str) {
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '-') {
                int l = str.charAt(i+1) - '0';
                while (l > 0) {
                    out.append(str.charAt(i + 2));
                    l--;
                }
                i = i + 2;
            } else out.append(str.charAt(i));
        }
        return out.toString();
    }
}