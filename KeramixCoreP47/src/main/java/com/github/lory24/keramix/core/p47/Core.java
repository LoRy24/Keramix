package com.github.lory24.keramix.core.p47;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@SuppressWarnings("unused")
public class Core {

    /**
     * When the core enables
     */
    public void enable(@NotNull JavaPlugin javaPlugin) {
        try {
            KeramixAntiCheat.INSTANCE.enable(javaPlugin);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * When the core disables
     */
    public void disable() {
        KeramixAntiCheat.INSTANCE.disable();
    }
}
