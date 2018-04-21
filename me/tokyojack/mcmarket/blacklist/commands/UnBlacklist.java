package me.tokyojack.mcmarket.blacklist.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import me.tokyojack.mcmarket.blacklist.Core;
import me.tokyojack.mcmarket.blacklist.utils.Kommand.Kommand.Kommand;

public class UnBlacklist extends Kommand {

	public UnBlacklist() {
		super("Removes a player to the blacklist", Arrays.asList("unbl"));
	}

	@Override
	public boolean execute(CommandSender commandSender, String arg1, String[] args) {
		if (!(commandSender.hasPermission("blacklist.unblacklist ") || commandSender.isOp())) {
			commandSender.sendMessage(Core.getPlugin().getKonfig().getString("blacklist.noPerm"));
			return false;
		}

		if (args.length <= 0) {
			commandSender.sendMessage(Core.getPlugin().getKonfig().getString("unblacklist.notCorrectArgs"));
			return false;
		}

		if (args.length >= 1) {
			if (!Core.getPlugin().getBlacklistManager().containsPlayer(args[0])) {
				commandSender
						.sendMessage(Core.getPlugin().getKonfig().getString("unblacklist.playerIsNotReadyBlacklisted"));
				return false;
			}

			Core.getPlugin().getBlacklistManager().removePlayer(args[0]);
			commandSender.sendMessage(
					Core.getPlugin().getKonfig().getString("unblacklist.success").replace("%player%", args[0]));
			Bukkit.broadcastMessage(
					Core.getPlugin().getKonfig().getString("unblacklist.broadcast").replace("%player%", args[0]));

		}

		return false;
	}

}
