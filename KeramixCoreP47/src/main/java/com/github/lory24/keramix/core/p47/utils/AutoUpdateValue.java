package com.github.lory24.keramix.core.p47.utils;

import lombok.Getter;
import org.bukkit.entity.Player;

public class AutoUpdateValue<T> {

    /**
     * The actual value
     */
    @Getter
    private T toValue;

    /**
     * The last value. Null if first tick
     */
    @Getter
    private T fromValue;

    /**
     * The functional interface used
     */
    private final UpdateValue<T> updateValueFunctionalInterface;

    /**
     * The constructor will istance the updateValue functional interface, used to update the value of this auto-update
     * class.
     */
    public AutoUpdateValue(UpdateValue<T> updateValueFunctionalInterface) {
        this.updateValueFunctionalInterface = updateValueFunctionalInterface;
    }

    /**
     * Update the values.
     */
    public void update(Player player) {
        fromValue = toValue;
        toValue = updateValueFunctionalInterface.update(player);
    }
}
