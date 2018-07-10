package hitchbot.commands;

import org.lwjgl.input.Keyboard;

import hitchbot.command.Command;
import hitchbot.main.Hitchbot;
import hitchbot.mods.Module;

public class Config extends Command{

	@Override
	public String getAlias() {
		return "config";
	}

	@Override
	public String getDescription() {
		return "set config values for a number of modules";
	}

	@Override
	public String getSyntax() {
		return "-config warzaim.range <range> (float) | warzaim.guns.add <itemid> (int) | warzaim.guns.del <itemid> (int) | warzaim.angle <angle> (float) | -config list";
	}

	@Override
	public void onCommand(String command, String[] args) throws Exception {
		if (args[0].equalsIgnoreCase("warzaim.range")) {
			Hitchbot.aimbotRange = Float.valueOf(args[1]);
			Hitchbot.addChatMessage("Range of WarzAim set to: " + args[1]);
		}else if(args[0].equalsIgnoreCase("warzaim.guns.add")) {
			Hitchbot.guns.add(Integer.valueOf(args[1]));
			Hitchbot.addChatMessage(args[1] + " was added to guns");
		}else if(args[0].equalsIgnoreCase("warzaim.guns.del")) {
			Hitchbot.guns.remove(Integer.valueOf(args[1]));
			Hitchbot.addChatMessage(args[1] + " was removed from guns");
		}
		else if(args[0].equalsIgnoreCase("warzaim.angle")) {
			Hitchbot.aimbotAngle= Float.valueOf(args[1]);
			Hitchbot.addChatMessage("MaxAngleDif in WarzAim set to: " + args[1]);
		}
		else if (args[0].equalsIgnoreCase("list")) {
			Hitchbot.addChatMessage("CONFIG:");
			Hitchbot.addChatMessage("range: " + Hitchbot.aimbotRange);
			Hitchbot.addChatMessage("angle: " + Hitchbot.aimbotAngle);
			String g = "";
			for (Integer s : Hitchbot.guns) {
				g+=String.valueOf(s) + " ";
			}
			Hitchbot.addChatMessage("guns: " + g);
		}
	}

}
