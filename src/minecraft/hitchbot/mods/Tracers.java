package hitchbot.mods;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Category;
import hitchbot.main.Hitchbot;
import hitchbot.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class Tracers extends Module{

	public Tracers() {
		super("Tracers", Keyboard.KEY_U, Category.RENDER);
	}
	
	@Override
	public void onRender() {
		if (this.isToggled()) {
			for (Object e : mc.theWorld.loadedEntityList) {
				if (e instanceof EntityPlayer && !((EntityPlayer) e).getName().equals(mc.thePlayer.getName())) {
					if (!Hitchbot.isFriend(((EntityPlayer)e).getName())) {
						RenderUtils.drawTracerLine(((EntityPlayer)e).posX-Minecraft.getMinecraft().thePlayer.posX, ((EntityPlayer)e).posY+1.45-Minecraft.getMinecraft().thePlayer.posY, ((EntityPlayer)e).posZ-Minecraft.getMinecraft().thePlayer.posZ, 1 - ((EntityPlayer)e).getHealth() / 20, ((EntityPlayer)e).getHealth() / 20, 0, 1, 1);
					}else {
						RenderUtils.drawTracerLine(((EntityPlayer)e).posX-Minecraft.getMinecraft().thePlayer.posX, ((EntityPlayer)e).posY+1.45-Minecraft.getMinecraft().thePlayer.posY, ((EntityPlayer)e).posZ-Minecraft.getMinecraft().thePlayer.posZ, 0, 0, 1, 1, 1);
					}
				}
			}
		}
	}

}
