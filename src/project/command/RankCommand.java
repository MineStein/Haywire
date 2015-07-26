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
        String[] usage = new String[] { "§6§l******** §e/rank §6§l********", "§7/rank get <player>", "§7/rank list", "§7/rank add <player> <rank id>", "§7/rank remove <player> <rank id>", "§7/rank permission <player>", "§7/rank reset <player>" };

        if (sender instanceof Player) {
            if (RankManager.getPermission(((Player) sender)) < 3 && !sender.isOp()) {
                sender.sendMessage("§c§l>> §eYou must have at least a permission level of §73§e!");

                return;
            }
        }

        if (arguments.length == 0) {
            sender.sendMessage(usage);
        } else {
            String subCommand = arguments[0];

            if (subCommand.equalsIgnoreCase("get")) {
                if (arguments.length == 1) {
                    sender.sendMessage(usage);
                } else {
                    final Player target = Bukkit.getPlayer(arguments[1]);

                    if (target != null) {
                        String ranksListing = "§6§l******** §7" + target.getName() + "'s §eranks §6§l********";

                        for (int i = 0; i < RankManager.getRanks(target).size(); i++) {
                            ranksListing += (i != RankManager.getRanks(target).size() ? "\n" : "") + "§7- " + RankManager.getRankPrefix(RankManager.getRanks(target).get(i));
                        }

                        sender.sendMessage(ranksListing);
                    } else {
                        sender.sendMessage("§4§lX §cThat player is offline!");
                    }
                }
            } else if (subCommand.equalsIgnoreCase("list")) {
                StringBuilder builder = new StringBuilder("§6§l******** §eRanks §6§l********");

                for (Rank rank : Rank.values()) {
                    builder.append("\n§7").append(rank.getIndex()).append(" - §").append(rank.getColor()).append("§l").append(rank.toString().trim().replaceAll("_", " ")).append(" §7- ").append(rank.getPermissionLevel());
                }

                sender.sendMessage(builder.toString());
            } else if (subCommand.equalsIgnoreCase("add")) {
                if (arguments.length == 1 || arguments.length == 2) {
                    sender.sendMessage(usage);
                } else {
                    final Player target = Bukkit.getPlayer(arguments[1]);

                    if (target != null) {
                        try {
                            int id = Integer.parseInt(arguments[2]);

                            if (id >= 0) {
                                if (id <= Rank.RANKS_BOUND) {
                                    if (!RankManager.hasRank(target, RankManager.getRankById(id))) {
                                        RankManager.addRank(target, RankManager.getRankById(id));

                                        sender.sendMessage("§a§l>> §eYou added the rank " + RankManager.getRankPrefix(RankManager.getRankById(id)) + " §eto §7" + target.getName() + "§e.");
                                        target.sendMessage("§a§l>> §eThe rank " + RankManager.getRankPrefix(RankManager.getRankById(id)) + " §ewas added to your account.");
                                    } else {
                                        sender.sendMessage("§4§lX §cThat player already has the rank " + RankManager.getRankPrefix(RankManager.getRankById(id)));
                                    }
                                } else {
                                    StringBuilder builder = new StringBuilder("§6§l******** §eRanks §6§l********");

                                    for (Rank rank : Rank.values()) {
                                        builder.append("\n§7").append(rank.getIndex()).append(" - §").append(rank.getColor()).append("§l").append(rank.toString().trim().replaceAll("_", " ")).append(" §7- ").append(rank.getPermissionLevel());
                                    }

                                    sender.sendMessage(builder.toString());
                                }
                            } else {
                                sender.sendMessage("§4§lX §cYou cannot specify a negative number.");
                            }
                        } catch (NumberFormatException exception) {
                            sender.sendMessage(usage);
                        }
                    } else {
                        sender.sendMessage("§4§lX §cThat player is offline!");
                    }
                }
            } else if (subCommand.equalsIgnoreCase("remove")) {
                if (arguments.length == 1 || arguments.length == 2) {
                    sender.sendMessage(usage);
                } else {
                    final Player target = Bukkit.getPlayer(arguments[1]);

                    if (target != null) {
                        try {
                            int id = Integer.parseInt(arguments[2]);

                            if (id >= 0) {
                                if (id <= Rank.RANKS_BOUND) {
                                    if (RankManager.hasRank(target, RankManager.getRankById(id))) {
                                        RankManager.removeRank(target, RankManager.getRankById(id));

                                        sender.sendMessage("§a§l>> §eYou removed the rank " + RankManager.getRankPrefix(RankManager.getRankById(id)) + " §efrom §7" + target.getName() + "§e.");
                                        target.sendMessage("§c§l>> §eThe rank " + RankManager.getRankPrefix(RankManager.getRankById(id)) + " §ewas removed from your account.");
                                    } else {
                                        sender.sendMessage("§4§lX §cThat player does not have the rank " + RankManager.getRankPrefix(RankManager.getRankById(id)));
                                    }
                                } else {
                                    StringBuilder builder = new StringBuilder("§6§l******** §eRanks §6§l********");

                                    for (Rank rank : Rank.values()) {
                                        builder.append("\n§7").append(rank.getIndex()).append(" - §").append(rank.getColor()).append("§l").append(rank.toString().trim().replaceAll("_", " ")).append(" §7- ").append(rank.getPermissionLevel());
                                    }

                                    sender.sendMessage(builder.toString());
                                }
                            } else {
                                sender.sendMessage("§4§lX §cYou cannot specify a negative number.");
                            }
                        } catch (NumberFormatException exception) {
                            sender.sendMessage(usage);
                        }
                    } else {
                        sender.sendMessage("§4§lX §cThat player is offline!");
                    }
                }
            } else if (subCommand.equalsIgnoreCase("permission")) {
                if (arguments.length == 1) {
                    sender.sendMessage(usage);
                } else {
                    final Player target = Bukkit.getPlayer(arguments[1]);

                    if (target != null) {
                        sender.sendMessage("§a§l>> §7" + target.getName() + "'s §epermission level: §7" + RankManager.getPermission(target) + "§e.");
                    } else {
                        sender.sendMessage("§4§lX §cThat player is offline!");
                    }
                }
            } else if (subCommand.equalsIgnoreCase("reset")) {
                if (arguments.length == 1) {
                    sender.sendMessage(usage);
                } else {
                    final Player target = Bukkit.getPlayer(arguments[1]);

                    if (target != null) {
                        RankManager.resetRank(target);

                        sender.sendMessage("§a§l>> §eYou reset §7" + target.getName() + "'s §eranks.");
                        target.sendMessage("§c§l>> §eYour ranks have been reset.");
                    } else {
                        sender.sendMessage("§4§lX §cThat player is offline!");
                    }
                }
            } else {
                sender.sendMessage("§4§lX §cUknown sub-command.");
            }
        }
    }
}
