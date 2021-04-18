package com.feed_the_beast.ftbutilities.command;

import com.feed_the_beast.ftblib.lib.command.CmdBase;
import com.feed_the_beast.ftblib.lib.command.CommandUtils;
import com.feed_the_beast.ftblib.lib.data.ForgePlayer;
import com.feed_the_beast.ftblib.lib.util.NBTUtils;
import com.feed_the_beast.ftbutilities.FTBUtilitiesPermissions;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

/**
 * @author LatvianModder
 */
public class CmdFly extends CmdBase
{
	public CmdFly()
	{
		super("fly", Level.ALL);
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index)
	{
		return index == 0;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		ForgePlayer fplayer = CommandUtils.getForgePlayer(sender);

		if (!fplayer.hasPermission(FTBUtilitiesPermissions.FLY))
		{
			throw new CommandException("commands.generic.permission");
		} else
			{

			EntityPlayerMP player = getCommandSenderAsPlayer(sender);
			NBTTagCompound nbt = NBTUtils.getPersistedData(player, true);

			if (nbt.getBoolean("fly"))
			{
				nbt.removeTag("fly");
				player.capabilities.allowFlying = false;
				player.capabilities.isFlying = false;
				player.sendStatusMessage(new TextComponentString("Flight disabled"), true);
			}
			else
			{
				nbt.setBoolean("fly", true);
				player.capabilities.allowFlying = true;
				player.sendStatusMessage(new TextComponentString("Flight enabled"), true);
			}

			player.sendPlayerAbilities();
		}


	}
}