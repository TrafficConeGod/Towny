package com.palmergames.bukkit.towny.newwar.casusbellis;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.AlreadyRegisteredException;
import com.palmergames.bukkit.towny.exceptions.EmptyNationException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.newwar.CasusBelli;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.Bukkit;

public class DemandTown extends CasusBelli {
	private Town town;
	public String getName() {
		return "demand_town";
	}
	public int getIndex() { return 1; }
	public float getInfamy() {
		return 11;
	}
	public void onAdd(Nation attacker, Nation defender) {
	}
	public void onPreDeclare(Nation attacker, Nation defender, String[] params) throws TownyException  {
		TownyUniverse townyUniverse = TownyUniverse.getInstance();
		if (params.length >= 1) {
			try {
				Town checkTown = townyUniverse.getDataSource().getTown(params[0]);
				if (checkTown.getNation().getName() == defender.getName() && !checkTown.isCapital()) {
					town = checkTown;
				} else {
					throw new TownyException(TownySettings.getLangString("msg_err_specify_town_name"));
				}
			} catch (NotRegisteredException e) {
				throw new TownyException(TownySettings.getLangString("msg_err_specify_town_name"));
			}
		} else {
			throw new TownyException(TownySettings.getLangString("msg_err_specify_town_name"));
		}
	}
	public void onDeclare(Nation attacker, Nation defender, String[] params) {
		
	}
	public void onPeaceAccepted(Nation victor, Nation loser) throws AlreadyRegisteredException, EmptyNationException, NotRegisteredException {
		TownyUniverse townyUniverse = TownyUniverse.getInstance();
		loser.removeTown(town);
		victor.addTown(town);
		townyUniverse.getDataSource().saveTown(town);
		townyUniverse.getDataSource().saveNation(victor);
		townyUniverse.getDataSource().saveNation(loser);
		townyUniverse.getDataSource().saveNationList();
	}
	public void loadSaveData(String[] tokens) throws NotRegisteredException {
		TownyUniverse townyUniverse = TownyUniverse.getInstance();
		if (tokens[0].equalsIgnoreCase("town")) {
			String townName = tokens[1];
			town = townyUniverse.getDataSource().getTown(townName);
		}
	}
	public String getSaveData() {
		if (town != null) {
			return "town=" + town.getName();
		} else {
			return "town=";
		}
	}
}
