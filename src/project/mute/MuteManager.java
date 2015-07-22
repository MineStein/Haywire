package project.mute;

import org.bukkit.entity.Player;
import project.Core;

import java.util.List;

/**
 * ****************************************************************************************
 * Copyright Tyler Grissom © 2013-2015
 * <p>
 * Any code contained within this document, and any associated API's with similar branding
 * are the sole property of Tyler Grissom. Distribution, reproduction, taking snippets, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 * <p>
 * Thanks, and have a nice day.
 * ****************************************************************************************
 */
public class MuteManager {

    public static List<String> getMuted() {
        return Core.getPlugin().getConfig().getStringList("mutes");
    }

    public static boolean isMuted(Player player) {
        return getMuted().contains(player.getUniqueId().toString());
    }

    public static void mute(Player player) {
        if (!isMuted(player)) {
            List<String> duplicate = getMuted();

            duplicate.add(player.getUniqueId().toString());

            Core.getPlugin().getConfig().set("mutes", duplicate);
            Core.getPlugin().saveConfig();
        }
    }

    public static void unmute(Player player) {
        if (isMuted(player)) {
            List<String> duplicate = getMuted();

            duplicate.remove(player.getUniqueId().toString());

            Core.getPlugin().getConfig().set("mutes", duplicate);
            Core.getPlugin().saveConfig();
        }
    }
}
