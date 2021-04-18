package com.feed_the_beast.ftbutilities.command;

import com.feed_the_beast.ftblib.lib.command.CmdBase;
import com.feed_the_beast.ftblib.lib.command.CommandUtils;
import com.feed_the_beast.ftblib.lib.data.ForgePlayer;
import com.feed_the_beast.ftblib.lib.util.NBTUtils;
import com.feed_the_beast.ftbutilities.FTBUtilitiesPermissions;
import com.feed_the_beast.ftbutilities.data.FTBUtilitiesPlayerData;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CmdMute extends CmdBase
{
	public CmdMute()
	{
		super("mute", Level.ALL);
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index)
	{
		return index == 0;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		ForgePlayer player = CommandUtils.getForgePlayer(sender);

		if (!player.hasPermission(FTBUtilitiesPermissions.MUTE))
		{
			throw new CommandException("commands.generic.permission");
		}
		else { NBTUtils.getPersistedData(getPlayer(server, sender, args[0]), true).setBoolean(FTBUtilitiesPlayerData.TAG_MUTED, true); }
	}
}