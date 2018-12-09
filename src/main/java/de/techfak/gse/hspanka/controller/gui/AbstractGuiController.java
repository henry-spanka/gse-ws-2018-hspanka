package de.techfak.gse.hspanka.controller.gui;

import de.techfak.gse.hspanka.app.ChessGameGUIApplication;

/**
 * The abstract GUI controller class.
 */
public class AbstractGuiController {
    /**
     * The GUI Application.
     */
    protected ChessGameGUIApplication app;

    /**
     * Set's the app.
     * @param app The App.
     */
    public void setApp(ChessGameGUIApplication app) {
        this.app = app;
    }
}
