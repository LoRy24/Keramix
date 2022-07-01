package com.github.lory24.keramix.core.p47.checks;

import com.github.lory24.keramix.core.p47.utils.KeramixNotificationsUtils;
import lombok.Getter;

import java.util.HashMap;

public abstract class SubChecker {

    /**
     * This HashMap will contain all the registered checks with their failed times.
     */
    protected final HashMap<Check, Integer> registeredChecks = new HashMap<>();

    /**
     * Reference to the main player's checker
     */
    @Getter
    private final PlayerChecker playerChecker;

    /**
     * Inject the playerChecker
     */
    protected SubChecker(PlayerChecker playerChecker) {
        this.playerChecker = playerChecker;
    }

    /**
     * Every checker should have this method in order to run all the checks
     */
    public void doAllChecks() {
        this.registeredChecks.forEach((check, times) -> ((AbstractCheck) check).check(this.playerChecker.getHackedPlayer()));
    }

    /**
     * Register a new check
     */
    public void registerCheck(Check check) {
        this.registeredChecks.put(check, 1); // Register the check with start value '0'
    }

    /**
     * Process a failed check
     */
    public void processFailedFlag(Check check) {
        // Just increase the flag times and send a notification
        int times = this.registeredChecks.get(check);
        KeramixNotificationsUtils.sendFlagNotification((AbstractCheck) check, times);
        this.registeredChecks.replace(check, times +1);
    }
}
