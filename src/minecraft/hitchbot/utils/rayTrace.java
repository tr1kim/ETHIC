package hitchbot.utils;

import java.util.List;

import hitchbot.main.Hitchbot;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class rayTrace  {
	public static boolean blocksInWay(double x1, double y1, double z1, double x2, double y2, double z2) {
		double xdif = x2-x1;
		double ydif = y2-y1;
		double zdif = z2-z1;
		double distpre = Math.sqrt(xdif*xdif+ydif*ydif);
		double dist = Math.sqrt(distpre*distpre+zdif*zdif); 
		List<Entity> entities = Minecraft.getMinecraft().theWorld.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.fromBounds(Minecraft.getMinecraft().thePlayer.posX - 50, Minecraft.getMinecraft().thePlayer.posY - 50, Minecraft.getMinecraft().thePlayer.posZ - 50, Minecraft.getMinecraft().thePlayer.posX + 50, Minecraft.getMinecraft().thePlayer.posY + 50, Minecraft.getMinecraft().thePlayer.posZ + 50));
		int counter = 0;
		MovingObjectPosition pos = Minecraft.getMinecraft().theWorld.rayTraceBlocks(new Vec3 (x1,  y1,  z1), new Vec3(x2, y2, z2), false);
		while (counter<dist*10) {
			Block b = Minecraft.getMinecraft().theWorld.getBlockState(new BlockPos(x1+xdif*((double)counter)/10/dist, y1+ydif*((double)counter)/10/dist, z1+zdif*((double)counter)/10/dist)).getBlock();
			if (b.getMaterial() != Material.air) {
				if (b.getBlockBoundsMaxX() > (x1+xdif*((double)counter)/10/dist)-Math.floor(x1+xdif*((double)counter)/10/dist)) {
					if (b.getBlockBoundsMaxY() > (y1+ydif*((double)counter)/10/dist)-Math.floor(y1+ydif*((double)counter)/10/dist)) {
						if (b.getBlockBoundsMaxZ() > (z1+zdif*((double)counter)/10/dist)-Math.floor(z1+zdif*((double)counter)/10/dist)) {
							if (b.getBlockBoundsMinX() < (x1+xdif*((double)counter)/10/dist)-Math.floor(x1+xdif*((double)counter)/10/dist)) {
								if (b.getBlockBoundsMinY() < (y1+ydif*((double)counter)/10/dist)-Math.floor(y1+ydif*((double)counter)/10/dist)) {
									if (b.getBlockBoundsMinZ() < (z1+zdif*((double)counter)/10/dist)-Math.floor(z1+zdif*((double)counter)/10/dist)) {
										return true;
									}
								}
							}
						}
					}
				}
			}
			counter++;
		}
		return false;
	}
	public static boolean inblock(double x1, double y1, double z1) {
		Block b = Minecraft.getMinecraft().theWorld.getBlockState(new BlockPos(x1, y1, z1)).getBlock();
		if (b.getMaterial() != Material.air) {
			if (b.getBlockBoundsMaxX() > x1-Math.floor(x1)) {
				if (b.getBlockBoundsMaxY() >  y1-Math.floor(y1)) {
					if (b.getBlockBoundsMaxZ() >  z1-Math.floor(z1)) {
						if (b.getBlockBoundsMinX() < x1-Math.floor(x1)) {
							if (b.getBlockBoundsMinY() <  y1-Math.floor(y1)) {
								if (b.getBlockBoundsMinZ() <  z1-Math.floor(z1)) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
}
