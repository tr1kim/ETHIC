package hitchbot.mods;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Category;

public class NameTags extends Module{

	public NameTags() {
		super("NameTags", Keyboard.KEY_H, Category.RENDER);
	}
}
