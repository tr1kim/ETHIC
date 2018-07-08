package hitchbot.mods;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Category;

public class ToggleSprint extends Module {

	public ToggleSprint() {
		super("ToggleSprint", Keyboard.KEY_I, Category.PLAYER);
		// TODO Auto-generated constructor stub
	}
	public void onUpdate() {
		if (this.isToggled()) {
			mc.thePlayer.setSprinting(true);
		}
		super.onUpdate();
	}
}
