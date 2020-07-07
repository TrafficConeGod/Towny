package com.palmergames.bukkit.towny.newwar;

import com.palmergames.bukkit.towny.object.Nation;
import org.bukkit.Bukkit;

public abstract class CasusBelli {
	public String name = "";
	public void onPeaceAccepted(Nation victor, Nation loser) {}
	public void onAdd(Nation attacker, Nation defender, String[] params) {
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "wait what");
	}
}
