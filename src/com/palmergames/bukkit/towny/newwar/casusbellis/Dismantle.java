package com.palmergames.bukkit.towny.newwar.casusbellis;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.AlreadyRegisteredException;
import com.palmergames.bukkit.towny.exceptions.EmptyNationException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.newwar.CasusBelli;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;

public class Dismantle extends CasusBelli {
	public String getName() {
		return "dismantle";
	}
	public int getIndex() { return 3; }
	public float getInfamy() {
		return 44;
	}
	public int getDaysForJustification() { return 216; }
	public void onPeaceAccepted(Nation victor, Nation loser) throws AlreadyRegisteredException, EmptyNationException, NotRegisteredException {
		TownyUniverse.getInstance().getDataSource().removeNation(loser);
		TownyMessaging.sendGlobalMessage(TownySettings.getDelNationMsg(loser));
	}
}
