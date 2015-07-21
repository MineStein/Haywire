package project.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
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
public class GamemodeCommand extends CommandBase {

    @Override
    public void execute(CommandSender sender, Command command, String[] arguments) {
        String usage = "§c§l>> §eUsage: §7/gamemode <gamemode> [player]";

        if (arguments.length == 0) {
            sender.sendMessage(usage);
        } else {
            String param = arguments[0];

            if (arguments.length == 1) {
                if (sender instanceof Player) {
                    final Player player = (Player) sender;

                    if (param.equalsIgnoreCase("0") || param.equalsIgnoreCase("survival") || param.equalsIgnoreCase("s")) {

                    }
                } else {
                    sender.sendMessage("§4§lX §cPlease specify a player.");
                }
            }
        }
    }
}
