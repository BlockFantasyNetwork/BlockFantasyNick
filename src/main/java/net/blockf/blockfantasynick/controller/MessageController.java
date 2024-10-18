package net.blockf.blockfantasynick.controller;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageController {
    public static String prefix = "&6&l[BlockFantasyNick]&f";
    public static void sendMessageToPlayer(Player player,String message){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix+message));


    }
}
