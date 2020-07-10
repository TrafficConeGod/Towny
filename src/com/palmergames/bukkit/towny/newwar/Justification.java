package com.palmergames.bukkit.towny.newwar;

public class Justification {
	private int index = -1;
	private int daysLeft = -1;

	public Justification(int index, int daysLeft) {
		this.index = index;
		this.daysLeft = daysLeft;
	}

	public int getIndex() {
		return index;
	}

	public int getDaysLeft() {
		return daysLeft;
	}

	public void setDaysLeft(int daysLeft) {
		this.daysLeft = daysLeft;
	}
}
