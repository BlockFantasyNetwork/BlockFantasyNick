package net.blockf.blockfantasynick.command;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;

import net.blockf.blockfantasynick.BlockFantasyNick;
import net.blockf.blockfantasynick.controller.MessageController;
import net.blockf.blockfantasynick.entity.BConfig;
import net.blockf.blockfantasynick.entity.BUser;
import net.blockf.blockfantasynick.service.DatabaseService;
import net.blockf.blockfantasynick.util.NickUtil;
import org.bukkit.entity.Player;

import java.util.UUID;

@Command(name="nick")
public class NickCommand {
    DatabaseService dbService = new DatabaseService();
    private final BlockFantasyNick plugin = BlockFantasyNick.getPlugin(BlockFantasyNick.class);

    @Execute
    void help(@Context Player player){
        BUser bUser = dbService.user().getUserNick(player);
        MessageController.sendMessageToPlayer(player,"&a=====命令帮助=====");
        MessageController.sendMessageToPlayer(player,"&a/nick 本界面");
        MessageController.sendMessageToPlayer(player,"&a/nick <昵称> 设置昵称");
        MessageController.sendMessageToPlayer(player,"&a/nickoff 关闭昵称");
        MessageController.sendMessageToPlayer(player,"&a/nickon 启用昵称");
        MessageController.sendMessageToPlayer(player,"&a/nickcancel 释放昵称");
        MessageController.sendMessageToPlayer(player,"&a/rnick 随机昵称");
        MessageController.sendMessageToPlayer(player,"&6您好,"+player.getName());
        MessageController.sendMessageToPlayer(player,"&6当前昵称:"+bUser.getDisplay_name());
        MessageController.sendMessageToPlayer(player,"&6开启状态:"+(bUser.getStatus()==1?"&a开启":"&c关闭"));
        MessageController.sendMessageToPlayer(player,"&6当前昵称更新时间:"+bUser.getUpdate_time());

    }
    @Execute
    @Permission("bf.nick")
    void nick(@Context Player player, @Arg String nickName) {
        this.nick(player,nickName,player);
    }

    @Execute
    @Permission("bf.nick.other")
    void nick(@Context Player player, @Arg String nickName,@Arg Player player1) {
        String nick = NickUtil.translate(nickName);
        String nocNick = NickUtil.getNoColorNick(nick);

        if (nocNick.length() < Integer.parseInt(BConfig.minchar)){
            MessageController.sendMessageToPlayer(player,"&c昵称长度小于最小限制 "+BConfig.minchar+" 字符");
            return;
        }
        if (nocNick.length() > Integer.parseInt(BConfig.maxchar)){
            MessageController.sendMessageToPlayer(player,"&c昵称长度超出最大限制 "+BConfig.maxchar+" 字符");

            return;
        }


        BUser nocUser = dbService.user().getNickByNameNoc(nocNick);
        if (nocUser!=null && !nocUser.getUuid().equals(player1.getUniqueId())){
            MessageController.sendMessageToPlayer(player,"&c昵称 "+nocNick+" 已被使用,请使用其他昵称");
            return;
        }
    if (!nocNick.equals(player1.getName()) && dbService.user().hasUser(nocNick)!=null){
        MessageController.sendMessageToPlayer(player,"&c你无法使用 "+nocNick+" 作为昵称,因为这是其他在本服玩家的ID");
        return;
    }
        BUser bUser = dbService.user().getUserNick(player1);
        bUser.setDisplay_name(nickName);
        bUser.setDisplay_name_noc(nocNick);
        bUser.setStatus(1);

        bUser.setUpdate_user(player.getName());
        bUser.setUpdate_user_uuid(player.getUniqueId());

        if (dbService.user().updateUserNick(bUser)!=1){
            UUID errorId = UUID.randomUUID();
            MessageController.sendMessageToPlayer(player,"出现错误,异常已被记录.请反馈至管理员 errorID:"+ errorId);
            plugin.getLogger().warning("出现错误,errorID:"+ errorId);
            return;
        }
        dbService.log().insertLog(bUser);
        player.setDisplayName(nick);
        MessageController.sendMessageToPlayer(player,"已将昵称设置为:"+nick);
        if (player!=player1){
            MessageController.sendMessageToPlayer(player1,player.getName()+"已将您的昵称设置为:"+nick);
        }
    }
}
