package project.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

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
public class MuteCommand extends CommandBase {

    @Override
    public void execute(CommandSender sender, Command command, String[] arguments) {
        String[] usage = new String[] { "§6§l******** §e/mute §6§l********", "§7/mute add <player>", "§7/mute remove <player>", "§7/mute check <player>" };

        if (arguments.length == 0) {
            sender.sendMessage(usage);
        } else {

        }
    }
}
