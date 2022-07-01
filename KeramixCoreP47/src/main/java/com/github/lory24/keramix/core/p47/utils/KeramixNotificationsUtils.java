package com.github.lory24.keramix.core.p47.utils;

import com.github.lory24.keramix.core.p47.ConfigValues;
import com.github.lory24.keramix.core.p47.checks.AbstractCheck;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@SuppressWarnings("SpellCheckingInspection")
public class KeramixNotificationsUtils {

    /**
     * Send the notification to every player that have the permission to read the notifications
     */
    public static void sendFlagNotification(AbstractCheck check, int times) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (player.hasPermission("keramix.flag.see")) {
                Player hacker = check.getSubChecker().getPlayerChecker().getHackedPlayer().getPlayer();
                String flagMessage = ConfigValues.messages_flagMessage.getParsedStringFromConfig().replace("${player}", hacker.getName()).replace("${flag-name}", check.getIdentifier().getIdentifierText())
                        .replace("${flag-times}", String.valueOf(times));
                TextComponent message = new TextComponent(flagMessage);
                message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, ((String) ConfigValues.teleportCommand.getFromConfig()).replace("${subject}",
                        player.getName()).replace("${target}", hacker.getName())));
                message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponent[]{new TextComponent(ConfigValues.flags_flyA_hover
                        .getParsedStringFromConfig())}));
                player.spigot().sendMessage(message);
            }
        });
    }
}
