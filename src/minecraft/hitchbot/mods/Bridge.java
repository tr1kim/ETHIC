package hitchbot.mods;

import org.lwjgl.input.Keyboard;


import hitchbot.main.Category;
import hitchbot.main.Hitchbot;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;

public class Bridge extends Module{

	public Bridge() {
		super("Bridge", Keyboard.KEY_Z, Category.PLAYER);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onUpdate() {
		if (isToggled()) {
			if (Hitchbot.blocks.contains(Item.getIdFromItem(Minecraft.getMinecraft().thePlayer.getHeldItem().getItem()))) {
				//Block block = new Block(Material.ground);
				
				//Block b = Minecraft.getMinecraft().theWorld.getBlockState(new BlockPos(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY-1, Minecraft.getMinecraft().thePlayer.posZ)).getBlock();
				//if b.can
			}
		}
		super.onUpdate();
	}
	

}
