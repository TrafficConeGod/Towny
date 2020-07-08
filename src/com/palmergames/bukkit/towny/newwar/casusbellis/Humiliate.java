package com.palmergames.bukkit.towny.newwar.casusbellis;

import com.palmergames.bukkit.towny.newwar.CasusBelli;
import com.palmergames.bukkit.towny.object.Nation;
import org.bukkit.Bukkit;

public class Humiliate extends CasusBelli {
	public String getName() {
		return "humiliate";
	}
	public int getIndex() { return 0; }
	public float getInfamy() {
		return 3;
	}
	public void onAdd(Nation attacker, Nation defender) {
	}
	public void onDeclare(Nation attacker, Nation defender, String[] params) {
	}
	public void onPeaceAccepted(Nation victor, Nation loser) {
	}
}
