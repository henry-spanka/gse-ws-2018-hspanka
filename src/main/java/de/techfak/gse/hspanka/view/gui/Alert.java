package de.techfak.gse.hspanka.view.gui;

public class Alert extends javafx.scene.control.Alert {
    public Alert(AlertType alertType) {
        super(alertType);
    }

    public void saveError() {
        setTitle("Chess Game Error");
        setHeaderText("An error occured during execution!");
        setContentText("Game could not be saved due to an unknown error.");
    }

    public void loadError() {
        setTitle("Chess Game Error");
        setHeaderText("An error occured during execution!");
        setContentText("Game could not be loaded due to an unknown error.");
    }
}
