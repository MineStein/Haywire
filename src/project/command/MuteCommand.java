package project.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import project.mute.MuteManager;
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
public class MuteCommand extends CommandBase {

    @Override
    public void execute(CommandSender sender, Command command, String[] arguments) {
        String[] usage = new String[] { "§6§l******** §e/mute §6§l********", "§7/mute add <player>", "§7/mute remove <player>", "§7/mute check <player>" };

        if (sender instanceof Player) {
            if (RankManager.getPermission(((Player) sender)) < 1 && !sender.isOp()) {
                sender.sendMessage("§c§l>> §eYou must have at least a permission level of §71§e!");

                return;
            }
        }

        if (arguments.length == 0 || arguments.length == 1) {
            sender.sendMessage(usage);
        } else {
            final String subCommand = arguments[0];
            final Player target = Bukkit.getPlayer(arguments[1]);

            if (subCommand.equalsIgnoreCase("add")) {
                if (!MuteManager.isMuted(target)) {
                    MuteManager.mute(target);

                    sender.sendMessage("§a§l>> §7" + target.getName() + " §ehas been muted.");
                } else {
                    sender.sendMessage("§4§lX §cThat player is already muted!");
                }
            } else if (subCommand.equalsIgnoreCase("remove")) {
                if (MuteManager.isMuted(target)) {
                    MuteManager.unmute(target);

                    sender.sendMessage("§a§l>> §7" + target.getName() + " §ehas been unmuted.");
                } else {
                    sender.sendMessage("§4§lX §cThat player is not muted!");
                }
            } else if (subCommand.equalsIgnoreCase("check")) {
                boolean muted = MuteManager.isMuted(target);

                sender.sendMessage("§a§l>> §7" + target.getName() + " §eis muted? " + (muted ? "§a§lYES" : "§c§lNO") );
            } else {
                sender.sendMessage(usage);
            }
        }
    }
}
