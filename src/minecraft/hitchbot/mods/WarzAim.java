package hitchbot.mods;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Category;
import hitchbot.main.Hitchbot;
import hitchbot.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C03PacketPlayer.C05PacketPlayerLook;
import net.minecraft.tileentity.TileEntityChest;

public class WarzAim extends Module{
	
	public WarzAim() {
		super("WarzAim", Keyboard.KEY_B, Category.COMBAT);
	}
	
	
}
