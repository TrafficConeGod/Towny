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
	private List<UUID> attackerCasualtyUuids = new ArrayList<>();
	private List<UUID> defenderCasualtyUuids = new ArrayList<>();
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
	
	public void addAlly(Nation leader, Nation ally) {
		if (attacker.getName() == leader.getName()) {
			attackerAllies.add(ally);
		} else if (defender.getName() == leader.getName()) {
			defenderAllies.add(ally);
		}
	}
	
	public boolean isInWar(Nation nation) {
		return isAnAttacker(nation) || isADefender(nation);
	}

	public void setAttackerCasualtyUuids(List<UUID> attackerCasualtyUuids) {
		this.attackerCasualtyUuids = attackerCasualtyUuids;
	}

	public void setDefenderCasualtyUuids(List<UUID> defenderCasualtyUuids) {
		this.defenderCasualtyUuids = defenderCasualtyUuids;
	}

	public void setAttackerAllies(List<Nation> attackerAllies) {
		this.attackerAllies = attackerAllies;
	}

	public void setDefenderAllies(List<Nation> defenderAllies) {
		this.defenderAllies = defenderAllies;
	}

	public List<Nation> getAttackerAllies() {
		return attackerAllies;
	}

	public List<Nation> getDefenderAllies() {
		return defenderAllies;
	}

	public List<UUID> getAttackerCasualtyUuids() {
		return attackerCasualtyUuids;
	}

	public void addAttackerCasualtyUuid(UUID uuid) {
		attackerCasualtyUuids.add(uuid);
	}

	public void removeAttackerCasualtyUuid(UUID uuid) {
		attackerCasualtyUuids.remove(uuid);
	}

	public List<UUID> getDefenderCasualtyUuids() {
		return defenderCasualtyUuids;
	}

	public void addDefenderCasualtyUuid(UUID uuid) {
		defenderCasualtyUuids.add(uuid);
	}

	public void removeDefenderCasualtyUuid(UUID uuid) {
		defenderCasualtyUuids.remove(uuid);
	}
	
	public void addWarToCombatants() {
		if (!attacker.getWars().contains(this)) {
			attacker.addWar(this);
		}
		if (!defender.getWars().contains(this)) {
			defender.addWar(this);
		}
		for (Nation nation : attackerAllies) {
			if (!nation.getWars().contains(this)) {
				nation.addWar(this);
			}
		}
		for (Nation nation : defenderAllies) {
			if (!nation.getWars().contains(this)) {
				nation.addWar(this);
			}
		}
	}
	
	public void removeWarFromCombatants() {
		attacker.removeWar(this);
		defender.removeWar(this);
		for (Nation nation : attackerAllies) {
			nation.removeWar(this);
		}
		for (Nation nation : defenderAllies) {
			nation.removeWar(this);
		}
	}
	
	public void removeCombatant(Nation nation) {
		attackerAllies.remove(nation);
		defenderAllies.remove(nation);
		nation.removeWar(this);
	}
	
	public float getAttackerWarscore() {
		int population = attacker.getNumResidents();
		for (Nation ally : attackerAllies) {
			population += ally.getNumResidents();
		}
		float warscore = defenderCasualtyUuids.size() / population;
		return warscore;
	}

	public float getDefenderWarscore() {
		int population = defender.getNumResidents();
		for (Nation ally : defenderAllies) {
			population += ally.getNumResidents();
		}
		float warscore = attackerCasualtyUuids.size() / population;
		return warscore;
	}
	
	public void addAttackerCasusBelli(CasusBelli casusBelli) {
		attackerCasusBellis.add(casusBelli);
	}

	public void addDefenderCasusBelli(CasusBelli casusBelli) {
		defenderCasusBellis.add(casusBelli);
	}
}
