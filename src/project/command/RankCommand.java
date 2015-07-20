package project.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import project.rank.Rank;
import project.rank.RankManager;

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
public class RankCommand extends CommandBase {

    @Override
    public void execute(CommandSender sender, Command command, String[] arguments) {
        String[] usage = new String[] { "§6§l******** §e/rank §6§l********", "§7/rank list", "§7/rank set <player> <rank id>", "§7/rank get <player>" };

        if (sender instanceof Player) {
            if (RankManager.getPermission(((Player) sender)) < 3 && !sender.isOp()) {
                sender.sendMessage("§c§l>> §eYou must have at least a permission level of §73§e!");

                return;
            }
        }

        if (arguments.length == 0) {
            sender.sendMessage(usage);
        } else {
            String argument = arguments[0];

            if (argument.equalsIgnoreCase("list")) {
                StringBuilder builder = new StringBuilder("§6§l******** §eRanks §6§l********");

                for (Rank rank : Rank.values()) {
                    builder.append("\n§7").append(rank.getIndex()).append(" - §").append(rank.getColor()).append("§l").append(rank.toString().trim().replaceAll("_", " ")).append(" §7- ").append(rank.getPermissionLevel());
                }

                sender.sendMessage(builder.toString());
            } else if (argument.equalsIgnoreCase("set")) {
                if (arguments.length == 1 || arguments.length == 2) {
                    sender.sendMessage(usage);
                } else {
                    final Player target = Bukkit.getPlayer(arguments[1]);

                    if (target != null) {
                        try {
                            int rankId = Integer.parseInt(arguments[2]);

                            if (rankId < 0 || rankId > 7) {
                                StringBuilder builder = new StringBuilder("§6§l******** §eRanks §6§l********\n§eRank ID - Rank - Permission Level");

                                for (Rank rank : Rank.values()) {
                                    builder.append("\n§7").append(rank.getIndex()).append(" - §").append(rank.getColor()).append("§l").append(rank.toString().trim().replaceAll("_", " ")).append(" §7- ").append(rank.getPermissionLevel());
                                }

                                sender.sendMessage(builder.toString());
                            } else {
                                RankManager.setRank(target, rankId);

                                target.sendMessage("§a§l>> §eYour rank has been updated to " + RankManager.getRankPrefix(target) + "§e.");
                                sender.sendMessage("§a§l>> §eYou updated §7" + target.getName() + "'s §erank.");
                            }
                        } catch (NumberFormatException exception) {
                            sender.sendMessage(usage);
                        }
                    } else {
                        sender.sendMessage("§4§lX §cThat player is offline!");
                    }
                }
            } else if (argument.equalsIgnoreCase("get")) {
                if (arguments.length == 1) {
                    sender.sendMessage(usage);
                } else {
                    final Player target = Bukkit.getPlayer(arguments[1]);

                    if (target != null) {
                        sender.sendMessage("§a§l>> §7" + target.getName() + "'s §erank: §f" + RankManager.getRankPrefix(target));
                    } else {
                        sender.sendMessage("§4§lX §cThat player is offline!");
                    }
                }
            }
        }
    }
}
