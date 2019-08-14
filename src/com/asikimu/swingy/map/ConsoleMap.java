package com.asikimu.swingy.map;

import com.asikimu.swingy.Files.ReadFile;
import com.asikimu.swingy.model.Villians.Villian;
import com.asikimu.swingy.model.CreateHero.Hero;
//import com.asikimu.swingy.model.Villians.*;
import com.asikimu.swingy.model.Weapons.Armor;
import com.asikimu.swingy.model.Weapons.Helm;
import com.asikimu.swingy.model.Weapons.Weapon;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ConsoleMap {
   private static ArrayList<Villian> enemyArray = new ArrayList<>();
    private static ArrayList<Villian> tempArray = new ArrayList<>();
    private static int mapy;
    private static int mapx;
    private static int size;
    private static int[][] map;
    private static Hero hero;
    private int villians;
    private int xcoord;
    private int ycoord;
    private int level;
    private Villian villian = new Villian();
    private boolean set = false;

    public ConsoleMap(Hero hero) {
        this.hero = hero;
    }

    public static void setMapSize() {
        size = (hero.getHeroStats().getLevel() - 1) * 5 + 10 - (hero.getHeroStats().getLevel() % 2);
        mapx = size;
        mapy = size;
        map = new int[size][size];
    }

    public void setVillians() {
        switch (this.villians = hero.getHeroStats().getLevel() * 8) {
        }
    }

    public void setHeroPosition() {
        ;
        int x = 0, y = 0;

        if ((size % 2) == 1) {
            x = (int) (size / 2);
            y = (int) (size / 2);
        } else if ((size % 2) == 0) {
            x = (size / 2);
            y = (size / 2);
        }
        this.xcoord = x;
        this.ycoord = y;
    }

    public void updateHeroPosition(int xpos, int ypos) {
        int prevx = this.xcoord;
        int prevy = this.xcoord;
        this.xcoord += xpos;
        if (this.xcoord < 0) {
            this.xcoord = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
            printMap();
        } else if (this.xcoord >= this.size) {
            this.xcoord = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
            printMap();
        } else {
            printMap();
        }

        this.ycoord += ypos;
        if (this.ycoord < 0) {
            this.ycoord = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
            printMap();
        } else if (this.ycoord >= this.size) {
            this.ycoord = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
            printMap();
        } else {
            printMap();
        }
    }

    public void printMap() {

        if (set == false) {
            setMapSize();
            setHeroPosition();
            setVillians();

            if (tempArray.isEmpty())
                createVillian();
            else
                enemyArray.addAll(tempArray);
            set = true;
        }

        /* initialize map array to zeros */

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                map[y][x] = 0;
            }
        }

        /* initialization of enemy */

        for (Villian villian : enemyArray) {
            map[villian.getVYcoord()][villian.getVXcoord()] = villian.getTypeID();
        }
        /* initialization of hero */

        map[this.ycoord][this.xcoord] = 4;

        /* check if hero  has crossed paths with enemy */

        for (Villian villian : enemyArray) {
            boolean meetEnemy = crossedEnemy(this.ycoord, this.xcoord, villian.getVYcoord(), villian.getVXcoord());
            if (meetEnemy == true)
                break;
        }

        System.out.println("Level: " + hero.getHeroStats().getLevel() + " | " +
                "Attack: " + hero.getHeroStats().getAttack() + " | " +
                "Defence: " + hero.getHeroStats().getDefense() + " | " +
                "Hitpoints: " + hero.getHeroStats().getHitPoints() + " | " +
                "Experience: " + hero.getHeroStats().getExperience() + "\n\n");

        for (int y = 0; y < mapy; y++) {
            for (int x = 0; x < mapx; x++) {
                switch (map[y][x]) {
                    case 0:
                        System.out.print("|   |");
                        break;
                    case 1:
                        System.out.print("| m |");
                        break;
                    case 2:
                        System.out.print("| s |");
                        break;
                    default:
                        System.out.print("| H |");
                        break;
                }
            }
            System.out.println();
        }
    }

    public static void registerVillian(Villian enemy) {
        if (enemyArray.contains(enemy))
            return;
        enemyArray.add(enemy);
    }

    public static void removeVillian(Villian enemy) {
        if (enemyArray.contains(enemy))
            enemyArray.remove(enemy);
    }

    public void createVillian() {
        for (int i = 0; i < this.villians; i++) {
            Random random = new Random();
            int eposx = random.nextInt(size);
            int eposy = random.nextInt(size);
            while (eposy == this.ycoord || eposx == this.xcoord) {
                eposx = random.nextInt(size);
                eposy = random.nextInt(size);
            }
            villian = Players.newVillian(hero);
            villian.setVPos(eposx, eposy);
            registerVillian(villian);
        }
    }

    public Villian getCrossedEnemy() {
        for (int i = 0; i < enemyArray.size(); i++) {
            if (enemyArray.get(i).getVYcoord() == this.ycoord && enemyArray.get(i).getVXcoord() == this.xcoord)
                return enemyArray.get(i);
        }
        return null;
    }

    public void upgradeXP(int type) {
        if (type == 1) {
            int xp;
            if (hero.getHeroStats().getExperience() < 2450) {
                xp = 2450;
                hero.getHeroStats().setExperience(xp);
            } else if (hero.getHeroStats().getExperience() < 4800) {
                xp = 4800;
                hero.getHeroStats().setExperience(xp);
            } else if (hero.getHeroStats().getExperience() < 8050) {
                xp = 8050;
                hero.getHeroStats().setExperience(xp);
            } else if (hero.getHeroStats().getExperience() < 12200) {
                xp = 12200;
                hero.getHeroStats().setExperience(xp);
            } else if (hero.getHeroStats().getExperience() < 12201) {
                System.out.println(">>>>AND THE ADVENTURE CONTINUES...UNTIL NEXT TIME>>>");
                System.exit(0);
            }

            hasWon();
        } else if (type == 2) {
            hero.getHeroStats().setExperience(hero.getHeroStats().getExperience() + villian.getPower());
            ReadFile.updateFile(hero);
            hasWon();
        }
    }

    public void hasWon() {
        if (hero.getHeroStats().getExperience() > 1000 && hero.getHeroStats().getExperience() < 2450) {
            this.level = 1;
        }
        else if (hero.getHeroStats().getExperience() >= 2450 && hero.getHeroStats().getExperience() < 4800) {
            this.level = 2;
        }
        else if (hero.getHeroStats().getExperience() >= 4800 && hero.getHeroStats().getExperience() < 8050) {
            this.level = 3;
        }
        else if (hero.getHeroStats().getExperience() >= 8050 && hero.getHeroStats().getExperience() < 12200) {
            this.level = 4;
        } else if (hero.getHeroStats().getExperience() == 12200) {
            this.level = 5;
        }

        if (this.level > hero.getHeroStats().getLevel()) {
            hero.getHeroStats().setLevel(this.level);
            ReadFile.updateFile(hero);

            System.out.println("You have defeated the Villian\n\n" +
                    "1. continue playing this game\n" +
                    "2. Quit game\n");

            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.matches("\\s*[1-2]\\s*")) {
                    int option = Integer.parseInt(line);

                    if (option == 1) {
                        enemyArray.removeAll(enemyArray);
                        Controller.run(hero);
//                        System.out.println("Continue to play game.");
                    } else if (option == 2) {
                        System.out.println("*****Thanks for taking the time to play this game...Until next time*****\n\n\n");
                        System.exit(0);
                    }
                } else
                    System.out.println("Invalid input. Please select any of the given options");
            }
        } else if (this.level == hero.getHeroStats().getLevel()) {
            enemyArray.removeAll(enemyArray);
        }
    }

    public boolean crossedEnemy(int heroy, int herox, int vily, int vilx) {
        if ((herox == vilx) && (heroy == vily)) {
            System.out.println("You have encountered a villian\n\n" +
                    "What do you wanna do:\n" +
                    "1. Run \n" +
                    "2. Fight\n");

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.matches("\\s*[1-2]\\s*")) {
                    int choice = Integer.parseInt(line);

                    if (choice == 1) {
                        Random random = new Random();
                        int run = random.nextInt(2) + 1;

                        if (run == 1) {
                            System.out.println("Ha ha ha...you are such a baby, now you lost 5XP\n");
                            System.out.println("Your current XP: " + (hero.getHeroStats().getExperience() - 5));
                            printMap();
                        }
                    } else if (choice == 2) {
                        Villian crossed = getCrossedEnemy();
                        int won = Controller.fight(hero, crossed);
                        if (won == 1) {
                            won(crossed);
                            removeVillian(crossed);
                            return true;

                        } else {

                            lost();
                            break;
                        }
                    } else
                        System.out.println("Invalid input. Please select any of the given options");
                } else
                    System.out.println("Invalid input. Please select any of the given options");
            }
        }
        return false;
    }

    public void won(Villian crossed) {
        enemyArray.remove(crossed);
        upgradeXP(2);
        if (Controller.inLuck() == true) {
            System.out.println("You killed the enemy, and he dropped down an artifact.\nYou can pickup enemy artifact (" + crossed.getArtifact().getType() + ")\n" +
                    "1. Pickup\n" +
                    "2. You have gained some experience, continue with your advanture");

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.matches("\\s*[1-2]\\s*")) {
                    int option = Integer.parseInt(line);
                    if (option == 1) {
                        String type = villian.getArtifact().getType();

                        if (type.equals("Weapon")) {
                            Weapon weapon = new Weapon("Weapon");
                            hero.setArtifact(weapon);
                            hero.getHeroStats().setAttack(65);
                            ReadFile.updateFile(hero);
                            Controller.run(hero);

                        } else if (type.equals("Armor")) {
                            Armor armor = new Armor("Armor");
                            hero.setArtifact(armor);
                            hero.getHeroStats().setDefense(55);
                            ReadFile.updateFile(hero);
                             Controller.run(hero);
                             System.out.println("armor");
                        }
                        else if (type.equals("Helm")) {
                            Helm helm = new Helm("Helm");
                            hero.setArtifact(helm);
                            hero.getHeroStats().setHitPoints(75);
                            ReadFile.updateFile(hero);
                             Controller.run(hero);
                             System.out.println("helm");
                        }
                    } else if (option == 2) {
                        upgradeXP(2);
                    }
                } else
                    System.out.println("Invalid input. Try again");
            }
        } else {
            upgradeXP(2);
            System.out.println("Battle won. 500 Experience Points gained.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.exit(0);
            }
            Controller.run(hero);
        }
    }

    public void lost() {
        System.out.println("\n\nYOU LOST THE BATTLE\n\n *********GAME OVER*********");
        System.exit(0);
    }
}
