package de.techfak.gse.hspanka.app;

import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;
import javafx.scene.Scene;

public interface ChessGameApplication {
    void run(String... args) throws ApplicationErrorException;

    void setScene(Scene scene);
}
