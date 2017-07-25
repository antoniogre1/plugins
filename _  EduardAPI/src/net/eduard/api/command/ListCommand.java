
package net.eduard.api.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.API;
import net.eduard.api.config.ConfigSection;
import net.eduard.api.manager.CMD;

public class ListCommand extends CMD {

	public String message = "�aTem $players jogadores online!";
	public ListCommand() {
		super("list");
	}
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		ConfigSection.chat(sender, message.replace("$players", ""+API.getPlayers().size()));
		return true;
	}
}
