package hitchbot.commands;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import hitchbot.command.Command;
import hitchbot.main.Hitchbot;
import hitchbot.mods.Module;

public class Friend extends Command {

	@Override
	public String getAlias() {
		return "friends";
	}

	@Override
	public String getDescription() {
		return "Handles the clients friendlist";
	}

	@Override
	public String getSyntax() {
		return "-friends add [name] | -friends del [name] | -friends list";
	}

	@Override
	public void onCommand(String command, String[] args) throws Exception {
		if (args[0].equalsIgnoreCase("add")) {
			Hitchbot.addFriend(args[1]);
			Hitchbot.addChatMessage("Added " + args[1] + " to your friend list");
		}else if(args[0].equalsIgnoreCase("del")) {
			Hitchbot.removeFriend(args[1]);
			Hitchbot.addChatMessage("Removed " + args[1] + " from your friend list");
		}else if(args[0].equalsIgnoreCase("list")) {
			ArrayList<String> f = Hitchbot.getFriends();
			Hitchbot.addChatMessage("Your friend list contains:");
			for (String s: f) {
				Hitchbot.addChatMessage(" > " + s);
			}
		}
	}

}
