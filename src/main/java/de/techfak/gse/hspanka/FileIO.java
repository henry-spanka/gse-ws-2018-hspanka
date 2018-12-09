package de.techfak.gse.hspanka;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
}
