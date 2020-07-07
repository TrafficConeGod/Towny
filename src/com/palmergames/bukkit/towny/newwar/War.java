package com.palmergames.bukkit.towny.newwar;

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
}
