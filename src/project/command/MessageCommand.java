package project.command;

import org.bukkit.Bukkit;
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
public class MessageCommand extends CommandBase {

    @Override
    public void execute(CommandSender sender, Command command, String[] arguments) {
        String usage = "§c§l>> §eUsage: §7/message <player> <message...>";

        if (arguments.length == 0) {
            sender.sendMessage(usage);
        } else {
            if (arguments.length == 1) {
                sender.sendMessage(usage);
            } else {
                final Player target = Bukkit.getPlayer(arguments[0]);

                if (target != null) {
                    if (sender instanceof Player && sender == target) {
                        sender.sendMessage("§c§l>> §eForever alone. :(");
                    } else {
                        StringBuilder builder = new StringBuilder();

                        for (int i = 1; i < arguments.length; i++) {
                            builder.append(arguments[i]).append(" ");
                        }

                        String message = builder.toString().trim();

                        sender.sendMessage("§7You §3§l§m---§f§3§l> §7" + target.getName() + " §a" + message);
                        target.sendMessage("§7" + sender.getName() + " §3§l§m---§f§3§l> §7You §a" + message);
                    }
                } else {
                    sender.sendMessage("§4§lX §cThat player is offline!");
                }
            }
        }
    }
}
