package net.blockf.blockfantasynick.command;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.permission.Permission;

@Command(name = "rnick")
@Permission("bf.rnick")
public class RNickCommand {

    @Execute
    void executeRNick() {
        // Execute the /rnick
    }

}
