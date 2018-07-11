package hitchbot.mods;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Category;
import hitchbot.main.Hitchbot;
import hitchbot.utils.RenderUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntityChest;

public class ChestEsp extends Module {

	public ChestEsp() {
		super("ChestEsp", Keyboard.KEY_Y ,Category.RENDER);
	}
	public void onRender()
	{
		if (this.isToggled()) {
			for(Object o : Minecraft.getMinecraft().theWorld.loadedTileEntityList)
			{
				if (o instanceof TileEntityChest) {
					RenderUtils.chestESPBox(((TileEntityChest)o));
				}
			}
		}
	}
}
