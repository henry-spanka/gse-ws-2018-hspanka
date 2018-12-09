package de.techfak.gse.hspanka;

import java.io.*;

public class FileIO {
    private final File file;

    public FileIO(final File file) {
        this.file = file;
    }

    public boolean write(String data) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public String read() {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(fileReader);

            return buffReader.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}
