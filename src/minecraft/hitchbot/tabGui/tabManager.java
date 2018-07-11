package hitchbot.tabGui;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import hitchbot.main.Hitchbot;
import hitchbot.mods.AutoSteak;
import hitchbot.mods.ChestEsp;
import hitchbot.mods.FullBright;
import hitchbot.mods.Module;
import hitchbot.mods.NameTags;
import hitchbot.mods.OptifineZoom;
import hitchbot.mods.PlayerEsp;
import hitchbot.mods.SimpleFlight;
import hitchbot.mods.ToggleSprint;
import hitchbot.mods.Tracers;
import hitchbot.mods.WarzAim;
import hitchbot.mods.WarzAimNS;

public class tabManager {
	private ArrayList<Tab>  tabs;
	private int currentTab;
	
	private HashMap<Integer, Module> renderMods, movementMods, combatMods;
	private int currentRenderMod, currentMovementMod, currentCombatMod;
	
	public tabManager() {
		tabs = new ArrayList();
		currentTab = 0;
		tabs.add(new tab1());
		tabs.add(new tab2());
		tabs.add(new tab3());
		renderMods = new HashMap();
		movementMods = new HashMap();
		combatMods = new HashMap();
		currentRenderMod = 0;
		currentMovementMod = 0;
		currentCombatMod = 0;
		
		renderMods.put(0, new PlayerEsp());
		renderMods.put(1, new OptifineZoom());
		renderMods.put(2, new FullBright());
		renderMods.put(3, new Tracers());
		renderMods.put(4, new ChestEsp());
		renderMods.put(5, new NameTags());
		
		movementMods.put(0, new ToggleSprint());
		movementMods.put(1, new SimpleFlight());
		
		combatMods.put(0, new WarzAim());
		combatMods.put(1, new AutoSteak());
		combatMods.put(2, new WarzAimNS());
	}
	
	public int getCurrentTab () {
		return currentTab;
	}
	
	public int getCurrentRenderMod() {
		return currentRenderMod;
	}
	
	public int getCurrentMovementMod() {
		return currentMovementMod;
	}
	
	public ArrayList<Tab> getTabs(){
		return tabs;
	}
	
	public int getCurrentCombatMod() {
		return currentCombatMod;
	}
	
	public void keyPressed(int k) {
		switch (k) {
		case 200:
			if (tabs.get(currentTab).isExpanded()) {
				switch (currentTab) {
				case 0:
					if (currentRenderMod != 0) {
						currentRenderMod--;
					}
					break;
				case 1:
					if (currentMovementMod != 0) {
						currentMovementMod--;
					}
					break;
				case 2:
					if (currentCombatMod != 0) {
						currentCombatMod--;
					}
					break;
				}
				
			}else {
				if(currentTab!=0) {
					currentTab--;
				}
			}
			break;
	case 208:
		if (tabs.get(currentTab).isExpanded()) {
			switch (currentTab) {
			case 0:
				if (currentRenderMod != renderMods.size()-1) {
					currentRenderMod++;
				}
				break;
			case 1:
				if (currentMovementMod != movementMods.size()-1) {
					currentMovementMod++;
				}
				break;
			case 2:
				if (currentCombatMod != combatMods.size()-1) {
					currentCombatMod++;
				}
				break;
			}
			
		}else {
			if (currentTab!=2) {
				currentTab++;
				}
		}
		break;
	
	case 205:
		if (tabs.get(currentTab).isExpanded()) {
			switch (currentTab) {
			case 0:
				toggleMod(renderMods.get(currentRenderMod).getName());
				break;
			case 1:
				toggleMod(movementMods.get(currentMovementMod).getName());
				break;
			case 2:
				toggleMod(combatMods.get(currentCombatMod).getName());
				break;
			}
		}else {
			tabs.get(currentTab).setExpanded(true);
		}
		break;
	
	case 203:
		if (tabs.get(currentTab).isExpanded()) {
			tabs.get(currentTab).setExpanded(false);
		}
		break;
	
	}
	}

	private void toggleMod(String n) {
		for (Module m: Hitchbot.getModules()) {
			if (m.getName().equalsIgnoreCase(n)) {
				m.toggle();
				break;
			}
		}
	}
}
