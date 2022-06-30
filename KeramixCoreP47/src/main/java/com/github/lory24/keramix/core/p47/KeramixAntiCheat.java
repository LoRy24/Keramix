package com.github.lory24.keramix.core.p47;

import com.github.lory24.keramix.core.p47.utils.HackedPlayersList;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("SpellCheckingInspection")
public enum KeramixAntiCheat {

    /**
     * Instance used to access to Keramix
     */
    INSTANCE;

    /**
     * Java plugin reference
     */
    @Getter
    private JavaPlugin javaPlugin;

    // region core features

    /**
     * The list of all the HackedPlayers
     */
    @Getter
    private HackedPlayersList hackedPlayersList;

    // endregion

    /**
     * Enable the Keramix AntiCheat core.
     */
    public void enable(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin; // Inject the JavaPlugin
        this.javaPlugin.saveDefaultConfig();

        // Core features
        this.hackedPlayersList = new HackedPlayersList(this.javaPlugin);
    }

    /**
     * Disable the core
     */
    public void disable() {
        this.hackedPlayersList.getHackedPlayersMap().clear();
    }
}
