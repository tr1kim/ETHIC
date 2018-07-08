package hitchbot.mods;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Category;
import hitchbot.utils.RenderUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerEsp extends Module{

	public PlayerEsp() {
		super("PlayerEsp", Keyboard.KEY_P , Category.RENDER);
	}
	
	@Override
	public void onRender() {
		if (this.isToggled()) {
			for (Object e : mc.theWorld.loadedEntityList) {
				if (e instanceof EntityPlayer && !((EntityPlayer) e).getName().equals(mc.thePlayer.getName())) {
					RenderUtils.entityESPBox((Entity) e, 0);
				}
			}
		}
	}

}
