package me.tokyojack.mcmarket.blacklist.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tokyojack.mcmarket.blacklist.Core;
import me.tokyojack.mcmarket.blacklist.utils.Kommand.Kommand.Kommand;

public class Blacklist extends Kommand {

	public Blacklist() {
		super("Adds a player to the blacklist", Arrays.asList("bl"));
	}

	@Override
	public boolean execute(CommandSender commandSender, String arg1, String[] args) {

		if (!(commandSender.hasPermission("blacklist.blacklist") || commandSender.isOp())) {
			commandSender.sendMessage(Core.getPlugin().getKonfig().getString("blacklist.noPerm"));
			return false;
		}

		if (args.length <= 0) {
			commandSender.sendMessage(Core.getPlugin().getKonfig().getString("blacklist.notCorrectArgs"));
			return false;
		}

		if (args.length == 1) {
			commandSender.sendMessage(Core.getPlugin().getKonfig().getString("blacklist.notCorrectArgs"));
			return false;
		}

		if (args.length >= 2) {
			if (Core.getPlugin().getBlacklistManager().containsPlayer(args[0])) {
				commandSender.sendMessage(Core.getPlugin().getKonfig().getString("blacklist.alreadyBlacklisted"));
				return false;
			}

			commandSender.sendMessage(
					Core.getPlugin().getKonfig().getString("blacklist.success").replace("%player%", args[0]));

			String playerName = args[0];

			boolean isSilent = false;

			StringBuilder reason = new StringBuilder();
			for (int i = 1; i < args.length; i++) {
				String arg = args[i];

				if (arg.equalsIgnoreCase("-s") || arg.equalsIgnoreCase("-silent")) {
					isSilent = true;
					break;
				}
				reason.append(arg + " ");
			}

			Core.getPlugin().getBlacklistManager().addPlayer(playerName, reason.toString(),
					getCommandSenderName(commandSender));

			Player player = Bukkit.getPlayer(playerName);

			if (player != null)
				player.kickPlayer(Core.getPlugin().getKonfig().getString("blacklist.kickPlayerWhenWhitelisted"));

			if (isSilent || args.length >= 2)
				return false;

			Bukkit.broadcastMessage(
					Core.getPlugin().getKonfig().getString("blacklist.broadcast").replace("%player%", args[0]));
			return true;

		}
		return true;

	}

	private String getCommandSenderName(CommandSender sender) {
		if (sender instanceof Player)
			return ((Player) sender).getName();
		else
			return "Console";
	}

}
