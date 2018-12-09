package de.techfak.gse.hspanka.view.gui;

import javafx.stage.Window;

import java.io.File;

/**
 * Opens a window to choose a file.
 */
public class FileChooser {
    /**
     * Which extensions should be allowed to select.
     */
    public static final javafx.stage.FileChooser.ExtensionFilter extensionFilter =
        new javafx.stage.FileChooser.ExtensionFilter("FEN files (*.fen)", "*.fen");

    /**
     * The File chooser.
     */
    private javafx.stage.FileChooser fileChooser;

    /**
     * The Window where the chooser should be displayed.
     */
    private Window window;

    /**
     * Initializes the File Chooser.
     * @param window The window where the chooser should be displayed.
     */
    public FileChooser(Window window) {
        fileChooser = new javafx.stage.FileChooser();
        fileChooser.getExtensionFilters().add(extensionFilter);
        this.window = window;
    }

    /**
     * Show a Save dialog.
     * @return The file selected.
     */
    public File saveFile() {
        return fileChooser.showSaveDialog(window);
    }

    /**
     * Show an Open dialog.
     * @return The file selected.
     */
    public File openFile() {
        return fileChooser.showOpenDialog(window);
    }
}
