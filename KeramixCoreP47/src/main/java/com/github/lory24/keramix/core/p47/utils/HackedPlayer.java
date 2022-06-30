package com.github.lory24.keramix.core.p47.utils;

import com.github.lory24.keramix.core.p47.KeramixAntiCheat;
import com.github.lory24.keramix.core.p47.checks.PlayerChecker;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class HackedPlayer {

    /**
     * Player's reference
     */
    @Getter
    private final Player player;

    /**
     * The primary loop task
     */
    @Getter
    private BukkitTask bukkitTask;

    /**
     * The Hacked player's data. This class will contain all the AutoUpdateValues of the player
     */
    @Getter
    private final HackedPlayerData hackedPlayerData;

    /**
     * The player checker. This is the main core of the anti cheat
     */
    @Getter
    private final PlayerChecker playerChecker;

    /**
     * The constructor for the HackedPlayer class. This will initialize some essentials values, and it will start the
     * observing loop for some of the player's data.
     * @param player The player's reference
     */
    public HackedPlayer(Player player) {
        this.player = player;
        this.hackedPlayerData = new HackedPlayerData(this);
        this.playerChecker = new PlayerChecker(this);
        this.startPrimaryLoop();
    }

    /**
     * Start the AntiCheat primary loop. It will first update the player's data, then it will do all the checks.
     */
    private void startPrimaryLoop() {
        // Start the async loop
        this.bukkitTask = Bukkit.getScheduler().runTaskTimerAsynchronously(KeramixAntiCheat.INSTANCE.getJavaPlugin(), () -> {
            // Update the data & do all the checks
            this.hackedPlayerData.updateAllData();
            this.playerChecker.doChecks();
        }, 0, 20L);
    }
}
