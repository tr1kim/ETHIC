package hitchbot.main;

import java.util.ArrayList;

import hitchbot.command.CommandManager;
import hitchbot.mods.AutoSteak;
import hitchbot.mods.Module;
import hitchbot.mods.OptifineZoom;
import hitchbot.mods.PlayerEsp;
import hitchbot.mods.SimpleFlight;
import hitchbot.mods.ToggleSprint;
import hitchbot.mods.WarzAim;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.ChatComponentText;

public class Hitchbot {
	
	private static  ArrayList<Module> mods;
	private static ArrayList<String> friends;
	private static CommandManager cmdManager;
	private static String lastTarget = "None";
	
	public Hitchbot() {
		mods = new ArrayList<Module>();
		cmdManager = new CommandManager();
		friends = new ArrayList<String>();
		addMod(new SimpleFlight());
		addMod(new WarzAim());
		addMod(new ToggleSprint());
		addMod(new PlayerEsp());
		addMod(new OptifineZoom());
		addMod(new AutoSteak());
	}
	
	public static void addFriend(String n) {
		friends.add(n);
	}
	
	public static ArrayList<String> getFriends() {
		return friends;
	}
	
	public static void removeFriend(String n) {
		friends.remove(n);
	}
	
	public static boolean isFriend(String n) {
		if (friends.contains(n)) {
			return true;
		}
		return false;
	}
	
	public static void addMod(Module m) {
		mods.add(m);
	}
	
	public static ArrayList<Module> getModules(){
		return mods;
	}
	
	public static String getLastTarget() {
		return lastTarget;
	}
	
	public static void setLastTarget(String t) {
		lastTarget = t;
		return;
	}
	
	public static void onUpdate() {
		for (Module m: mods) {
			m.onUpdate();
		}
	}
	
	public static void onRender() {
		for (Module m: mods) {
			m.onRender();
		}
	}
	
	public static void onKeyPressed(int k) {
		for (Module m : mods) {
			if (m.getKey() == k && SebeanRest(m)) {
				m.toggle();
			}
		}
	}
	public static boolean SebeanRest (Module m) {
		if (Minecraft.getMinecraft().thePlayer.getName().equalsIgnoreCase("Sebean")) {
			if (m.getName().equalsIgnoreCase("ToggleSprint") || m.getName().equalsIgnoreCase("OptifineZoom")) {
				return true;
			}
			return false;
		}
		return true;
	}
	public static void addChatMessage(String s) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("[HITCHBOT] > " + s));
	}
	
	public static boolean onSendChatMessage(String s) {
		if (s.startsWith("-")) {
			cmdManager.callCommand(s.substring(1));
			return false;
		}
		for (Module m: getModules()) {
			if (m.isToggled()) {
				m.onSendChatMessage(s);
			}
		}
		return true;
	}
	
	public static boolean onRevieveChatMessage(S02PacketChat packet) {
		for (Module m : getModules()) {
			if (m.isToggled()) {
				return m.onRecieveChatMessage(packet);
			}
		}
		return true;
	}
	
}
