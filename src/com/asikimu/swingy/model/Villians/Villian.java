package com.asikimu.swingy.model.Villians;

import com.asikimu.swingy.model.Weapons.Artifacts;

public class Villian {
   private int xcoord;
    private int ycoord;
    private int level, attack, defense, hitpoints, experience, power;
    private long id;
    private long idCounter = 1;
    private int typeID;
    private Artifacts artifact;
    private static final String enemies[] = {"SKELETON", "MONSTER"};

    public Villian(){

    }

	public Villian(int level, int attack, int defense, int hitpoints, int experience, Artifacts artifact){
        this.level = level;
        this.attack = attack;
        this.defense = defense;
        this.hitpoints = hitpoints;
        this.experience = experience;
        this.id = nextId();
        this.power = attack + defense;
        this.artifact = artifact;
    }

    private long nextId(){
        idCounter += 1;
        return idCounter;
    }

 //getters
    public int getVXcoord(){
        return this.xcoord;
    }

    public int getVYcoord(){
        return this.ycoord;
    }

    public int getTypeID(){
		return this.typeID;
    }

	public int getLevel(){
		return this.level;
	}

	public int getHitPoints(){
		return this.hitpoints;
	}

	public int getPower(){
		return this.power;
    }

    public Artifacts getArtifact(){
        return artifact;
    }

	//setters

	public void setLevel(int level){
		this.level += level;
	}

    public void setTypeID(int id){
        this.typeID = id;
    }

	public void setHitPoints(int hitpoints){
        this.hitpoints += hitpoints;
        if (this.hitpoints <= 0)
            this.hitpoints = 0;
	}

    public void setVPos(int xcoord, int ycoord){
        this.xcoord = xcoord;
        this.ycoord = ycoord;
    }


}
