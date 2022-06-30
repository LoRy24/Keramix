package com.github.lory24.keramix.core.p47.checks.movement;

import com.github.lory24.keramix.core.p47.checks.PlayerChecker;
import com.github.lory24.keramix.core.p47.checks.SubChecker;
import com.github.lory24.keramix.core.p47.checks.movement.fly.FlyA;
import lombok.Getter;

public class MovementChecker extends SubChecker {

    /**
     * Reference to the main player's checker
     */
    @Getter
    private final PlayerChecker playerChecker;

    /**
     * Inject the player's main checker and register all the checkers
     */
    public MovementChecker(PlayerChecker playerChecker) {
        this.playerChecker = playerChecker;

        // Register the checkers
        this.registerCheck(new FlyA(MovementChecker.this));
    }
}
