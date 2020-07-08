package com.palmergames.bukkit.towny.newwar;

import com.palmergames.bukkit.towny.exceptions.AlreadyRegisteredException;
import com.palmergames.bukkit.towny.exceptions.EmptyNationException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Nation;
import org.bukkit.Bukkit;

import java.util.UUID;

public abstract class CasusBelli implements Cloneable {
	public Nation attacker;
	public Nation defender;
	private UUID uuid;
	public String getName() { return "default"; }
	public int getIndex() { return -1; }
	public float getInfamy() {
		return 0;
	}
	public void onPeaceAccepted(Nation victor, Nation loser) throws AlreadyRegisteredException, EmptyNationException, NotRegisteredException {
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "say This message is never meant to be seen. If you see this please report this to the developers of this server. onPeaceAccepted");
	}
	public void onAdd() {
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "say This message is never meant to be seen. If you see this please report this to the developers of this server. onAdd");
	}
	public void onPreDeclare(String[] params) throws TownyException {
		
	}
	public void onDeclare(String[] params) {
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "say This message is never meant to be seen. If you see this please report this to the developers of this server. onAdd");
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
