package com.palmergames.bukkit.towny.newwar;

import com.palmergames.bukkit.towny.object.Nation;

public class Justification {
	private int index = -1;
	private int daysLeft = -1;
	private Nation nation;

	public Justification(int index, int daysLeft, Nation nation) {
		this.index = index;
		this.daysLeft = daysLeft;
		this.nation = nation;
	}

	public int getIndex() {
		return index;
	}

	public Nation getNation() {
		return nation;
	}

	public int getDaysLeft() {
		return daysLeft;
	}

	public void setDaysLeft(int daysLeft) {
		this.daysLeft = daysLeft;
	}
}
