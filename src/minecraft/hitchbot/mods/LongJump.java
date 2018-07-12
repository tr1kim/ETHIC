package hitchbot.mods;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Category;
import hitchbot.main.Hitchbot;
import net.minecraft.client.Minecraft;

public class LongJump extends Module {

	
	
	public LongJump() {
		super("LongJump", Keyboard.KEY_F, Category.PLAYER);
		// TODO Auto-generated constructor stub
	}
	
	
	public void onDisable() {
		super.onDisable();
	}

}
