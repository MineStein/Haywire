package project.loadout;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import project.item.ItemBuilder;

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
public class LoadoutManager {

    public static void giveLoadout(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        player.getInventory().setHelmet(new ItemBuilder().type(Material.DIAMOND_HELMET).build());
        player.getInventory().setChestplate(new ItemBuilder().type(Material.DIAMOND_CHESTPLATE).build());
        player.getInventory().setLeggings(new ItemBuilder().type(Material.DIAMOND_LEGGINGS).build());
        player.getInventory().setBoots(new ItemBuilder().type(Material.DIAMOND_BOOTS).build());
        player.getInventory().addItem(new ItemBuilder().type(Material.DIAMOND_SWORD).build());
    }

    public static Loadout getLoadout(Player player) {
        return new Loadout(player);
    }
}
