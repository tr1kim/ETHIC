package hitchbot.utils;

import java.util.List;

import hitchbot.main.Hitchbot;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;

public class rayTrace {
	public static boolean blocksInWay(double x1, double y1, double z1, double x2, double y2, double z2) {
		double xdif = x2-x1;
		double ydif = y2-y1;
		double zdif = z2-z1;
		double distpre = Math.sqrt(xdif*xdif+ydif*ydif);

		double dist = Math.sqrt(distpre*distpre+zdif*zdif); 
		List<Entity> entities = Minecraft.getMinecraft().theWorld.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.fromBounds(Minecraft.getMinecraft().thePlayer.posX - 50, Minecraft.getMinecraft().thePlayer.posY - 50, Minecraft.getMinecraft().thePlayer.posZ - 50, Minecraft.getMinecraft().thePlayer.posX + 50, Minecraft.getMinecraft().thePlayer.posY + 50, Minecraft.getMinecraft().thePlayer.posZ + 50));
		int counter = 0;
		while (counter<dist*10) {
			if (Minecraft.getMinecraft().theWorld.getBlockState(new BlockPos(x1+xdif*((double)counter)/10/dist, y1+ydif*((double)counter)/10/dist, z1+zdif*((double)counter)/10/dist)).getBlock() != Blocks.air) {
				return true;
			}
			counter++;
		}
		return false;
	}
}
