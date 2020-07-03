package com.palmergames.bukkit.towny.object;

import com.palmergames.bukkit.towny.exceptions.AlreadyRegisteredException;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;

import java.util.Collection;

/**
 * Represents an object that contains, and manages towns.
 */
public interface TownOwner {
	
	/**
	 * Gets a collection of towns that belong to this object.
	 * 
	 * @return An unmodifiable collection of towns.
	 */
	Collection<Town> getTowns();

	/**
	 * Whether or not the given town is in this object.
	 * 
	 * @param town The town to search for.
	 * @return true if found, false otherwise.
	 */
	boolean hasTown(Town town);

	/**
	 * Adds a town to this object.
	 * 
	 * @param town The town to add.
	 * @throws AlreadyRegisteredException When the town already is in the object.   
	 */
	void addTown(Town town) throws AlreadyRegisteredException;

	/**
	 * Removes a town from this object.
	 * 
	 * @param town The town to remove.
	 * @throws NotRegisteredException When the town cannot be removed.
	 */
	void removeTown(Town town) throws NotRegisteredException;
}
