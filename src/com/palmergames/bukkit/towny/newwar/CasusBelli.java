package com.palmergames.bukkit.towny.newwar;

import com.palmergames.bukkit.towny.exceptions.AlreadyRegisteredException;
import com.palmergames.bukkit.towny.exceptions.EmptyNationException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Nation;
import org.bukkit.Bukkit;

import java.util.UUID;

public abstract class CasusBelli implements Cloneable {
	protected Nation attacker;
	protected Nation defender;
	private UUID uuid;
	public String getName() { return "default"; }
	public int getIndex() { return -1; }
	public float getInfamy() {
		return 0;
	}
	public int getDaysForJustification() { return 1; }
	public String getDisplaySuffix() { return ""; }
	public float getWarscoreNeeded() { return 2; }
	public void onPeaceAccepted(Nation victor, Nation loser) throws AlreadyRegisteredException, EmptyNationException, NotRegisteredException {
	}
	public void onAdd() {
	}
	public void onPreDeclare(String[] params) throws TownyException {
	}
	public void onDeclare(String[] params) {
	}
	public boolean canUse() {
		return true;
	}
	public Nation getAttacker() {
		return attacker;
	}
	public Nation getDefender() {
		return defender;
	}
	public void setAttacker(Nation attacker) {
		this.attacker = attacker;
	}
	public void setDefender(Nation defender) {
		this.defender = defender;
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public String getSaveData() {
		return "";
	}
	public void loadSaveData(String[] tokens) throws NotRegisteredException {
		
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
