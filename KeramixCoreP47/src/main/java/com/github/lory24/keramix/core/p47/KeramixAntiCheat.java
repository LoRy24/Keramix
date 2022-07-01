package com.github.lory24.keramix.core.p47;

import com.github.lory24.keramix.core.p47.utils.HackedPlayersList;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

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

    /**
     * The custom protocol config
     */
    @Getter
    private FileConfiguration customConfig;

    // endregion

    /**
     * Enable the Keramix AntiCheat core.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void enable(JavaPlugin javaPlugin) throws IOException {
        this.javaPlugin = javaPlugin; // Inject the JavaPlugin

        // Load the custom config
        File dataFolder = this.javaPlugin.getDataFolder(), customConfigFile = new File(dataFolder, "config_1.8.yml");
        if (!dataFolder.exists()) dataFolder.mkdir();
        if (!customConfigFile.exists()) {
            customConfigFile.createNewFile();
            Files.copy(this.javaPlugin.getResource("config_1.8.yml"), Path.of(customConfigFile.getAbsolutePath()), new StandardCopyOption[]{StandardCopyOption.REPLACE_EXISTING});
        }
        this.customConfig = YamlConfiguration.loadConfiguration(customConfigFile);

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
