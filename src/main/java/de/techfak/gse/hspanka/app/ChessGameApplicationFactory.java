package de.techfak.gse.hspanka.app;

public class ChessGameApplicationFactory {
    public ChessGameApplication makeConsoleApplication() {
        ChessGameApplication app = new ChessGameConsoleApplication();

        return app;
    }

    public ChessGameApplication makeGuiApplication() {
        ChessGameApplication app = new ChessGameGUIApplication();

        return app;
    }
}
