package de.techfak.gse.hspanka.app;

/**
 * Creates the various application UIs.
 */
public class ChessGameApplicationFactory {
    /**
     * Creates a console application.
     * @return The Console application instance implementing ChessGameApplication.
     */
    public ChessGameApplication makeConsoleApplication() {
        final ChessGameApplication app = new ChessGameConsoleApplication();

        return app;
    }

    /**
     * Creates a GUI application.
     * @return The GUI application instance implementing ChessGameApplication.
     */
    public ChessGameApplication makeGuiApplication() {
        final ChessGameApplication app = new ChessGameGUIApplication();

        return app;
    }
}
