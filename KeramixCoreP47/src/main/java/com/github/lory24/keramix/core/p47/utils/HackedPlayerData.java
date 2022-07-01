package com.github.lory24.keramix.core.p47.utils;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;

import java.util.Arrays;
import java.util.List;

public class HackedPlayerData {

    /**
     * The hacked player reference
     */
    @Getter
    private final HackedPlayer hackedPlayer;

    // region values

    @Getter
    private final AutoUpdateValue<Location> playerLocation;

    // Touching values

    @Getter private final AutoUpdateValue<Boolean> playerTouchingClimbable;
    @Getter private final AutoUpdateValue<Boolean> playerInLiquid;

    // endregion

    /**
     * Constructor that will instance the hackedPlayer and all the auto update values
     * @param hackedPlayer The player's reference
     */
    public HackedPlayerData(HackedPlayer hackedPlayer) {
        this.hackedPlayer = hackedPlayer;

        // Give a value to all the AutoUpdateValues
        this.playerLocation = new AutoUpdateValue<>(Entity::getLocation); // player's location
        this.playerTouchingClimbable = new AutoUpdateValue<>(player -> List.of(Material.LADDER, Material.VINE).contains(player.getLocation().getBlock().getType())); // Get if the player is touching a climbable
        this.playerInLiquid = new AutoUpdateValue<>(player -> List.of(Material.WATER, Material.LAVA).contains(player.getLocation().getBlock().getType())); // Get if the player is in liquid
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
