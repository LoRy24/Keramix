package com.github.lory24.keramix.core.p47.checks.movement.fly;

import com.github.lory24.keramix.core.p47.checks.AbstractCheck;
import com.github.lory24.keramix.core.p47.checks.ChecksIdentifiers;
import com.github.lory24.keramix.core.p47.checks.SubChecker;
import com.github.lory24.keramix.core.p47.utils.HackedPlayer;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.jetbrains.annotations.NotNull;

public class FlyA extends AbstractCheck {

    // Used to obtain when to start flagging
    private int totalTimes = 0;

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
    public void check(@NotNull final HackedPlayer player) {
        // Locations
        final Location from = player.getHackedPlayerData().getPlayerLocation().getFromValue();
        final Location to = player.getHackedPlayerData().getPlayerLocation().getToValue();

        // Fix on player join error
        if (to == null || from == null) return;

        // Get the entity player
        final EntityPlayer entityPlayer = ((CraftPlayer) player.getPlayer()).getHandle();

        // asus
        final boolean clientOnGround = entityPlayer.onGround;
        final boolean serverOnGround = to.getY() % 0.015625 == 0.0 && from.getY() % 0.015625 == 0.0;

        if (clientOnGround != serverOnGround) {
            if (++this.totalTimes > 4)
                this.fail();
        } else totalTimes = 0;
    }
}
