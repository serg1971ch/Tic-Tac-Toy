package game.view;

import game.model.CurrentCountWinner;
import game.model.Game;

import javax.swing.*;
import java.awt.*;

public class InfoGrid extends JPanel {
    private Game game;
    private static int countUser = 0;
    private static int countComp = 0;
    private static int countDraw = 0;
    GameWindow gameWindow;

    public InfoGrid(GameWindow gameWindow) {
        JLabel userLaber;
        JLabel userCount;
        JLabel computerLaber;
        JLabel computerCount;
        JLabel playersDraw;
        JLabel playersDrawCount;
        userLaber = new JLabel("Игрок: ");
        computerLaber = new JLabel("Компьютер: ");
        userCount = new JLabel(String.valueOf(countUser));
        computerCount = new JLabel(String.valueOf((countComp)));
        playersDraw = new JLabel("Ничья: ");
        playersDrawCount = new JLabel(String.valueOf(countDraw));
        BorderFactory.createBevelBorder(3, Color.black, Color.cyan);
        Game.setCurrentWinnerCount(new CurrentCountWinner() {
            @Override
            public void setCurrentWinX() {
                countUser++;
                int action = JOptionPane.showConfirmDialog(InfoGrid.this,
                        "Поздравляю вы выиграли!" + "\n" + "Если хотите продолжить нажмите Ok" + "\n" + "Для того, чтобы выйти нажмите Canсel", "Winner of XO", JOptionPane.OK_CANCEL_OPTION);
                if (action != JOptionPane.OK_CANCEL_OPTION) {
//                    gameWindow.setVisible(false);
                    Game.stopGame();
                    Game game = new Game();
                    game.start();
                } else {
                    System.exit(0);
                }
            }

            @Override
            public void setCurrentWinY() {
                countComp++;
                int action = JOptionPane.showConfirmDialog(InfoGrid.this,
                        "Увы, вы проиграли!" + "\n" + "Если хотите продолжить нажмите Ok" + "\n" + "Для того, чтобы выйти нажмите Canсel", "Winner of XO", JOptionPane.OK_CANCEL_OPTION);
                if (action != JOptionPane.OK_CANCEL_OPTION) {
                    gameWindow.setVisible(false);
                    Game game = new Game();
                    game.start();
                    System.out.println("New Game");
                } else {
                    System.exit(0);
                }
            }

            @Override
            public void setCurrentDraw() {
                countDraw++;
                int action = JOptionPane.showConfirmDialog(InfoGrid.this,
                        "Ничья!" + "\n" + "Если хотите продолжить нажмите Ok" + "\n" + "Для того, чтобы выйти нажмите Canсel", "Winner of XO", JOptionPane.OK_CANCEL_OPTION);
                if (action != JOptionPane.CANCEL_OPTION) {
//                    закрыть предыдущее окно
                    Game game = new Game();
                    game.start();
                } else {
                    System.exit(0);
                }
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints infoGrid = new GridBagConstraints();
        infoGrid.gridx = 0;
        infoGrid.gridy = 0;
        infoGrid.weightx = 0.1;
        infoGrid.weighty = 0.1;
        infoGrid.anchor = GridBagConstraints.WEST;
        infoGrid.insets = new Insets(0, 10, 10, 30);
        infoGrid.fill = GridBagConstraints.NONE;
        add(userLaber, infoGrid);
        infoGrid.gridx = 1;
        infoGrid.gridy = 0;
        infoGrid.weightx = 0.1;
        infoGrid.weighty = 0.1;
        infoGrid.anchor = GridBagConstraints.CENTER;
        infoGrid.insets = new Insets(0, 0, 10, 100);
        infoGrid.fill = GridBagConstraints.NONE;
        add(userCount, infoGrid);
        infoGrid.gridx = 0;
        infoGrid.gridy = 1;
        infoGrid.weightx = 0.1;
        infoGrid.weighty = 0.1;
        infoGrid.anchor = GridBagConstraints.WEST;
        infoGrid.insets = new Insets(0, 10, 10, 30);
        infoGrid.fill = GridBagConstraints.NONE;
        add(computerLaber, infoGrid);
        infoGrid.gridx = 1;
        infoGrid.gridy = 1;
        infoGrid.weightx = 0.1;
        infoGrid.weighty = 0.1;
        infoGrid.anchor = GridBagConstraints.CENTER;
        infoGrid.insets = new Insets(0, 0, 10, 100);
        infoGrid.fill = GridBagConstraints.NONE;
        add(computerCount, infoGrid);
        infoGrid.gridx = 0;
        infoGrid.gridy = 2;
        infoGrid.weightx = 0.1;
        infoGrid.weighty = 0.1;
        infoGrid.anchor = GridBagConstraints.WEST;
        infoGrid.insets = new Insets(0, 10, 10, 10);
        infoGrid.fill = GridBagConstraints.NONE;
        add(playersDraw, infoGrid);
        infoGrid.gridx = 1;
        infoGrid.gridy = 2;
        infoGrid.weightx = 0.1;
        infoGrid.weighty = 0.1;
        infoGrid.anchor = GridBagConstraints.CENTER;
        infoGrid.insets = new Insets(0, 0, 10, 100);
        infoGrid.fill = GridBagConstraints.NONE;
        add(playersDrawCount, infoGrid);
    }
}
