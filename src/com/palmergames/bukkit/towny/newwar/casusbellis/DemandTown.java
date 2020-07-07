package com.palmergames.bukkit.towny.newwar.casusbellis;

import com.palmergames.bukkit.towny.newwar.CasusBelli;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Bukkit;

public class DemandTown extends CasusBelli {
	private Town town;
	public String getName() {
		return "humiliate";
	}
	public void onAdd(Nation attacker, Nation defender) {
	}
	public void onDeclare(Nation attacker, Nation defender, String[] params) {
		
	}
	public void onPeaceAccepted(Nation victor, Nation loser) {
	}
}
