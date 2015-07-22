package project.loadout;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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
public class Loadout {

    String user;
    ItemStack[] armor;
    ItemStack primaryWeapon;
    ItemStack secondaryWeapon;
    ItemStack[] extras;
    LoadoutPower loadoutPower;

    public Loadout(Player player) {
        this.user = player.getUniqueId().toString();
    }

    public String getUser() {
        return user;
    }

    public ItemStack[] getArmor() {
        return armor;
    }

    public ItemStack getPrimaryWeapon() {
        return primaryWeapon;
    }

    public ItemStack getSecondaryWeapon() {
        return secondaryWeapon;
    }

    public ItemStack[] getExtras() {
        return extras;
    }

    public LoadoutPower getLoadoutPower() {
        return loadoutPower;
    }
}
