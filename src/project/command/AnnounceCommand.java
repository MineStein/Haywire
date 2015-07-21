package project.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import project.rank.RankManager;
import project.title.TitleUtility;

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
public class AnnounceCommand extends CommandBase {

    @Override
    public void execute(CommandSender sender, Command command, String[] arguments) {
        String usage = "§c§l>> §eUsage: §7/announce <message...>";

        if (sender instanceof Player) {
            if (RankManager.getPermission(((Player) sender)) < 2 && !sender.isOp()) {
                sender.sendMessage("§c§l>> §eYou must have at least a permission level of §72§e!");

                return;
            }
        }

        if (sender instanceof Player) {
            final Player player = (Player) sender;

            if (arguments.length == 0) {
                sender.sendMessage(usage);
            } else {
                StringBuilder builder = new StringBuilder();

                for (int i = 0; i < arguments.length; i++) {
                    builder.append(arguments[i]).append(" ");
                }

                String message = builder.toString().trim();

                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    TitleUtility.sendTitle(onlinePlayer, "§c§lAnnouncement from §7§l" + player.getName(), "§e§l" + message, 20, 100, 20);
                }
            }
        } else {
            sender.sendMessage("§4§lX §cOnly players can execute this command!");
        }
    }
}
