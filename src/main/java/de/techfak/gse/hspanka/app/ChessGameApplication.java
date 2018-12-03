package de.techfak.gse.hspanka.app;

import de.techfak.gse.hspanka.exceptions.ApplicationErrorException;

public interface ChessGameApplication {
    void run(String... args) throws ApplicationErrorException;
}
