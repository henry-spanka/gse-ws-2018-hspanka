package de.techfak.gse.hspanka.view.gui;

import javafx.stage.Window;

import java.io.File;

public class FileChooser {
    public static final javafx.stage.FileChooser.ExtensionFilter extensionFilter =
        new javafx.stage.FileChooser.ExtensionFilter("FEN files (*.fen)", "*.fen");
    private javafx.stage.FileChooser fileChooser;
    private Window window;

    public FileChooser(Window window) {
        fileChooser = new javafx.stage.FileChooser();
        fileChooser.getExtensionFilters().add(extensionFilter);
        this.window = window;
    }

    public File getFile() {
        return fileChooser.showSaveDialog(window);
    }
}
