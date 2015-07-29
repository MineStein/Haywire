package project.listener;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
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
public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(final EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player) event.getEntity();

            if (ArenaStorage.getSpawnKill().contains(player.getUniqueId().toString())) {
                event.setCancelled(true);
            }

            for (int i = 0; i < event.getDamage(); i++) {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.playEffect(player.getLocation().add(Core.getRandom().nextInt(2), Core.getRandom().nextInt(2), Core.getRandom().nextInt(2)), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
                }
            }
        }
    }
}
