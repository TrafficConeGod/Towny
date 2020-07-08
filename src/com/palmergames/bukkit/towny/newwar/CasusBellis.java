package com.palmergames.bukkit.towny.newwar;

import com.palmergames.bukkit.towny.newwar.casusbellis.Conquer;
import com.palmergames.bukkit.towny.newwar.casusbellis.DemandTown;
import com.palmergames.bukkit.towny.newwar.casusbellis.Dismantle;
import com.palmergames.bukkit.towny.newwar.casusbellis.Humiliate;

public class CasusBellis {
	public static CasusBelli[] casusBellis = {
		new Humiliate(),
		new DemandTown(),
		new Conquer(),
		new Dismantle()
	};
}
