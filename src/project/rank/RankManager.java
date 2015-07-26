package project.rank;

import org.bukkit.entity.Player;
import project.Core;

import java.util.ArrayList;
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
public class RankManager {

    private static String translateRankToPrefix(Rank rank) {
        return rank.toString().replaceAll("_", " ");
    }

    private static boolean hasRank(Player player) {
        return Core.getPlugin().getConfig().getIntegerList("ranks." + player.getUniqueId().toString()) == null;
    }

    public static boolean hasRank(Player player, Rank rank) {
        return getRanks(player).contains(rank);
    }

    public static String getRankPrefix(Rank rank) {
        return "§" + rank.getColor() + "§l" + rank.toString().replaceAll("_", " ");
    }

    public static Rank getRankById(int id) {
        if (id > Rank.RANKS_BOUND || id < 0) return Rank.DEFAULT;

        for (Rank rank : Rank.values()) {
            if (rank.getIndex() == id) return rank;
        }

        return Rank.DEFAULT;
    }

    public static void createRankAccount(Player player) {
        if (hasRank(player)) return;

        addRank(player, Rank.DEFAULT);
    }

    public static int getPermission(Rank rank) {
        return rank.getPermissionLevel();
    }

    public static int getPermission(Player player) {
        List<Rank> ranks = getRanks(player);
        Rank highest = Rank.DEFAULT;
        
        for (Rank rank : ranks) {
            if (rank.getPermissionLevel() > highest.getPermissionLevel()) highest = rank;
        }

        return highest.getPermissionLevel();
    }

    public static List<Integer> getRanksAsIntegers(Player player) {
        return Core.getPlugin().getConfig().getIntegerList("ranks." + player.getUniqueId().toString());
    }

    public static List<Rank> getRanks(Player player) {
        List<Rank> ranks = new ArrayList<>();
        List<Integer> filter = new ArrayList<>();

        for (Integer integer : Core.getPlugin().getConfig().getIntegerList("ranks." + player.getUniqueId().toString())) {
            if (filter.contains(integer)) continue;

            filter.add(integer);
            ranks.add(getRankById(integer));
        }

        return ranks;
    }

    public static String getRankPrefix(Player player) {
        List<Rank> ranks = getRanks(player);
        String prefix = "";

        for (Rank rank : ranks) {
            prefix += "§" + rank.getColor() + "§l" + translateRankToPrefix(rank) + " ";
        }

        return prefix.trim();
    }

    public static void addRank(Player player, Rank rank) {
        List<Integer> newList = new ArrayList<>(getRanksAsIntegers(player));

        if (!newList.contains(rank.getIndex())) newList.add(rank.getIndex());

        Core.getPlugin().getConfig().set("ranks." + player.getUniqueId().toString(), newList);
        Core.getPlugin().saveConfig();

        player.setPlayerListName(" " + RankManager.getRankPrefix(RankManager.getRanks(player).get(0)) + " §7" + player.getName());
    }

    public static void removeRank(Player player, Rank rank) {
        List<Integer> newList = new ArrayList<>(getRanksAsIntegers(player));

        if (newList.contains(rank.getIndex())) {
            newList.remove(new Integer(rank.getIndex()));
        }

        Core.getPlugin().getConfig().set("ranks." + player.getUniqueId().toString(), newList);
        Core.getPlugin().saveConfig();

        if (getRanks(player).size() == 0) addRank(player, Rank.DEFAULT);

        player.setPlayerListName(" " + RankManager.getRankPrefix(RankManager.getRanks(player).get(0)) + " §7" + player.getName());
    }

    public static void resetRank(Player player) {
        for (Rank rank : getRanks(player)) {
            removeRank(player, rank);
        }

        addRank(player, Rank.DEFAULT);

        player.setPlayerListName(" " + RankManager.getRankPrefix(RankManager.getRanks(player).get(0)) + " §7" + player.getName());
    }
}
