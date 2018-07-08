package net.minecraft.network.play.client;

import java.io.IOException;
import java.util.List;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Hitchbot;
import hitchbot.mods.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.AxisAlignedBB;

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

        public C04PacketPlayerPosition(double posX, double posY, double posZ, boolean isOnGround)
        {
            this.x = posX;
            this.y = posY;
            this.z = posZ;
            this.onGround = isOnGround;
            this.moving = true;
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
            for (Module m: Hitchbot.getModules()) {
            	if (m.getName().equalsIgnoreCase("WarzAim")) {
            		if (m.isToggled()) {
            			if (Keyboard.isKeyDown(Keyboard.getKeyIndex("V"))) {
            				Entity ent = null;
            				if (!Hitchbot.getLastTarget().equalsIgnoreCase("None")) {
	            			    List<Entity> entities = Minecraft.getMinecraft().theWorld.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.fromBounds(Minecraft.getMinecraft().thePlayer.posX - 50, Minecraft.getMinecraft().thePlayer.posY - 50, Minecraft.getMinecraft().thePlayer.posZ - 50, Minecraft.getMinecraft().thePlayer.posX + 50, Minecraft.getMinecraft().thePlayer.posY + 50, Minecraft.getMinecraft().thePlayer.posZ + 50));
            					for (Entity e : entities) {
            						if (e.getName().equals(Hitchbot.getLastTarget())) {
            							ent = e;
            						}
            					}
            				}else {
            					float cls = 50;
	            			    List<Entity> entities = Minecraft.getMinecraft().theWorld.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.fromBounds(Minecraft.getMinecraft().thePlayer.posX - 50, Minecraft.getMinecraft().thePlayer.posY - 50, Minecraft.getMinecraft().thePlayer.posZ - 50, Minecraft.getMinecraft().thePlayer.posX + 50, Minecraft.getMinecraft().thePlayer.posY + 50, Minecraft.getMinecraft().thePlayer.posZ + 50));
	            			    for (Entity e : entities) {
	            			    	if (e instanceof EntityPlayer && (e.getName() != Minecraft.getMinecraft().thePlayer.getName()) &&(!Hitchbot.isFriend(e.getName()))) {
						    			double currentrotation1 = (Minecraft.getMinecraft().thePlayer.getRotationYawHead()%360);
						    			
						    			double Z = e.posZ - Minecraft.getMinecraft().thePlayer.posZ;
						    			double X = e.posX - Minecraft.getMinecraft().thePlayer.posX;
						    			double Y = Minecraft.getMinecraft().thePlayer.posY-e.posY;
						    			
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
						    			if ((((currentyawe-yawe<= 45)&&(currentyawe-yawe>= -45)) || ((currentyawe-yawe<= 405)&&(currentyawe-yawe>= 315)) || ((currentyawe-yawe>= -405)&&(currentyawe-yawe<= -315)))) {
						    				if (e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)<cls) {
						    					cls = (float) e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ);
						    					ent = e;
						    					Hitchbot.setLastTarget(e.getName());
						    				}
						    			}
		            			    }
	            			    }
            				}
            				if (ent != null) {
            					double mult = 130/30+ent.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)/4.5;
            					double posX = ent.posX + (ent.posX - ent.lastTickPosX)*mult;
            					double ySpeed = ent.posY - ent.lastTickPosY;
            					double posY = ent.posY;
            					if (ent.isAirBorne) {
            						posY-=posY + ySpeed + -0.04*mult*mult;
            					}
            					double posZ = ent.posZ + (ent.posZ - ent.lastTickPosZ)*mult;
				    			double Z = posZ - Minecraft.getMinecraft().thePlayer.posZ;
				    			double X = posX - Minecraft.getMinecraft().thePlayer.posX;
				    			double Y = Minecraft.getMinecraft().thePlayer.posY-posY;
				    			double yawe = Math.atan2(posZ-Minecraft.getMinecraft().thePlayer.posZ,posX- Minecraft.getMinecraft().thePlayer.posX);
				    			double pitchdife = Math.atan2(Math.sqrt(Z * Z + X * X), Y);
				    			this.yaw = (float) ((float)yawe*180/Math.PI-90);
				    			this.pitch = 90F-(float) ((float) pitchdife*180/Math.PI);
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
            for (Module m: Hitchbot.getModules()) {
            	if (m.getName().equalsIgnoreCase("WarzAim")) {
            		if (m.isToggled()) {
            			if (Keyboard.isKeyDown(Keyboard.getKeyIndex("V"))) {
            				Entity ent = null;
            				if (!Hitchbot.getLastTarget().equalsIgnoreCase("None")) {
	            			    List<Entity> entities = Minecraft.getMinecraft().theWorld.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.fromBounds(Minecraft.getMinecraft().thePlayer.posX - 50, Minecraft.getMinecraft().thePlayer.posY - 50, Minecraft.getMinecraft().thePlayer.posZ - 50, Minecraft.getMinecraft().thePlayer.posX + 50, Minecraft.getMinecraft().thePlayer.posY + 50, Minecraft.getMinecraft().thePlayer.posZ + 50));
            					for (Entity e : entities) {
            						if (e.getName().equals(Hitchbot.getLastTarget())) {
            							ent = e;
            						}
            					}
            				}else {
            					float cls = 50;
	            			    List<Entity> entities = Minecraft.getMinecraft().theWorld.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.fromBounds(Minecraft.getMinecraft().thePlayer.posX - 50, Minecraft.getMinecraft().thePlayer.posY - 50, Minecraft.getMinecraft().thePlayer.posZ - 50, Minecraft.getMinecraft().thePlayer.posX + 50, Minecraft.getMinecraft().thePlayer.posY + 50, Minecraft.getMinecraft().thePlayer.posZ + 50));
	            			    for (Entity e : entities) {
	            			    	if (e instanceof EntityPlayer && (e.getName() != Minecraft.getMinecraft().thePlayer.getName()) &&(!Hitchbot.isFriend(e.getName()))) {
						    			double currentrotation1 = (Minecraft.getMinecraft().thePlayer.getRotationYawHead()%360);
						    			
						    			double Z = e.posZ - Minecraft.getMinecraft().thePlayer.posZ;
						    			double X = e.posX - Minecraft.getMinecraft().thePlayer.posX;
						    			double Y = Minecraft.getMinecraft().thePlayer.posY-e.posY;
						    			
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
						    			if ((((currentyawe-yawe<= 45)&&(currentyawe-yawe>= -45)) || ((currentyawe-yawe<= 405)&&(currentyawe-yawe>= 315)) || ((currentyawe-yawe>= -405)&&(currentyawe-yawe<= -315)))) {
						    				if (e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)<cls) {
						    					cls = (float) e.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ);
						    					ent = e;
						    					Hitchbot.setLastTarget(e.getName());
						    				}
						    			}
		            			    }
	            			    }
            				}
            				if (ent != null) {
            					double mult = 130/30+ent.getDistance(Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ)/4.5;
            					double posX = ent.posX + (ent.posX - ent.lastTickPosX)*mult;
            					double ySpeed = ent.posY - ent.lastTickPosY;
            					double posY = ent.posY;
            					if (ent.isAirBorne) {
            						posY-=posY + ySpeed + -0.04*mult*mult;
            					}
            					double posZ = ent.posZ + (ent.posZ - ent.lastTickPosZ)*mult;
				    			double Z = posZ - Minecraft.getMinecraft().thePlayer.posZ;
				    			double X = posX - Minecraft.getMinecraft().thePlayer.posX;
				    			double Y = Minecraft.getMinecraft().thePlayer.posY-posY;
				    			double yawe = Math.atan2(posZ-Minecraft.getMinecraft().thePlayer.posZ,posX- Minecraft.getMinecraft().thePlayer.posX);
				    			double pitchdife = Math.atan2(Math.sqrt(Z * Z + X * X), Y);
				    			this.yaw = (float) ((float)yawe*180/Math.PI-90);
				    			this.pitch = 90F-(float) ((float) pitchdife*180/Math.PI);
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
