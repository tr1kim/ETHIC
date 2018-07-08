package hitchbot.main;

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
        mc.fontRendererObj.drawString("ETHIC v1.0 - Dev" , 2, 2+(0*10), 0xff0000);
        int count = 1;
        for (Module m: Hitchbot.getModules()) {
        	if(m.isToggled())
        	{
        		mc.fontRendererObj.drawString(m.getName(), 2, 2+(count*10), 0x00ff00);
        		count++;
        	}
        }
    }
}
