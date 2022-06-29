package com.github.lory24.keramix.plugin;

import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("unused")
public final class Entry extends JavaPlugin {

    /**
     * The core entry object. This is the main class of the core
     */
    private Class<?> coreEntry;

    @Override
    public void onEnable() {
        // Get the server version
        String minecraftVersion = MinecraftServer.getServer().getVersion();

        // Run the core based on the server's version
        try {
            // Start the core
            this.coreEntry = Class.forName("com.github.lory24.keramix.core.p" + this.protocolVersion(minecraftVersion) + ".Core");
            this.coreEntry.getDeclaredMethod("enable", JavaPlugin.class).invoke(this.coreEntry.getDeclaredConstructor().newInstance(), this);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException |
                 InstantiationException e) {
            // Notify and disable the plugin
            this.getLogger().warning("Minecraft version " + minecraftVersion + " isn't supported!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        try {
            this.coreEntry.getDeclaredMethod("disable").invoke(this.coreEntry.getDeclaredConstructor().newInstance()); // Disable the core
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException |
                 InstantiationException ignored) {
            // Function not found.
        }
    }

    /**
     * This function will parse a Minecraft version into his protocol version.
     * @param versionString The Minecraft's server version
     * @return The protocol id.
     */
    @Contract(pure = true)
    private int protocolVersion(@NotNull String versionString) {
        return versionString.startsWith("1.8") ? 47 : 0;
    }
}
