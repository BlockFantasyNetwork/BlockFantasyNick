package net.blockf.blockfantasynick.event;

import net.blockf.blockfantasynick.entity.BUser;
import net.blockf.blockfantasynick.service.DatabaseService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class JoinServerEvent implements Listener {
    DatabaseService dbService = new DatabaseService();
    @EventHandler
    public void joinEvent(PlayerLoginEvent event){
        Player player = event.getPlayer();
        BUser bUser = dbService.user().getUserNick(player);
        if (bUser.getStatus()==1){
            player.setDisplayName(bUser.getDisplay_name());
        }
    }
}
