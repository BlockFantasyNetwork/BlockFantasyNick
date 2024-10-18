package net.blockf.blockfantasynick.database.hikaricp;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import net.blockf.blockfantasynick.BlockFantasyNick;
import net.blockf.blockfantasynick.entity.BUser;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.Date;
import java.util.UUID;

public class HikariUtil {
    private HikariDataSource dataSource;
    private static final HikariUtil instance = new HikariUtil();
    public synchronized static HikariUtil getInstance() {
        return instance;
    }

    public void init(){
        BlockFantasyNick plugin = BlockFantasyNick.getPlugin(BlockFantasyNick.class);
        FileConfiguration config = plugin.getConfig();

        // 连接数据库的基础配置
        ConfigurationSection databaseSection = config.getConfigurationSection("database");
        String host = databaseSection.getString("host", "localhost");
        int port = databaseSection.getInt("port", 3306);
        String username = databaseSection.getString("username", "root");
        String password = databaseSection.getString("password", "");
        String database = databaseSection.getString("database", "minecraft");

        // 连接池参数
        ConfigurationSection hikaricpSection = databaseSection.getConfigurationSection("hikaricp");
        long connectionTimeout = hikaricpSection.getLong("connectionTimeout",30000L);
        long idleTimeout = hikaricpSection.getLong("idleTimeout",600000L);
        long maxLifetime = hikaricpSection.getLong("maxLifetime",1800000L);
        String connectionTestQuery = hikaricpSection.getString("connectionTestQuery","SELECT 1");
        int minimumIdle = hikaricpSection.getInt("minimumIdle",10);
        int maximumPoolSize = hikaricpSection.getInt("maximumPoolSize",30);

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setConnectionTimeout(connectionTimeout);
        hikariConfig.setIdleTimeout(idleTimeout);
        hikariConfig.setMaxLifetime(maxLifetime);
        hikariConfig.setConnectionTestQuery(connectionTestQuery);
        hikariConfig.setMinimumIdle(minimumIdle);
        hikariConfig.setMaximumPoolSize(maximumPoolSize);
        // 设置驱动类
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        // 设置jdbc URL
        plugin.getLogger().info(hikariConfig.getJdbcUrl());
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database+"?useUnicode=true&characterEncoding=utf8&useSSL=false");

        dataSource = new HikariDataSource(hikariConfig);
    }
    public HikariDataSource getDataSource() {
        return dataSource;
    }
}
