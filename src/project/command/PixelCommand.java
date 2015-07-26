package project.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import project.rank.RankManager;
import project.statistic.StatisticManager;

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
public class PixelCommand extends CommandBase {

    @Override
    public void execute(CommandSender sender, Command command, String[] arguments) {
        String[] usage = new String[] { "§6§l******** §e/pixel §6§l********", "§7/pixel get <player>", "§7/pixel set <player> <integer>", "§7/pixel add <player> <integer>", "§7/pixel subtract <player> <integer", "§7/pixel reset <player>" };

        if (sender instanceof Player) {
            if (RankManager.getPermission(((Player) sender)) < 3 && !sender.isOp()) {
                sender.sendMessage("§c§l>> §eYou must have at least a permission level of §73§e!");

                return;
            }
        }

        if (arguments.length == 0 || arguments.length == 1) {
            sender.sendMessage(usage);
        } else {
            String subCommand = arguments[0];
            final Player target = Bukkit.getPlayer(arguments[1]);

            if (target != null) {
                if (subCommand.equalsIgnoreCase("get")) {
                    int pixels = StatisticManager.getPixels(target);

                    sender.sendMessage("§a§l>> §7" + target.getName() + "'s §epixels: §7" + pixels + "§e.");
                } else if (subCommand.equalsIgnoreCase("set")) {
                    if (arguments.length == 2) {
                        sender.sendMessage(usage);
                    } else {
                        try {
                            // pixels set MineStein_ 100

                            int amount = Integer.parseInt(arguments[2]);

                            if (amount >= 0) {
                                StatisticManager.setPixels(target, amount);

                                sender.sendMessage("§a§l>> §eYou set §7" + target.getName() + "'s §epixels to §7" + amount + "§e.");
                                target.sendMessage("§" + (amount == 0 ? "c" : "a") + "§l>> §eYour pixels have been set to §7" + amount + "§e.");
                            } else {
                                sender.sendMessage("§4§lX §cThe amount you specify must not be negative.");
                            }
                        } catch (NumberFormatException exception) {
                            sender.sendMessage(usage);
                        }
                    }
                } else if (subCommand.equalsIgnoreCase("add")) {
                    if (arguments.length == 2) {
                        sender.sendMessage(usage);
                    } else {
                        try {
                            // pixels add MineStein_ 1

                            int amount = Integer.parseInt(arguments[2]);

                            if (amount > 0) {
                                StatisticManager.addPixels(target, amount);

                                sender.sendMessage("§a§l>> §eYou added §7" + amount + " §epixels to §7" + target.getName() + "'s §eaccount.");
                                target.sendMessage("§a§l>> §eYou received §7" + amount + " §epixels from §7" + target.getName() + "§e.");
                            } else {
                                sender.sendMessage("§4§lX §cThe amount you specify must be positive.");
                            }
                        } catch (NumberFormatException exception) {
                            sender.sendMessage(usage);
                        }
                    }
                } else if (subCommand.equalsIgnoreCase("subtract")) {
                    if (arguments.length == 2) {
                        sender.sendMessage(usage);
                    } else {
                        try {
                            // pixels subtract MineStein_ 1

                            if (StatisticManager.getPixels(target) > 0) {
                                int amount = Integer.parseInt(arguments[2]);

                                if (amount > 0) {
                                    int difference = StatisticManager.getPixels(target) - amount;

                                    if (difference >= 0) {
                                        StatisticManager.subtractPixels(target, amount);

                                        sender.sendMessage("§a§l>> §eYou subtracted §7" + amount + " §epixels from §7" + target.getName() + "'s §eaccount.");
                                        target.sendMessage("§c§l>> §7" + amount + " §epixels were subtracted from your account.");
                                    } else {
                                        StatisticManager.resetPixels(target);

                                        sender.sendMessage("§a§l>> §eYou removed §7" + amount + " §epixels from §7" + target.getName() + "'s §eaccount.");
                                        target.sendMessage("§c§l>> §eAll of your pixels were removed.");
                                    }
                                } else {
                                    sender.sendMessage("§4§lX §cThe amount you specify must be positive.");
                                }
                            } else {
                                sender.sendMessage("§4§lX §cThat player doesn't have any more pixels.");
                            }
                        } catch (NumberFormatException exception) {
                            sender.sendMessage(usage);
                        }
                    }
                } else if (subCommand.equalsIgnoreCase("reset")) {
                    StatisticManager.resetPixels(target);

                    target.sendMessage("§c§l>> §eYour pixels have been reset!");
                    sender.sendMessage("§a§l>> §eYou reset §7" + target.getName() + "'s §epixels!");
                } else {
                    sender.sendMessage("§4§lX §cUnknown sub-command.");
                }
            } else {
                sender.sendMessage("§4§lX §cThat player is offline!");
            }
        }
    }
}
