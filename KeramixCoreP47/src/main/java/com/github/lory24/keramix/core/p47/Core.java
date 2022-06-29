package com.github.lory24.keramix.core.p47;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class Core {

    /**
     * When the core enables
     */
    public void enable(@NotNull JavaPlugin javaPlugin) {
        KeramixAntiCheat.INSTANCE.enable(javaPlugin);
    }

    /**
     * When the core disables
     */
    public void disable() {
        KeramixAntiCheat.INSTANCE.disable();
    }
}
