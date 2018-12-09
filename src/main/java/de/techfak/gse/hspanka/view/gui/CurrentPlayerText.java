package de.techfak.gse.hspanka.view.gui;

import de.techfak.gse.hspanka.Player;
import javafx.scene.text.Text;

/**
 * Shows the current player.
 */
public class CurrentPlayerText extends Text {
    /**
     * Set's the text appropriately.
     * @param player The current player.
     */
    public void setPlayer(final Player player) {
        if (player == Player.WHITE) {
            setText("Current Player: White");
        } else {
            setText("Current Player: Black");
        }
    }
}
