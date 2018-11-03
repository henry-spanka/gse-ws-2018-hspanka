package de.techfak.gse.hspanka;

/**
 * The board model
 */
public class Board {
    /**
     * The default configuration if not manually set
     */
    private String configuration = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w";

    /**
     * @return Current board configuration
     */
    public String getConfiguration() {
        return configuration;
    }
}
