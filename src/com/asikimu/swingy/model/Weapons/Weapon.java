package com.asikimu.swingy.model.Weapons;

public class Weapon extends Artifacts{
	private int attack = 75;

	public Weapon(String type){
		super(type);
	}

	public int getAttack(){
		return this.attack;
	}
}
