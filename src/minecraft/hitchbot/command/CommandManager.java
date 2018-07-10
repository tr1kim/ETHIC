package hitchbot.command;

import java.util.ArrayList;

import hitchbot.commands.Bind;
import hitchbot.commands.Config;
import hitchbot.commands.Friend;
import hitchbot.main.Hitchbot;

public class CommandManager {
	
	private ArrayList<Command> commands;
	
	public CommandManager() {
		commands = new ArrayList();
		addCommand(new Bind());
		addCommand(new Friend());
		addCommand(new Config());
	}
	
	public void addCommand(Command c) {
		commands.add(c);
	}
	
	public ArrayList<Command> getCommands(){
		return commands;
	}
	
	public void callCommand(String input) {
		String [] split = input.split(" ");
		String command = split[0];
		String args = input.substring(command.length()).trim();
		for (Command c: getCommands()) {
			if (c.getAlias().equalsIgnoreCase(command)) {
				try {
					c.onCommand(command, args.split(" "));
				}catch(Exception e){
					Hitchbot.addChatMessage("Invalid Command!");
					Hitchbot.addChatMessage(c.getSyntax());
				}
				return;
			}
		}
		Hitchbot.addChatMessage("Command not found!");
	}
	
}
