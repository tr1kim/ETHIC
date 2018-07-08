package hitchbot.mods;

import hitchbot.main.Category;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.server.S02PacketChat;

public class Module {
	
	protected Minecraft mc = Minecraft.getMinecraft();
	private int key;
	private String name;
	private boolean toggled;
	private Category category;
	
	public Module(String nm, int k, Category c) {
		name = nm;
		key = k;
		category = c;
		toggled = false;
	}

	public void toggle() {
		toggled = !toggled;
		if (toggled) {
			onEnable();
		}else {
			onDisable();
		}
	}
	
	public void onEnable() 	{}
	public void onDisable() {}
	public void onUpdate() {}
	public void onRender() {}
	public Minecraft getMc() {
		return mc;
	}

	public void setMc(Minecraft mc) {
		this.mc = mc;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isToggled() {
		return toggled;
	}

	public void setToggled(boolean toggled) {
		this.toggled = toggled;
	}
	
	public boolean onSendChatMessage(String s){
		return true;
	}
	
	public boolean onRecieveChatMessage(S02PacketChat packet){
		return true;
	}
}
