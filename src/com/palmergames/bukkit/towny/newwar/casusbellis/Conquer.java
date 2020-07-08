package com.palmergames.bukkit.towny.newwar.casusbellis;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.AlreadyRegisteredException;
import com.palmergames.bukkit.towny.exceptions.EmptyNationException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.newwar.CasusBelli;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;

public class Conquer extends CasusBelli {
	public String getName() {
		return "conquer";
	}
	public int getIndex() { return 2; }
	public float getInfamy() {
		return 22;
	}
	public boolean canUse() {
		return defender.getTowns().size() == 1;
	}
	public void onPeaceAccepted(Nation victor, Nation loser) throws AlreadyRegisteredException, EmptyNationException, NotRegisteredException {
		TownyUniverse townyUniverse = TownyUniverse.getInstance();
		for (Town town : loser.getTowns()) {
			loser.removeTown(town);
			victor.addTown(town);
			townyUniverse.getDataSource().saveTown(town);
		}
		townyUniverse.getDataSource().saveNation(victor);
		townyUniverse.getDataSource().removeNation(loser);
		townyUniverse.getDataSource().saveNationList();
	}
}
