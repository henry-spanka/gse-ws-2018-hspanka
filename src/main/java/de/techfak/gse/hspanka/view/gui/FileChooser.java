package de.techfak.gse.hspanka.view.gui;

import javafx.stage.Window;

import java.io.File;

/**
 * Opens a window to choose a file.
 */
public class FileChooser {
    /**
     * The Fen file extension.
     */
    private static final String FEN_EXTENSION = ".fen";
    /**
     * Which extensions should be allowed to select.
     */
    private static final javafx.stage.FileChooser.ExtensionFilter EXTENSION_FILTER =
        new javafx.stage.FileChooser.ExtensionFilter("FEN files (*" + FEN_EXTENSION + ")", "*" + FEN_EXTENSION);

    /**
     * The File chooser.
     */
    private final javafx.stage.FileChooser fxFileChooser;

    /**
     * The Window where the chooser should be displayed.
     */
    private final Window window;

    /**
     * Initializes the File Chooser.
     * @param window The window where the chooser should be displayed.
     */
    public FileChooser(final Window window) {
        fxFileChooser = new javafx.stage.FileChooser();
        fxFileChooser.getExtensionFilters().add(EXTENSION_FILTER);
        this.window = window;
    }

    /**
     * Show a Save dialog.
     * @return The file selected.
     */
    public File saveFile() {
        File file = fxFileChooser.showSaveDialog(window);

        if (!file.toString().endsWith("." + FEN_EXTENSION)) {
            file = new File(file.toString() + FEN_EXTENSION);
        }

        return file;
    }

    /**
     * Show an Open dialog.
     * @return The file selected.
     */
    public File openFile() {
        return fxFileChooser.showOpenDialog(window);
    }
}
