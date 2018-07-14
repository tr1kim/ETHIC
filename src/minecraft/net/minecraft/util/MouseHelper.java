package net.minecraft.util;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import hitchbot.main.Hitchbot;
import hitchbot.mods.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;

public class MouseHelper
{
    /** Mouse delta X this frame */
    public float deltaX;

    /** Mouse delta Y this frame */
    public float deltaY;

    /**
     * Grabs the mouse cursor it doesn't move and isn't seen.
     */
    public void grabMouseCursor()
    {
        Mouse.setGrabbed(true);
        this.deltaX = 0;
        this.deltaY = 0;
    }

    /**
     * Ungrabs the mouse cursor so it can be moved and set it to the center of the screen
     */
    public void ungrabMouseCursor()
    {
        Mouse.setCursorPosition(Display.getWidth() / 2, Display.getHeight() / 2);
        Mouse.setGrabbed(false);
    }

    public void mouseXYChange()
    {

    	for (Module m: Hitchbot.getModules()) {
    		if (m.isToggled() && m.getName().equalsIgnoreCase("WarzAimNS") && Minecraft.getMinecraft().thePlayer.getHeldItem() != null && Hitchbot.guns.contains(Item.getIdFromItem(Minecraft.getMinecraft().thePlayer.getHeldItem().getItem()))) {
		    	if (Hitchbot.aimbottruefalse) {
		    		this.deltaX = (float) ((float) (Mouse.getDX())+0.1);
		        	this.deltaY = Mouse.getDY();
		    	}else {
		    		this.deltaX = (float) ((float) (Mouse.getDX())-0.1);
		        	this.deltaY = Mouse.getDY();
		    	}
		    	Hitchbot.aimbottruefalse = !Hitchbot.aimbottruefalse;
		    	return;
    		}
    	}
		this.deltaX = Mouse.getDX();
    	this.deltaY = Mouse.getDY();
    }
}
