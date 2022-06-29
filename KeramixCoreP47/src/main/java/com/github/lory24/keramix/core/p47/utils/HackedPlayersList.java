package com.github.lory24.keramix.core.p47.utils;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class HackedPlayersList implements Listener {

    /**
     * The hashmap with each initialized hackedplayer
     */
    @Getter
    private final Map<Player, HackedPlayer> hackedPlayersMap = new HashMap<>();

    /**
     * This constructor will start all the initializations loops.
     */
    public HackedPlayersList(JavaPlugin javaPlugin) {
        // Start the two events listeners
        Bukkit.getPluginManager().registerEvents(this, javaPlugin);
        this.registerPlayers();
    }

    /**
     * Register all the online players and start
     */
    public void registerPlayers() {
        // Add all the online players in the map
        Bukkit.getOnlinePlayers().forEach(this::registerPlayer);
    }

    /**
     * Register a player into the hackedPlayersMap
     */
    public void registerPlayer(@NotNull Player player) {
        hackedPlayersMap.put(player, new HackedPlayer(player));
    }

    /**
     * Unregister a player from the map
     */
    public void unregisterPlayer(@NotNull Player player) {
        this.hackedPlayersMap.get(player).getBukkitTask().cancel(); // Stop the update loop task
        this.hackedPlayersMap.remove(player);
    }

    // region events

    /**
     * OnPlayerJoinEvent listener. Adds the players into the hackedPlayersMap. Lowest priority
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerJoin(@NotNull PlayerJoinEvent event) {
        registerPlayer(event.getPlayer());
    }

    /**
     * OnPlayerJoinEvent listener. Removes a player from the list
     */
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerQuit(@NotNull PlayerQuitEvent event) {
        unregisterPlayer(event.getPlayer());
    }

    // endregion
}
