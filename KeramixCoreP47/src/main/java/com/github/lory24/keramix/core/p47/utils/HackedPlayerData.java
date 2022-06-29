package com.github.lory24.keramix.core.p47.utils;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

import java.util.Arrays;

public class HackedPlayerData {

    /**
     * The hacked player reference
     */
    @Getter
    private final HackedPlayer hackedPlayer;

    // region values

    @Getter
    private final AutoUpdateValue<Location> playerLocation;

    // endregion

    /**
     * Constructor that will instance the hackedPlayer and all the auto update values
     * @param hackedPlayer The player's reference
     */
    public HackedPlayerData(HackedPlayer hackedPlayer) {
        this.hackedPlayer = hackedPlayer;

        // Give a value to all the AutoUpdateValues
        this.playerLocation = new AutoUpdateValue<>(Entity::getLocation); // player's location
    }

    /**
     * This function will update all the data
     */
    public void updateAllData() {
        Arrays.stream(this.getClass().getDeclaredFields()).filter(field -> field.getType().equals(AutoUpdateValue.class)).forEach(field -> {
            try {
                ((AutoUpdateValue<?>) field.get(this)).update(this.hackedPlayer.getPlayer());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
