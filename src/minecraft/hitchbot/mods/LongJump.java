package hitchbot.mods;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Category;

public class LongJump extends Module {

	
	
	public LongJump() {
		super("LJump", Keyboard.KEY_F, Category.PLAYER);
		// TODO Auto-generated constructor stub
	}
	
	
	public void onDisable() {
		super.onDisable();
	}
	
	public void onUpdate() {
		if (this.isToggled()) {
			if (mc.gameSettings.keyBindJump.isPressed()) {
				//mc.thePlayer.motionX += 0.05;
			}
			super.onUpdate();
		}
	}
}
