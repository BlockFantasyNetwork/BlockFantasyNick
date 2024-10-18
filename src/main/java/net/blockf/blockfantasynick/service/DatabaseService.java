package net.blockf.blockfantasynick.service;

import net.blockf.blockfantasynick.BlockFantasyNick;
import net.blockf.blockfantasynick.service.database.BConfigDatabaseService;
import net.blockf.blockfantasynick.service.database.BLogDatabaseService;
import net.blockf.blockfantasynick.service.database.BUserDatabaseService;

public class DatabaseService {
    private final BlockFantasyNick plugin = BlockFantasyNick.getPlugin(BlockFantasyNick.class);
    private final BUserDatabaseService bUserDatabaseService = new BUserDatabaseService();
    private final BLogDatabaseService bLogDatabaseService = new BLogDatabaseService();
    private final BConfigDatabaseService bConfigDatabaseService = new BConfigDatabaseService();

    public BUserDatabaseService user() {
        return bUserDatabaseService;
    }

    public BLogDatabaseService log() {
        return bLogDatabaseService;
    }
    public BConfigDatabaseService conf(){return bConfigDatabaseService;}
}
