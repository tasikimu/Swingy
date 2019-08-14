package com.asikimu.swingy.display;

public class DisplayConsole {
   public static void begin() {
        String player;
        int type, createHero, option = 0, play;
        Hero hero;

        try {
            createHero = HeroDisplay.playerSetUp();

            if (createHero == 1) {
                player = HeroDisplay.WelcomePlayer();
                type = HeroDisplay.printHeroSelection();
                hero = GameView.NewHero(player, type);
                play = DisplayHeroStats.printDetails(type, player, hero);
                if (play == 1) {

                    GameController.run(hero);
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
                hero = GameView.dbHero(ReadFile.getHero(option));
                GameController.run(hero);
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
