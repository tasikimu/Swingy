package com.asikimu.swingy.map;

import com.asikimu.swingy.Files.ReadFile;
import com.asikimu.swingy.display.DisplayHero;
import com.asikimu.swingy.model.CreateHero.Hero;

import java.io.Console;
import java.util.Random;

public class Controller {
   public Controller() {
    }

    public static void run(Hero hero) {
        ConsoleMap printMap = new ConsoleMap(hero);

        printMap.printMap();
        DisplayHero.printDirections();
        Console console = System.console();
        while (true) {
            String line = console.readLine();


            if (line.matches("\\s*[1-5]\\s*")) {
                int direction = Integer.parseInt(line);

                if (direction == 1) {
                    printMap.updateHeroPosition(1, 0);
                    printMap.printMap();
                    DisplayHero.printDirections();
                } else if (direction == 2) {
                    printMap.updateHeroPosition(0, 1);
                    printMap.printMap();
                    DisplayHero.printDirections();
                } else if (direction == 3) {
                    printMap.updateHeroPosition(-1, 0);
                    printMap.printMap();
                    DisplayHero.printDirections();
                }
                else if (direction == 4) {
                    printMap.updateHeroPosition(0, -1);
                    printMap.printMap();
                    DisplayHero.printDirections();
                }
                    else if(direction == 5)
                    {
                        System.exit(0);
                    }
            }
            else {
                System.out.println("invalid input");

            }
        }
    }

    public static boolean inLuck() {
        Random random = new Random();
        int luck = random.nextInt(2) + 1;

        if (luck == 1)
            return true;
        return false;
    }

    public static int fight(Hero hero, com.asikimu.swingy.Villians.Villian enemy) {
        int fight = 0, won = 0, hit = 0;
        Random random = new Random();

        if (inLuck() == true || hero.getHeroStats().getPower() > enemy.getPower()) {
            fight = 1;
        }

        if (hero.getHeroStats().getHitPoints() > 0) {
            while (hero.getHeroStats().getHitPoints() > 0 && enemy.getHitPoints() > 0) {
                System.out.println("Hero hp: " + hero.getHeroStats().getHitPoints());
                System.out.println("Enemy hp: " + enemy.getHitPoints());
                if (fight == 0) {
                    hit = random.nextInt(30) + 1;
                    if (enemy.getHitPoints() > 0) {
                        hero.getHeroStats().setHitPoints(-hit);
                        ReadFile.updateFile(hero);
                        System.out.println("The Enemy attacked you and you lost " + hit + " hitpoints.");

                        if (hero.getHeroStats().getHitPoints() <= 0) {
                            won = 0;
                            break;
                        }
                        fight = 1;
                    }
                } else if (fight == 1) {
                    hit = random.nextInt(50) + 1;
                    if (hero.getHeroStats().getHitPoints() > 0) {
                        enemy.setHitPoints(-hit);
                        System.out.println("You attacked the Villian and gave " + hit + " damage.");
                        if (enemy.getHitPoints() <= 0) {
                            won = 1;
                            break;
                        }
                        fight = 0;
                    }
                }
            }
        }
        else
            System.out.println("You are too weak to fight, go regain your strength!!!\n\n" +
                    "Your HP is " + hero.getHeroStats().getHitPoints());
        return won;
    }
}
