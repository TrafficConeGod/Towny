package com.palmergames.bukkit.towny.newwar;

import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Nation;

import java.util.*;

public class War {
	private Nation attacker;
	private Nation defender;
	private List<CasusBelli> attackerCasusBellis;
	private List<CasusBelli> defenderCasusBellis;
	private HashMap<UUID, Integer> attackerLives = new HashMap<>();
	private HashMap<UUID, Integer> defenderLives = new HashMap<>();
	private List<Nation> attackerAllies = new ArrayList<>();
	private List<Nation> defenderAllies = new ArrayList<>();
	private float attackerWarscore = 0;
	private float defenderWarscore = 0;
	private HashMap<Nation, Boolean> isAttackerMap = new HashMap<>();
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
		if (!isAttackerMap.containsKey(nation)) {
			if (attacker.getName() == nation.getName()) {
				isAttackerMap.put(attacker, true);
			}
			if (!isAttackerMap.containsKey(nation)) {
				for (Nation ally : attackerAllies) {
					if (ally.getName() == nation.getName()) {
						isAttackerMap.put(ally, true);
					}
				}
			}
		}
		if (isAttackerMap.containsKey(nation)) {
			return isAttackerMap.get(nation);
		} else {
			return false;
		}
	}
	
	public boolean isADefender(Nation nation) {
		if (!isAttackerMap.containsKey(nation)) {
			if (defender.getName() == nation.getName()) {
				isAttackerMap.put(defender, false);
			}
			if (!isAttackerMap.containsKey(nation)) {
				for (Nation ally : defenderAllies) {
					if (ally.getName() == nation.getName()) {
						isAttackerMap.put(ally, false);
					}
				}
			}
		}
		if (isAttackerMap.containsKey(nation)) {
			return !isAttackerMap.get(nation);
		} else {
			return false;
		}
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

	public void setAttackerLives(HashMap<UUID, Integer> attackerLives) {
		this.attackerLives = attackerLives;
	}

	public void setDefenderLives(HashMap<UUID, Integer> defenderLives) {
		this.defenderLives = defenderLives;
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

	public HashMap<UUID, Integer> getAttackerLives() {
		return attackerLives;
	}

	public void setAttackerLife(UUID uuid, int lives) {
		attackerLives.put(uuid, lives);
	}
	
	// UNUSED
//	public void removeAttackerCasualtyUuid(UUID uuid) {
//		attackerCasualtyUuids.remove(uuid);
//	}

	public HashMap<UUID, Integer> getDefenderLives() {
		return defenderLives;
	}
	
	public void setDefenderLife(UUID uuid, int lives) {
		defenderLives.put(uuid, lives);
	}
	
	// UNUSED
//	public void removeDefenderCasualtyUuid(UUID uuid) {
//		defenderCasualtyUuids.remove(uuid);
//	}
	
	public boolean wasKilledInCombat(Nation nation, UUID uuid) {
		if (isAnAttacker(nation)) {
			if (!attackerLives.containsKey(uuid)) {
				return false;
			}
			int lives = attackerLives.get(uuid);
			if (lives <= 0) {
				return true;
			}
		} else if (isADefender(nation)) {
			if (!defenderLives.containsKey(uuid)) {
				return false;
			}
			int lives = defenderLives.get(uuid);
			if (lives <= 0) {
				return true;
			}
		}
		return false;
	}
	
	public int getCasualtyCount(Nation nation) {
		int count = 0;
		if (isAnAttacker(nation)) {
			for (Map.Entry<UUID, Integer> entry : attackerLives.entrySet()) {
				int lives = entry.getValue();
				if (lives <= 0) {
					count++;
				}
			}
		} else if (isADefender(nation)) {
			for (Map.Entry<UUID, Integer> entry : defenderLives.entrySet()) {
				int lives = entry.getValue();
				if (lives <= 0) {
					count++;
				}
			}
		}
		return count;
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
	
	public float getAttackerPopulation() {
		int population = attacker.getNumResidents();
		for (Nation ally : attackerAllies) {
			population += ally.getNumResidents();
		}
		return (float)population;
	}

	public float getDefenderPopulation() {
		int population = defender.getNumResidents();
		for (Nation ally : defenderAllies) {
			population += ally.getNumResidents();
		}
		return (float)population;
	}
	
	// UNUSED
//	public float getAttackerWarscore() {
//		int population = defender.getNumResidents();
//		for (Nation ally : defenderAllies) {
//			population += ally.getNumResidents();
//		}
//		float warscore = (float)defenderLives.size() / (float)population;
//		return warscore;
//	}
//
//	public float getDefenderWarscore() {
//		int population = attacker.getNumResidents();
//		for (Nation ally : attackerAllies) {
//			population += ally.getNumResidents();
//		}
//		float warscore = (float)attackerLives.size() / (float)population;
//		return warscore;
//	}
//	
	
	public void addAttackerCasusBelli(CasusBelli casusBelli) {
		attackerCasusBellis.add(casusBelli);
	}
	public void removeAttackerCasusBelli(CasusBelli casusBelli) {
		attackerCasusBellis.remove(casusBelli);
	}

	public void addDefenderCasusBelli(CasusBelli casusBelli) {
		defenderCasusBellis.add(casusBelli);
	}
	public void removeDefenderCasusBelli(CasusBelli casusBelli) {
		defenderCasusBellis.remove(casusBelli);
	}

	public float getAttackerWarscore() {
		return attackerWarscore;
	}

	public void setAttackerWarscore(float attackerWarscore) {
		if (attackerWarscore >= 1f) {
			attackerWarscore = 1f;
		}
		if (attackerWarscore <= -1f) {
			attackerWarscore = -1f;
		}
		this.attackerWarscore = attackerWarscore;
	}

	public float getDefenderWarscore() {
		return defenderWarscore;
	}

	public void setDefenderWarscore(float defenderWarscore) {
		if (defenderWarscore >= 1f) {
			defenderWarscore = 1f;
		}
		if (defenderWarscore <= -1f) {
			defenderWarscore = -1f;
		}
		this.defenderWarscore = defenderWarscore;
	}
	
	public void addKillToWarscore(Nation victor, Nation loser) {
		if (isAnAttacker(victor)) {
			float victorWarscore = attackerWarscore;
			float loserWarscore = defenderWarscore;
			float loserPopulation = getDefenderPopulation();
			float warscoreChange = TownySettings.getKillWarscoreChange() / loserPopulation;
			setAttackerWarscore(victorWarscore + warscoreChange);
			setDefenderWarscore(loserWarscore - warscoreChange);
		} else if (isADefender(victor)) {
			float victorWarscore = defenderWarscore;
			float loserWarscore = attackerWarscore;
			float loserPopulation = getAttackerPopulation();
			float warscoreChange = TownySettings.getKillWarscoreChange() / loserPopulation;
			setDefenderWarscore(victorWarscore + warscoreChange);
			setAttackerWarscore(loserWarscore - warscoreChange);
		}
	}
}
