package hitchbot.mods;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Category;

public class OptifineZoom extends Module {

	public OptifineZoom() {
		super("OptifineZoom", Keyboard.KEY_C, Category.RENDER);
	}

}
