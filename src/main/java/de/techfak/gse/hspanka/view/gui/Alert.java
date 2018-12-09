package de.techfak.gse.hspanka.view.gui;

/**
 * Shows an alert box.
 */
public class Alert extends javafx.scene.control.Alert {
    public Alert(final AlertType alertType) {
        super(alertType);
    }

    /**
     * Set's the alert box to show a save error.
     */
    public void saveError() {
        setTitle("Chess Game Error");
        setHeaderText("An error occured during execution!");
        setContentText("Game could not be saved due to an unknown error.");
    }

    /**
     * Set's the alert box to show a load error.
     */
    public void loadError() {
        setTitle("Chess Game Error");
        setHeaderText("An error occured during execution!");
        setContentText("Game could not be loaded due to an unknown error.");
    }
}
