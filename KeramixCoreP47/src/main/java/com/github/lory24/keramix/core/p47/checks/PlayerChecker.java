package com.github.lory24.keramix.core.p47.checks;

import com.github.lory24.keramix.core.p47.utils.HackedPlayer;
import lombok.Getter;

public class PlayerChecker {

    /**
     * Hacked player reference
     */
    @Getter
    private final HackedPlayer hackedPlayer;

    // region checkers

    // endregion

    /**
     * Inject the hackedPlayer instance and start the checker loop
     */
    public PlayerChecker(HackedPlayer hackedPlayer) {
        this.hackedPlayer = hackedPlayer;
    }

    /**
     * Execute all che sub checkers for each cheat check.
     */
    public void doChecks() {

    }
}
