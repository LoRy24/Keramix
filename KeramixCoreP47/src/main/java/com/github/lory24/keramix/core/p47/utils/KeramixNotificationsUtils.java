package com.github.lory24.keramix.core.p47.utils;

import com.github.lory24.keramix.core.p47.ConfigValues;
import com.github.lory24.keramix.core.p47.checks.AbstractCheck;
import org.bukkit.Bukkit;

@SuppressWarnings("SpellCheckingInspection")
public class KeramixNotificationsUtils {

    /**
     * Send the notification to every player that have the permission to read the notifications
     */
    public static void sendFlagNotification(AbstractCheck check, int times) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (player.hasPermission("keramix.flag.see")) {
                String message = ConfigValues.messages_flagMessage.getParsedStringFromConfig().replace("${player}", player.getName()).replace("${flag-name}",
                        check.getIdentifier().getIdentifierText()).replace("${flag-times}", String.valueOf(times));
                player.sendMessage(message);
            }
        });
    }
}
