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

public class CmdGod extends CmdBase
{
	public CmdGod()
	{
		super("god", Level.OP);
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

		if (!fplayer.hasPermission(FTBUtilitiesPermissions.CHAT_NICKNAME_SET))
		{
			throw new CommandException("commands.generic.permission");
		} else {
			EntityPlayerMP player = args.length == 0 ? getCommandSenderAsPlayer(sender) : getPlayer(server, sender, args[0]);
			NBTTagCompound nbt = NBTUtils.getPersistedData(player, true);

			if (nbt.getBoolean("god"))
			{
				nbt.removeTag("god");
				player.capabilities.disableDamage = false;
				player.sendStatusMessage(new TextComponentString("God disabled"), true);
			}
			else
			{
				nbt.setBoolean("god", true);
				player.capabilities.disableDamage = true;
				player.sendStatusMessage(new TextComponentString("God enabled"), true);
			}

			player.sendPlayerAbilities();
		}

	}
}
