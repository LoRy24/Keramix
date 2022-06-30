package com.github.lory24.keramix.core.p47.checks.movement.fly;

import com.github.lory24.keramix.core.p47.checks.AbstractCheck;
import com.github.lory24.keramix.core.p47.checks.ChecksIdentifiers;
import com.github.lory24.keramix.core.p47.checks.SubChecker;

public class FlyA extends AbstractCheck {

    /**
     * Set all the most important features of the check.
     *
     * @param subChecker The sub checker that manages this check
     */
    public FlyA(SubChecker subChecker) {
        super(subChecker, ChecksIdentifiers.MOVEMENT_FLY_TYPE_A);
    }

    /**
     * Process the check
     */
    @Override
    public void check() {
        this.fail(); // Fail the check just for testing
    }
}
