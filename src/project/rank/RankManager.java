package project.rank;

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
public class RankManager {

    public static int getPermission(Player player) {
        return getRank(player).getPermissionLevel();
    }

    public static void setRank(Player player, int rankId) {
        if (rankId < 0 || rankId > 9) rankId = 0;

        Core.getPlugin().getConfig().set("ranks." + player.getUniqueId().toString(), rankId);
        Core.getPlugin().saveConfig();

        player.setPlayerListName(" " + getRankPrefix(player) + " §7" + player.getName());
    }

    public static void setRank(Player player, Rank rank) {
        Core.getPlugin().getConfig().set("ranks." + player.getUniqueId(), rank.getIndex());
        Core.getPlugin().saveConfig();

        player.setPlayerListName(" " + getRankPrefix(player) + " §7" + player.getName());
    }

    public static Rank getRank(Player player) {
        int rankId = Core.getPlugin().getConfig().getInt("ranks." + player.getUniqueId().toString());

        for (Rank rank : Rank.values()) {
            if (rank.getIndex() == rankId) return rank;
        }

        return Rank.DEFAULT;
    }

    public static String getRankPrefix(Player player) {
        return "§" + getRank(player).getColor() + "§l" + getRank(player).toString().toUpperCase().replaceAll("_", " ");
    }
}
