package network;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigLoader {
    private Path filePath;

    public ConfigLoader(String localPath) {
        this.filePath = Path.of(localPath);
    }

    public int readPortNumber() {
        String result;
        try {
            result = nextWord(readFileContent(), "PortNumber:");
        } catch (IOException e) {
            result = "8000";
        }
        if(result == null){
            result = "8000";
        }
        return Integer.parseInt(result);
    }

    public String readIP() {
        String result;
        try {
            result = nextWord(readFileContent(), "IP:");
        } catch (IOException e) {
            result = "localhost";
        }
        if(result == null) {
            result = "localhost";
        }
        return result;
    }

    private String readFileContent() throws IOException {
        String result = null;
        result = Files.readString(filePath);
        return result;
    }

    private String nextWord(String message, String word) {
        String[] strArr = message.split(" ");
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals(word)) {
                return strArr[i + 1];
            }
        }
        return null;
    }
}
