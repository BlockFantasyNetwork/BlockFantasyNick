package net.blockf.blockfantasynick.util;

import org.bukkit.ChatColor;

public class NickUtil {
    public static String getNoColorNick(String nick){
        char[] b = nick.toCharArray();
        for (int i = 0; i < b.length - 1; i++) {
            if (b[i] == ChatColor.COLOR_CHAR) {
                b[i] = ' ';
                b[i+1] = ' ';
            }
        }
        return new String(b).replaceAll("\\s*","");
    }
    public static String translate(String nickname){
        return ChatColor.translateAlternateColorCodes('&',nickname);

    }
}
