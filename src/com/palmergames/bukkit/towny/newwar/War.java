package com.palmergames.bukkit.towny.newwar;

import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Nation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class War {
	private Nation attacker;
	private Nation defender;
	private List<CasusBelli> attackerCasusBellis;
	private List<CasusBelli> defenderCasusBellis;
	// NOT IMPLEMENTED YET
	private List<Nation> attackerAllies = new ArrayList<>();
	private List<Nation> defenderAllies = new ArrayList<>();
	private UUID uuid;
	
	public War(Nation attacker, Nation defender, List<CasusBelli> attackerCasusBellis, List<CasusBelli> defenderCasusBellis) {
		this.attacker = attacker;
		this.defender = defender;
		this.attackerCasusBellis = attackerCasusBellis;
		this.defenderCasusBellis = defenderCasusBellis;
	}
	
	public Nation getAttacker() {
		return attacker;
	}
	public Nation getDefender() {
		return defender;
	}
	public List<CasusBelli> getAttackerCasusBellis() {
		return attackerCasusBellis;
	}
	public List<CasusBelli> getDefenderCasusBellis() {
		return defenderCasusBellis;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	public Nation getAtWarWith(Nation nation) throws TownyException {
		if (isAnAttacker(nation)) {
			return defender;
		} else if (isADefender(nation)) {
			return attacker;
		}
		throw new TownyException(TownySettings.getLangString("msg_err_not_at_war_with"));
	}
	
	public boolean isAnAttacker(Nation nation) {
		if (attacker.getName() == nation.getName()) {
			return true;
		}
		for (Nation ally : attackerAllies) {
			if (ally.getName() == nation.getName()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isADefender(Nation nation) {
		if (defender.getName() == nation.getName()) {
			return true;
		}
		for (Nation ally : defenderAllies) {
			if (ally.getName() == nation.getName()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isWarLeader(Nation nation) {
		if (attacker.getName() == nation.getName()) {
			return true;
		}
		if (defender.getName() == nation.getName()) {
			return true;
		}
		return false;
	}
	
	public List<CasusBelli> getCasusBellisAgainst(Nation nation) throws TownyException {
		if (isAnAttacker(nation)) {
			return defenderCasusBellis;
		} else if (isADefender(nation)) {
			return attackerCasusBellis;
		}
		throw new TownyException(TownySettings.getLangString("msg_err_not_at_war_with"));
	}
	
	public void addAlly(Nation nation, Nation ally) {
		if (attacker.getName() == nation.getName()) {
			attackerAllies.add(ally);
		} else if (defender.getName() == nation.getName()) {
			defenderAllies.add(ally);
		}
	}
}
