package hitchbot.mods;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Category;
import net.minecraft.item.Item;

public class AutoSteak extends Module {
	
	private static double timer = System.currentTimeMillis(); 
	private static int attackSlot = 0;
	private static int steackSlot = 0;
	public AutoSteak() {
		super("AutoSteak",Keyboard.KEY_N, Category.COMBAT);
	}
	public void onRender() {
		if (this.isToggled()) {
			if (mc.thePlayer.getHealth() < 10) {
				if (System.currentTimeMillis() - timer > 1000) {
					if (mc.thePlayer.inventory.getStackInSlot(0) != null && mc.thePlayer.inventory.getStackInSlot(0).getItem().equals(Item.getItemById(364))) {
						   try {
							Robot r = new Robot();
						    r.mousePress(InputEvent.BUTTON3_MASK);
						    attackSlot = mc.thePlayer.inventory.currentItem;
						    steackSlot = 0;
						    mc.thePlayer.inventory.currentItem  = 0;
						  } catch (AWTException e) {
						    e.printStackTrace();
						  }
					}
					if (mc.thePlayer.inventory.getStackInSlot(1) != null &&  mc.thePlayer.inventory.getStackInSlot(1).getItem().equals(Item.getItemById(364))) {
						   try {
							Robot r = new Robot();
						    r.mousePress(InputEvent.BUTTON3_MASK);
						    attackSlot = mc.thePlayer.inventory.currentItem;
						    steackSlot = 1;
						    mc.thePlayer.inventory.currentItem  = 1;
						  } catch (AWTException e) {
						    e.printStackTrace();
						  }
					}
					if (mc.thePlayer.inventory.getStackInSlot(2) != null && mc.thePlayer.inventory.getStackInSlot(2).getItem().equals(Item.getItemById(364))) {
						   try {
							Robot r = new Robot();
						    r.mousePress(InputEvent.BUTTON3_MASK);
						    attackSlot = mc.thePlayer.inventory.currentItem;
						    steackSlot = 2;
						    mc.thePlayer.inventory.currentItem  = 2;
						  } catch (AWTException e) {
						    e.printStackTrace();
						  }
					}
					if (mc.thePlayer.inventory.getStackInSlot(3) != null && mc.thePlayer.inventory.getStackInSlot(3).getItem().equals(Item.getItemById(364))) {
						   try {
							Robot r = new Robot();
						    r.mousePress(InputEvent.BUTTON3_MASK);
						    attackSlot = mc.thePlayer.inventory.currentItem;
						    steackSlot = 3;
						    mc.thePlayer.inventory.currentItem  = 3;
						  } catch (AWTException e) {
						    e.printStackTrace();
						  }
					}
					if (mc.thePlayer.inventory.getStackInSlot(4) != null && mc.thePlayer.inventory.getStackInSlot(4).getItem().equals(Item.getItemById(364))) {
						   try {
							Robot r = new Robot();
						    r.mousePress(InputEvent.BUTTON3_MASK);
						    attackSlot = mc.thePlayer.inventory.currentItem;
						    steackSlot = 4;
						    mc.thePlayer.inventory.currentItem  = 4;
						  } catch (AWTException e) {
						    e.printStackTrace();
						  }
					}
					if (mc.thePlayer.inventory.getStackInSlot(5) != null && mc.thePlayer.inventory.getStackInSlot(5).getItem().equals(Item.getItemById(364))) {
						   try {
							Robot r = new Robot();
						    r.mousePress(InputEvent.BUTTON3_MASK);
						    attackSlot = mc.thePlayer.inventory.currentItem;
						    steackSlot = 5;
						    mc.thePlayer.inventory.currentItem  = 5;
						  } catch (AWTException e) {
						    e.printStackTrace();
						  }
					}
					if (mc.thePlayer.inventory.getStackInSlot(6) != null && mc.thePlayer.inventory.getStackInSlot(6).getItem().equals(Item.getItemById(364))) {
						   try {
							Robot r = new Robot();
						    r.mousePress(InputEvent.BUTTON3_MASK);
						    attackSlot = mc.thePlayer.inventory.currentItem;
						    steackSlot = 6;
						    mc.thePlayer.inventory.currentItem  = 6;
						  } catch (AWTException e) {
						    e.printStackTrace();
						  }
					}
					if (mc.thePlayer.inventory.getStackInSlot(7) != null && mc.thePlayer.inventory.getStackInSlot(7).getItem().equals(Item.getItemById(364))) {
						   try {
							Robot r = new Robot();
						    r.mousePress(InputEvent.BUTTON3_MASK);
						    attackSlot = mc.thePlayer.inventory.currentItem;
						    steackSlot = 7;
						    mc.thePlayer.inventory.currentItem  = 7;
						  } catch (AWTException e) {
						    e.printStackTrace();
						  }
					}
					if (mc.thePlayer.inventory.getStackInSlot(8) != null && mc.thePlayer.inventory.getStackInSlot(8).getItem().equals(Item.getItemById(364))) {
						   try {
							Robot r = new Robot();
						    r.mousePress(InputEvent.BUTTON3_MASK);
						    attackSlot = mc.thePlayer.inventory.currentItem;
						    steackSlot = 8;
						    mc.thePlayer.inventory.currentItem  = 8;
						  } catch (AWTException e) {
						    e.printStackTrace();
						  }
					}
					timer = System.currentTimeMillis();
				}
				if (System.currentTimeMillis() - timer > 400 && System.currentTimeMillis() - timer < 500) {
					if (steackSlot == 0) {
						   try {
							Robot r = new Robot();
						    r.mouseRelease(InputEvent.BUTTON3_MASK);
						  } catch (AWTException e) {
						    e.printStackTrace();
						  }
					}
					if (steackSlot == 1) {
						   try {
							Robot r = new Robot();
						    r.mouseRelease(InputEvent.BUTTON3_MASK);
						  } catch (AWTException e) {
						    e.printStackTrace();
						  }
					}
					if (steackSlot == 2) {
						   try {
							Robot r = new Robot();
						    r.mouseRelease(InputEvent.BUTTON3_MASK);
						  } catch (AWTException e) {
						    e.printStackTrace();
						  }
					}
					if (steackSlot == 3) {
						   try {
							Robot r = new Robot();
						    r.mouseRelease(InputEvent.BUTTON3_MASK);
						  } catch (AWTException e) {
						    e.printStackTrace();
						  }
					}
					if (steackSlot == 4) {
						   try {
							Robot r = new Robot();
						    r.mouseRelease(InputEvent.BUTTON3_MASK);
						  } catch (AWTException e) {
						    e.printStackTrace();
						  }
					}
					if (steackSlot == 5) {
						   try {
							Robot r = new Robot();
						    r.mouseRelease(InputEvent.BUTTON3_MASK);
						  } catch (AWTException e) {
						    e.printStackTrace();
						  }
					}
					if (steackSlot == 6) {
						   try {
							Robot r = new Robot();
						    r.mouseRelease(InputEvent.BUTTON3_MASK);
						  } catch (AWTException e) {
						    e.printStackTrace();
						  }
					}
					if (steackSlot == 7) {
						   try {
							Robot r = new Robot();
						    r.mouseRelease(InputEvent.BUTTON3_MASK);
						  } catch (AWTException e) {
						    e.printStackTrace();
						  }
					}
					if (steackSlot == 8) {
						   try {
							Robot r = new Robot();
						    r.mouseRelease(InputEvent.BUTTON3_MASK);
						  } catch (AWTException e) {
						    e.printStackTrace();
						  }
					}
				}
				if (System.currentTimeMillis() - timer > 500 && System.currentTimeMillis() - timer < 600) {
				    mc.thePlayer.inventory.currentItem  = attackSlot;
				}
			}
		}
		super.onUpdate();
	}

}
