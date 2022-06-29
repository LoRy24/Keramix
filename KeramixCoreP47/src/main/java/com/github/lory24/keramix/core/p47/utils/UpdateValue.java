package com.github.lory24.keramix.core.p47.utils;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface UpdateValue<T> {

    /**
     * @return The code that will be used to obtain the value from the player
     */
    T update(@NotNull Player player);
}
