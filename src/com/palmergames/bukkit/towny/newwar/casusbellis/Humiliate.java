package com.palmergames.bukkit.towny.newwar.casusbellis;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.newwar.CasusBelli;
import com.palmergames.bukkit.towny.object.Nation;
import org.bukkit.Bukkit;

public class Humiliate extends CasusBelli {
	public String getName() {
		return "humiliate";
	}
	public int getIndex() { return 0; }
	public int getDaysForJustification() {
		return TownySettings.getHumiliateDays();//72;
	}
	public float getInfamy() {
		return TownySettings.getHumiliateDays();//3;
	}
	public float getWarscoreNeeded() { return 0.1f; }
}
