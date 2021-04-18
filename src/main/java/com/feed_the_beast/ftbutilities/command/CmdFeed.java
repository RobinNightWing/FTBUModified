package com.feed_the_beast.ftbutilities.command;

import com.feed_the_beast.ftblib.lib.command.CmdBase;
import com.feed_the_beast.ftblib.lib.command.CommandUtils;
import com.feed_the_beast.ftblib.lib.data.ForgePlayer;
import com.feed_the_beast.ftbutilities.FTBUtilitiesPermissions;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class CmdFeed extends CmdBase {

    public CmdFeed() {
        super("feed", Level.ALL);
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        ForgePlayer player = CommandUtils.getForgePlayer(sender);
        EntityPlayerMP feedPlayer = getCommandSenderAsPlayer(sender);

        if (!player.hasPermission(FTBUtilitiesPermissions.FEED))
        {
            throw new CommandException("commands.generic.permission");
        } else {
            feedPlayer.getFoodStats().addStats(40, 40F);
            ITextComponent feedbackMessage = new TextComponentString("You have been satiated");
            feedPlayer.sendStatusMessage(feedbackMessage, true);
        }

    }
}
