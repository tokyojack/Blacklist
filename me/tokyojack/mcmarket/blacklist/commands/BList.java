package me.tokyojack.mcmarket.blacklist.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tokyojack.mcmarket.blacklist.Core;
import me.tokyojack.mcmarket.blacklist.objects.BlacklistedPlayer;
import me.tokyojack.mcmarket.blacklist.utils.Kommand.Kommand.Kommand;

public class BList extends Kommand {

	public BList() {
		super("Checks out the black-listed players", Arrays.asList("blacklists", "blacklistedplayers"));
	}

	@Override
	public boolean execute(CommandSender commandSender, String arg1, String[] args) {
		if (!(commandSender.hasPermission("blacklist.blist") || commandSender.isOp())) {
			commandSender.sendMessage(Core.getPlugin().getKonfig().getString("blist.noPerm"));
			return false;
		}

		if (Core.getPlugin().getBlacklistManager().getBlacklistedPlayers().size() <= 0) {
			commandSender.sendMessage(Core.getPlugin().getKonfig().getString("blist.noBlackListedPlayers"));
			return false;
		}

		if (args.length <= 0) {
			displayPage(1, commandSender);
		}

		if (args.length >= 1) {
			if (!isNumber(args[0])) {
				commandSender.sendMessage(Core.getPlugin().getKonfig().getString("blist.isNotANumber"));
				return false;
			}

			int amount = Integer.parseInt(args[0]);

			displayPage(amount, commandSender);
		}

		return false;
	}

	private void displayPage(int page, CommandSender commandSender) {
		LinkedHashMap<UUID, BlacklistedPlayer> blacklistedPlayers = Core.getPlugin().getBlacklistManager()
				.getBlacklistedPlayers();
		ArrayList<UUID> values = new ArrayList<UUID>(blacklistedPlayers.keySet());

		if (blacklistedPlayers.size() <= ((page - 1) * 10)) {
			commandSender.sendMessage(Core.getPlugin().getKonfig().getString("blist.pastPages"));
			return;
		}

		for (int i = 10 * (page - 1); i < page * 10; i++) {
			if (i >= blacklistedPlayers.size())
				break;

			String playerName = getNameFromPlayername(values.get(i));
			String reason = blacklistedPlayers.get(values.get(i)).getReason();
			String blacklister = getNameFromPlayername(blacklistedPlayers.get(values.get(i)).getBlackLister());

			commandSender.sendMessage(Core.getPlugin().getKonfig().getString("blist.lineFormat")
					.replace("%player%", playerName).replace("%reason%", reason).replace("%blackLister%", blacklister));
		}

		commandSender.sendMessage(
				Core.getPlugin().getKonfig().getString("blist.page").replace("%page%", String.valueOf(page)));
	}

	private String getNameFromPlayername(UUID playerUUID) {
		Player player = Bukkit.getPlayer(playerUUID);
		return player != null ? player.getName() : Bukkit.getOfflinePlayer(playerUUID).getName();
	}

	private boolean isNumber(String string) {
		try {
			Integer.parseInt(string);
			return true;
		} catch (NumberFormatException exception) {
			return false;
		}
	}
}
