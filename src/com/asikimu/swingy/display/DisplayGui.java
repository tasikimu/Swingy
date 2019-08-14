package com.asikimu.swingy.display;

import com.asikimu.swingy.model.CreateHero.*;
import com.asikimu.swingy.Files.ReadFile;
import com.asikimu.swingy.Files.WriteFile;
import com.asikimu.swingy.map.GuiMap;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayGui extends JFrame {
    private String player;
    private String artifact;
    private String heroData;
    private String[] check = null;
    private int type;
    private static final long serialVersionUID = 42L;
    private final JFrame welcomeFrame = new JFrame("Player Creation");
    private final JFrame PlayerFrame = new JFrame("Welcome to Swingy...The Game of Heroes ");
    private final JFrame createFrame = new JFrame("Create Hero");
    private final JFrame selectFrame = new JFrame("Select Hero");
    private final JFrame statsFrame = new JFrame("Hero Stats");
    private final JFrame gameFrame = new JFrame("Game in progress");
    private static JFrame gameOverFrame = new JFrame("Game Complete");
    private final JRadioButton ninja = new JRadioButton("NINJA");
    private final JRadioButton assassian = new JRadioButton("ASSASSIAN");
    private final String[] items = ReadFile.ReadLine();
    private final JList heroList = new JList<>(items);
    private JLabel label, label1;
    private JTextField playerName;
    private JTextArea area;
    private JButton welcomeBtn, createPlayer, selectPlayer;
    private Hero hero = new Hero();
    private GuiMap map;


    public DisplayGui (){
    }

    public void createFrame(){
        label = new JLabel("CREATE PLAYER");
        label.setBackground(Color.red);
        label.setBounds(220,200, 200,30);
        label1 = new JLabel("PLEASE ENTER YOUR NAME");
        label1.setBounds(210,240, 200,30);
        label.setBackground(Color.GREEN);
        playerName = new JTextField();
        playerName.setBounds(200, 280, 200, 30);
        playerName.setCaretColor(Color.CYAN);
        welcomeBtn = new JButton("ENTER");
        welcomeBtn.setBounds(200, 320, 200, 30);
        welcomeBtn.setBackground(Color.red);
        welcomeFrame.add(label);
        welcomeFrame.setBackground(Color.yellow);
        welcomeFrame.add(label1);
        welcomeFrame.add(playerName);
        welcomeFrame.add(welcomeBtn);
        welcomeFrame.setSize(600,600);
        welcomeFrame.setLocationRelativeTo(null);
        welcomeFrame.setLayout(null);
        welcomeFrame.setVisible(true);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcomeBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                player = playerName.getText();
                player = player.trim();

                if (player.length() > 0){
                    check = player.split("\\s");

                    if (check != null)
                        player = String.join("_", check);

                    if (player.isEmpty())
                        JOptionPane.showMessageDialog(null, "Name cannot be empty!");
                    else{
                        createPlayer();
                        welcomeFrame.setVisible(false);
                        welcomeFrame.dispose();
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Blank spaces not allowed!");

            }
        });
    }

    public void welcomeToGui(){
        createPlayer = new JButton("CREATE PLAYER");
        createPlayer.setBackground(Color.yellow);
        createPlayer.setBounds(210,240, 200,30);
        selectPlayer = new JButton("SELECT PLAYER");
        selectPlayer.setBackground(Color.MAGENTA);
        selectPlayer.setBounds(210,280, 200,30);
        PlayerFrame.add(createPlayer);
        PlayerFrame.add(selectPlayer);
        PlayerFrame.setSize(600,600);
        PlayerFrame.setLocationRelativeTo(null);
        PlayerFrame.setLayout(null);
        PlayerFrame.setVisible(true);
        PlayerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createPlayer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                createFrame();
                PlayerFrame.dispose();
            }
        });

        selectPlayer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPlayer();
                PlayerFrame.setVisible(false);
                PlayerFrame.dispose();
            }
        });
    }

    public void createPlayer(){
        ButtonGroup group = new ButtonGroup();
        JButton enter;

        enter = new JButton("CONTINUE");

        ninja.setBounds(210,240, 200,30);
        assassian.setBounds(210,260, 200,30);
        enter.setBounds(210,300, 200,30);

        group.add(ninja);
        group.add(assassian);

        createFrame.add(ninja);
        createFrame.add(assassian);
        createFrame.add(enter);
        createFrame.setSize(600,600);
        createFrame.setLocationRelativeTo(null);
        createFrame.setLayout(null);
        createFrame.setVisible(true);
        createFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ninja.isSelected())
                    type = 2;
                else if (assassian.isSelected())
                    type = 1;
                heroStats();
                createFrame.setVisible(false);
                createFrame.dispose();
            }
        });

    }

    public void selectPlayer(){
        JLabel label;
        JButton enter, exit;

        label= new JLabel("SELECT EXISTING HERO");
        label.setBounds(20, 20, 200, 30);

        heroList.setBounds(20, 50, 350, 520);
        enter = new JButton("Continue");
        enter.setBounds(400, 50, 100, 30);
        exit = new JButton("Quit Game");
        exit.setBounds(400, 105, 100, 30);

        heroList.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                heroData = heroList.getSelectedValue().toString();
                hero = StartGame.dbHero(heroData);

            }
        });

        enter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (heroData == null)
                    JOptionPane.showMessageDialog(null, "Select hero first!");
                else{
                    playGame();
                    selectFrame.setVisible(false);
                    selectFrame.dispose();
                }

            }
        });

        exit.addActionListener(e -> selectFrame.dispose());

        selectFrame.add(label);
        selectFrame.add(enter);
        selectFrame.add(exit);
        selectFrame.add(heroList);
        selectFrame.setSize(600, 600);
        selectFrame.setLocationRelativeTo(null);
        selectFrame.setLayout(null);
        selectFrame.setVisible(true);
        selectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void heroStats(){
        hero = StartGame.NewHero(player, type);
        JLabel l1, l2, l3, l4, l5, l6, l7, l8;
        JButton enter;

        l8 = new JLabel("YOUR HERO STATS");
        l8.setBounds(200,200, 200,30);
        l1 = new JLabel("Hero: " + player);
        l1.setBounds(200,220, 200,30);
        String heroType;
        l2 = new JLabel("Hero: " + (heroType = hero.getHeroStats().getHeroType()));
        l2.setBounds(200,240, 200,30);
        int level;
        l3 = new JLabel("Level: " +  (level = hero.getHeroStats().getLevel()));
        l3.setBounds(200,260, 200,30);
        int attack;
        l4 = new JLabel("Attack: " + (attack = hero.getHeroStats().getAttack()));
        l4.setBounds(200,280, 200,30);
        int defense;
        l5 = new JLabel("Defense: " + (defense = hero.getHeroStats().getDefense()));
        l5.setBounds(200,300, 200,30);
        int hitpoints;
        l6 = new JLabel("Hitpoints: " + (hitpoints = hero.getHeroStats().getHitPoints()));
        l6.setBounds(200,320, 200,30);
        l7 = new JLabel("Artifact: " + (artifact = hero.getArtifact().getType()));
        l7.setBounds(200,340, 200,30);
        enter = new JButton("Enter");
        enter.setBounds(200,370, 200,30);

        statsFrame.add(l1);
        statsFrame.add(l2);
        statsFrame.add(l3);
        statsFrame.add(l4);
        statsFrame.add(l5);
        statsFrame.add(l6);
        statsFrame.add(l7);
        statsFrame.add(l8);
        statsFrame.add(enter);
        statsFrame.setSize(600, 600);
        statsFrame.setLocationRelativeTo(null);
        statsFrame.setLayout(null);
        statsFrame.setVisible(true);
        statsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                heroData = hero.getHeroStats().getHeroType() + " " +
                        player + " " + hero.getHeroStats().getLevel() + " " +
                        hero.getHeroStats().getAttack() + " " + hero.getHeroStats().getAttack() +
                        " " + hero.getHeroStats().getHitPoints() + " " + hero.getHeroStats().getExperience() +
                        " " + artifact.toUpperCase();

                WriteFile.writeToFile(heroData);
                WriteFile.closeFile();
                playGame();
                statsFrame.setVisible(false);
                statsFrame.dispose();
            }
        });
    }

    public void playGame(){
        JButton north, south, east, west;

        map = new GuiMap(hero, gameFrame);

        area = map.MapDisplay();


        north = new JButton("NORTH");
        north.setBounds(20,580, 100, 30);
        south = new JButton("SOUTH");
        south.setBounds(140,580, 100, 30);
        east = new JButton("EAST");
        east.setBounds(280,580, 100, 30);
        west = new JButton("WEST");
        west.setBounds(400,580, 100, 30);

        north.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPosition(0, -1);
            }
        });

        south.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPosition(0, 1);
            }
        });

        east.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPosition(1, 0);
            }
        });

        west.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPosition(-1, 0);
            }
        });

        area.setBounds(20,90, 800, 480);
        gameFrame.add(area);
        gameFrame.add(north);
        gameFrame.add(south);
        gameFrame.add(east);
        gameFrame.add(west);
        gameFrame.setSize(850, 650);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setLayout(null);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void gameOver(){
        JLabel label;
        JButton close;

        label = new JLabel("****And You continue with your adventure****");
        label.setBounds(200,240, 400,30);
        close = new JButton("COMPLETE GAME");
        close.setBounds(210,280, 200,30);

        close.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gameOverFrame.dispose();
                System.exit(0);
            }
        });

        gameOverFrame.add(label);
        gameOverFrame.add(close);
        gameOverFrame.setSize(600, 600);
        gameOverFrame.setLocationRelativeTo(null);
        gameOverFrame.setLayout(null);
        gameOverFrame.setVisible(true);
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
