package hitchbot.mods;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Category;
import hitchbot.main.Hitchbot;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C03PacketPlayer.C05PacketPlayerLook;

public class WarzAimNS extends Module{

	public WarzAimNS() {
		super("WarzAimNS", Keyboard.KEY_V, Category.COMBAT);
	}
	
	@Override
	public void onRender() {
		if (this.isToggled()) {
		}
		super.onRender();
	}

}
