package hitchbot.mods;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Category;

public class Xray extends Module {

	public Xray() {
		super("Xray", Keyboard.KEY_J, Category.RENDER);
	}

}
