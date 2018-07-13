package hitchbot.mods;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Category;

public class ESPShader extends Module{

	public ESPShader() {
		super("ESPShader", Keyboard.KEY_X, Category.RENDER);
	}

}
