package game.controller;

import game.model.Computer;
import game.model.Field;
import game.model.Point;
import game.model.User;
import game.view.GameWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameController {
    private GameWindow gameWindow;
    private User user;
    private Computer computer;
    public static int SHOOT_COUNT = 0;
    public JButton[][] buttons;
    public Object key = new Object();

    public GameController(GameWindow gameWindow, User user, Computer computer) {
        this.gameWindow = gameWindow;
        this.user = user;
        this.computer = computer;
    }

    public void initGameController() {
        buttons = gameWindow.getButtons();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton jButton = this.buttons[i][j];
                final int finalJ = j;
                final int finalI = i;
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String buttonText = e.getActionCommand();
                        System.out.printf("Button: %s, x: %d, y: %d%n", buttonText, finalJ, finalI);
                        doShoot(new Point(finalI, finalJ), SHOOT_COUNT % 2 == 0 ? Field.Type.X : Field.Type.O);
                        System.out.println(SHOOT_COUNT);
                    }
                });
            }
        }
    }


    public void doShoot(Point point, Field.Type type) {
        if (type.equals(Field.Type.X)) {
            synchronized (key) {
                user.setPoint(point);
                user.setIsShoot(true);
                key.notifyAll();
            }
            SHOOT_COUNT++;
        }
        if (type.equals(Field.Type.O)) {
            computer.setPoint();
            SHOOT_COUNT++;
        }
    }

    public void showController(Field.Type[][] cells, Point point, Field.Type who) {
        gameWindow.showField(cells, point , who);
    }
}