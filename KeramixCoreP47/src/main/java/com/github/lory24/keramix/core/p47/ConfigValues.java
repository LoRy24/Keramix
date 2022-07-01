package com.github.lory24.keramix.core.p47;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;

public enum ConfigValues {

    messages_prefix("settings.messages.prefix"),
    messages_flagMessage("settings.messages.flagMessage")
    ;

    /**
     * The path field.
     */
    @Getter
    private final String path;

    /**
     * Inject the path value
     */
    ConfigValues(String path) {
        this.path = path;
    }

    /**
     * Obtain an object from the config
     */
    public Object getFromConfig() {
        return KeramixAntiCheat.INSTANCE.getCustomConfig().get(this.path);
    }

    /**
     * Obtain a string from the config with colors and custom parameters.
     */
    @NotNull
    public String getParsedStringFromConfig() {
        String resultString = (String) this.getFromConfig();
        for (ConfigValues value: ConfigValues.values()) resultString = resultString.replace("${" + value.name() + "}", (String) value.getFromConfig());
        return ChatColor.translateAlternateColorCodes('&', resultString);
    }
}
