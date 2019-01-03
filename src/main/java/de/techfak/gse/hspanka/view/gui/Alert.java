package de.techfak.gse.hspanka.view.gui;

/**
 * Shows an alert box.
 */
public class Alert extends javafx.scene.control.Alert {
    /**
     * The Chess Game error.
     */
    private static final String ERR_TITLE = "Chess Game Error";

    /**
     * The Chess game unknown error.
     */
    private static final String ERR_UNKNOWN = "An error occured during execution!";

    /**
     * Creates a new alert box.
     *
     * @param alertType The alert type.
     */
    public Alert(final AlertType alertType) {
        super(alertType);
    }

    /**
     * Set's the alert box to show a save error.
     */
    public void saveError() {
        setTitle(ERR_TITLE);
        setHeaderText(ERR_UNKNOWN);
        setContentText("Game could not be saved due to an unknown error.");
    }

    /**
     * Set's the alert box to show a load error.
     */
    public void loadError() {
        setTitle(ERR_TITLE);
        setHeaderText(ERR_UNKNOWN);
        setContentText("Game could not be loaded due to an unknown error.");
    }
}
