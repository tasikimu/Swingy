package com.asikimu.swingy;

import com.asikimu.swingy.Files.WriteFile;
import com.asikimu.swingy.display.DisplayConsole;
import com.asikimu.swingy.display.DisplayGui;

public class main {
    public static void main(String[] args) {
        try {
            WriteFile.createFile();
            if (args.length != 1 || (!args[0].equals("console") && !args[0].equals("gui"))){
                System.out.println("Program arguments: console or gui , please pass in the right arguments");
                System.exit(1);
            }
            if (args[0].equals("console")) {
                DisplayConsole.begin();
            }
            else {
                DisplayGui gui = new DisplayGui();
                gui.welcomeToGui();
            }
        }
        finally {
            WriteFile.closeFile();
        }

//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
////                new mainFrame();
//                new springDemo();
//            }
//        });
    }
}