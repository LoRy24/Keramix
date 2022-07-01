package com.github.lory24.keramix.core.p47;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;

public enum ConfigValues {

    teleportCommand("settings.teleportCommand"),

    messages_prefix("settings.messages.prefix"),
    messages_flagMessage("settings.messages.flagMessage"),

    flags_flyA_enabled("settings.flags.flyA.enabled"),
    flags_flyA_ticks("settings.flags.flyA.ticks"),
    flags_flyA_hover("settings.flags.flyA.hover"),
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
        for (ConfigValues value: ConfigValues.values()) resultString = resultString.replace("${" + value.name() + "}", String.valueOf(value.getFromConfig()));
        return ChatColor.translateAlternateColorCodes('&', resultString);
    }
}
