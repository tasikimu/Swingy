package com.asikimu.swingy.display;

import com.asikimu.swingy.Files.ReadFile;
import com.asikimu.swingy.map.Controller;

import java.util.Scanner;

public class DisplayConsole {
   public static void begin() {
        String player;
        int type, createHero, option = 0, play;
        com.asikimu.swingy.CreateHero.Hero hero;

        try {
            createHero = DisplayHero.playerSetUp();

            if (createHero == 1) {
                player = DisplayHero.WelcomePlayer();
                type = DisplayHero.printHeroSelection();
                hero = StartGame.NewHero(player, type);
                play = Herostats.printDetails(type, player, hero);
                if (play == 1) {

                    Controller.run(hero);
                } else {
                    System.out.println("****** Its a pity you chose to leave, u would have enjoyed the game!!! ******\n\n");
                    System.exit(0);
                }
            }
            else if (createHero == 2) {
                ReadFile.getDBHeroes();
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    int Countlines = ReadFile.getLinesCount();

                    if (isDigit(line) == true) {
                        try {
                            int index = Integer.parseInt(line);
                            if (index > 0 && index <= Countlines) {
                                option = index;
                                break;
                            }
                        }
                        catch (Exception e)
                        {
                            System.out.println("Invalid input. Try again.");
                        }
                    }
                    else
                        System.out.println("Invalid input. Try again.");
                }
                hero = StartGame.dbHero(ReadFile.getHero(option));
                Controller.run(hero);
            }
        }
        catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public static boolean isDigit(String line)
    {
        for (char c : line.toCharArray()){
            if (Character.isDigit(c) != true)
                return false;
        }
        return true;
    }

}
