package project.command;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import project.Core;
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
public class JoinCommand extends CommandBase {

    @Override
    public void execute(CommandSender sender, Command command, String[] arguments) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;

            if (!ArenaStorage.getInArena().contains(player.getUniqueId().toString())) {
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

                player.sendMessage("§a§l>> §eTeleporting...");
                player.teleport(new Location(Bukkit.getWorld("arena"), 322.5, 130, 362.5));

                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    ((CraftPlayer) onlinePlayer).getHandle().playerConnection.sendPacket(packet);
                }

                ArenaStorage.getInArena().add(player.getUniqueId().toString());
                ArenaStorage.getSpawnKill().add(player.getUniqueId().toString());

                Bukkit.getScheduler().runTaskLater(Core.getPlugin(), () -> ArenaStorage.getSpawnKill().remove(player.getUniqueId().toString()), 60);
            } else {
                sender.sendMessage("§4§lX §cYou are already in the arena.");
            }
        } else {
            sender.sendMessage("§4§lX §cOnly players can execute that command.");
        }
    }
}
