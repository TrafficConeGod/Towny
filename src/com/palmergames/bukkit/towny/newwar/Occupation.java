package com.palmergames.bukkit.towny.newwar;

import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Nation;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Occupation {
	private Town townOccupied;
	private float progress = 0f;
	private List<Player> playersOccupying = new ArrayList<>();
	private List<Player> playersBlocking = new ArrayList<>();
	private float townOccupationPercentagePerSecond = TownySettings.getTownOccupationPercentagePerSecond();
	private TownyUniverse townyUniverse = TownyUniverse.getInstance();

	public Occupation(Town townOccupied) {
		this.townOccupied = townOccupied;
	}

	public float getProgress() {
		return progress;
	}
	
	public Nation getNationOccupying(int index) throws NotRegisteredException {
		Player player = playersOccupying.get(index);
		Resident resident = townyUniverse.getDataSource().getResident(player.getName());
		if (resident.hasNation()) {
			return resident.getTown().getNation();
		} else {
			return getNationOccupying(index + 1);
		}
	}
	
	public Nation getNationOccupying() throws NotRegisteredException {
		return getNationOccupying(0);
	}
	

	public void setProgress(float progress) {
		if (progress >= 1f) {
			progress = 1f;
			completeOccupation();
		}
		this.progress = progress;
	}
	
	public void completeOccupation() {
		if (townOccupied.hasNation()) {
			try {
				Nation nationOccupying = getNationOccupying();
				if (nationOccupying == townOccupied.getNation()) {
					Nation loser = townOccupied.getOccupiedBy();
					War war = nationOccupying.getWar(loser);
					war.addTownOccupyToWarscore(nationOccupying, townOccupied);
					townOccupied.setOccupiedBy(null);
				} else {
					Nation loser = townOccupied.getNation();
					War war = nationOccupying.getWar(loser);
					war.addTownOccupyToWarscore(nationOccupying, townOccupied);
					townOccupied.setOccupiedBy(nationOccupying);
				}
			} catch (NotRegisteredException e) {
				e.printStackTrace();
			} catch (TownyException e) {
				e.printStackTrace();
			}
		}
		
		quitOccupation();
	}
	
	public void quitOccupation() {
		townOccupied.setOccupation(null);
		townyUniverse.getDataSource().saveTown(townOccupied);
	}

	public Town getTownOccupied() {
		return townOccupied;
	}
	
	public void secondTick() {
		if (!townOccupied.hasNation()) {
			quitOccupation();
			return;
		}
		if (playersBlocking.size() <= 0) {
			setProgress(progress + townOccupationPercentagePerSecond * playersOccupying.size());
		}
		for (Player player : playersOccupying) {
			player.sendMessage((progress * 100) + "% occupied");
		}
	}

	public List<Player> getPlayersOccupying() {
		return playersOccupying;
	}

	public void addPlayerOccupying(Player player) {
		playersOccupying.add(player);
	}
	
	public void removePlayerOccupying(Player player) {
		playersOccupying.remove(player);
		if (playersOccupying.size() <= 0) {
			quitOccupation();
		}
	}

	public List<Player> getPlayersBlocking() {
		return playersBlocking;
	}
	
	public void addPlayerBlocking(Player player) {
		playersBlocking.add(player);
	}

	public void removePlayerBlocking(Player player) {
		playersBlocking.remove(player);
	}
}