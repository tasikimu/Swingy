package com.asikimu.swingy.display;

import com.asikimu.swingy.model.CreateHero.Hero;
import com.asikimu.swingy.model.CreateHero.HeroStats;
import com.asikimu.swingy.model.Weapons.Weapon;

import java.util.Scanner;

public class Herostats extends HeroStats {

    public static int printDetails(long choice, String player, Hero hero) {
        System.out.println("Welcome to the game of heroes...SWINGY...\n\n" + player + ", Here is your stats: ");
        if (choice == 1) {
            System.out.println("You are a Fighter \n" +
                    "Your Level is: " + hero.getHeroStats().getLevel() + "\n" +
                    "Your Attack: " + hero.getHeroStats().getAttack() + "\n" +
                    "Your Defense: " + hero.getHeroStats().getDefense() + "\n" +
                    "Your Experience: " + hero.getHeroStats().getExperience() + "\n" +
                    "And Hit Points: " + hero.getHeroStats().getHitPoints() + "\n\n");
                    gameOption();
        }
        else if (choice == 2) {
            System.out.println("You are an Hero \n" +
                    "Your Level is: " + hero.getHeroStats().getLevel() + "\n" +
                    "Your Attack: " + hero.getHeroStats().getAttack() + "\n" +
                    "Your Defense: " + hero.getHeroStats().getDefense() + "\n" +
                    "Your Experience: " + hero.getHeroStats().getExperience() + "\n" +
                    "And Hit Points: " + hero.getHeroStats().getHitPoints() + "\n\n");
            gameOption();
        }

        int option = 0;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.matches("\\s*[1-2]\\s*")) {
                option = Integer.parseInt(line);
                break;
            } else
                System.out.println("Invalid input. please enter any of the options given below.");
        }
        return option;
    }
    public static void gameOption()
    {
        System.out.println( "What would you like to do??\n" +
                "1. Start game\n" +
                "2. Quit game\n");
    }

}
