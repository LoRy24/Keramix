package com.github.lory24.keramix.core.p47.checks;

import com.github.lory24.keramix.core.p47.utils.HackedPlayer;
import lombok.Getter;
import org.bukkit.entity.Player;

public abstract class AbstractCheck implements Check {

    // All the important check values

    /**
     * Reference to the subChecker
     */
    @Getter
    private final SubChecker subChecker;

    /*
     * Internal utils values
     */

    // Identifier
    @Getter
    private final ChecksIdentifiers identifier;

    /**
     * Set all the most important features of the check.
     */
    public AbstractCheck(SubChecker subChecker, ChecksIdentifiers identifier) {
        this.subChecker = subChecker;
        this.identifier = identifier;
    }

    /**
     * Process the check's actions
     */
    public abstract void check(final HackedPlayer player);

    /**
     * Implemented method used to fail a check
     */
    @Override
    public void fail() {
        this.subChecker.processFailedFlag(this);
    }
}
