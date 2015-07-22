package project.command;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import project.inventory.LoadoutInventory;

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
public class LoadoutCommand extends CommandBase {

    @Override
    public void execute(CommandSender sender, Command command, String[] arguments) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;

            player.playSound(player.getLocation(), Sound.CLICK, 1F, 1F);
            player.openInventory(LoadoutInventory.getInventory(player, LoadoutInventory.Page.LOADOUTS));
        } else {
            sender.sendMessage("§4§lX §cOnly players can execute this command.");
        }
    }
}
