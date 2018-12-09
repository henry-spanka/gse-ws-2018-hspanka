package de.techfak.gse.hspanka.view.gui;

import de.techfak.gse.hspanka.Player;
import javafx.scene.text.Text;

public class CurrentPlayerText extends Text {
    public void setPlayer(Player player) {
        if (player == Player.WHITE) {
            setText("Current Player: White");
        } else {
            setText("Current Player: Black");
        }
    }
}
