package com.palmergames.bukkit.towny.newwar.casusbellis;

import com.palmergames.bukkit.towny.newwar.CasusBelli;
import com.palmergames.bukkit.towny.object.Nation;
import org.bukkit.Bukkit;

public class Humiliate extends CasusBelli {
	public Humiliate() {
		name = "humiliate";
	}
	public void onPeaceAccepted(Nation victor, Nation loser) {
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "say lol get humiliated lmao");
	}
}
