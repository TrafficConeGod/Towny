package com.palmergames.bukkit.towny.newwar;

import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Nation;

import java.util.List;

public class War {
	private Nation attacker;
	private Nation defender;
	private List<CasusBelli> attackerCasusBellis;
	private List<CasusBelli> defenderCasusBellis;
	
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
	
	public Nation getAtWarWith(Nation nation) throws TownyException {
		if (attacker.getName() == nation.getName()) {
			return defender;
		} else if (defender.getName() == nation.getName()) {
			return attacker;
		}
		throw new TownyException("Not at war with nation");
	}
}
