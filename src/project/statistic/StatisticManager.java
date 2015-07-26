package project.statistic;

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
public class StatisticManager {

    private static boolean hasStats(Player player) {
        return Core.getPlugin().getConfig().getConfigurationSection("stats." + player.getUniqueId().toString()) != null;
    }

    public static void createStats(Player player) {
        if (hasStats(player)) return;

        final ConfigurationSection section = Core.getPlugin().getConfig().getConfigurationSection("stats").createSection(player.getUniqueId().toString());

        section.set("kills", 0);
        section.set("deaths", 0);
        section.set("pixels", 0);

        Core.getPlugin().saveConfig();
    }

    public static void resetPixels(Player player) {
        setPixels(player, 0);
    }

    public static void setPixels(Player player, int pixels) {
        Core.getPlugin().getConfig().getConfigurationSection("stats." + player.getUniqueId().toString()).set("pixels", pixels);
        Core.getPlugin().saveConfig();
    }

    public static void addPixels(Player player, int pixels) {
        int currentPixels = getPixels(player);
        
        if (pixels <= 0) return;
        
        int newPixels = currentPixels + pixels;

        setPixels(player, newPixels);
    }

    public static void subtractPixels(Player player, int pixels) {
        int currentPixels = getPixels(player);

        if (currentPixels < 0) {
            setPixels(player, 0);

            return;
        }

        int newPixels = currentPixels - pixels;

        setPixels(player, newPixels);
    }

    public static int getPixels(Player player) {
        return Core.getPlugin().getConfig().getConfigurationSection("stats." + player.getUniqueId().toString()).getInt("pixels");
    }

    public static void incrementKills(Player player) {
        Core.getPlugin().getConfig().getConfigurationSection("stats." + player.getUniqueId().toString()).set("kills", getKills(player) + 1);
        Core.getPlugin().saveConfig();
    }

    public static int getKills(Player player) {
        return Core.getPlugin().getConfig().getConfigurationSection("stats." + player.getUniqueId().toString()).getInt("kills");
    }

    public static void incrementDeaths(Player player) {
        Core.getPlugin().getConfig().getConfigurationSection("stats." + player.getUniqueId().toString()).set("deaths", getDeaths(player) + 1);
        Core.getPlugin().saveConfig();
    }

    public static int getDeaths(Player player) {
        return Core.getPlugin().getConfig().getConfigurationSection("stats." + player.getUniqueId().toString()).getInt("deaths");
    }
}