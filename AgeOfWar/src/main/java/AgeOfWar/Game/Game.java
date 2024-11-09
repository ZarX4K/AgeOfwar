package AgeOfWar.Game;

import AgeOfWar.Game.Graphics.BackGroundScreens;
import AgeOfWar.Game.Graphics.GameGraphics;
import AgeOfWar.Game.Logic.MainLogic;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game {
    public static void main(String[] args) {
        // Initialize the necessary objects
        MainLogic logic = new MainLogic();
        BackGroundScreens backGroundScreens = new BackGroundScreens();
        // Create the GameGraphics window
        GameGraphics window = new GameGraphics(logic, backGroundScreens);


    }
}
