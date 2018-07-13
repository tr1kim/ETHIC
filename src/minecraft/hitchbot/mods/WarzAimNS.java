package hitchbot.mods;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Category;
import hitchbot.main.Hitchbot;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C03PacketPlayer.C05PacketPlayerLook;
import net.minecraft.network.play.client.C03PacketPlayer.C06PacketPlayerPosLook;
import paulscode.sound.libraries.LibraryLWJGLOpenAL.Exception;

public class WarzAimNS extends Module{

	public WarzAimNS() {
		super("WarzAimNS", Keyboard.KEY_V, Category.COMBAT);
	}
	
	@Override
	public void onEnable() {
		Hitchbot.aimbotYawTemp = Minecraft.getMinecraft().thePlayer.rotationYaw;
		super.onEnable();
	}
	
	@Override
	public void onUpdate() {
		if (Minecraft.getMinecraft().thePlayer.getHeldItem() != null &&  Minecraft.getMinecraft().thePlayer != null && this.isToggled() && Hitchbot.guns.contains(Item.getIdFromItem(Minecraft.getMinecraft().thePlayer.getHeldItem().getItem()))) {
			Hitchbot.aimbotYawTemp = Minecraft.getMinecraft().thePlayer.rotationYaw;
			Hitchbot.aimbotPitchTemp = Minecraft.getMinecraft().thePlayer.rotationPitch;
			Minecraft.getMinecraft().thePlayer.rotationYaw = Hitchbot.yawt;
			Minecraft.getMinecraft().thePlayer.rotationPitch = Hitchbot.pitcht;
		}
		super.onUpdate();
	}
	
	@Override
	public void onUpdatePOST() {
		if (Minecraft.getMinecraft().thePlayer.getHeldItem() != null &&  Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.ticksExisted > 20 && this.isToggled() && Hitchbot.guns.contains(Item.getIdFromItem(Minecraft.getMinecraft().thePlayer.getHeldItem().getItem()))) {
			Minecraft.getMinecraft().thePlayer.rotationYaw= Hitchbot.aimbotYawTemp;
			Minecraft.getMinecraft().thePlayer.rotationPitch = Hitchbot.aimbotPitchTemp;
		}
		super.onUpdatePOST();
	}

}
