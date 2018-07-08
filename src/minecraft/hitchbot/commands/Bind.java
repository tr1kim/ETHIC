package hitchbot.commands;

import org.lwjgl.input.Keyboard;

import hitchbot.command.Command;
import hitchbot.main.Hitchbot;
import hitchbot.mods.Module;

public class Bind extends Command {

	@Override
	public String getAlias() {
		return "bind";
	}

	@Override
	public String getDescription() {
		return "Allows user to change keybinds of modules";
	}

	@Override
	public String getSyntax() {
		return "-bind set [Module] [Key] | -bind del [Module] | -bind clear";
	}

	@Override
	public void onCommand(String command, String[] args) throws Exception {
		if (args[0].equalsIgnoreCase("set")) {
			args[2] = args[2].toUpperCase();
			int key = Keyboard.getKeyIndex(args[2]);
			
			for (Module m: Hitchbot.getModules()) {
				if (args[1].equalsIgnoreCase(m.getName())) {
					m.setKey(Keyboard.getKeyIndex(Keyboard.getKeyName(key)));
					Hitchbot.addChatMessage(args[1] + " <> " + key);
				}
			}
		}else if(args[0].equalsIgnoreCase("del")) {
			for (Module m: Hitchbot.getModules()) {
				if (m.getName().equalsIgnoreCase(args[1])){
					m.setKey(0);
					Hitchbot.addChatMessage(args[1] + " <> null");
				}
			}
		}else if(args[0].equalsIgnoreCase("clear")) {
			for (Module m: Hitchbot.getModules()) {
				m.setKey(0);
			}
		}
	}

}
