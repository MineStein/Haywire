package project.listener;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

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
public class SoundUtility {

    public static void playSound(Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, 1F, 1F);
    }
}
