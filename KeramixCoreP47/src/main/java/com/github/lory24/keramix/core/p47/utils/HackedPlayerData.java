package com.github.lory24.keramix.core.p47.utils;

import com.github.lory24.keramix.core.p47.KeramixAntiCheat;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitTask;

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
     * The update loop task
     */
    private BukkitTask bukkitTask;

    /**
     * Constructor that will instance the hackedPlayer and all the auto update values
     * @param hackedPlayer The player's reference
     */
    public HackedPlayerData(HackedPlayer hackedPlayer) {
        this.hackedPlayer = hackedPlayer;

        // Give a value to all the AutoUpdateValues
        this.playerLocation = new AutoUpdateValue<>(Entity::getLocation); // player's location

        // Start updating all the values
        this.launchUpdateLoop();
    }

    /**
     * This function will start to update every value
     */
    private void launchUpdateLoop() {
        // Start the timer task
        this.bukkitTask = Bukkit.getScheduler().runTaskTimerAsynchronously(KeramixAntiCheat.INSTANCE.getJavaPlugin(), () ->
                Arrays.stream(this.getClass().getDeclaredFields()).filter(field -> field.getType().equals(AutoUpdateValue.class)).forEach(field -> {
            try {
                ((AutoUpdateValue<?>) field.get(this)).update(this.hackedPlayer.getPlayer());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }), 0L, 1L);
    }

    /**
     * Stop the task taht is updating all the values
     */
    public void stopUpdateLoop() {
        this.bukkitTask.cancel();
    }
}
