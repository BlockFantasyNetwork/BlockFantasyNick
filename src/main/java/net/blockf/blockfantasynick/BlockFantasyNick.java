package net.blockf.blockfantasynick;

import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import net.blockf.blockfantasynick.command.*;
import net.blockf.blockfantasynick.database.hikaricp.HikariUtil;
import net.blockf.blockfantasynick.entity.BConfig;
import net.blockf.blockfantasynick.event.JoinServerEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class BlockFantasyNick extends JavaPlugin {
    private LiteCommands<CommandSender> liteCommands;
    public static boolean enableCommand;

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            getLogger().warning("未能找到 PlaceholderAPI! 这是必须的."); //
            Bukkit.getPluginManager().disablePlugin(this);
        }

        saveDefaultConfig();
        HikariUtil.getInstance().init();
        Bukkit.getPluginManager().registerEvents(new JoinServerEvent(),this);
        BConfig.init();
        FileConfiguration config = getConfig();
        if (config.getBoolean("enable_command",true)){
            getLogger().info("当前子服启用控制指令");
            this.liteCommands = LiteBukkitFactory.builder()
                    .settings(settings -> settings
                            .fallbackPrefix("bfNick")
                            .nativePermissions(false)
                    )
                    .commands(new NickCommand())
                    .commands(new RNickCommand())
                    .commands(new NickOffCommand())
                    .commands(new NickOnCommand())
                    .commands(new NickCancelCommand())
                    .build();
        }else {
            getLogger().info("当前子服 禁用 控制指令!!!");

        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        if (this.liteCommands != null) {
            this.liteCommands.unregister();
        }
    }
}
