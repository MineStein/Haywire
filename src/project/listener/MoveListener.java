package project.listener;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
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
public class MoveListener implements Listener {

    @EventHandler
    public void onMove(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();

        if (player.isOp()) {
            for (int i = 0; i < 10; i++) {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.playEffect(player.getLocation().add(Core.getRandom().nextInt(2), Core.getRandom().nextInt(2), Core.getRandom().nextInt(2)), Effect.COLOURED_DUST, 1);
                }
            }
        }

        if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE)) {
            if (Math.floor(player.getLocation().getY()) == 125 || Math.floor(player.getLocation().getY()) == 120 || Math.floor(player.getLocation().getY()) == 115) {
                player.setVelocity(player.getLocation().getDirection().multiply(3));
                player.setVelocity(new Vector(player.getVelocity().getX(), 1, player.getVelocity().getZ()));
                player.setFireTicks(60);
                player.setFallDistance(0F);

                player.playSound(player.getLocation(), Sound.FIREWORK_LARGE_BLAST, 1F, 1F);

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

                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    ((CraftPlayer) onlinePlayer).getHandle().playerConnection.sendPacket(packet);
                }
            }
        }
    }
}
