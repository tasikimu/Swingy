package com.asikimu.swingy.model.Villians;

import com.asikimu.swingy.model.Weapons.Artifacts;

public class Skeleton  extends Villian {

	public Skeleton(int level, int attack, int defense, int hitpoints, int experience, Artifacts artifact){
		super(level, attack, defense, hitpoints, experience, artifact);
		int typeID = 1;
		super.setTypeID(typeID);
	}
}
