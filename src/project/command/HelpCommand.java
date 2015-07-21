package project.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import project.Core;
import project.rank.RankManager;
import project.title.TitleUtility;

import java.util.ArrayList;
import java.util.List;

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
public class HelpCommand extends CommandBase {

    private List<String> cooldown = new ArrayList<>();

    @Override
    public void execute(CommandSender sender, Command command, String[] arguments) {
        String usage = "§c§l>> §eUsage: §7/help <message...>";

        if (sender instanceof Player) {
            final Player player = (Player) sender;

            if (arguments.length == 0) {
                sender.sendMessage(usage);
            } else {
                if (!cooldown.contains(player.getUniqueId().toString())) {
                    cooldown.add(player.getUniqueId().toString());

                    Bukkit.getScheduler().runTaskLater(Core.getPlugin(), () -> cooldown.remove(player.getUniqueId().toString()), 20 * 60 * 5);

                    StringBuilder builder = new StringBuilder();

                    for (int i = 0; i < arguments.length; i++) {
                        builder.append(arguments[i]).append(" ");
                    }

                    String message = builder.toString().trim();

                    Bukkit.getOnlinePlayers().stream().filter(onlinePlayer -> RankManager.getRank(onlinePlayer).getPermissionLevel() > 1 || onlinePlayer.isOp()).forEach(onlinePlayer -> {
                        TitleUtility.sendTitle(onlinePlayer, "§c§lHelp from §7§l" + player.getName(), "§e" + message, 20, 100, 20);
                    });
                } else {
                    player.sendMessage("§4§lX §cYou can only send help messages every five minutes!");
                }
            }
        } else {
            sender.sendMessage("§4§lX §cOnly players can execute this command.");
        }
    }
}
