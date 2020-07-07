package com.palmergames.bukkit.towny.newwar.casusbellis;

import com.palmergames.bukkit.towny.newwar.CasusBelli;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Bukkit;

public class DemandTown extends CasusBelli {
	private Town town;
	public DemandTown() {
		name = "demand_town";
	}
	public void onPeaceAccepted(Nation victor, Nation loser) {
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "say lol get humiliated lmao");
	}
	public void onAdd(Nation attacker, Nation defender, String[] params) {
		
	}
}
