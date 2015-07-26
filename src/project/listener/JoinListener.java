package project.listener;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import project.loadout.LoadoutManager;
import project.rank.RankManager;
import project.scoreboard.ScoreboardManager;
import project.statistic.StatisticManager;
import project.title.TitleUtility;

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
public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        StatisticManager.createStats(player);

        RankManager.createRankAccount(player);

        player.setPlayerListName(" " + RankManager.getRankPrefix(RankManager.getRanks(player).get(0)) + " §7" + player.getName());

        player.setBedSpawnLocation(new Location(Bukkit.getWorld("pvp_arena"), 258.5, 151, 362.5), true);
        player.teleport(new Location(Bukkit.getWorld("pvp_arena"), 258.5, 151, 362.5));

        player.setMaxHealth(40.0);
        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);

        ScoreboardManager.refreshScoreboard(player);

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            TitleUtility.sendTabHF(onlinePlayer, "§3§lHaywire PvP", "§eThere are §7" + Bukkit.getOnlinePlayers().size() + "§e/§7" + Bukkit.getServer().getMaxPlayers() + " §eplayers online.");
        }

        event.setJoinMessage("§a§l>> " + RankManager.getRankPrefix(player) + " §7" + player.getName() + " §ehas joined.");

        LoadoutManager.giveLoadout(player);

        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(
                EnumParticle.LAVA,	// particle type.
                true,							// true
                (float) player.getLocation().getX(),		// x coordinate
                (float) player.getLocation().getY(),		// y coordinate
                (float) player.getLocation().getZ(),		// z coordinate
                5,								// x offset
                5,								// y offset
                5,								// z offset
                10,								// speed
                10_000,							// number of particles
                null
        );

        ((CraftPlayer) event.getPlayer()).getHandle().playerConnection.sendPacket(packet);
    }
}
