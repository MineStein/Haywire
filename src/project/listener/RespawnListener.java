package project.listener;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import project.storage.ArenaStorage;

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
public class RespawnListener implements Listener {

    @EventHandler
    public void onRespawn(final PlayerRespawnEvent event) {
        final Player player = event.getPlayer();

        if (ArenaStorage.getInArena().contains(player.getUniqueId().toString())) {
            ArenaStorage.getInArena().remove(player.getUniqueId().toString());
        }

        player.setMaxHealth(40.0);
        player.setHealth(40.0);
        player.setFoodLevel(20);

        player.setBedSpawnLocation(new Location(Bukkit.getWorld("pvp_arena"), 258.5, 151, 362.5), true);
        player.teleport(new Location(Bukkit.getWorld("pvp_arena"), 258.5, 151, 362.5));

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
