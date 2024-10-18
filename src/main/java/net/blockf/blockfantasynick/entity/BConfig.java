package net.blockf.blockfantasynick.entity;

import net.blockf.blockfantasynick.BlockFantasyNick;
import net.blockf.blockfantasynick.service.DatabaseService;

public class BConfig {
    static BlockFantasyNick plugin = BlockFantasyNick.getPlugin(BlockFantasyNick.class);
    static DatabaseService dbService = new DatabaseService();
    public static int serverId;
    public static String minchar;
    public static String maxchar;

    public static void init(){
        serverId = plugin.getConfig().getInt("serverId");
        minchar = dbService.conf().getConfig("minchar");
        maxchar = dbService.conf().getConfig("maxchar");
    }
}
