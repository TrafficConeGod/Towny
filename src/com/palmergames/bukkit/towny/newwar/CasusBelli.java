package com.palmergames.bukkit.towny.newwar;

import com.palmergames.bukkit.towny.exceptions.AlreadyRegisteredException;
import com.palmergames.bukkit.towny.exceptions.EmptyNationException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Nation;
import org.bukkit.Bukkit;

public abstract class CasusBelli implements Cloneable {
	private Nation attacker;
	private Nation defender;
	public String getName() { return "default"; }
	public void onPeaceAccepted(Nation victor, Nation loser) throws AlreadyRegisteredException, EmptyNationException, NotRegisteredException {
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "say This message is never meant to be seen. If you see this please report this to the developers of this server. onPeaceAccepted");
	}
	public void onAdd(Nation attacker, Nation defender) {
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "say This message is never meant to be seen. If you see this please report this to the developers of this server. onAdd");
	}
	public void onPreDeclare(Nation attacker, Nation defender, String[] params) throws TownyException {
		
	}
	public void onDeclare(Nation attacker, Nation defender, String[] params) {
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "say This message is never meant to be seen. If you see this please report this to the developers of this server. onAdd");
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
