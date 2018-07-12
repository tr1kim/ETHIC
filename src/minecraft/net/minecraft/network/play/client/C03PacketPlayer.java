package net.minecraft.network.play.client;

import java.io.IOException;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import hitchbot.main.Hitchbot;
import hitchbot.mods.Module;
import hitchbot.utils.rayTrace;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;

public class C03PacketPlayer implements Packet<INetHandlerPlayServer>
{
    protected double x;
    protected double y;
    protected double z;
    protected float yaw;
    protected float pitch;
    protected boolean onGround;
    protected boolean moving;
    protected boolean rotating;

    public C03PacketPlayer()
    {
    }

    public C03PacketPlayer(boolean isOnGround)
    {
        this.onGround = isOnGround;
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(INetHandlerPlayServer handler)
    {
        handler.processPlayer(this);
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.onGround = buf.readUnsignedByte() != 0;
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeByte(this.onGround ? 1 : 0);
    }

    public double getPositionX()
    {
        return this.x;
    }

    public double getPositionY()
    {
        return this.y;
    }

    public double getPositionZ()
    {
        return this.z;
    }

    public float getYaw()
    {
        return this.yaw;
    }

    public float getPitch()
    {
        return this.pitch;
    }

    public boolean isOnGround()
    {
        return this.onGround;
    }

    public boolean isMoving()
    {
        return this.moving;
    }

    public boolean getRotating()
    {
        return this.rotating;
    }

    public void setMoving(boolean isMoving)
    {
        this.moving = isMoving;
    }

    public static class C04PacketPlayerPosition extends C03PacketPlayer
    {
        public C04PacketPlayerPosition()
        {
            this.moving = true;
        }

        public C04PacketPlayerPosition(double playerX, double playerY, double playerZ, boolean isOnGround)
        {
            this.x = playerX;
            this.y = playerY;
            this.z = playerZ;
            this.onGround = isOnGround;
            this.moving = true;
            int rayid = 0;
            boolean raytraceval = false;
            for (Module m: Hitchbot.getModules()) {
            	if (m.getName().equalsIgnoreCase("WarzAim") || m.getName().equalsIgnoreCase("WarzAimNS")) {
            		if (m.isToggled() && Minecraft.getMinecraft().thePlayer.getHeldItem() != null && Hitchbot.guns.contains(Item.getIdFromItem(Minecraft.getMinecraft().thePlayer.getHeldItem().getItem()))) {
            			if (Mouse.isButtonDown(2) || m.getName().equalsIgnoreCase("WarzAimNS")) {
            				Entity ent = null;

            					float cls = Hitchbot.aimbotRange;
	            			    List<Entity> entities = Minecraft.getMinecraft().theWorld.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.fromBounds(Minecraft.getMinecraft().thePlayer.posX - 50, Minecraft.getMinecraft().thePlayer.posY - 50, Minecraft.getMinecraft().thePlayer.posZ - 50, Minecraft.getMinecraft().thePlayer.posX + 50, Minecraft.getMinecraft().thePlayer.posY + 50, Minecraft.getMinecraft().thePlayer.posZ + 50));
	            			    for (Entity e : entities) {
	            			    	if (e instanceof EntityPlayer && (e.getName() != Minecraft.getMinecraft().thePlayer.getName()) &&(!Hitchbot.isFriend(e.getName()))) {
						    			double currentrotation1 = (Minecraft.getMinecraft().thePlayer.getRotationYawHead()%360);
		            					double mult = e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)/3.9;
		            					try {
		            					mult = Minecraft.getMinecraft().getCurrentServerData().pingToServer/25+e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)/3.9;
		            					}catch(NullPointerException ex){
		            						ex.printStackTrace();
		            					}finally{
		            						
		            					}
		            					float t = e.fallDistance;
		            					double posX = e.posX + (e.posX - e.lastTickPosX)*mult;
		            					double ySpeed = e.posY - e.lastTickPosY-0.04;
		            					double posY = e.posY;
		            					double posZ = e.posZ + (e.posZ - e.lastTickPosZ)*mult;
		            					double xspeed = (e.posX - e.lastTickPosX);
		            					double zspeed = (e.posZ - e.lastTickPosZ);
		            					double xyspeed = Math.sqrt(zspeed*zspeed + xspeed*xspeed);
		            					boolean grappling = xyspeed > 0.3333 ? true : false;
		            					boolean hitground = false;
		            					if (!e.onGround) {
		            						posX = e.posX;
		            						posZ = e.posZ;
			            					for (int i = 0; i<Math.floor(mult); i++) {
			            						if (grappling && hitground) break;
			            						posX += xspeed;
			            						posZ += zspeed;
			            						if (!hitground) {
			            							xspeed = xspeed *0.98;
			            							zspeed = zspeed *0.98;
			            							posY = posY + ySpeed -0.08*(i+1);
			            						}
			            						if (rayTrace.inblock(posX, posY, posZ)) {
			            							hitground = true;
			            							posY=Math.floor(posY)+1;
			            						}
			            					}
		            					}
		            					posY +=0.05;

						    			double Z = posZ - Minecraft.getMinecraft().thePlayer.posZ;
						    			double X = posX - Minecraft.getMinecraft().thePlayer.posX;
						    			double Y = Minecraft.getMinecraft().thePlayer.posY-posY;
						    			double pitche = ((90-Minecraft.getMinecraft().thePlayer.rotationPitch) * Math.PI) / 180;
						    			double currentyawe  = currentrotation1+90;
						    			if (currentyawe < 0) {
						    				currentyawe += 360;
						    			}
						    			if (currentyawe>360) {
						    				currentyawe -=360;
						    			}
						    			double yawe = Math.atan2(e.posZ-Minecraft.getMinecraft().thePlayer.posZ,e.posX- Minecraft.getMinecraft().thePlayer.posX);
						    			double pitchdife = Math.atan2(Math.sqrt(Z * Z + X * X), Y);
						    			double tene = 1;
						    			yawe = yawe * 180 / Math.PI;
		            			    	if (yawe < 0) {
		            			    		yawe += 360;
		            			    	}
		            			    	if (!raytraceval && !hitchbot.utils.rayTrace.blocksInWay(posX, posY+1.5, posZ, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY+1.5, Minecraft.getMinecraft().thePlayer.posZ)) {
		            			    		rayid = 0;
		            			    		raytraceval = true;
		            			    	}
		            			    	if (!raytraceval && !hitchbot.utils.rayTrace.blocksInWay(posX+0.35, posY+1.5, posZ+0.35, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY+1.5, Minecraft.getMinecraft().thePlayer.posZ)) {
		            			    		rayid = 1;
		            			    		raytraceval = true;
		            			    	}
		            			    	if (!raytraceval && !hitchbot.utils.rayTrace.blocksInWay(posX+0.35, posY+1.5, posZ-0.35, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY+1.5, Minecraft.getMinecraft().thePlayer.posZ)) {
		            			    		rayid = 2;
		            			    		raytraceval = true;
		            			    	}
		            			    	if (!raytraceval && !hitchbot.utils.rayTrace.blocksInWay(posX-0.35, posY+1.5, posZ-0.35, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY+1.5, Minecraft.getMinecraft().thePlayer.posZ)) {
		            			    		rayid = 3;
		            			    		raytraceval = true;
		            			    	}
		            			    	if (!raytraceval && !hitchbot.utils.rayTrace.blocksInWay(posX-0.35, posY+1.5, posZ+0.35, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY+1.5, Minecraft.getMinecraft().thePlayer.posZ)) {
		            			    		rayid = 4;
		            			    		raytraceval = true;
		            			    	}
						    			if ((((currentyawe-yawe<= Hitchbot.aimbotAngle)&&(currentyawe-yawe>= -Hitchbot.aimbotAngle)) || ((currentyawe-yawe<= 360+Hitchbot.aimbotAngle)&&(currentyawe-yawe>= 360-Hitchbot.aimbotAngle)) || ((currentyawe-yawe>= -360-Hitchbot.aimbotAngle)&&(currentyawe-yawe<= -360+Hitchbot.aimbotAngle))) && raytraceval) {
						    				if (e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)<cls || (e.getName().equals(Hitchbot.getLastTarget()) && e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)< cls*1.8)) {

						    					cls = (float) e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ);
						    					ent = e;
		                						if (e.getName().equals(Hitchbot.getLastTarget())) {
		                							ent = e;
							    					cls = (float) e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ);
							    					if (cls < 6) {
							    						cls = 0.1F;
							    					}else {
							    						cls = (float) (cls / 1.7);
							    					}
		                						}
		                						Hitchbot.setLastTarget(e.getName());
						    				}
						    			}
						    			
		            			    }
            				}
            				if (ent != null) {
            					double mult = ent.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)/3.9;
            					try {
            					mult = Minecraft.getMinecraft().getCurrentServerData().pingToServer/25+ent.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)/3.9;
            					}catch(NullPointerException e){
            						e.printStackTrace();
            					}finally{
            						
            					}
            					float t = ent.fallDistance;
            					double posX = ent.posX + (ent.posX - ent.lastTickPosX)*mult;
            					double ySpeed = ent.posY - ent.lastTickPosY-0.04;
            					double posY = ent.posY;
            					double posZ = ent.posZ + (ent.posZ - ent.lastTickPosZ)*mult;
            					double xspeed = (ent.posX - ent.lastTickPosX);
            					double zspeed = (ent.posZ - ent.lastTickPosZ);
            					double xyspeed = Math.sqrt(zspeed*zspeed + xspeed + xspeed);
            					boolean grappling = xyspeed > 0.3333 ? true : false;
            					boolean hitground = false;
            					if (!ent.onGround) {
            						posX = ent.posX;
            						posZ = ent.posZ;
	            					for (int i = 0; i<Math.floor(mult); i++) {
	            						if (grappling && hitground) break;
	            						posX += xspeed;
	            						posZ += zspeed;
	            						if (!hitground) {
	            							xspeed = xspeed *0.98;
	            							zspeed = zspeed *0.98;
	            							posY = posY + ySpeed -0.08*(i+1);
	            						}
	            						if (rayTrace.inblock(posX, posY, posZ)) {
	            							hitground = true;
	            							posY=Math.floor(posY)+1;
	            						}
	            					}
            					}
            					posY +=0.05;
            					switch (rayid) {
            					case 1:
            						posX += 0.35;
            						posZ += 0.35;
            						break;
            					case 2:
            						posX += 0.35;
            						posZ -= 0.35;
            						break;
            					case 3:
            						posX -= 0.35;
            						posZ -= 0.35;
            						break;
            					case 4:
            						posX -= 0.35;
            						posZ += 0.35;
            						break;
            					}
				    			double Z = posZ - Minecraft.getMinecraft().thePlayer.posZ;
				    			double X = posX - Minecraft.getMinecraft().thePlayer.posX;
				    			double Y = Minecraft.getMinecraft().thePlayer.posY-posY;
				    			double yawe = Math.atan2(posZ-Minecraft.getMinecraft().thePlayer.posZ,posX- Minecraft.getMinecraft().thePlayer.posX);
				    			double pitchdife = Math.atan2(Math.sqrt(Z * Z + X * X), Y);
				    			this.yaw = (float) ((float)yawe*180/Math.PI-90);
				    			this.pitch = 90F-(float) ((float) pitchdife*180/Math.PI);
				                this.rotating = true;
				    			if (hitchbot.utils.rayTrace.blocksInWay(posX, posY+1.5, posZ, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY+1.5, Minecraft.getMinecraft().thePlayer.posZ)) {
				    				Hitchbot.setLastTarget("None");
				    			}
            				}
            			}else {
            				Hitchbot.setLastTarget("None");
            			}
            		}
            	}
            }
        }

        public void readPacketData(PacketBuffer buf) throws IOException
        {
            this.x = buf.readDouble();
            this.y = buf.readDouble();
            this.z = buf.readDouble();
            super.readPacketData(buf);
        }

        public void writePacketData(PacketBuffer buf) throws IOException
        {
            buf.writeDouble(this.x);
            buf.writeDouble(this.y);
            buf.writeDouble(this.z);
            super.writePacketData(buf);
        }
    }

    public static class C05PacketPlayerLook extends C03PacketPlayer
    {
        public C05PacketPlayerLook()
        {
            this.rotating = true;
        }

        public C05PacketPlayerLook(float playerYaw, float playerPitch, boolean isOnGround)
        {
            this.yaw = playerYaw;
            this.pitch = playerPitch;
            int rayid = 0;
            boolean raytraceval = false;
            for (Module m: Hitchbot.getModules()) {
            	if (m.getName().equalsIgnoreCase("WarzAim") || m.getName().equalsIgnoreCase("WarzAimNS")) {
            		if (m.isToggled() && Minecraft.getMinecraft().thePlayer.getHeldItem() != null && Hitchbot.guns.contains(Item.getIdFromItem(Minecraft.getMinecraft().thePlayer.getHeldItem().getItem()))) {
            			if (Mouse.isButtonDown(2) || m.getName().equalsIgnoreCase("WarzAimNS")) {
            				Entity ent = null;

            					float cls = Hitchbot.aimbotRange;
	            			    List<Entity> entities = Minecraft.getMinecraft().theWorld.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.fromBounds(Minecraft.getMinecraft().thePlayer.posX - 50, Minecraft.getMinecraft().thePlayer.posY - 50, Minecraft.getMinecraft().thePlayer.posZ - 50, Minecraft.getMinecraft().thePlayer.posX + 50, Minecraft.getMinecraft().thePlayer.posY + 50, Minecraft.getMinecraft().thePlayer.posZ + 50));
	            			    for (Entity e : entities) {
	            			    	if (e instanceof EntityPlayer && (e.getName() != Minecraft.getMinecraft().thePlayer.getName()) &&(!Hitchbot.isFriend(e.getName()))) {
						    			double currentrotation1 = (Minecraft.getMinecraft().thePlayer.getRotationYawHead()%360);
		            					double mult = e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)/3.9;
		            					try {
		            					mult = Minecraft.getMinecraft().getCurrentServerData().pingToServer/25+e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)/3.9;
		            					}catch(NullPointerException ex){
		            						ex.printStackTrace();
		            					}finally{
		            						
		            					}
		            					float t = e.fallDistance;
		            					double posX = e.posX + (e.posX - e.lastTickPosX)*mult;
		            					double ySpeed = e.posY - e.lastTickPosY-0.04;
		            					double posY = e.posY;
		            					double posZ = e.posZ + (e.posZ - e.lastTickPosZ)*mult;
		            					double xspeed = (e.posX - e.lastTickPosX);
		            					double zspeed = (e.posZ - e.lastTickPosZ);
		            					double xyspeed = Math.sqrt(zspeed*zspeed + xspeed*xspeed);
		            					boolean grappling = xyspeed > 0.3333 ? true : false;
		            					boolean hitground = false;
		            					if (!e.onGround) {
		            						posX = e.posX;
		            						posZ = e.posZ;
			            					for (int i = 0; i<Math.floor(mult); i++) {
			            						if (grappling && hitground) break;
			            						posX += xspeed;
			            						posZ += zspeed;
			            						if (!hitground) {
			            							xspeed = xspeed *0.98;
			            							zspeed = zspeed *0.98;
			            							posY = posY + ySpeed -0.08*(i+1);
			            						}
			            						if (rayTrace.inblock(posX, posY, posZ)) {
			            							hitground = true;
			            							posY=Math.floor(posY)+1;
			            						}
			            					}
		            					}
		            					posY +=0.05;

						    			double Z = posZ - Minecraft.getMinecraft().thePlayer.posZ;
						    			double X = posX - Minecraft.getMinecraft().thePlayer.posX;
						    			double Y = Minecraft.getMinecraft().thePlayer.posY-posY;
						    			double pitche = ((90-Minecraft.getMinecraft().thePlayer.rotationPitch) * Math.PI) / 180;
						    			double currentyawe  = currentrotation1+90;
						    			if (currentyawe < 0) {
						    				currentyawe += 360;
						    			}
						    			if (currentyawe>360) {
						    				currentyawe -=360;
						    			}
						    			double yawe = Math.atan2(e.posZ-Minecraft.getMinecraft().thePlayer.posZ,e.posX- Minecraft.getMinecraft().thePlayer.posX);
						    			double pitchdife = Math.atan2(Math.sqrt(Z * Z + X * X), Y);
						    			double tene = 1;
						    			yawe = yawe * 180 / Math.PI;
		            			    	if (yawe < 0) {
		            			    		yawe += 360;
		            			    	}
		            			    	if (!raytraceval && !hitchbot.utils.rayTrace.blocksInWay(posX, posY+1.5, posZ, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY+1.5, Minecraft.getMinecraft().thePlayer.posZ)) {
		            			    		rayid = 0;
		            			    		raytraceval = true;
		            			    	}
		            			    	if (!raytraceval && !hitchbot.utils.rayTrace.blocksInWay(posX+0.35, posY+1.5, posZ+0.35, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY+1.5, Minecraft.getMinecraft().thePlayer.posZ)) {
		            			    		rayid = 1;
		            			    		raytraceval = true;
		            			    	}
		            			    	if (!raytraceval && !hitchbot.utils.rayTrace.blocksInWay(posX+0.35, posY+1.5, posZ-0.35, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY+1.5, Minecraft.getMinecraft().thePlayer.posZ)) {
		            			    		rayid = 2;
		            			    		raytraceval = true;
		            			    	}
		            			    	if (!raytraceval && !hitchbot.utils.rayTrace.blocksInWay(posX-0.35, posY+1.5, posZ-0.35, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY+1.5, Minecraft.getMinecraft().thePlayer.posZ)) {
		            			    		rayid = 3;
		            			    		raytraceval = true;
		            			    	}
		            			    	if (!raytraceval && !hitchbot.utils.rayTrace.blocksInWay(posX-0.35, posY+1.5, posZ+0.35, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY+1.5, Minecraft.getMinecraft().thePlayer.posZ)) {
		            			    		rayid = 4;
		            			    		raytraceval = true;
		            			    	}
						    			if ((((currentyawe-yawe<= Hitchbot.aimbotAngle)&&(currentyawe-yawe>= -Hitchbot.aimbotAngle)) || ((currentyawe-yawe<= 360+Hitchbot.aimbotAngle)&&(currentyawe-yawe>= 360-Hitchbot.aimbotAngle)) || ((currentyawe-yawe>= -360-Hitchbot.aimbotAngle)&&(currentyawe-yawe<= -360+Hitchbot.aimbotAngle))) && raytraceval) {
						    				if (e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)<cls || (e.getName().equals(Hitchbot.getLastTarget()) && e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)< cls*1.8)) {

						    					cls = (float) e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ);
						    					ent = e;
		                						if (e.getName().equals(Hitchbot.getLastTarget())) {
		                							ent = e;
							    					cls = (float) e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ);
							    					if (cls < 6) {
							    						cls = 0.1F;
							    					}else {
							    						cls = (float) (cls / 1.7);
							    					}
		                						}
		                						Hitchbot.setLastTarget(e.getName());
						    				}
						    			}
						    			
		            			    }
            				}
            				if (ent != null) {
            					double mult = ent.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)/3.9;
            					try {
            					mult = Minecraft.getMinecraft().getCurrentServerData().pingToServer/25+ent.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)/3.9;
            					}catch(NullPointerException e){
            						e.printStackTrace();
            					}finally{
            						
            					}
            					float t = ent.fallDistance;
            					double posX = ent.posX + (ent.posX - ent.lastTickPosX)*mult;
            					double ySpeed = ent.posY - ent.lastTickPosY-0.04;
            					double posY = ent.posY;
            					double posZ = ent.posZ + (ent.posZ - ent.lastTickPosZ)*mult;
            					double xspeed = (ent.posX - ent.lastTickPosX);
            					double zspeed = (ent.posZ - ent.lastTickPosZ);
            					double xyspeed = Math.sqrt(zspeed*zspeed + xspeed + xspeed);
            					boolean grappling = xyspeed > 0.3333 ? true : false;
            					boolean hitground = false;
            					if (!ent.onGround) {
            						posX = ent.posX;
            						posZ = ent.posZ;
	            					for (int i = 0; i<Math.floor(mult); i++) {
	            						if (grappling && hitground) break;
	            						posX += xspeed;
	            						posZ += zspeed;
	            						if (!hitground) {
	            							xspeed = xspeed *0.98;
	            							zspeed = zspeed *0.98;
	            							posY = posY + ySpeed -0.08*(i+1);
	            						}
	            						if (rayTrace.inblock(posX, posY, posZ)) {
	            							hitground = true;
	            							posY=Math.floor(posY)+1;
	            						}
	            					}
            					}
            					posY +=0.05;
            					switch (rayid) {
            					case 1:
            						posX += 0.35;
            						posZ += 0.35;
            						break;
            					case 2:
            						posX += 0.35;
            						posZ -= 0.35;
            						break;
            					case 3:
            						posX -= 0.35;
            						posZ -= 0.35;
            						break;
            					case 4:
            						posX -= 0.35;
            						posZ += 0.35;
            						break;
            					}
				    			double Z = posZ - Minecraft.getMinecraft().thePlayer.posZ;
				    			double X = posX - Minecraft.getMinecraft().thePlayer.posX;
				    			double Y = Minecraft.getMinecraft().thePlayer.posY-posY;
				    			double yawe = Math.atan2(posZ-Minecraft.getMinecraft().thePlayer.posZ,posX- Minecraft.getMinecraft().thePlayer.posX);
				    			double pitchdife = Math.atan2(Math.sqrt(Z * Z + X * X), Y);
				    			this.yaw = (float) ((float)yawe*180/Math.PI-90);
				    			this.pitch = 90F-(float) ((float) pitchdife*180/Math.PI);
				                this.rotating = true;
				    			if (hitchbot.utils.rayTrace.blocksInWay(posX, posY+1.5, posZ, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY+1.5, Minecraft.getMinecraft().thePlayer.posZ)) {
				    				Hitchbot.setLastTarget("None");
				    			}
            				}
            			}else {
            				Hitchbot.setLastTarget("None");
            			}
            		}
            	}
            }
            this.onGround = isOnGround;
            this.rotating = true;
        }

        public void readPacketData(PacketBuffer buf) throws IOException
        {
            this.yaw = buf.readFloat();
            this.pitch = buf.readFloat();
            super.readPacketData(buf);
        }

        public void writePacketData(PacketBuffer buf) throws IOException
        {
            buf.writeFloat(this.yaw);
            buf.writeFloat(this.pitch);
            super.writePacketData(buf);
        }
    }

    public static class C06PacketPlayerPosLook extends C03PacketPlayer
    {
        public C06PacketPlayerPosLook()
        {
            this.moving = true;
            this.rotating = true;
        }

        public C06PacketPlayerPosLook(double playerX, double playerY, double playerZ, float playerYaw, float playerPitch, boolean playerIsOnGround)
        {
            this.x = playerX;
            this.y = playerY;
            this.z = playerZ;
            this.yaw = playerYaw;
            this.pitch = playerPitch;
            int rayid = 0;
            boolean raytraceval = false;
            for (Module m: Hitchbot.getModules()) {
            	if (m.getName().equalsIgnoreCase("WarzAim") || m.getName().equalsIgnoreCase("WarzAimNS")) {
            		if (m.isToggled() && Minecraft.getMinecraft().thePlayer.getHeldItem() != null && Hitchbot.guns.contains(Item.getIdFromItem(Minecraft.getMinecraft().thePlayer.getHeldItem().getItem()))) {
            			if (Mouse.isButtonDown(2) || m.getName().equalsIgnoreCase("WarzAimNS")) {
            				Entity ent = null;

            					float cls = Hitchbot.aimbotRange;
	            			    List<Entity> entities = Minecraft.getMinecraft().theWorld.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.fromBounds(Minecraft.getMinecraft().thePlayer.posX - 50, Minecraft.getMinecraft().thePlayer.posY - 50, Minecraft.getMinecraft().thePlayer.posZ - 50, Minecraft.getMinecraft().thePlayer.posX + 50, Minecraft.getMinecraft().thePlayer.posY + 50, Minecraft.getMinecraft().thePlayer.posZ + 50));
	            			    for (Entity e : entities) {
	            			    	if (e instanceof EntityPlayer && (e.getName() != Minecraft.getMinecraft().thePlayer.getName()) &&(!Hitchbot.isFriend(e.getName()))) {
						    			double currentrotation1 = (Minecraft.getMinecraft().thePlayer.getRotationYawHead()%360);
		            					double mult = e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)/3.9;
		            					try {
		            					mult = Minecraft.getMinecraft().getCurrentServerData().pingToServer/25+e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)/3.9;
		            					}catch(NullPointerException ex){
		            						ex.printStackTrace();
		            					}finally{
		            						
		            					}
		            					float t = e.fallDistance;
		            					double posX = e.posX + (e.posX - e.lastTickPosX)*mult;
		            					double ySpeed = e.posY - e.lastTickPosY-0.04;
		            					double posY = e.posY;
		            					double posZ = e.posZ + (e.posZ - e.lastTickPosZ)*mult;
		            					double xspeed = (e.posX - e.lastTickPosX);
		            					double zspeed = (e.posZ - e.lastTickPosZ);
		            					double xyspeed = Math.sqrt(zspeed*zspeed + xspeed*xspeed);
		            					boolean grappling = xyspeed > 0.3333 ? true : false;
		            					boolean hitground = false;
		            					if (!e.onGround) {
		            						posX = e.posX;
		            						posZ = e.posZ;
			            					for (int i = 0; i<Math.floor(mult); i++) {
			            						if (grappling && hitground) break;
			            						posX += xspeed;
			            						posZ += zspeed;
			            						if (!hitground) {
			            							xspeed = xspeed *0.98;
			            							zspeed = zspeed *0.98;
			            							posY = posY + ySpeed -0.08*(i+1);
			            						}
			            						if (rayTrace.inblock(posX, posY, posZ)) {
			            							hitground = true;
			            							posY=Math.floor(posY)+1;
			            						}
			            					}
		            					}
		            					posY +=0.05;

						    			double Z = posZ - Minecraft.getMinecraft().thePlayer.posZ;
						    			double X = posX - Minecraft.getMinecraft().thePlayer.posX;
						    			double Y = Minecraft.getMinecraft().thePlayer.posY-posY;
						    			double pitche = ((90-Minecraft.getMinecraft().thePlayer.rotationPitch) * Math.PI) / 180;
						    			double currentyawe  = currentrotation1+90;
						    			if (currentyawe < 0) {
						    				currentyawe += 360;
						    			}
						    			if (currentyawe>360) {
						    				currentyawe -=360;
						    			}
						    			double yawe = Math.atan2(e.posZ-Minecraft.getMinecraft().thePlayer.posZ,e.posX- Minecraft.getMinecraft().thePlayer.posX);
						    			double pitchdife = Math.atan2(Math.sqrt(Z * Z + X * X), Y);
						    			double tene = 1;
						    			yawe = yawe * 180 / Math.PI;
		            			    	if (yawe < 0) {
		            			    		yawe += 360;
		            			    	}
		            			    	if (!raytraceval && !hitchbot.utils.rayTrace.blocksInWay(posX, posY+1.5, posZ, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY+1.5, Minecraft.getMinecraft().thePlayer.posZ)) {
		            			    		rayid = 0;
		            			    		raytraceval = true;
		            			    	}
		            			    	if (!raytraceval && !hitchbot.utils.rayTrace.blocksInWay(posX+0.35, posY+1.5, posZ+0.35, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY+1.5, Minecraft.getMinecraft().thePlayer.posZ)) {
		            			    		rayid = 1;
		            			    		raytraceval = true;
		            			    	}
		            			    	if (!raytraceval && !hitchbot.utils.rayTrace.blocksInWay(posX+0.35, posY+1.5, posZ-0.35, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY+1.5, Minecraft.getMinecraft().thePlayer.posZ)) {
		            			    		rayid = 2;
		            			    		raytraceval = true;
		            			    	}
		            			    	if (!raytraceval && !hitchbot.utils.rayTrace.blocksInWay(posX-0.35, posY+1.5, posZ-0.35, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY+1.5, Minecraft.getMinecraft().thePlayer.posZ)) {
		            			    		rayid = 3;
		            			    		raytraceval = true;
		            			    	}
		            			    	if (!raytraceval && !hitchbot.utils.rayTrace.blocksInWay(posX-0.35, posY+1.5, posZ+0.35, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY+1.5, Minecraft.getMinecraft().thePlayer.posZ)) {
		            			    		rayid = 4;
		            			    		raytraceval = true;
		            			    	}
						    			if ((((currentyawe-yawe<= Hitchbot.aimbotAngle)&&(currentyawe-yawe>= -Hitchbot.aimbotAngle)) || ((currentyawe-yawe<= 360+Hitchbot.aimbotAngle)&&(currentyawe-yawe>= 360-Hitchbot.aimbotAngle)) || ((currentyawe-yawe>= -360-Hitchbot.aimbotAngle)&&(currentyawe-yawe<= -360+Hitchbot.aimbotAngle))) && raytraceval) {
						    				if (e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)<cls || (e.getName().equals(Hitchbot.getLastTarget()) && e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)< cls*1.8)) {

						    					cls = (float) e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ);
						    					ent = e;
		                						if (e.getName().equals(Hitchbot.getLastTarget())) {
		                							ent = e;
							    					cls = (float) e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ);
							    					if (cls < 6) {
							    						cls = 0.1F;
							    					}else {
							    						cls = (float) (cls / 1.7);
							    					}
		                						}
		                						Hitchbot.setLastTarget(e.getName());
						    				}
						    			}
						    			
		            			    }
            				}
            				if (ent != null) {
            					double mult = ent.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)/3.9;
            					try {
            					mult = Minecraft.getMinecraft().getCurrentServerData().pingToServer/25+ent.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)/3.9;
            					}catch(NullPointerException e){
            						e.printStackTrace();
            					}finally{
            						
            					}
            					float t = ent.fallDistance;
            					double posX = ent.posX + (ent.posX - ent.lastTickPosX)*mult;
            					double ySpeed = ent.posY - ent.lastTickPosY-0.04;
            					double posY = ent.posY;
            					double posZ = ent.posZ + (ent.posZ - ent.lastTickPosZ)*mult;
            					double xspeed = (ent.posX - ent.lastTickPosX);
            					double zspeed = (ent.posZ - ent.lastTickPosZ);
            					double xyspeed = Math.sqrt(zspeed*zspeed + xspeed + xspeed);
            					boolean grappling = xyspeed > 0.3333 ? true : false;
            					boolean hitground = false;
            					if (!ent.onGround) {
            						posX = ent.posX;
            						posZ = ent.posZ;
	            					for (int i = 0; i<Math.floor(mult); i++) {
	            						if (grappling && hitground) break;
	            						posX += xspeed;
	            						posZ += zspeed;
	            						if (!hitground) {
	            							xspeed = xspeed *0.98;
	            							zspeed = zspeed *0.98;
	            							posY = posY + ySpeed -0.08*(i+1);
	            						}
	            						if (rayTrace.inblock(posX, posY, posZ)) {
	            							hitground = true;
	            							posY=Math.floor(posY)+1;
	            						}
	            					}
            					}
            					posY +=0.05;
            					switch (rayid) {
            					case 1:
            						posX += 0.35;
            						posZ += 0.35;
            						break;
            					case 2:
            						posX += 0.35;
            						posZ -= 0.35;
            						break;
            					case 3:
            						posX -= 0.35;
            						posZ -= 0.35;
            						break;
            					case 4:
            						posX -= 0.35;
            						posZ += 0.35;
            						break;
            					}
				    			double Z = posZ - Minecraft.getMinecraft().thePlayer.posZ;
				    			double X = posX - Minecraft.getMinecraft().thePlayer.posX;
				    			double Y = Minecraft.getMinecraft().thePlayer.posY-posY;
				    			double yawe = Math.atan2(posZ-Minecraft.getMinecraft().thePlayer.posZ,posX- Minecraft.getMinecraft().thePlayer.posX);
				    			double pitchdife = Math.atan2(Math.sqrt(Z * Z + X * X), Y);
				    			this.yaw = (float) ((float)yawe*180/Math.PI-90);
				    			this.pitch = 90F-(float) ((float) pitchdife*180/Math.PI);
				                this.rotating = true;
				    			if (hitchbot.utils.rayTrace.blocksInWay(posX, posY+1.5, posZ, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY+1.5, Minecraft.getMinecraft().thePlayer.posZ)) {
				    				Hitchbot.setLastTarget("None");
				    			}
            				}
            			}else {
            				Hitchbot.setLastTarget("None");
            			}
            		}
            	}
            }
            this.onGround = playerIsOnGround;
            this.rotating = true;
            this.moving = true;
        }

        public void readPacketData(PacketBuffer buf) throws IOException
        {
            this.x = buf.readDouble();
            this.y = buf.readDouble();
            this.z = buf.readDouble();
            this.yaw = buf.readFloat();
            this.pitch = buf.readFloat();
            super.readPacketData(buf);
        }

        public void writePacketData(PacketBuffer buf) throws IOException
        {
            buf.writeDouble(this.x);
            buf.writeDouble(this.y);
            buf.writeDouble(this.z);
            buf.writeFloat(this.yaw);
            buf.writeFloat(this.pitch);
            super.writePacketData(buf);
        }
    }
}
