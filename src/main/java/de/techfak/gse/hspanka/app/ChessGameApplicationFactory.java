package de.techfak.gse.hspanka.app;

/**
 * Creates the various application UIs.
 */
public class ChessGameApplicationFactory {
    /**
     * Creates a console application.
     *
     * @return The Console application instance implementing ChessGameApplication.
     */
    public ChessGameApplication makeConsoleApplication() {
        return new ChessGameConsoleApplication();
    }

    /**
     * Creates a GUI application.
     *
     * @return The GUI application instance implementing ChessGameApplication.
     */
    public ChessGameApplication makeGuiApplication() {
        return new ChessGameGUIApplication();
    }
}
