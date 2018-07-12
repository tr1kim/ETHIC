package hitchbot.main;

import java.util.ArrayList;

import hitchbot.mods.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class GuiIngameHook extends GuiIngame{

	public GuiIngameHook(Minecraft mcIn) {
		super(mcIn);
	}

    public void renderGameOverlay(float partialTicks)
    {
    	super.renderGameOverlay(partialTicks);
        ScaledResolution scaledresolution = new ScaledResolution(this.mc);
        this.mc.entityRenderer.setupOverlayRendering();
        GlStateManager.enableBlend();
        int count = 0;
        mc.fontRendererObj.drawString("\2475\247lActive mods:", 5, 107, 0xffffff);
        for (Module m: Hitchbot.getModules()) {
        	if(m.isToggled())
        	{
        		mc.fontRendererObj.drawString(m.getName(), 5, 117+(count*10), 0xffffff);
        		count++;
        	}
        }
        drawTabGui();
    }
    private boolean isEnabled(String s) {
    	ArrayList<Module> mds = Hitchbot.getModules();
    	for (Module m : mds) {
    		if (m.getName().equalsIgnoreCase(s)) {
    			if (m.isToggled()) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    public void drawTabGui() {
    	drawRect(75, 5, 5, 25, 0xaf676c6e);
    	mc.fontRendererObj.drawString("\2475\247lETHIC \247r v1.0", 9, 11, 0xffffffff);
    	
    	drawRect(75, 25, 5, 45, Hitchbot.tabManager.getCurrentTab() == 0 ? 0xaf0d0d0d : 0xaf676c6e);
    	mc.fontRendererObj.drawString("Render", 9, 31, 0xffffffff);
    	
    	drawRect(75, 45, 5, 65, Hitchbot.tabManager.getCurrentTab() == 1 ? 0xaf0d0d0d : 0xaf676c6e);
    	mc.fontRendererObj.drawString("Movement", 9, 51, 0xffffffff);
    	
    	drawRect(75, 65, 5, 85, Hitchbot.tabManager.getCurrentTab() == 2 ? 0xaf0d0d0d : 0xaf676c6e);
    	mc.fontRendererObj.drawString("Combat", 9, 71, 0xffffffff);
    	if (Hitchbot.tabManager.getTabs().get(0).isExpanded()) {
    		drawRect(145, 25, 75, 45, Hitchbot.tabManager.getCurrentRenderMod()== 0 ? 0xaf0d0d0d : 0xaf676c6e);
        	mc.fontRendererObj.drawString("PlayerEsp", 85, 31, isEnabled("PlayerEsp") ? 0x0000ff00 : 0xffffffff );
    		drawRect(145, 45, 75, 65, Hitchbot.tabManager.getCurrentRenderMod()== 1 ? 0xaf0d0d0d : 0xaf676c6e);
        	mc.fontRendererObj.drawString("Zoom", 85, 51, isEnabled("OptifineZoom") ? 0x0000ff00 : 0xffffffff);
    		drawRect(145, 65, 75, 85, Hitchbot.tabManager.getCurrentRenderMod()== 2 ? 0xaf0d0d0d : 0xaf676c6e);
        	mc.fontRendererObj.drawString("FullBright", 85, 71, isEnabled("FullBright") ? 0x0000ff00 : 0xffffffff);
    		drawRect(145, 85, 75, 105, Hitchbot.tabManager.getCurrentRenderMod()== 3 ? 0xaf0d0d0d : 0xaf676c6e);
        	mc.fontRendererObj.drawString("Tracers", 85, 91, isEnabled("Tracers") ? 0x0000ff00 : 0xffffffff);
    		drawRect(145, 105, 75, 125, Hitchbot.tabManager.getCurrentRenderMod()== 4 ? 0xaf0d0d0d : 0xaf676c6e);
        	mc.fontRendererObj.drawString("ChestEsp", 85, 111, isEnabled("ChestEsp") ? 0x0000ff00 : 0xffffffff);
    		drawRect(145, 125, 75, 145, Hitchbot.tabManager.getCurrentRenderMod()== 5 ? 0xaf0d0d0d : 0xaf676c6e);
        	mc.fontRendererObj.drawString("NameTags", 85, 131, isEnabled("NameTags") ? 0x0000ff00 : 0xffffffff);
    		drawRect(145, 145, 75, 165, Hitchbot.tabManager.getCurrentRenderMod()== 6 ? 0xaf0d0d0d : 0xaf676c6e);
        	mc.fontRendererObj.drawString("Xray (P)", 85, 151, isEnabled("Xray") ? 0x0000ff00 : 0xffffffff);
    	}
    	if (Hitchbot.tabManager.getTabs().get(1).isExpanded()) {
    		drawRect(145, 45, 75, 65, Hitchbot.tabManager.getCurrentMovementMod()== 0 ? 0xaf0d0d0d : 0xaf676c6e);
        	mc.fontRendererObj.drawString("Sprint", 85, 51, isEnabled("ToggleSprint") ? 0x0000ff00 : 0xffffffff);
    		drawRect(145, 65, 75, 85, Hitchbot.tabManager.getCurrentMovementMod()== 1 ? 0xaf0d0d0d : 0xaf676c6e);
        	mc.fontRendererObj.drawString("Fly", 85, 71, isEnabled("SimpleFlight") ? 0x0000ff00 : 0xffffffff);
    	}
    	if (Hitchbot.tabManager.getTabs().get(2).isExpanded()) {
    		drawRect(145, 65, 75, 85, Hitchbot.tabManager.getCurrentCombatMod()== 0 ? 0xaf0d0d0d : 0xaf676c6e);
        	mc.fontRendererObj.drawString("WarzAim.S", 85, 71, isEnabled("WarzAim") ? 0x0000ff00 : 0xffffffff);
    		drawRect(145, 85, 75, 105, Hitchbot.tabManager.getCurrentCombatMod()== 1 ? 0xaf0d0d0d : 0xaf676c6e);
        	mc.fontRendererObj.drawString("AutoSteak", 85, 91, isEnabled("AutoSteak") ? 0x0000ff00 : 0xffffffff);
    		drawRect(145, 105, 75, 125, Hitchbot.tabManager.getCurrentCombatMod()== 2 ? 0xaf0d0d0d : 0xaf676c6e);
        	mc.fontRendererObj.drawString("WarzAim", 85, 111, isEnabled("WarzAimNS") ? 0x0000ff00 : 0xffffffff);
    	}
    }
}
