package io.github.cottonmc.energy.impl;

import io.github.cottonmc.energy.api.ActionType;
import io.github.cottonmc.energy.api.EnergyComponent;

import javax.annotation.Nonnull;

public class EnergyHandler implements EnergyComponent {

	private int maxEnergy;
	private int currentEnergy = 0;

	public EnergyHandler(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	@Override
	public int getMaxEnergy() {
		return maxEnergy;
	}

	@Override
	public int getCurrentEnergy() {
		return currentEnergy;
	}

	@Override
	public boolean canInsertEnergy() {
		return true;
	}

	@Override
	public boolean canExtractEnergy() {
		return true;
	}

	@Nonnull
	@Override
	public int insertEnergy(int amount, ActionType actionType) {
		int insertRoom = maxEnergy - currentEnergy;
		int insertAmount = (amount <= insertRoom)? amount : insertRoom;
		if (actionType == ActionType.PERFORM) currentEnergy += insertAmount;
		return insertAmount;
	}

	@Nonnull
	@Override
	public int extractEnergy(int amount, ActionType actionType) {
		int extractAmount = (amount <= currentEnergy)? amount : currentEnergy;
		if (actionType == ActionType.PERFORM) currentEnergy -= extractAmount;
		return extractAmount;
	}

	void setCurrentEnergy(int amount) {
		if (amount <= maxEnergy) currentEnergy = amount;
		else currentEnergy = maxEnergy;
	}

	void setMaxEnergy(int amount) {
		maxEnergy = amount;
		if (currentEnergy > maxEnergy) currentEnergy = maxEnergy;
	}
}
