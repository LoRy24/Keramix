package com.github.lory24.keramix.core.p47.utils;

import lombok.Getter;
import org.bukkit.entity.Player;

public class HackedPlayer {

    /**
     * Player's reference
     */
    @Getter
    private final Player player;

    /**
     * The Hacked player's data. This class will contain all the AutoUpdateValues of the player
     */
    @Getter
    private final HackedPlayerData hackedPlayerData;

    /**
     * The constructor for the HackedPlayer class. This will initialize some essentials values, and it will start the
     * observing loop for some of the player's data.
     * @param player The player's reference
     */
    public HackedPlayer(Player player) {
        this.player = player;
        this.hackedPlayerData = new HackedPlayerData(this);
    }
}
