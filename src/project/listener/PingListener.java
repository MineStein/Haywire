package project.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import project.Core;

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
public class PingListener implements Listener {

    @EventHandler
    public void onPing(final ServerListPingEvent event) {
        String[] motds = new String[] {
            "Color or colour?",
                "Sexy!",
                "It's a trap!",
                "Open the pod bay doors.",
                "Your mom!",
                "#AnvilGod",
                "I blame Josh",
                "Dammit Josh!",
                "I blame Andre",
                "I don't blame Tyler",
                "Message of the day!",
                "Fifty Shades of Aquamarine",
                "Seriously tho",
                "Girl you perfect",
                "You always work t",
                "And you deserve it",
                "The way you work it",
                "Rated T",
                "iPone 6X",
                "LOOK OUT!",
                "#Meow",
                "#Hashtag",
                "Not on steam!",
                "Not Hypickle",
                "Java 8!",
                "Ask your parents first!",
                "Certified",
                "Follow us on Twitter!",
                "@minestien",
                "@AntiVenom237",
                "@joshua2345432",
                "I'm all about that bass!",
                "RIP Chloe",
                "Focus on the picture",
                "Yay or nay?",
                "Life is Strange",
                "#LifeIsStrange",
                "This is a message",
                "for (int i=0;i<10;i++)",
                "System.exit();",
                "Player#sendMessage",
                "Laura Croft",
                "Lara Croft",
                "Roth! NOOOOOOOOOOO!",
                "Zumba",
                "Brush and floss",
                "Look out!",
                "#d3d3d3",
                "222 Shades of Grey",
                "§4§lR§6§la§e§li§a§ln§9§lb§1§lo§5§lw",
                "business [at] nothappening [dot] com"
        };

        event.setMotd("§3§lHaywire §7// §e§o" + motds[Core.getRandom().nextInt(motds.length)]);
    }
}
