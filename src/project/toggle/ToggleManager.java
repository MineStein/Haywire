package project.toggle;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import project.Core;

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
public class ToggleManager {

    public enum Toggle {

        BLOOD,

        SWEARING,

        PRIVATE_MESSAGES,

        MATCH_REQUESTS;

        public String getName() {
            return toString().toLowerCase().replaceAll("_", "-");
        }
    }

    private static boolean hasTogglesAccount(Player player) {
        return Core.getPlugin().getConfig().getConfigurationSection("toggles." + player.getUniqueId().toString()) != null;
    }

    public static void createTogglesAccount(Player player) {
        if (hasTogglesAccount(player)) return;

        final ConfigurationSection section = Core.getPlugin().getConfig().createSection("toggles." + player.getUniqueId().toString());

        for (Toggle toggle : Toggle.values()) {
            section.set(toggle.getName(), false);
        }

        Core.getPlugin().saveConfig();
    }

    public static void toggle(Player player, Toggle toggle) {
        Core.getPlugin().getConfig().getConfigurationSection("toggles." + player.getUniqueId().toString()).set(toggle.getName(), !Core.getPlugin().getConfig().getBoolean("toggles." + player.getUniqueId().toString() + "." + toggle.getName()));
        Core.getPlugin().saveConfig();
    }

    public static boolean getToggle(Player player, Toggle toggle) {
        return Core.getPlugin().getConfig().getBoolean("toggles." + player.getUniqueId().toString() + "." + toggle.getName());
    }
}
