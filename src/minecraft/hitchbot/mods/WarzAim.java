package hitchbot.mods;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Category;

public class WarzAim extends Module{
	
	public WarzAim() {
		super("WarzAim", Keyboard.KEY_B, Category.COMBAT);
	}
	
}
