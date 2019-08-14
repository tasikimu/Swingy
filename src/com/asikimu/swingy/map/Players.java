package com.asikimu.swingy.map;

//import com.asikimu.swingy.model.Villians.Skeleton;
import com.asikimu.swingy.model.CreateHero.Asassian;
import com.asikimu.swingy.model.CreateHero.Hero;
import com.asikimu.swingy.model.CreateHero.HeroStats;
import com.asikimu.swingy.model.CreateHero.Ninja;
import com.asikimu.swingy.model.Villians.Monster;
import com.asikimu.swingy.model.Villians.Skeleton;
import com.asikimu.swingy.model.Weapons.Armor;
import com.asikimu.swingy.model.Weapons.Artifacts;
import com.asikimu.swingy.model.Weapons.Helm;
import com.asikimu.swingy.model.Weapons.Weapon;

import java.util.Random;

public class Players {
   public static Hero newPlayer(String hero, String player, HeroStats stats, Artifacts artifact){

            if (hero.equals("Ninja")){
                return new Ninja(player, stats, artifact);
            }
            else if (hero.equals("Assassian")){
                return new Asassian(player, stats, artifact);
            }
            else
                return null;
        }

        public static Monster newVillian(Hero hero){
            Random random = new Random();
            int villian = random.nextInt(2) + 1;
            String artifact = Artifacts.randomiseArtifact();
            int level = 0, attack = 0, defense = 0, hitpoints = 0, experience = 0;

            if (villian == 1){
                if (artifact.equals("WEAPON")){
                    Weapon weapon = new Weapon("Weapon");
                    level = hero.getHeroStats().getLevel();
                    attack = 100 + weapon.getAttack();
                    defense = 100;
                    hitpoints = 100;
                    experience = 0;
                    return new Monster(level, attack, defense, hitpoints, experience, weapon);
                }
                else if (artifact.equals("ARMOR")){
                    Armor armor = new Armor("Armor");
                    level = hero.getHeroStats().getLevel();
                    attack = 100;
                    defense = 100 + armor.getDefense();
                    hitpoints = 100;
                    experience = 0;
                    return new Monster(level, attack, defense, hitpoints, experience, armor);
                }
                else if (artifact.equals("HELM")){
                    Helm helm = new Helm("Helm");
                    level = hero.getHeroStats().getLevel();
                    attack = 100 + helm.getHitPoints();
                    defense = 100;
                    hitpoints = 100;
                    experience = 0;
                    return new Monster(level, attack, defense, hitpoints, experience, helm);
                }
            }
            else if (villian == 2){
                if (artifact.equals("WEAPON")){
                    Weapon weapon = new Weapon("Weapon");
                    level = hero.getHeroStats().getLevel();
                    attack = 100 + weapon.getAttack();
                    defense = 100;
                    hitpoints = 100;
                    experience = 0;
                    return new Skeleton(level, attack, defense, hitpoints, experience, weapon);
                }
                else if (artifact.equals("ARMOR")){
                    Armor armor = new Armor("Armor");
                    level = hero.getHeroStats().getLevel();
                    attack = 100;
                    defense = 100 + armor.getDefense();
                    hitpoints = 100;
                    experience = 0;
                    return new Skeleton(level, attack, defense, hitpoints, experience, armor);
                }
                else if (artifact.equals("HELM")){
                    Helm helm = new Helm("Helm");
                    level = hero.getHeroStats().getLevel();
                    attack = 100 + helm.getHitPoints();
                    defense = 100;
                    hitpoints = 100;
                    experience = 0;
                    return new Skeleton(level, attack, defense, hitpoints, experience, helm);
                }
            }
            return null;
        }
}
