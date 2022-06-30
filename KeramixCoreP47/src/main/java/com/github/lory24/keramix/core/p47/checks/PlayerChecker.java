package com.github.lory24.keramix.core.p47.checks;

import com.github.lory24.keramix.core.p47.checks.movement.MovementChecker;
import com.github.lory24.keramix.core.p47.utils.HackedPlayer;
import lombok.Getter;

import java.util.Arrays;

public class PlayerChecker {

    /**
     * Hacked player reference
     */
    @Getter
    private final HackedPlayer hackedPlayer;

    // region checkers

    /**
     * Movement checker
     */
    @Getter
    private final MovementChecker movementChecker;

    // endregion

    /**
     * Inject the hackedPlayer instance and instance all the checkers
     */
    public PlayerChecker(HackedPlayer hackedPlayer) {
        this.hackedPlayer = hackedPlayer;

        // Instance the checkers
        this.movementChecker = new MovementChecker(PlayerChecker.this);
    }

    /**
     * Execute all che sub checkers for each cheat check.
     */
    public void doChecks() {
        Arrays.stream(this.getClass().getDeclaredFields()).filter(field -> field.getType().getSuperclass().equals(SubChecker.class)).forEach(field -> {
            try {
                ((SubChecker) field.get(this)).doAllChecks();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
