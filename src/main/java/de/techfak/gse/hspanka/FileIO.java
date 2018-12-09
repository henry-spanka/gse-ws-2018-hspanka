package de.techfak.gse.hspanka;

import java.io.*;

/**
 * Reads and Writes to Files.
 */
public class FileIO {
    /**
     * The file that should be used.
     */
    private final File file;

    /**
     * Initialize the class.
     * @param file The file that should be used.
     */
    public FileIO(final File file) {
        this.file = file;
    }

    /**
     * Write a string to the file.
     * @param data The data to be written.
     * @return Returns true if the write was successfull. False instead.
     */
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

    /**
     * Reads the first line from the file.
     * @return The string or null if an error occurred.
     */
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
