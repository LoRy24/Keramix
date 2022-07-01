package com.github.lory24.keramix.core.p47.checks.movement.nofall;

import com.github.lory24.keramix.core.p47.checks.AbstractCheck;
import com.github.lory24.keramix.core.p47.checks.ChecksIdentifiers;
import com.github.lory24.keramix.core.p47.checks.SubChecker;
import com.github.lory24.keramix.core.p47.utils.HackedPlayer;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.jetbrains.annotations.NotNull;

public class NoFallA extends AbstractCheck {

    // Used to obtain when to start flagging
    private int totalTimes = 0;

    /**
     * Set all the most important features of the check.
     */
    public NoFallA(SubChecker subChecker) {
        super(subChecker, ChecksIdentifiers.MOVEMENT_NOFALL_TYPE_A);
    }

    @Override
    public void check(@NotNull HackedPlayer player) {

    }
}
