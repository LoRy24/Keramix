package com.github.lory24.keramix.core.p47.checks.movement;

import com.github.lory24.keramix.core.p47.ConfigValues;
import com.github.lory24.keramix.core.p47.checks.PlayerChecker;
import com.github.lory24.keramix.core.p47.checks.SubChecker;
import com.github.lory24.keramix.core.p47.checks.movement.fly.FlyA;
import com.github.lory24.keramix.core.p47.checks.movement.nofall.NoFallA;

public class MovementChecker extends SubChecker {

    /**
     * Inject the player's main checker and register all the checkers
     */
    public MovementChecker(PlayerChecker playerChecker) {
        super(playerChecker);

        // Register the checkers
        if ((boolean) ConfigValues.flags_flyA_enabled.getFromConfig()) this.registerCheck(new FlyA(this));
        this.registerCheck(new NoFallA(this));
    }
}
