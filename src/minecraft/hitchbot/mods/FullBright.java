package hitchbot.mods;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Category;

public class FullBright extends Module {

	public FullBright() {
		super("FullBright", Keyboard.KEY_O, Category.RENDER);
	}

	@Override
	public void onUpdate() {
		if (this.isToggled()) {
			mc.gameSettings.gammaSetting = 100F;
		}else {
			mc.gameSettings.gammaSetting = 0F;
		}
		super.onUpdate();
	}
}
