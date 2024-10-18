package net.blockf.blockfantasynick.command;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;
import net.blockf.blockfantasynick.BlockFantasyNick;
import net.blockf.blockfantasynick.controller.MessageController;
import net.blockf.blockfantasynick.entity.BUser;
import net.blockf.blockfantasynick.service.DatabaseService;
import org.bukkit.entity.Player;

import java.util.UUID;

@Command(name = "nickoff")
@Permission("bf.nick")
public class NickOffCommand {
    DatabaseService dbService = new DatabaseService();
    private final BlockFantasyNick plugin = BlockFantasyNick.getPlugin(BlockFantasyNick.class);

    @Execute
    void executeNickOff(@Context Player player) {
        executeNickOff(player,player);
    }

    @Execute
    @Permission("bf.nick.other")
    void executeNickOff(@Context Player player, @Arg Player player1) {
        BUser bUser = dbService.user().getUserNick(player1);
        bUser.setStatus(0);
        bUser.setUpdate_user(player1.getName());
        bUser.setUpdate_user_uuid(player1.getUniqueId());

        if (dbService.user().updateUserNick(bUser)!=1){
            UUID errorId = UUID.randomUUID();
            MessageController.sendMessageToPlayer(player,"出现错误,异常已被记录.请反馈至管理员 errorID:"+ errorId);
            plugin.getLogger().warning("出现错误,errorID:"+ errorId);
            return;
        }
        dbService.log().insertLog(bUser);
        MessageController.sendMessageToPlayer(player,"已将 "+player.getName()+" 昵称显示&c关闭");
        player1.setDisplayName(player.getName());

        if (player!=player1){
            MessageController.sendMessageToPlayer(player1,player.getName()+"已将您的昵称显示&c关闭");
        }
    }
}
