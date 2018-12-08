package de.techfak.gse.hspanka.controller.gui;

import de.techfak.gse.hspanka.app.ChessGameGUIApplication;

public class AbstractGuiController {
    protected ChessGameGUIApplication app;

    public void setApp(ChessGameGUIApplication app) {
        this.app = app;
    }
}
