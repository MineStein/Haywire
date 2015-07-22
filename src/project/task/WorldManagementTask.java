package project.task;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

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
public class WorldManagementTask implements Runnable {

    @Override
    public void run() {
        Bukkit.getWorld("pvp_arena").getEntities().stream().filter(entity -> entity.getType() != EntityType.PLAYER && entity.getType() != EntityType.ARROW && entity.getType() != EntityType.ARMOR_STAND).forEach(org.bukkit.entity.Entity::remove);
        Bukkit.getWorld("pvp_arena").setStorm(false);

        for (Player player : Bukkit.getOnlinePlayers()) {
            for (PotionEffect effect : player.getActivePotionEffects()) {
                player.removePotionEffect(effect.getType());
            }
        }
    }
}
