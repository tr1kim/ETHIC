package hitchbot.mods;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Category;

public class SimpleFlight extends Module {

	public static float flyHackSpeed = 0.2F;
	
	
	public SimpleFlight() {
		super("flight", Keyboard.KEY_F, Category.PLAYER);
		// TODO Auto-generated constructor stub
	}
	
	
	public void onDisable() {
		mc.thePlayer.capabilities.isFlying = false;
		super.onDisable();
	}
	
	public void onUpdate() {
		if (this.isToggled()) {
			mc.thePlayer.capabilities.isFlying = true;
			
			if (mc.gameSettings.keyBindJump.isPressed()) {
				mc.thePlayer.motionY = 0.2;
			}
			
			if (mc.gameSettings.keyBindSneak.isPressed()) {
				mc.thePlayer.motionY = -0.2;
			}
		}
		
		if (mc.gameSettings.keyBindForward.isPressed()) {
			mc.thePlayer.capabilities.setFlySpeed(flyHackSpeed);
		}
		super.onUpdate();
	}
}
