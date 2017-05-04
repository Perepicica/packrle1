package MyClass.Part2;

import java.io.*;

public class PackRLE {
    private final File fileInput;
    private final File fileOutput;

    public PackRLE(String setInput, String setOutput) {
        this.fileInput = new File(setInput);
        if (setOutput == null) {
            this.fileOutput = new File("out" + this.fileInput.getName());
        } else {
            this.fileOutput = new File(setOutput);
        }
    }

    public void encoding() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileInput))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutput))) {
                String str;
                while ((str = reader.readLine()) != null) {
                    writer.write(encode(str));
                }
            }
        }
    }

    public void decoding() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileInput))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutput))) {
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
                if (runLength > 9) {
                    while (runLength > 9) {
                        out.append("-");
                        out.append("9");
                        out.append(str.charAt(i));
                        runLength = runLength - 9;
                    }
                }
                if (runLength < 3) {
                    if (runLength == 2) {
                        out.append(str.charAt(i));
                        out.append(str.charAt(i));
                    } else out.append(str.charAt(i));
                } else {
                    out.append("-");
                    out.append(runLength);
                    out.append(str.charAt(i));
                }

                if (i + 1 == str.length() - 1) out.append(str.charAt(i + 1));
            }
        }
        return out.toString();
    }

    public static String decode(String str) {
        StringBuffer out = new StringBuffer();
        int last =0;
        for (int i = 0; i < str.length() -2; i++) {
            if (str.charAt(i) == '-' & i < str.length()- 2 & str.charAt(i + 1) >= '0' & str.charAt(i + 1) <= '9') {
                int l = str.charAt(i + 1) - '0';
                last = i;
                while (l > 0) {
                    out.append(str.charAt(i + 2));
                    l--;
                }
                i = i + 2;
            } else out.append(str.charAt(i));
        }
        if (last < str.length()-4){
            out.append(str.charAt(str.length()-2));
            out.append(str.charAt(str.length()-1));
        }
        if (last == str.length()-4) out.append(str.charAt(str.length()-1));
        return out.toString();
    }
}